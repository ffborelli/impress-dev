//package br.ufabc.impress.doc;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//public class DataBaseIvan {
//
//	public static void main(String[] args) {
//
//		int row = 0;
//		String sql = "";
//		try {
//			FileWriter arquivo = new FileWriter(new File("script.txt"), true);
//			PrintWriter gravarArq = new PrintWriter(arquivo);
//
//			Class.forName("org.postgresql.Driver");
//			Connection con;
//			con = DriverManager
//					.getConnection("jdbc:postgresql://localhost/ivan?user=postgres&password=postgres");
//			//Statement stm = con.createStatement();
//			
//			sql += " insert into place_type(description) values ('CAMPUS'); \n";
//			sql += " insert into place_type(description) values ('BUILD'); \n";
//			sql += " insert into place_type(description) values ('FLOOR'); \n";
//			sql += " insert into place_type(description) values ('CLASS ROOM'); \n";
//			sql += " insert into place_type(description) values ('TEACHER ROOM'); \n";
//			sql += " insert into place_type(description) values ('ROW IN CLASS ROOM');  \n";
//			sql += " insert into place_type(description) values ('WALL IN CLASS ROOM'); \n";
//
//			
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('UFPE',1,NULL,0);  \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('CIN',2,1,0); ";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('FIRST FLOOR',3,2,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('REVIEW CLASSROOM',4,3,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 1',6,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 2',6,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 3',6,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('ROW 4',6,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 1',7,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 2',7,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 3',7,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('WALL 4',7,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('BETWEEN 1nd and 2nd ROW',6,4,0); \n";
//			sql += " insert into place (description, id_place_type_fk, id_place_fk,dependent_0_independent_1) values ('BETWEEN 3th and 4th ROW',6,4,0); \n";
//
//
//			sql += " insert into resource_type (description, sensor_0_actuator_1) values ('LIGHT SENSOR TYPE',0); \n";
//			sql += " insert into resource_type (description, sensor_0_actuator_1) values ('AIR SENSOR TYPE',0);  \n";
//			sql += " insert into resource_type (description, sensor_0_actuator_1) values ('TRAFFIC SENSOR TYPE',0);  \n";
//			sql += " insert into resource_type (description, sensor_0_actuator_1) values ('NOISE SENSOR TYPE',0);  \n";
//			sql += " insert into resource_type (description, sensor_0_actuator_1) values ('STRUCTURAL SENSOR TYPE',0);  \n";
//			sql += " insert into resource_type (description, sensor_0_actuator_1) values ('WASTE SENSOR TYPE',0);  \n";
//
////			PreparedStatement statement = con.prepareStatement(sql,
////					Statement.RETURN_GENERATED_KEYS);
////
////			statement.execute();
////			
//			System.out.println("LIGHT SENSOR TYPE");
////			sql = "";
//						
//			
//			
////			statement = con.prepareStatement(sql,
////					Statement.RETURN_GENERATED_KEYS);
////
////			statement.execute();
////			
////			sql="";
//			
//			sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('LIGHT SENSOR FUSION',2,1,1); \n";
//			sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('AIR SENSOR FUSION',2,1,1); \n";
//			sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('TRAFFIC SENSOR FUSION',2,1,1); \n";
//			sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('NOISE SENSOR FUSION',2,1,1); \n";
//			sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('STRUCTURAL SENSOR FUSION',2,1,1); \n";
//			sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('WASTE SENSOR FUSION',2,1,1); \n";
//			
//			sql+=" insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('LIGHT 1',1,1,1); \n";
//			
//			System.out.println("AIR SENSOR TYPE");
//			for (int i = 7; i <= 100006; i++){
//				System.out.print(i + "AIR \n");
//				sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('AIR + " + i + "',2,1,1); \n";
////				statement = con.prepareStatement(sql,
////						Statement.RETURN_GENERATED_KEYS);
////
////				statement.execute();
//			}
//						
//			System.out.println("TRAFFIC SENSOR TYPE");
//			for (int i = 100007; i <= 200007; i++){
//				System.out.print(i + "TRAFFIC \n");
//				sql += "insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('TRAFFIC + " + i + "',2,1,1); \n";
////				statement = con.prepareStatement(sql,
////						Statement.RETURN_GENERATED_KEYS);
////
////				statement.execute();
//			}		
//
//
//			System.out.println("NOISE SENSOR TYPE");
//			for (int i = 200008; i <= 300008; i++){
//				
//				System.out.print(i + "NOISE \n");
//				sql += " insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('NOISE + " + i + "',2,1,1); \n";
////				statement = con.prepareStatement(sql,
////						Statement.RETURN_GENERATED_KEYS);
////
////				statement.execute();
//			}
//
//			
//			System.out.println("STRUCTURAL SENSOR TYPE");
//			for (int i = 300009; i <= 400009; i++){
//				System.out.print(i + "STRUCTURAL \n");
//				sql +=" insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('STRUCTURAL + " + i + "',2,1,1); ";
////				statement = con.prepareStatement(sql,
////						Statement.RETURN_GENERATED_KEYS);
////
////				statement.execute();
//			}
//			
//			System.out.println("WASTE SENSOR TYPE");
//			for (int i = 400010; i <= 500010; i++){
//				System.out.print(i + "WASTE \n");
//				sql +=" insert into resource (description, id_resource_type_fk, id_place_fk,dependent_0_independent_1) values ('WASTE + " + i + "',2,1,1); \n";
////				statement = con.prepareStatement(sql,
////						Statement.RETURN_GENERATED_KEYS);
////
////				statement.execute();
//			}
//			
//			//sql="";
//			
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(1,'1','10/10/14 12:35:47'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(2,'1','10/10/14 12:35:54'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(3,'0','10/10/14 12:36:47'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(4,'1','10/10/14 12:36:54'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(5,'19','10/10/14 12:37:47'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(6,'18.9','10/10/14 12:37:54'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(7,'0.135','10/10/14 12:37:54'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(8,'19.8','10/10/14 12:35:54'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(9,'23.7','10/10/14 12:36:47'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(10,'19.2','10/10/14 12:36:54'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(11,'23.9','10/10/14 12:37:47');\n ";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(12,'18.9','10/10/14 12:37:54'); \n";
//			sql += " insert into resource_log(id_resource_fk, resource_log_value, creation_date) values(13,'19','10/10/14 12:37:54'); \n";
//
//			
//			sql += " insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) " +
//				" values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Averege Temperature',1);  \n";
//				
//			sql += " insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) "+
//				" values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 1',1);  \n";
//				
//			sql += " insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) "+
//				" values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 2',1); \n";
//				
//			sql += " insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) " +
//				" values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 3',1); \n";
//				
//			sql += " insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) " +
//				" values('select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT from DemoEsperType().win:time_batch(3 sec)  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6', 'Presence Sensor 4',1); \n";
//				
//			sql += " insert into fusion(fusion_text,fusion_name,dependent_0_independent_1) "+
//				" values('select  DemoEsperType.resourceLogValue as S1 , * from DemoEsperType().win:time_batch(3 sec) where DemoEsperType.resource.id = 16', 'Presence Sensor 2',1); \n";
//
//			
//			sql += " insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(1,'23.2','10/10/14 12:45:47'); \n";
//			sql += " insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(2,'0.8','10/10/14 12:46:54'); \n";
//			sql += " insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(1,'19.7','10/10/14 12:56:47'); \n";
//			sql += " insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(1,'19.4','10/10/14 13:06:54'); \n";
//			sql += " insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(2,'0.9','10/10/14 13:07:47'); \n";
//			sql += " insert into fusion_log (id_fusion_fk, fusion_log_value, creation_date) values(2,'1.1','10/10/14 13:17:54'); \n";
//			
//
//		    sql += " insert into resource_fusion (id_fusion_fk, id_resource_fk) values(1,1); ";
//		    sql += " insert into resource_fusion (id_fusion_fk, id_resource_fk) values(2,2); ";
//		    sql += " insert into resource_fusion (id_fusion_fk, id_resource_fk) values(3,3); ";
//		    sql += " insert into resource_fusion (id_fusion_fk, id_resource_fk) values(4,4); ";
//		    sql += " insert into resource_fusion (id_fusion_fk, id_resource_fk) values(5,5); ";
//		    sql += " insert into resource_fusion (id_fusion_fk, id_resource_fk) values(6,6); ";
//			
//
////			statement = con.prepareStatement(sql,
////					Statement.RETURN_GENERATED_KEYS);
////
////			statement.execute();
////			
////			sql="";
//			
//			
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(1,'TURN ON'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(1,'TURN OFF'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(2,'TURN ON'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(2,'TURN OFF'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(3,'TURN ON'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(3,'TURN OFF'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(4,'TURN ON'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(4,'TURN OFF'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(5,'TURN ON'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(5,'TURN OFF'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(6,'TURN ON'); \n";
//			sql += " insert into rsc_action_type (id_resource_type_fk, rsc_action_type_text) values(6,'TURN OFF'); \n";
//
//
//			sql += " insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(1,5,'14/10/14 13:35:47'); \n";
//			sql += " insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(1,6,'14/10/14 13:35:47'); \n";
//			sql += " insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,1,'14/10/14 13:35:47'); \n";
//			sql += " insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,2,'14/10/14 13:35:47'); \n";
//			sql += " insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,3,'14/10/14 13:35:47'); \n";
//			sql += " insert into rsc_fusion_log (id_fusion_log_fk, id_resource_log_fk, creation_date) values(2,4,'14/10/14 13:35:47'); \n";
//
//			sql += " insert into rule(id_rule,rule_text, rule_name,dependent_0_independent_1) values(1,'rule \"Rule 1\" when (Valor > 22.0) then Ação end', 'RULE 1',0); \n";
//
//
//			sql += " insert into fusion_rule(id_fusion_fk, id_rule_fk) values(1,1); \n";
//			sql += " insert into fusion_rule(id_fusion_fk, id_rule_fk) values(2,1); \n";
//			sql += " insert into fusion_rule(id_fusion_fk, id_rule_fk) values(3,1); \n";
//			sql += " insert into fusion_rule(id_fusion_fk, id_rule_fk) values(4,1); \n";
//			sql += " insert into fusion_rule(id_fusion_fk, id_rule_fk) values(5,1); \n";
//			sql += " insert into fusion_rule(id_fusion_fk, id_rule_fk) values(6,1); \n";
//
//
//			sql += " insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,1,'14/10/14 13:45:54'); \n";
//			sql += " insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,2,'14/10/14 13:45:54'); \n";
//			sql += " insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,3,'14/10/14 14:26:54'); \n";
//			sql += " insert into rule_action_log(id_rule_fk, id_rsc_action_type_fk, id_resource_fk, creation_date) values(1,1,4,'14/10/14 14:26:54'); \n";
//			 
//			sql += " insert into fusion_rule_log(id_fusion_log_fk, id_rule_action_log_fk, creation_date) values(1,1,'14/10/14 12:35:47');  \n";
//			 
//			sql += " insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(1,1,1); \n";
//			sql += " insert into action(id_rule_fk, id_resource_fk, id_rsc_action_type_fk) values(1,1,2); \n";
//			
//			sql += " insert into context_type(description) values('Row Occupied'); \n";
//			sql += " insert into context_type(description) values('Row empty'); \n";
//			sql += " insert into context_type(description) values('Cold Classroom'); \n";
//			sql += " insert into context_type(description) values('Hot Classroom'); \n";
//			sql += " insert into context_type(description) values('Warm Classroom'); \n";
//			
////			statement = con.prepareStatement(sql,
////					Statement.RETURN_GENERATED_KEYS);
////
////			statement.execute();
////						
////			con.close();
//			
//			gravarArq.write(sql);
//			gravarArq.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
