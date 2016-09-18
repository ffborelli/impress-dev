package br.ufabc.impress.tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufabc.impress.Param;

public class TrackerPooling implements Runnable {

	//private String address_db = "jdbc:postgresql://localhost/checkTracker?user=postgres&password=postgres";
	//private String address_db = "jdbc:postgresql://localhost/impress_review?user=postgres&password=postgres";

	@Override
	public void run() {

		while (true) {

			try {
				Class.forName("org.postgresql.Driver");
				Connection con;
				con = DriverManager.getConnection(Param.address_db);
				Statement stm = con.createStatement();
				
				ResultSet rs = stm.executeQuery("select id_rule_action_log, creation_date, tracker from rule_action_log where tracker = false");
				//ResultSet rs = stm.executeQuery("select id_rule_action_log, creation_date, tracker from rule_action_log where tracker = false and creation_date = '2014-10-14 13:45:54'");
				
			    con.close();

				while (rs.next()) {
					//String statusStr = rs.getString("id_fusion_fk");
					//boolean status = Boolean.parseBoolean(statusStr);
					
					String dateStr = rs.getString("creation_date");
					String idStr = rs.getString("id_rule_action_log");
					
					int index = Integer.parseInt(idStr);
					int row = 0;
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					try {

						Date date = formatter.parse(dateStr);
						new TrackerContext().tracker(new Timestamp(date.getTime()));
					
						//update table 
						
						con = DriverManager.getConnection(Param.address_db);
						stm = con.createStatement();
						
			            String sql = "UPDATE rule_action_log SET tracker = ? WHERE id_rule_action_log = '" + index + "'";
			            
			            PreparedStatement statement = con.prepareStatement(sql);
			            statement.setBoolean(1, true);
			           
			            row = statement.executeUpdate();
			            
			            if (row > 0) {
			                System.out.println("Update row " + index +" has done");
			            }
			            
			            con.close();
			            
			           
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}

	}
}
