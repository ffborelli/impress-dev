insert into place_type(id_place_type, description) values(1,'Build');
insert into place_type(id_place_type, description) values(2,'Floor');
insert into place_type(id_place_type, description) values(3,'Classroom');

insert into place(id_place, description, id_place_type_fk, id_place_fk, dependent_0_independent_1) values(1,'B',1,NULL,1);
insert into place(id_place, description, id_place_type_fk, id_place_fk, dependent_0_independent_1) values(2,'8ª',2,1,1);
insert into place(id_place, description, id_place_type_fk, id_place_fk, dependent_0_independent_1) values(3,'3º',2,1,1);
insert into place(id_place, description, id_place_type_fk, id_place_fk, dependent_0_independent_1) values(4,'R808',3,2,1);
insert into place(id_place, description, id_place_type_fk, id_place_fk, dependent_0_independent_1) values(5,'A',1,NULL,1);
insert into place(id_place, description, id_place_type_fk, id_place_fk, dependent_0_independent_1) values(6,'R300',3,3,1);

insert into resource_type(description, sensor_0_actuator_1) values('Air Conditioning',1);
insert into resource_type(description, sensor_0_actuator_1) values('Thermometer',0);
insert into resource_type(description, sensor_0_actuator_1) values('Light Sensor',0);
insert into resource_type(description, sensor_0_actuator_1) values('Lamp',1);


insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('AC1320',1,4,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('AC1321',1,4,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('T1310',2,4,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L8001',4,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L8002',4,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0088',3,6,1,NULL);


insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(6,'60%','14/10/29 12:35:44');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(3,'23.4','14/10/29 12:35:47');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(6,'55%','14/10/29 12:35:54');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(3,'22.9','14/10/29 12:35:57');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(4,'Off','14/10/29 12:36:54');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(5,'Off','14/10/29 12:37:47');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(1,'On','14/10/29 12:46:47');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(2,'On','14/10/29 12:46:57');

insert into fusion(fusion_name, fusion_text, dependent_0_independent_1) values('Average2','Calculate the average of the last 2 measurements',1);
insert into fusion(fusion_name, fusion_text, dependent_0_independent_1) values('Average5','Calculate the average of the last 5 measurements',1);
insert into fusion(fusion_name, fusion_text, dependent_0_independent_1) values('Average10','Calculate the average of the last 10 measurements',1);

insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'23.15','14/10/29 12:55:47');
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'57.5','14/10/29 12:56:54');

insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,3);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,4);

insert into rsc_action_type(id_resource_type_fk, rsc_action_type_text) values(1,'On');
insert into rsc_action_type(id_resource_type_fk, rsc_action_type_text) values(1,'Off');
insert into rsc_action_type(id_resource_type_fk, rsc_action_type_text) values(1,'Increase');
insert into rsc_action_type(id_resource_type_fk, rsc_action_type_text) values(1,'Decrease');
insert into rsc_action_type(id_resource_type_fk, rsc_action_type_text) values(4,'On');
insert into rsc_action_type(id_resource_type_fk, rsc_action_type_text) values(4,'Off');

insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(1,2,'14/10/29 13:35:47');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(1,4,'14/10/29 13:35:47');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,1,'14/10/29 13:35:47');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,3,'14/10/29 13:35:47');

insert into rule(rule_name, rule_text, dependent_0_independent_1) values('TempHot','rule TEMPERATURE R808 when n : Room(avgT > 20); then n.setTurnOnArCondicioando(true);end',1);
insert into rule(rule_name, rule_text, dependent_0_independent_1) values('LightClearly','rule LIGHTING R300 when n : Room(avgL > 50); then n.setTurnOffLight(true);end',1);

insert into fusion_rule(id_fusion_fk, id_rule_fk) values(1,1);
insert into fusion_rule(id_fusion_fk, id_rule_fk) values(1,2);

insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,1,'14/10/29 13:45:54');
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,2,'14/10/29 13:45:54');
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,4,'14/10/29 14:26:54');
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,5,'14/10/29 14:26:54');

insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(1,1,'14/10/29 12:35:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(1,2,'14/10/29 12:45:54');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(2,3,'14/10/29 12:56:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(2,4,'14/10/29 13:26:54');

insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(1,1,1);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(1,2,1);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,5,5);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,6,5);

insert into context_type(description) values('Hot Classroom');
insert into context_type(description) values('Cold Classroom');
insert into context_type(description) values('Clearly Classroom');
insert into context_type(description) values('Dark Classroom');

insert into context(context_name, id_place_fk, id_context_type_fk, id_context_fk, context_sequence, context_count, context_registered, enable_0_disable_1) values('Hot R808 Classroom',4,1,null, '1*3,SENSOR:2*1,FUSION;2*1,FUSION:3*1,RULE;3*1,RULE:4*1,ACTUATOR;', 0, 1, 0);
insert into context(context_name, id_place_fk, id_context_type_fk, id_context_fk, context_sequence, context_count, context_registered, enable_0_disable_1) values('Clearly R300 Classroom',6,3,null, '1*3,SENSOR:2*2,FUSION;3*6,SENSOR:2*2,FUSION;2*2,FUSION:4*1,RULE;4*1,RULE:6*2,ACTUATOR;4*1,RULE:5*1,ACTUATOR;', 0, 1, 0);

insert into resource_context(id_resource_fk, id_context_fk) values(1,1);
insert into resource_context(id_resource_fk, id_context_fk) values(2,1);
insert into resource_context(id_resource_fk, id_context_fk) values(3,1);
insert into resource_context(id_resource_fk, id_context_fk) values(4,2);
insert into resource_context(id_resource_fk, id_context_fk) values(5,2);
insert into resource_context(id_resource_fk, id_context_fk) values(6,2);

insert into fusion_context(id_fusion_fk, id_context_fk) values(1,1);
insert into fusion_context(id_fusion_fk, id_context_fk) values(1,2);

insert into rule_context(id_rule_fk, id_context_fk) values(1,1);
insert into rule_context(id_rule_fk, id_context_fk) values(2,2);

insert into context_log(id_context_fk,creation_date) values(1,'14/10/29 13:26:54');
insert into context_log(id_context_fk,creation_date) values(2,'14/10/29 13:27:54');


insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','14/10/29 12:35:58');
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'52,5%','14/10/29 12:35:59');


insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0007',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0008',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0009',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L0010',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L0011',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('Average2;LS007;LS008',3,6,1,3);

insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(7,'60%','14/10/29 12:35:44');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(8,'50%','14/10/29 12:35:54');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(9,'50%','14/10/29 12:35:56');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(12,'55%','14/10/29 12:35:57');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(10,'On','14/10/29 12:45:44');
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(11,'On','14/10/29 12:46:44');

insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,7);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,8);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,9);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,12);

insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(3,9,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(3,10,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(4,11,'14/10/29 12:35:59');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(4,12,'14/10/29 12:35:59');

insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,10,'14/10/29 16:26:54');
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,11,'14/10/29 16:26:54');

insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(4,5,'14/10/29 12:35:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(4,6,'14/10/29 12:35:47');

insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(1,10,4);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(1,11,4);







insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/10/29 12:38:58'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/10/29 12:39:59'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/10/29 12:40:59'); 

insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:38:58'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:39:59'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:40:59'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:41:59'); 


insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L0013',4,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L0014',4,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0015',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0016',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0017',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0018',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('Average2;LS015;LS016',3,6,1,5);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('Average2;LS017;LS018',3,6,1,6);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0021',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0022',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0023',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0024',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('Average2;LS029;LS021',3,6,1,8);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('Average2;LS023;LS024',3,6,1,9);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L0027',4,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('L0028',4,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('LS0029',3,6,1,NULL);
insert into resource(description, id_resource_type_fk, id_place_fk, dependent_0_independent_1,dependence_fusion_log_fk) values('Average2;LS025;LS022',3,6,1,10);

insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(15,'60%','15/10/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(16,'50%','15/10/29 12:35:54'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(17,'60%','15/10/29 12:35:56'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(18,'50%','15/10/29 12:35:57'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(19,'55%','15/10/29 12:35:56'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(20,'55%','15/10/29 12:35:57'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(13,'Decrease','15/10/29 12:45:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(14,'Decrease','15/10/29 12:46:44'); 

insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(29,'60%','15/11/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(21,'50%','15/11/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(22,'55%','15/11/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(23,'60%','15/11/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(24,'50%','15/11/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(25,'55%','15/11/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(26,'55%','15/11/29 12:35:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(27,'Decrease','15/11/29 12:45:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(28,'Decrease','15/11/29 12:46:44'); 
insert into resource_log(id_resource_fk, resource_value_log, creation_date) values(30,'55%','15/11/29 12:55:44');      

insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/10/29 12:38:58'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/10/29 12:39:59'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/10/29 12:40:59'); 

insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:38:58'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:39:59'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:40:59'); 
insert into fusion_log(id_fusion_fk, fusion_value_log, creation_date) values(1,'55%','15/11/29 12:41:59'); 

insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(5,15,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(5,16,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(6,17,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(6,18,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(7,19,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(7,20,'14/10/29 12:35:58');

insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(8,23,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(8,24,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(9,25,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(9,28,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(10,26,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(10,27,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(11,29,'14/10/29 12:35:58');
insert into rsc_fusion_log(id_fusion_log_fk, id_resource_log_fk, creation_date) values(11,32,'14/10/29 12:35:58');


insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,13,'13/10/29 16:26:54'); 
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,14,'13/10/29 16:26:54'); 
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,27,'15/10/29 16:26:54'); 
insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(2,4,28,'15/10/29 16:26:54'); 

insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(7,7,'14/10/29 12:35:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(7,8,'14/10/29 12:35:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(11,9,'14/10/29 12:35:47');
insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(11,10,'14/10/29 12:35:47');

insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,15);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,16);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,17);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,18);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,19);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,20);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,29);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,21);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,25);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,22);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,23);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,24);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,30);
insert into resource_fusion(id_fusion_fk, id_resource_fk) values(1,26);


insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,13,4);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,14,4);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,27,4);
insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(2,28,4);
