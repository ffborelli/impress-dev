var actuators = angular.module('actuators', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

app.controller('actuatorsController', function($scope, $rootScope, $http, $routeParams){
	
	$scope.actuators = [];
	
	$http({ 
    	method: 'GET',
    	url: 'service/action/all'
    }).
    success(function(data) {
    	
        $scope.actions = angular.fromJson(data);
        
        for(var i=0; i<$scope.actions.length; i++){
        	
        	if($scope.actions[i].rule.id == parseInt($routeParams.id) &&
        		$scope.actions[i].resource.resourceType.sensorActuator == 1){
        		
        		$scope.actuators.push($scope.actions[i].resource);
        		
        	}
        	
        }
        
    });
	
	$scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.rule = {currentPage: 1};
    
    $scope.gridOptions = {
        data: 'actuators',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'description', displayName: 'Description', enableCellEdit: true },
            { field: 'resourceType.description', displayName: 'Resource Type', enableCellEdit: true }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('actuatorsSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };
    
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteActuator', row.entity.id);
    };
    
    $scope.$watch('sortInfo.fields[0]', function () {
        $scope.refreshGrid();
    }, true);
    
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });
    
    $scope.$on('refreshGrid', function () {
        $scope.refreshGrid();
    });
    
    $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
        $scope.allSelected = false;
    });
    
    $scope.$on('search', function(event, data) {
        $scope.rule = data;
    });
    
    $scope.$on('actuatorsSelected', function (event, id) {
    	$scope.searchModelID = id;
    });
	
});
