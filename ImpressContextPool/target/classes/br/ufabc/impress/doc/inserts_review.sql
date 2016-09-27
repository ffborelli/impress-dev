

insert into place_type(description) values ('CAMPUS');
insert into place_type(description) values ('BUILD');
insert into place_type(description) values ('FLOOR');
insert into place_type(description) values ('CLASS ROOM');
insert into place_type(description) values ('TEACHER ROOM');
insert into place_type(description) values ('ROW IN CLASS ROOM');
insert into place_type(description) values ('WALL IN CLASS ROOM');

--place and auto relation
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('UFPE',1,NULL,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('CIN',2,1,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('FIRST FLOOR',3,2,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('REVIEW CLASSROOM',4,3,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 1',6,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 2',6,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 3',6,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 4',6,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 1',7,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 2',7,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 3',7,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 4',7,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('BETWEEN 1nd and 2nd ROW',6,4,0);
insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('BETWEEN 3th and 4th ROW',6,4,0);


insert into resource_type (description, sensor_0_actuator_1) values ('TEMPERATURE SENSOR TYPE',0);
insert into resource_type (description, sensor_0_actuator_1) values ('USAGE SENSOR TYPE',0);
insert into resource_type (description, sensor_0_actuator_1) values ('ULTRASONIC PRESENCE SENSOR TYPE',0);
insert into resource_type (description, sensor_0_actuator_1) values ('LIGHT SENSOR TYPE',1);
insert into resource_type (description, sensor_0_actuator_1) values ('ANDROID SENSOR',0);
insert into resource_type (description, sensor_0_actuator_1) values ('ANDROID ACTUATOR',1);
insert into resource_type (description, sensor_0_actuator_1) values ('KINECT',0);
insert into resource_type (description, sensor_0_actuator_1) values ('AIR CONDITIONER TYPE',1);
insert into resource_type (description, sensor_0_actuator_1) values ('VIRTUAL',0);
insert into resource_type (description, sensor_0_actuator_1) values ('OCCUPANCY SENSOR TYPE',0);

--insert into resource(description, id_resource_type_fk, id_place_fk) values('AC1320',1,4);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('AC1321',1,4);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('T1310',3,4);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('AC8001',1,6);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('C0077',4,6);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('T0088',3,6);

--PRESENCE SENSOR
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PS1',7,5,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PS2',7,6,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PS3',7,7,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PS4',7,8,0);

--TEMPERATURE SENSOR
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('TS1',1,4,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('TS2',1,4,0);

--LIGHT ACTUATORS
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PHIL1',4,5,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PHIL2',4,6,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PHIL3',4,7,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('PHIL4',4,8,0);

--COOLEr
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('AC1',8,4,0);
--averege temperature sensor
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('AVG_T_1',9,4,0);
--sum presence sensor 
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('SUM PRESENCE',9,4,0);
--kinects
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('KINECT 1',7,13,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('KINECT 2',7,13,0);

--kinects + ultrasonic sensors
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('KINECT 1 + US 1',7,13,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('KINECT 1 + US 2',7,13,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('KINECT 2 + US 3',7,13,0);
insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('KINECT 2 + US 4',7,13,0);


insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(1,'1','10/10/14 12:35:47');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(2,'1','10/10/14 12:35:54');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(3,'0','10/10/14 12:36:47');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(4,'1','10/10/14 12:36:54');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(5,'19','10/10/14 12:37:47');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(6,'18.9','10/10/14 12:37:54');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(7,'0.135','10/10/14 12:37:54');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(8,'19.8','10/10/14 12:35:54');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(9,'23.7','10/10/14 12:36:47');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(10,'19.2','10/10/14 12:36:54');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(11,'23.9','10/10/14 12:37:47');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(12,'18.9','10/10/14 12:37:54');
insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(13,'19','10/10/14 12:37:54');

--insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) values('Calcular a média das últimas 10 medições', 'FS1',0);
--insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) values('Calcular o desvio padrão das últimas 10 medições', 'FS2',0);
--insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) values('Descobrir ponto de máximo', 'FS3',0);
--insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) values('Calcular o desvio padrão das últimas 5 medições', 'FS4',0);
--insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) values('Descobrir inclinação da curva', 'FS5',0);
--insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) values('Calcular média das últimas 5 medições', 'FS6',0);

insert into fusion(fusion_text,fusion_name,dependent_0_independent_1)
	values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Averege Temperature',0);
	
insert into fusion(fusion_text,fusion_name,dependent_0_independent_1)
	values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 1',0);
	
	insert into fusion(fusion_text,fusion_name,dependent_0_independent_1)
	values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 2',0);
	
	insert into fusion(fusion_text,fusion_name,dependent_0_independent_1)
	values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 3',0);
	
	insert into fusion(fusion_text,fusion_name,dependent_0_independent_1)
	values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 4',0);
	
--insert into fusion(fusion_text,fusion_name,dependent_0_independent_1)
--	values('select  DemoEsperType.resourceLogValue as S1 , * from DemoEsperType().win:time_batch(3 sec) where DemoEsperType.resource.id = 16', 'Presence Sensor 2',0);

--quando é executado a fusão	
insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(1,'23.2','10/10/14 12:45:47');
insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(2,'0.8','10/10/14 12:46:54');
insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(1,'19.7','10/10/14 12:56:47');
insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(1,'19.4','10/10/14 13:06:54');
insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(2,'0.9','10/10/14 13:07:47');
insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(2,'1.1','10/10/14 13:17:54');

insert into resource_fusion (id_fusion_fk, id_resource_fk) values(1,5);
insert into resource_fusion (id_fusion_fk, id_resource_fk) values(1,6);
insert into resource_fusion (id_fusion_fk, id_resource_fk) values(2,1);
insert into resource_fusion (id_fusion_fk, id_resource_fk) values(2,2);
insert into resource_fusion (id_fusion_fk, id_resource_fk) values(2,3);
insert into resource_fusion (id_fusion_fk, id_resource_fk) values(2,4);

insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(4,'TURN ON');
insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(4,'TURN OFF');
insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(8,'TURN ON');
insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(8,'TURN OFF');
insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(3,'DETECTED');
insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(3,'NOT DETECTED');

--quando o ????
insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(1,5,'14/10/14 13:35:47');
insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(1,6,'14/10/14 13:35:47');
insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,1,'14/10/14 13:35:47');
insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,2,'14/10/14 13:35:47');
insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,3,'14/10/14 13:35:47');
insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,4,'14/10/14 13:35:47');

insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 1" when (Valor > 22.0) then Ação end', 'RULE 1',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 2" when (Valor > 0.8) then Ação end', 'RULE 2',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 3" when (Valor > 22.0) then Ação end', 'RULE 3',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 4" when (Valor > 22.0) then Ação end', 'RULE 4',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 5" when (Valor > 22.0) then Ação end', 'RULE 5',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 6',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 7',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 8',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 9',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 10',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 11',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 12',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 13',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 14',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 15',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 16',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 17',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Rule 6" when (Valor > 22.0) then Ação end', 'RULE 18',0);

--insert into rules_log(id_rules_fk, creation_date) values(1,'10/10/14 12:35:47');
--insert into rules_log(id_rules_fk, creation_date) values(1,'10/10/14 12:45:54');
--insert into rules_log(id_rules_fk, creation_date) values(1,'10/10/14 12:56:47');
--insert into rules_log(id_rules_fk, creation_date) values(2,'10/10/14 13:26:54');
--insert into rules_log(id_rules_fk, creation_date) values(2,'10/10/14 13:37:47');
--insert into rules_log(id_rules_fk, creation_date) values(2,'10/10/14 13:47:54');

insert into fusion_rule(id_fusion_fk, id_rule_fk) values(1,1);
insert into fusion_rule(id_fusion_fk, id_rule_fk) values(2,1);


insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,1,'14/10/14 13:45:54');
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,2,'14/10/14 13:45:54');
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,3,'14/10/14 14:26:54');
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,4,'14/10/14 14:26:54');

insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(1,1,'14/10/14 12:35:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(1,2,'14/10/14 12:45:54');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(2,3,'14/10/14 12:56:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(2,4,'14/10/14 13:26:54');

insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,7,1);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,8,1);

insert into context_type(description) values('Row Occupied');
insert into context_type(description) values('Row empty');
insert into context_type(description) values('Cold Classroom');
insert into context_type(description) values('Hot Classroom');
insert into context_type(description) values('Warm Classroom');

--insert into context(context_name, id_place_fk, id_context_type_fk, id_context_fk,enable_0_disable_1) values('Hot R808 Classroom',4,1,1,0);
--insert into context(context_name, id_place_fk, id_context_type_fk, id_context_fk,enable_0_disable_1) values('Clearly R300 Classroom',6,3,2,0);

