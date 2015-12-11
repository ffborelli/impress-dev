package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ufabc.impress.facade.FusionRuleLogFacade;
import br.ufabc.impress.facade.ResourceFusionLogFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.facade.RuleActionLogFacade;
import br.ufabc.impress.model.ContextCount;
import br.ufabc.impress.model.FusionLog;
import br.ufabc.impress.model.FusionRuleLog;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceFusionLog;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.model.RuleActionLog;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContextCountDAO extends GenericDAO<ContextCount> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	
	
	private Connection con;
	private Statement stm;
	
	public ContextCountDAO() {
		super(ContextCount.class);
	}

	public void delete(ContextCount obj) {
		super.delete(obj.getId(), ContextCount.class);
	}

	public List<ContextCount> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ContextCount> query = builder.createQuery(ContextCount.class);
			Root<ContextCount> variableRoot = query.from(ContextCount.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ContextCount> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
//	public List<Resource> searchContext(){
//		
//
//		//Query query = em.createQuery("SELECT e FROM Pessoa e");
//		
//	   String sql = "SELECT r FROM Resource r"
//				+ " where r.id in (SELECT rl.id FROM ResourceLog rl where rl.id in"
//				+ " (SELECT rfl.resource FROM ResourceFusionLog rfl where rfl.id in"
//				+ " (SELECT frl.resource FROM FusionRuleLog frl where frl.fusion.id in "
//				+ " (SELECT ral.id FROM RuleActionLog ral ))))";
//	   
//	   //String sql = "SELECT r FROM Resource r";
//				
//		
//	   Query query = getEntityManager().createQuery(sql);
//	   @SuppressWarnings("unchecked")
//	List<Resource> res = (List<Resource>) query.getResultList();
//           
//	   System.out.println(res.size());
//		for (int i = 0; i < res.size(); i++){
//			//searchTree(res.get(i));
//			System.out.println(res.get(i).getDescription());
//		}
//		
//		return res;
//		
//	}
	
//	private void getConnection() throws SQLException{
//		
//		if (con.isClosed()) {
//			try {
//
//				Class.forName("org.postgresql.Driver");
//
//				con = DriverManager
//						.getConnection("jdbc:postgresql://localhost/impress_2?user=postgres&password=admin");
//				stm = con.createStatement();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	 public String recursiveContext(String element) {
//
//	        if (element == null) {
//	        	
//	        	//System.out.printf("Chegou em uma folha\n");
//	        	return null;
//	        	
//	        } else {
//	              		
//	    		try {  
//	 	  
//	    			this.getConnection();
//	 	  
//		           ResultSet rs = stm.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '" + element + "'))");   	           
//	 	           
//		           ArrayList<String> list = new ArrayList<String>();
//	 	           
//	 	           while (rs.next()) { 
//	 	        	   list.add(rs.getString("dependence_fusion_log_fk")); 
//	 	           } 
//	         	   //System.out.println(lista2); 
//	         	   
//	         	   
//	         	   //System.out.printf("Chama a função recursiva para todos os elementos da lista\n"); 
//	         	   
//	         	   for (int i = 0; i < list.size(); i++) { 
//	        			recursiveContext(list.get(i)); 
//	         	   }
//	                
//	 	           con.close();  
//	 	  
//	 	        } catch (Exception e) {  
//	 	             e.printStackTrace();  
//	 	        }  
//	    		
//	            return null;
//
//	        }
//
//	    }
	
//	public void searchContext() {
//		
//		try {
//			this.getConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		ResultSet rs = null;
//		try {
//			rs = stm.executeQuery("select dependence_fusion_log_fk from resource where "
//					+ "id_resource in (select id_resource_fk from resource_log where id_resource_log in "
//					+ "(select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in "
//					+ "(select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in "
//					+ "(select id_rule_action_log from rule_action_log where creation_date = '14/10/29 16:26:54'))))");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		ArrayList<String> list = new ArrayList<String>();
//
//		try {
//			while (rs.next()) {
//				list.add(rs.getString("dependence_fusion_log_fk"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//System.out.println(lista);
//
//		//System.out.printf("Chama a função recursiva para todos os elementos da lista\n");
//		
//		for (int i = 0; i < list.size(); i++) {
//			recursiveContext(list.get(i));
//		}
//
//		try {
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		
//	}
	
	private FusionLog recurviseContext(FusionLog fusionLog){
		
//        "select dependence_fusion_log_fk from resource where id_resource in "
//        + "(select id_resource_fk from resource_log where id_resource_log in "
//        + "(select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '" + elemento + "'))"   
		
		System.out.println("FusionLog Recursive ID " + fusionLog.getId());
		
		//it is wrong
		if (fusionLog == null){
			return null;
		}
		else{
			//select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk =
			ResourceFusionLogFacade resourceFusionLogFacade = new ResourceFusionLogFacade();
			List<ResourceFusionLog> listResourceFusionLog = resourceFusionLogFacade.getByFusionLog(fusionLog);
			
			for (int i = 0; i < listResourceFusionLog.size(); i++ ){
				this.recurviseContext(listResourceFusionLog.get(i).getFusionLog());
			}
			
			return null;
		}
		
		
		
	}
	
	public void searchContext(){
		
//        "select dependence_fusion_log_fk from resource where id_resource in "
//        + "(select id_resource_fk from resource_log where id_resource_log in "
//        + "(select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in "
//        + "(select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in "
//        + "(select id_rule_action_log from rule_action_log where creation_date = '15/10/29 16:26:54'))))"  
		
		//select id_rule_action_log from rule_action_log
		RuleActionLogFacade ruleActionLogFacade = new RuleActionLogFacade();
		List<RuleActionLog> listRuleActionLog = ruleActionLogFacade.listAll();
		
		for (int i = 0; i < listRuleActionLog.size(); i++){
			System.out.println(listRuleActionLog.get(i).getId() );
		}
		
		//select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in
		FusionRuleLogFacade fusionRuleLogFacade = new FusionRuleLogFacade();
		List<FusionRuleLog> listFusionRuleLog = fusionRuleLogFacade.getByRuleAction(listRuleActionLog);
		
		ArrayList<FusionLog> listFusionLog = new ArrayList<FusionLog>();
		
		for (int i = 0; i < listFusionRuleLog.size(); i++){
			listFusionLog.add(listFusionRuleLog.get(i).getFusionLog());
		}
		//select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in
		ResourceFusionLogFacade resourceFusionLogFacade = new ResourceFusionLogFacade();
		List<ResourceFusionLog> listResourceFusionLog = resourceFusionLogFacade.getByFusionLog(listFusionLog);
		
		for (int i = 0; i < listResourceFusionLog.size(); i++ ){
			System.out.println("FusionLog ID " + listFusionRuleLog.get(i).getFusionLog().getId() + " Dependence " + listResourceFusionLog.get(i).getResourceLog().getResource().getDependentIndependent());
			//this.recurviseContext(listFusionRuleLog.get(i).getFusionLog());
			
		}
		
		System.out.println("List");
		for (int i = 0; i < listFusionRuleLog.size(); i++){
			System.out.println(listFusionRuleLog.get(i).getId());	
		}
		
	}
	
	private CriteriaBuilder getCriteriaBuilder(){
		if (builder == null){
		 builder = getEntityManager().getCriteriaBuilder();
		}
		return builder;
	}
	
}
