package eu.com.impress.resteasy;

import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.core.Application;

public class ImpressApplication extends Application {
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(ActionResource.class);
		s.add(ContextCountResource.class);
		s.add(ContextLogResource.class);
		s.add(ContextResource.class);
		s.add(ContextTypeResource.class);
		s.add(FusionContextResource.class);
		s.add(FusionLogResource.class);
		s.add(FusionResource.class);
		s.add(FusionRuleLogResource.class);
		s.add(FusionRuleResource.class);
		s.add(PlaceResource.class);
		s.add(PlaceTypeResource.class);
		s.add(ResourceActionTypeResource.class);
		s.add(ResourceContextResource.class);
		s.add(ResourceFusionLogResource.class);
		s.add(ResourceFusionResource.class);
		s.add(ResourceLogResource.class);
		s.add(ResourceResource.class);
		s.add(ResourceScheduleResource.class);
		s.add(ResourceTypeResource.class);
		s.add(RuleActionLogResource.class);
		s.add(RuleContextResource.class);
		s.add(RuleResource.class);
		s.add(ScheduleResource.class);
		s.add(ScheduleLogResource.class);
		return s;
	}
}
