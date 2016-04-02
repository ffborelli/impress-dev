var rule = angular.module('rule', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

app.filter('rule_dependence_filter', function(){
	return function(text, length, end){
		if(text){
			return 'Independent';
		}
		return 'Dependent';
	};
});

app.controller('ruleSimpleToggle', function($scope){
	
	$scope.toggle = false;
	
	$scope.Show = function(){
		if (!$scope.toggle) $scope.toggle = !$scope.toggle; 
	};
	
	$scope.Hide = function(){
		if ($scope.toggle) $scope.toggle = !$scope.toggle; 
	};
	
});

//Create a controller with name placesListController to bind to the grid section.
app.controller('ruleListController', function ($scope, $rootScope, $window, ruleService, ruleServiceSearch) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.rule = {currentPage: 1};
    
    $scope.gridOptions = {
        data: 'rule.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'ruleName', displayName: 'Rule Name', enableCellEdit: true },
            { field: 'ruleText', displayName: 'Rule Text', enableCellEdit: true },
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('ruleSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listRuleArgs = {
            page: $scope.rule.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        ruleService.get(listRuleArgs, function (data) {
            $scope.rule = data;
        })
    };
    

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteRule', row.entity.id);
    };

    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo.fields[0]', function () {
        $scope.refreshGrid();
    }, true);

    // Do something when the grid is sorted.
    // The grid throws the ngGridEventSorted that gets picked up here and assigns the sortInfo to the scope.
    // This will allow to watch the sortInfo in the scope for changed and refresh the grid.
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });

    // Picks the event broadcasted when a place is saved or deleted to refresh the grid elements with the most
    // updated information.
    $scope.$on('refreshGrid', function () {
        $scope.refreshGrid();
    });

    // Picks the event broadcasted when the form is cleared to also clear the grid selection.
    $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
        $scope.allSelected = false;
    });
    
    $scope.$on('search', function(event, data) {
        $scope.rule = data;
    });
    
    $scope.$on('ruleSelected', function (event, id) {
    	$scope.searchModelID = id;
    	$scope.$broadcast('loadActions', {id: $scope.searchModelID});
    	$scope.Show();
    });
    
    $scope.showActuators = function(){
    	if($scope.searchModelID != null){
    		$window.location.href = '#/actuators/'+$scope.searchModelID;
    	}else {
    		alert("Select a rule to show its actuators.");
    	}
    };
    
    $scope.apiSearch = function() {
    	
    	var search = "null";
    	
    	if ($scope.searchModel === ''){
    		search = "null";
    	}
    	else if(typeof $scope.searchModel !== 'undefined'){
    		search = $scope.searchModel ;
    	}
    	else{};
    	
        var listRuleArgs = {
                page: $scope.rule.currentPage,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0],
                words: search ,
                isArray: true 
        };
            //console.log($scope.search.value);
        ruleServiceSearch.get(listRuleArgs, function (data) {
            	
           	$rootScope.$broadcast('search', data);
        });
        
    };
});

app.controller('ruleActionsListController', function ($scope, $rootScope, $http) {
    
	$scope.actions = [];
	
	// Usar eventos para chamar esta função!!! 
	$scope.$on('loadActions', function(event, args){
		alert('teste');
		$http({ 
    		method: 'GET',
    		url: 'service/action/all'
    	}).
    	success(function(data) {
    		
        	$scope.action = angular.fromJson(data);
        	
        	for(var i=0; i<$scope.actions.length; i++){
        		
        		if($scope.action[i].rule.id == parseInt(args.id) &&
        			$scope.action[i].resource.resourceType.sensorActuator == 1){
        			
        			$scope.actions.push($scope.action[i]);
        			
        		}
        		
        	}
        	
        	alert($scope.actions);
        	
    	});
	
	});
	
	//$scope.sortInfo = {fields: ['id'], directions: ['asc']};
    //$scope.rule = {currentPage: 1};
    
    $scope.gridActionOptions = {
        data: 'actions',
        useExternalSorting: true,
        sortInfo: {fields: ['id'], directions: ['asc']},

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'description', displayName: 'Actuator', enableCellEdit: true },
            { field: 'resourceType.description', displayName: 'Resource Type', enableCellEdit: true },
            { field: 'resourceActionType.resourceActionTypeText', displayName: 'Action', enableCellEdit: true }
        ],

        multiSelect: false,
        selectedItems: []
    };
    
    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
    	$scope.actions = [];
    };
    
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteActions', row.entity.id);
    };
    
    $scope.$watch('sortInfo.fields[0]', function () {
        $scope.refreshGrid();
    }, true);
    
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });
    
    $scope.$on('refreshActionGrid', function () {
        $scope.refreshGrid();
    });
	
});

//Create a controller with name placesFormController to bind to the form section.
app.controller('ruleFormController', function ($scope, $rootScope, ruleService,$http) {
	  	
    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.rule = null;
        // For some reason, I was unable to clear field values with type 'url' if the value is invalid.
        // This is a workaroud. Needs proper investigation.
        //document.getElementById('imageUrl').value = null;
        // Resets the form validation state.
        $scope.ruleForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a place.
    $scope.updateRule = function () {
    	$scope.rule.dependentIndependent = 1;
        ruleService.save($scope.rule).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a save message.
                $rootScope.$broadcast('ruleSaved');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    };
    
    $scope.cancel = function(){
    	$scope.clearForm();
    	$scope.Hide();
    };

    // Picks up the event broadcasted when the place is selected from the grid and perform the place load by calling
    // the appropiate rest service.
    $scope.$on('ruleSelected', function (event, id) {
        $scope.rule = ruleService.get({id: id});
    });

    // Picks us the event broadcasted when the place is deleted from the grid and perform the actual place delete by
    // calling the appropiate rest service.
    $scope.$on('deleteRule', function (event, id) {
        ruleService.delete({id: id}).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a delete message.
                $rootScope.$broadcast('ruleDeleted');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    });
    
});

//Create a controller with name alertMessagesController to bind to the feedback messages section.
app.controller('alertMessagesController', function ($scope) {
    // Picks up the event to display a saved message.
    $scope.$on('ruleSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('ruleDeleted', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record deleted successfully!' }
        ];
    });

    // Picks up the event to display a server error message.
    $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'There was a problem in the server!' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});

//Service that provides places operations
app.factory('ruleService', function ($resource) {
    return $resource('service/rule/:id');
});

// Service that provides places operations of search
app.factory('ruleServiceSearch', function ($resource) {
    //return $resource('service/place-api/search/:words');
    return $resource('service/rule/search/:words');
});
