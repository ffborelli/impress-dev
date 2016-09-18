package br.ufabc.impress.drools;
//package br.com.impress.drools;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import eu.com.impress.facade.ActionFacade;
//import eu.com.impress.model.Action;
//
//public class FireActions {
//	
//	private String actions;
//	
//	
//	
//	
//	private ArrayList<Action> actionList;
//	
//	public FireActions(String actions){
//		this.actions = actions;
//		actionList = new ArrayList<Action>();
//	}
//
//	private String[] splitActions(){
//		String tmp[] = actions.split("-");
//		
//		return tmp;
//	}
//	
//	public boolean fire(){
//		
//		this.getActionList();
//		
//		for (int i =0 ; i < actionList.size(); i++){
//			
//			if (actionList.get(i).getDescription() != null){
//				System.out.println(actionList.get(i).getId() + " --> " + actionList.get(i).getDescription());
//			}
//		}
//		
//		return true;
//	}
//	
//	private void getActionList(){
//		String tmp[] = this.splitActions();
//		
//		for (int i = 0; i < tmp.length; i++){
//			Action a = this.getActionFacade().find(Integer.valueOf(tmp[i]));
//			actionList.add(a);
//		}
//	}
//		
//	private ActionFacade getActionFacade(){
//		if (actionFacade == null){
//			actionFacade = new ActionFacade();
//		}
//		return actionFacade;
//	}
//}
