CREATE TABLE place_type(
	id_place_type serial NOT NULL PRIMARY KEY,
	description text NOT NULL
);

CREATE TABLE place(
	id_place serial NOT NULL PRIMARY KEY,
	description text NOT NULL,
	id_place_type_fk int,
	CONSTRAINT id_place_type_fk FOREIGN KEY (id_place_type_fk) REFERENCES place_type(id_place_type),
	id_place_fk int,
	CONSTRAINT id_place_fk FOREIGN KEY (id_place_fk) REFERENCES place(id_place),
	dependent_0_independent_1 int NOT NULL
);

CREATE TABLE resource_type(
	id_resource_type serial NOT NULL PRIMARY KEY,
	description text NOT NULL,
	sensor_0_actuator_1 int NOT NULL
);

CREATE TABLE resource(
	id_resource serial NOT NULL PRIMARY KEY,
	uid varchar(255),
	description text NOT NULL,
	dependence_fusion_log_fk int,
	id_resource_type_fk int,
	is_reserved boolean default false,
	mqtt_topic varchar(255),
    mqtt_address varchar(255),
    rai_address varchar(255),
	CONSTRAINT id_resource_type_fk FOREIGN KEY (id_resource_type_fk) REFERENCES resource_type(id_resource_type),
	id_place_fk int,
	CONSTRAINT id_place_fk FOREIGN KEY (id_place_fk) REFERENCES place(id_place),
	dependent_0_independent_1 int NOT NULL
);

CREATE TABLE resource_log(
	id_resource_log serial NOT NULL PRIMARY KEY,
	id_resource_fk int,
	CONSTRAINT id_resource_fk FOREIGN KEY (id_resource_fk) REFERENCES resource(id_resource),
	resource_log_value text NOT NULL, /* value para log_resource_value */
	creation_date timestamp NOT NULL /* timestamp para creation_date */
);

ALTER TABLE resource_log ADD COLUMN born_date timestamp without time zone;

ALTER TABLE resource_log ADD COLUMN replication int;

CREATE TABLE fusion(
	id_fusion serial NOT NULL PRIMARY KEY,
	fusion_name text NOT NULL, 
	fusion_text text NOT NULL, /* text para fusion_text */
	is_running boolean default false,
	is_avaliable boolean default false,
);

CREATE TABLE fusion_log(
	id_fusion_log serial NOT NULL PRIMARY KEY,
	id_fusion_fk int,
	CONSTRAINT id_fusion_fk FOREIGN KEY (id_fusion_fk) REFERENCES fusion(id_fusion),
	fusion_log_value text NOT NULL, /* value para log_fusion_value */
	creation_date timestamp NOT NULL /* timestamp para creation_date */
);

CREATE TABLE resource_fusion(
	id_resource_fusion serial NOT NULL PRIMARY KEY,
	id_fusion_fk int,
	CONSTRAINT id_fusion_fk FOREIGN KEY (id_fusion_fk) REFERENCES fusion(id_fusion),
	id_resource_fk int,
	CONSTRAINT id_resource_fk FOREIGN KEY (id_resource_fk) REFERENCES resource(id_resource)
);

CREATE TABLE rsc_action_type(
	id_rsc_action_type serial NOT NULL PRIMARY KEY,
	id_resource_type_fk int,
	CONSTRAINT id_resource_type_fk FOREIGN KEY (id_resource_type_fk) REFERENCES resource_type(id_resource_type),
	rsc_action_type_text text NOT NULL /* text para type_rsc_action */
);

CREATE TABLE rsc_fusion_log(
	id_rsc_fusion_log serial NOT NULL PRIMARY KEY,
	id_fusion_log_fk int,
	CONSTRAINT id_fusion_log_fk FOREIGN KEY (id_fusion_log_fk) REFERENCES fusion_log(id_fusion_log),
	id_resource_log_fk int,
	CONSTRAINT id_resource_log_fk FOREIGN KEY (id_resource_log_fk) REFERENCES resource_log(id_resource_log),
	creation_date timestamp NOT NULL 
);

CREATE TABLE rule(
	id_rule serial NOT NULL PRIMARY KEY,
	rule_name text NOT NULL, 
	rule_text text NOT NULL, /* text para rule_text */
	dependent_0_independent_1 int NOT NULL
);


CREATE TABLE fusion_rule(
	id_fusion_rule serial NOT NULL PRIMARY KEY,
	id_fusion_fk int,
	CONSTRAINT id_fusion_fk FOREIGN KEY (id_fusion_fk) REFERENCES fusion(id_fusion),
	id_rule_fk int,
	CONSTRAINT id_rule_fk FOREIGN KEY (id_rule_fk) REFERENCES rule(id_rule)
);


CREATE TABLE action(
	id_action serial NOT NULL PRIMARY KEY,
	id_rule_fk int,
	CONSTRAINT id_rule_fk FOREIGN KEY (id_rule_fk) REFERENCES rule(id_rule),
	id_resource_fk int,
	CONSTRAINT id_resource_fk FOREIGN KEY (id_resource_fk) REFERENCES resource(id_resource),
	id_rsc_action_type_fk int,
	CONSTRAINT id_rsc_action_type_fk FOREIGN KEY (id_rsc_action_type_fk) REFERENCES rsc_action_type(id_rsc_action_type)	
);

CREATE TABLE rule_action_log(
	id_rule_action_log serial NOT NULL PRIMARY KEY,
	id_rule_fk int,
    CONSTRAINT id_rule_fk FOREIGN KEY (id_rule_fk) REFERENCES rule(id_rule),
    	id_rsc_action_type_fk int,
    CONSTRAINT id_rsc_action_type_fk FOREIGN KEY (id_rsc_action_type_fk) REFERENCES rsc_action_type(id_rsc_action_type),
        id_resource_fk int,
    CONSTRAINT id_resource_fk FOREIGN KEY (id_resource_fk) REFERENCES resource(id_resource),
    creation_date timestamp NOT NULL, /* timestamp para creation_date */
    tracker boolean default false NOT NULL
);

CREATE TABLE fusion_rule_log(
	id_fusion_rule_log serial NOT NULL PRIMARY KEY,
	id_fusion_log_fk int,
	CONSTRAINT id_fusion_log_fk FOREIGN KEY (id_fusion_log_fk) REFERENCES fusion_log(id_fusion_log),
	id_rule_action_log_fk int,
	CONSTRAINT id_rule_action_log_fk FOREIGN KEY (id_rule_action_log_fk) REFERENCES rule_action_log(id_rule_action_log),
	creation_date timestamp NOT NULL /* timestamp para creation_date */
);

CREATE TABLE context_type(
	id_context_type serial NOT NULL PRIMARY KEY,
	description text NOT NULL
);

CREATE TABLE context(
	id_context serial NOT NULL PRIMARY KEY,
	context_name text NOT NULL,
	id_place_fk int,
	context_sequence text NOT NULL,
    context_count int NOT NULL default 0,
    context_registered int NOT NULL default 0,
	CONSTRAINT id_place_fk FOREIGN KEY (id_place_fk) REFERENCES place(id_place),
	id_context_type_fk int,
	CONSTRAINT id_context_type_fk FOREIGN KEY (id_context_type_fk) REFERENCES context_type(id_context_type),
	id_context_fk int,
	CONSTRAINT id_context_fk FOREIGN KEY (id_context_fk) REFERENCES context(id_context),
	enable_0_disable_1 int NOT NULL
);

CREATE TABLE resource_context(
	id_resource_context serial NOT NULL PRIMARY KEY,
	id_context_fk int,
	CONSTRAINT id_context_fk FOREIGN KEY (id_context_fk) REFERENCES context(id_context),
	id_resource_fk int,
	CONSTRAINT id_resource_fk FOREIGN KEY (id_resource_fk) REFERENCES resource(id_resource)
);

CREATE TABLE fusion_context(
	id_fusion_context serial NOT NULL PRIMARY KEY,
	id_context_fk int,
	CONSTRAINT id_context_fk FOREIGN KEY (id_context_fk) REFERENCES context(id_context),
	id_fusion_fk int,
	CONSTRAINT id_fusion_fk FOREIGN KEY (id_fusion_fk) REFERENCES fusion(id_fusion)
);

CREATE TABLE rule_context(
	id_rule_context serial NOT NULL PRIMARY KEY,
	id_context_fk int,
	CONSTRAINT id_context_fk FOREIGN KEY (id_context_fk) REFERENCES context(id_context),
	id_rule_fk int,
	CONSTRAINT id_rule_fk FOREIGN KEY (id_rule_fk) REFERENCES rule(id_rule)
);

CREATE TABLE context_log(
	id_context_log serial NOT NULL PRIMARY KEY,
	id_context_fk int,
        CONSTRAINT id_context_fk FOREIGN KEY (id_context_fk) REFERENCES context(id_context),
        creation_date timestamp NOT NULL /* timestamp para creation_date */
);

CREATE TABLE context_count(
	id_context_count serial NOT NULL PRIMARY KEY,
	context_sequence text NOT NULL,
    context_count int NOT NULL,
    context_registered int NOT NULL
);


CREATE TABLE eval_sdp(
	id bigserial NOT NULL PRIMARY KEY,
	exp int NOT NULL,
	resource_type int references resource_type(id_resource_type), 
	resource int NOT NULL references resource(id_resource),
	rep int NOT NULL,
	p1 timestamp,
	p1_disk_read bigint,
	p1_disk_write bigint,
	p1_mem_usage bigint,
	p1_mem_total bigint,
	p1_proc_usage numeric(5,1),
	p2 timestamp,
	p2_disk_read bigint,
	p2_disk_write bigint,
	p2_mem_usage bigint,
	p2_mem_total bigint,
	p2_proc_usage numeric(5,1),
	p3 timestamp,
	p3_disk_read bigint,
	p3_disk_write bigint,
	p3_mem_usage bigint,
	p3_mem_total bigint,
	p3_proc_usage numeric(5,1),
	p4 timestamp,
	p4_disk_read bigint,
	p4_disk_write bigint,
	p4_mem_usage bigint,
	p4_mem_total bigint,
	p4_proc_usage bigint,
	p5 timestamp,
	p5_disk_read bigint,
	p5_disk_write bigint,
	p5_mem_usage bigint,
	p5_mem_total bigint,
	p5_proc_usage numeric(5,1),
	p6 timestamp,
	p6_disk_read bigint,
	p6_disk_write bigint,
	p6_mem_usage bigint,
	p6_mem_total bigint,
	p6_proc_usage numeric(5,1)
);

CREATE TABLE tracker (
	id serial NOT NULL PRIMARY KEY,
	creation_date timestamp,
	status boolean NOT NULL
);

CREATE TABLE config (
	id serial NOT NULL PRIMARY KEY,
	server_address varchar(255),
	creation_date timestamp
);
