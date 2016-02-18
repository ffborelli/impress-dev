var app = angular.module('contextmonitor', ['impressApp']);

app.controller('contextMonitorController', function($scope){
	
	$scope.listStatus = true;
	$scope.graphStatus = false;
	$scope.typeStatus = false;
	
	$scope.changeToList = function(){
		$scope.listStatus = true;
		$scope.graphStatus = false;
		$scope.typeStatus = false;
	};
	
	$scope.changeToGraph = function(){
		$scope.listStatus = false;
		$scope.graphStatus = true;
		$scope.typeStatus = false;
	};
	
	$scope.changeToType = function(){
		$scope.listStatus = false;
		$scope.graphStatus = false;
		$scope.typeStatus = true;
	};
	
});