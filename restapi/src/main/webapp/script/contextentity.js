var contextentitiy = angular.module('contextentity', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

app.controller('contextSimpleToggle', function($scope){
	
	$scope.toggle = false;
	
	$scope.Show1 = function(){
		$scope.toggle = !$scope.toggle; 
	};
	
	$scope.Show2 = function(){
		if (!$scope.toggle) $scope.toggle = !$scope.toggle; 
	};
	
});

app.filter('context_status_filter', function(){
	return function(text, length, end){
		if(text){
			return 'Disable';
		}
		return 'Enable';
	};
});

//Create a controller with name placesListController to bind to the grid section.
app.controller('contextEntityListController', function ($scope, $rootScope, $window, contextEntityService, contextEntityServiceSearch) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.context = {currentPage: 1};
    
    $scope.searchModelID = null;
    
    $scope.gridOptions = {
        data: 'context.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'contextName', displayName: 'Name', enableCellEdit: true },
            { field: 'place.description', displayName: 'Place', enableCellEdit: true },
            { field: 'contextCount', displayName: 'Count', enableCellEdit: true },
            { field: 'contextRegistred', displayName: 'Registred', enableCellEdit: true },
            { field: 'enableDisable', displayName: 'Status', cellFilter: 'context_status_filter', enableCellEdit: true },
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('contextEntitySelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listContextEntityArgs = {
            page: $scope.context.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        contextEntityService.get(listContextEntityArgs, function (data) {
            $scope.context = data;
        })
    };
    

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteContextEntity', row.entity.id);
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
        $scope.context = data;
    });
    
    $scope.$on('contextEntitySelected', function (event, id) {
    	$scope.searchModelID = id;
    });
    
    $scope.loadGraph = function(){
    	if($scope.searchModelID != null){
    		$window.location.href = '#/contextdesigner/'+$scope.searchModelID;
    	}else {
    		alert("Select a context to load its graph.");
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
    	
        var listContextEntityArgs = {
                page: $scope.context.currentPage,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0],
                words: search ,
                isArray: true 
        };
            //console.log($scope.search.value);
        contextEntityServiceSearch.get(listContextEntityArgs, function (data) {
            	
           	$rootScope.$broadcast('search', data);
        });
        
    };
});

//Create a controller with name placesFormController to bind to the form section.
app.controller('contextEntityFormController', function ($scope, $rootScope, contextEntityService,$http) {
	  	
	$http({ 
    	method: 'GET',
    	url: 'service/contexttype/all'
    }).
    success(function(data) {
  	
        $scope.contextTypes = angular.fromJson(data);
    });
    
    $http({ 
    	method: 'GET',
    	url: 'service/place/all'
    }).
    success(function(data) {
  	
        $scope.places = angular.fromJson(data);
    });
    
    $http({ 
    	method: 'GET',
    	url: 'service/context/all'
    }).
    success(function(data) {
  	
        $scope.contexts = angular.fromJson(data);
    });
	
    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.context = null;
        // For some reason, I was unable to clear field values with type 'url' if the value is invalid.
        // This is a workaroud. Needs proper investigation.
        //document.getElementById('imageUrl').value = null;
        // Resets the form validation state.
        $scope.contextEntityForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a place.
    $scope.updateContextEntity = function () {
        contextEntityService.save($scope.context).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a save message.
                $rootScope.$broadcast('contextEntitySaved');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    };

    // Picks up the event broadcasted when the place is selected from the grid and perform the place load by calling
    // the appropiate rest service.
    $scope.$on('contextEntitySelected', function (event, id) {
        $scope.context = contextEntityService.get({id: id});
    });

    // Picks us the event broadcasted when the place is deleted from the grid and perform the actual place delete by
    // calling the appropiate rest service.
    $scope.$on('deleteContextEntity', function (event, id) {
        contextEntityService.delete({id: id}).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a delete message.
                $rootScope.$broadcast('contextEntityDeleted');
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
    $scope.$on('contextEntitySaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('contextEntityDeleted', function () {
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
app.factory('contextEntityService', function ($resource) {
    return $resource('service/context/:id');
});

// Service that provides places operations of search
app.factory('contextEntityServiceSearch', function ($resource) {
    //return $resource('service/place-api/search/:words');
    return $resource('service/context/search/:words');
});
