
insert into place_type(description) values ('BUILD');
insert into place_type(description) values ('FLOOR');
insert into place_type(description) values ('CLASS ROOM');
insert into place_type(description) values ('TEACHER ROOM');
insert into place_type(description) values ('ROW IN CLASS ROOM');

--place and auto relation
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('BUILD B',1,NULL,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('8 FLOOR',2,1,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('3 FLOOR',2,1,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('ROOM 1',4,2,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('BUILD A',1,NULL,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('ROOM 2',4,3,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('ROW 1',5,4,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('ROW 2',5,4,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('ROW 3',5,4,0);
insert into place(description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values('ROW 4',5,4,0);

insert into resource_type(description, sensor_0_actuator_1) values('TEMPERATURE SENSOR TYPE',0);
insert into resource_type(description, sensor_0_actuator_1) values('USAGE SENSOR',0);
insert into resource_type(description, sensor_0_actuator_1) values('ULTRASONIC PRESENCE SENSOR TYPE',0);
insert into resource_type(description, sensor_0_actuator_1) values('LIGHT SENSOR TYPE',1);
insert into resource_type(description, sensor_0_actuator_1) values('ANDROID SENSOR',0);
insert into resource_type(description, sensor_0_actuator_1) values('ANDROID ACTUATOR',1);
insert into resource_type(description, sensor_0_actuator_1) values('KINECT',0);
insert into resource_type(description, sensor_0_actuator_1) values('AIR CONDITIONER',1);
insert into resource_type(description, sensor_0_actuator_1) values('VIRTUAL',0);

--insert into resource(description, id_resource_type_fk, id_place_fk) values('AC1320',1,4);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('AC1321',1,4);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('T1310',3,4);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('AC8001',1,6);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('C0077',4,6);
--insert into resource(description, id_resource_type_fk, id_place_fk) values('T0088',3,6);

--PRESENCE SENSOR
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('PS1',3,7,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('PS2',3,8,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('PS3',3,9,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('PS4',3,10,0);
--TEMPERATURE SENSOR
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('TS1',1,4,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('TS2',1,4,0);

--USAGE SENSOR
--insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('US1',7,4,0);

--Actuator Temperature
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('LI1',4,7,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('LI2',4,8,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('LI3',4,9,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('LI4',4,10,0);

--COOLEr
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('AC1',8,4,0);

--sensor and actuator of Android
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('AN1',5,4,0);
insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('AN1',6,4,0);

insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('AVG_T_1',9,4,0);

insert into resource(description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values('SUM PRESENCE',9,4,0);


--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(1,'1','10/10/14 12:35:47');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(2,'1','10/10/14 12:35:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(3,'0','10/10/14 12:36:47');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(4,'1','10/10/14 12:36:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(5,'19','10/10/14 12:37:47');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(6,'18.9','10/10/14 12:37:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(7,'0.135','10/10/14 12:37:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(8,'19.8','10/10/14 12:35:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(9,'23.7','10/10/14 12:36:47');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(10,'19.2','10/10/14 12:36:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(11,'23.9','10/10/14 12:37:47');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(12,'18.9','10/10/14 12:37:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(13,'19','10/10/14 12:37:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(14,'1','10/10/14 12:37:54');
--insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(15,'3','10/10/14 12:37:54');


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
	values('select  DemoEsperType.resourceLogValue as S1 , * from DemoEsperType().win:time_batch(3 sec) where DemoEsperType.resource.id = 16', 'Presence Sensor 2',0);

	
--insert into fusion_log(id_fusion_fk, fusion_log_value, creation_date) values(1,'23.2','10/10/29 12:45:47');
--insert into fusion_log(id_fusion_fk, fusion_log_value, creation_date) values(2,'0.8','10/10/29 12:46:54');
--insert into fusion_log(id_fusion_fk, fusion_log_value, creation_date) values(1,'19.7','10/10/29 12:56:47');
--insert into fusion_log(id_fusion_fk, fusion_log_value, creation_date) values(1,'19.4','10/10/29 13:06:54');
--insert into fusion_log(id_fusion_fk, fusion_log_value, creation_date) values(2,'0.9','10/10/29 13:07:47');
--insert into fusion_log(id_fusion_fk, fusion_log_value, creation_date) values(2,'1.1','10/10/29 13:17:54');

insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,1);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,3);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,5);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(2,2);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(2,4);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(2,6);

--insert into type_rsc_actions(id_resource_type_fk, type_rsc_actions_text) values(1,'TURN ON');
--insert into type_rsc_actions(id_resource_type_fk, type_rsc_actions_text) values(1,'TURN OFF');
--insert into type_rsc_actions(id_resource_type_fk, type_rsc_actions_text) values(1,'Aumentar');
--insert into type_rsc_actions(id_resource_type_fk, type_rsc_actions_text) values(1,'Diminuir');
--insert into type_rsc_actions(id_resource_type_fk, type_rsc_actions_text) values(4,'OPEN');
--insert into type_rsc_actions(id_resource_type_fk, type_rsc_actions_text) values(4,'CLOSE');

--insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk) values(1,1);
--insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk) values(1,3);
--insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk) values(1,5);
--insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk) values(2,2);
--insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk) values(2,4);
--insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk) values(2,6);

insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Regra 1" when (Valor > 22.0) then Ação end', 'RULE 1',0);
insert into rule(rule_text, rule_name,dependent_0_independent_1) values('rule "Regra 2" when (Valor > 0.8) then Ação end', 'RULE 2',0);

--insert into rules_log(id_rules_fk, creation_date) values(1,'10/10/29 12:35:47');
--insert into rules_log(id_rules_fk, creation_date) values(1,'10/10/29 12:45:54');
--insert into rules_log(id_rules_fk, creation_date) values(1,'10/10/29 12:56:47');
--insert into rules_log(id_rules_fk, creation_date) values(2,'10/10/29 13:26:54');
--insert into rules_log(id_rules_fk, creation_date) values(2,'10/10/29 13:37:47');
--insert into rules_log(id_rules_fk, creation_date) values(2,'10/10/29 13:47:54');

insert into fusion_rule(id_fusion_rule,id_fusion_fk, id_rule_fk) values(1,1,1);
insert into fusion_rule(id_fusion_rule,id_fusion_fk, id_rule_fk) values(2,6,1);
insert into fusion_rule(id_fusion_rule,id_fusion_fk, id_rule_fk) values(3,2,2);
insert into fusion_rule(id_fusion_rule,id_fusion_fk, id_rule_fk) values(4,4,2);

--insert into fusion_log_rules(id_fusion_rules_fk, creation_date) values(1,'10/10/29 12:35:47');
--insert into fusion_log_rules(id_fusion_rules_fk, creation_date) values(1,'10/10/29 12:45:54');
--insert into fusion_log_rules(id_fusion_rules_fk, creation_date) values(1,'10/10/29 12:56:47');
--insert into fusion_log_rules(id_fusion_rules_fk, creation_date) values(2,'10/10/29 13:26:54');
--insert into fusion_log_rules(id_fusion_rules_fk, creation_date) values(2,'10/10/29 13:37:47');
--insert into fusion_log_rules(id_fusion_rules_fk, creation_date) values(2,'10/10/29 13:47:54');

--insert into rules_actions(id_rules_fk, id_resource_fk, id_type_rsc_actions_fk) values(1,1,1);
--insert into rules_actions(id_rules_fk, id_resource_fk, id_type_rsc_actions_fk) values(1,1,3);
--insert into rules_actions(id_rules_fk, id_resource_fk, id_type_rsc_actions_fk) values(2,2,1);
--insert into rules_actions(id_rules_fk, id_resource_fk, id_type_rsc_actions_fk) values(2,2,3);

--insert into rules_log_actions(id_rules_fk, id_type_rsc_actions_fk, id_resource_fk, creation_date) values(1,1,1,'14/10/29 12:35:47');
--insert into rules_log_actions(id_rules_fk, id_type_rsc_actions_fk, id_resource_fk, creation_date) values(1,1,1,'14/10/29 12:45:54');
--insert into rules_log_actions(id_rules_fk, id_type_rsc_actions_fk, id_resource_fk, creation_date) values(1,1,3,'14/10/29 12:56:47');
--insert into rules_log_actions(id_rules_fk, id_type_rsc_actions_fk, id_resource_fk, creation_date) values(2,2,1,'14/10/29 13:26:54');
--insert into rules_log_actions(id_rules_fk, id_type_rsc_actions_fk, id_resource_fk, creation_date) values(2,2,3,'14/10/29 13:37:47');
--insert into rules_log_actions(id_rules_fk, id_type_rsc_actions_fk, id_resource_fk, creation_date) values(2,2,3,'14/10/29 13:47:54');
