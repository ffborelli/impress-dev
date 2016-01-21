/*
 * IMPReSS Context
 */

"use strict";

var impressApp = angular.module("impressApp",
		["ui.bootstrap", "ui.router", "ngCookies", "places", "placetype", "resourcetype", "contexttype", "actiontype"
		 , "contextentity", "contextgrid", "resource", "fusion", "contextcount", "action", "schedule"]);

// routes
impressApp.config(function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise("/");

	$stateProvider
		.state("home", {
			url: "/",
			templateUrl: "templates/home.html"
		})
		.state("placetype", {
			url: "/placetype",
			templateUrl: "templates/place_type.html"
		})
		.state("placelist", {
			url: "/placelist",
			templateUrl: "templates/place_list.html"
		})
		.state("resourcetype", {
			url: "/resourcetype",
			templateUrl: "templates/resource_type.html"
		})
		.state("resourcelist", {
			url: "/resourcelist",
			templateUrl: "templates/resource_list.html"
		})
		.state("resourcelog", {
			url: "/resourcelog",
			templateUrl: "templates/resource_log.html"
		})
		.state("fusionlist", {
			url: "/fusionlist",
			templateUrl: "templates/fusion_list.html"
		})
		.state("fusionlog", {
			url: "/fusionlog",
			templateUrl: "templates/fusion_log.html"
		})
		.state("rulelist", {
			url: "/rulelist",
			templateUrl: "templates/rule_list.html"
		})
		.state("rulelog", {
			url: "/rulelog",
			templateUrl: "templates/rule_log.html"
		})
		.state("contexttype", {
			url: "/contexttype",
			templateUrl: "templates/context_type.html"
		})
		.state("contextentity", {
			url: "/contextentity",
			templateUrl: "templates/context_entity.html"
		})
		.state("contextlog", {
			url: "/contextlog",
			templateUrl: "templates/context_log.html"
		})
		.state("contextcount", {
			url: "/contextcount",
			templateUrl: "templates/context_count.html"
		})
		.state("contextgrid", {
			url: "/contextgrid",
			templateUrl: "templates/context_grid.html"
		})
		.state("context", {
			url: "/context",
			templateUrl: "templates/context.html"
		})
		.state("contextgraph", {
			url: "/contextgraph",
			templateUrl: "templates/context.html"
		})
		.state("actiontype", {
			url: "/actiontype",
			templateUrl: "templates/action_type.html"
		})
		.state("actionlist", {
			url: "/actionlist",
			templateUrl: "templates/action_list.html"
		})
		.state("actionlog", {
			url: "/actionlog",
			templateUrl: "templates/action_log.html"
		})
		.state("schedulelist", {
			url: "/schedulelist",
			templateUrl: "templates/schedule_list.html"
		})
		.state("schedulelog", {
			url: "/schedulelog",
			templateUrl: "templates/schedule_log.html"
		})
		.state("newactiontype", {
			url: "/newactiontype",
			templateUrl: "templates/new_action_type.html"
		})
		.state("newaction", {
			url: "/newaction",
			templateUrl: "templates/new_action.html"
		})
		.state("newcontexttype", {
			url: "/newcontexttype",
			templateUrl: "templates/new_context_type.html"
		})
		.state("newcontext", {
			url: "/newcontext",
			templateUrl: "templates/new_context.html"
		})
		.state("newfusion", {
			url: "/newfusion",
			templateUrl: "templates/new_fusion.html"
		})
		.state("newplacetype", {
			url: "/newplacetype",
			templateUrl: "templates/new_place_type.html"
		})
		.state("newplace", {
			url: "/newplace",
			templateUrl: "templates/new_place.html"
		})
		.state("newresourcetype", {
			url: "/newresourcetype",
			templateUrl: "templates/new_resource_type.html"
		})
		.state("newresource", {
			url: "/newresource",
			templateUrl: "templates/new_resource.html"
		})
		.state("newrule", {
			url: "/newrule",
			templateUrl: "templates/new_rule.html"
		})
		.state("newschedule", {
			url: "/newschedule",
			templateUrl: "templates/new_schedule.html"
		});

});

// Sidebar
impressApp.controller("MasterCtrl", function($scope, $cookieStore){

	var mobileView = 992;

	$scope.getWidth = function(){
		return window.innerWidth;
	};

	$scope.$watch($scope.getWidth, function(newValue, oldValue){
		if(newValue >= mobileView){
			if(angular.isDefined($cookieStore.get("toggle"))){
				$scope.toggle = ! $cookieStore.get("toggle") ? false : true;
			}else{
				$scope.toggle = true;
			}
		}else{
			$scope.toggle = false;
		}
	});

	$scope.toggleSidebar = function(){
		$scope.toggle = !$scope.toggle;
		$cookieStore.put("toggle", $scope.toggle);
	};

	window.onresize = function(){
		$scope.$apply();
	}

});

//Sub-menu
impressApp.controller("MenuController", function($scope){
	
	$scope.placeSubListState = false;
	$scope.resourceSubListState = false;
	$scope.fusionSubListState = false;
	$scope.ruleSubListState = false;
	$scope.contextSubListState = false;
	$scope.actionSubListState = false;
	$scope.scheduleSubListState = false;
	
	$scope.placeSubList = function(){
		$scope.placeSubListState = !$scope.placeSubListState;
		$scope.resourceSubListState = false;
		$scope.fusionSubListState = false;
		$scope.ruleSubListState = false;
		$scope.contextSubListState = false;
		$scope.actionSubListState = false;
		$scope.scheduleSubListState = false;
	};
	
	$scope.resourceSubList = function(){
		$scope.placeSubListState = false;
		$scope.resourceSubListState = !$scope.resourceSubListState;
		$scope.fusionSubListState = false;
		$scope.ruleSubListState = false;
		$scope.contextSubListState = false;
		$scope.actionSubListState = false;
		$scope.scheduleSubListState = false;
	};
	
	$scope.fusionSubList = function(){
		$scope.placeSubListState = false;
		$scope.resourceSubListState = false;
		$scope.fusionSubListState = !$scope.fusionSubListState;
		$scope.ruleSubListState = false;
		$scope.contextSubListState = false;
		$scope.actionSubListState = false;
		$scope.scheduleSubListState = false;
	};
	
	$scope.ruleSubList = function(){
		$scope.placeSubListState = false;
		$scope.resourceSubListState = false;
		$scope.fusionSubListState = false;
		$scope.ruleSubListState = !$scope.ruleSubListState;
		$scope.contextSubListState = false;
		$scope.actionSubListState = false;
		$scope.scheduleSubListState = false;
	};
	
	$scope.contextSubList = function(){
		$scope.placeSubListState = false;
		$scope.resourceSubListState = false;
		$scope.fusionSubListState = false;
		$scope.ruleSubListState = false;
		$scope.contextSubListState = !$scope.contextSubListState;
		$scope.actionSubListState = false;
		$scope.scheduleSubListState = false;
	};
	
	$scope.actionSubList = function(){
		$scope.placeSubListState = false;
		$scope.resourceSubListState = false;
		$scope.fusionSubListState = false;
		$scope.ruleSubListState = false;
		$scope.contextSubListState = false;
		$scope.actionSubListState = !$scope.actionSubListState;
		$scope.scheduleSubListState = false;
	};
	
	$scope.scheduleSubList = function(){
		$scope.placeSubListState = false;
		$scope.resourceSubListState = false;
		$scope.fusionSubListState = false;
		$scope.ruleSubListState = false;
		$scope.contextSubListState = false;
		$scope.actionSubListState = false;
		$scope.scheduleSubListState = !$scope.scheduleSubListState;
	};
	
});