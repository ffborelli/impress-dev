/*
 * IMPReSS Context
 */

"use strict";

var impressApp = angular.module('impressApp',
		['ui.bootstrap', 'ngRoute', 'ngCookies', 'places', 'placetype', 'resourcetype', 'contexttype', 'actiontype',
		 'contextentity', 'contextgrid', 'resource', 'fusion', 'contextdesigner', 'action', 'schedule']);

// routes
impressApp.config(['$routeProvider', function($routeProvider){
	
	$routeProvider
		.when('/', {
			templateUrl: 'templates/home.html'
		})
		.when('/placetype', {
			templateUrl: 'templates/place_type.html'
		})
		.when('/placelist', {
			templateUrl: 'templates/place_list.html'
		})
		.when('/resourcetype', {
			templateUrl: 'templates/resource_type.html'
		})
		.when('/resourcelist', {
			templateUrl: 'templates/resource_list.html'
		})
		.when('/resourcelog', {
			templateUrl: 'templates/resource_log.html'
		})
		.when('/fusionlist', {
			templateUrl: 'templates/fusion_list.html'
		})
		.when('/fusionlog', {
			templateUrl: 'templates/fusion_log.html'
		})
		.when('/rulelist', {
			templateUrl: 'templates/rule_list.html'
		})
		.when('/rulelog', {
			templateUrl: 'templates/rule_log.html'
		})
		.when('/contexttype', {
			templateUrl: 'templates/context_type.html'
		})
		.when('/contextentity', {
			templateUrl: 'templates/context_entity.html'
		})
		.when('/contextlog', {
			templateUrl: 'templates/context_log.html'
		})
		.when('/contextdesigner', {
			templateUrl: 'templates/context_designer.html'
		})
		.when('/contextdesigner/:id', {
			templateUrl: 'templates/context_designer.html'
		})
		.when('/contextgrid', {
			templateUrl: 'templates/context_grid.html'
		})
		.when('/context', {
			templateUrl: 'templates/context.html'
		})
		.when('/contextgraph', {
			templateUrl: 'templates/context.html'
		})
		.when('/actiontype', {
			templateUrl: 'templates/action_type.html'
		})
		.when('/actionlist', {
			templateUrl: 'templates/action_list.html'
		})
		.when('/actionlog', {
			templateUrl: 'templates/action_log.html'
		})
		.when('/schedulelist', {
			templateUrl: 'templates/schedule_list.html'
		})
		.when('/schedulelog', {
			templateUrl: 'templates/schedule_log.html'
		})
		.when('/newactiontype', {
			templateUrl: 'templates/new_action_type.html'
		})
		.when('/newaction', {
			templateUrl: 'templates/new_action.html'
		})
		.when('/newcontexttype', {
			templateUrl: 'templates/new_context_type.html'
		})
		.when('/newcontext', {
			templateUrl: 'templates/new_context.html'
		})
		.when('/newfusion', {
			templateUrl: 'templates/new_fusion.html'
		})
		.when('/newplacetype', {
			templateUrl: 'templates/new_place_type.html'
		})
		.when('/newplace', {
			templateUrl: 'templates/new_place.html'
		})
		.when('/newresourcetype', {
			templateUrl: 'templates/new_resource_type.html'
		})
		.when('/newresource', {
			templateUrl: 'templates/new_resource.html'
		})
		.when('/newrule', {
			templateUrl: 'templates/new_rule.html'
		})
		.when('/newschedule', {
			templateUrl: 'templates/new_schedule.html'
		})
		.otherwise({
			redirectTo: '/'
		});
	
}]);

// Sidebar
impressApp.controller('MasterCtrl', function($scope, $cookieStore){

	var mobileView = 992;

	$scope.getWidth = function(){
		return window.innerWidth;
	};

	$scope.$watch($scope.getWidth, function(newValue, oldValue){
		if(newValue >= mobileView){
			if(angular.isDefined($cookieStore.get('toggle'))){
				$scope.toggle = ! $cookieStore.get('toggle') ? false : true;
			}else{
				$scope.toggle = true;
			}
		}else{
			$scope.toggle = false;
		}
	});

	$scope.toggleSidebar = function(){
		$scope.toggle = !$scope.toggle;
		$cookieStore.put('toggle', $scope.toggle);
	};

	window.onresize = function(){
		$scope.$apply();
	}

});

//Sub-menu
impressApp.controller('MenuController', function($scope){
	
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