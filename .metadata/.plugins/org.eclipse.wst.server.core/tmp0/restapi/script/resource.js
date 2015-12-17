var resource = angular.module('resource', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

app.filter('resource_dependence_filter', function(){
	return function(text, length, end){
		if(text){
			return 'Independent';
		}
		return 'Dependent';
	};
});

app.controller('resourceSimpleToggle', function($scope){
	
	$scope.toggle = false;
	
	$scope.Show1 = function(){
		$scope.toggle = !$scope.toggle; 
	};
	
	$scope.Show2 = function(){
		if (!$scope.toggle) $scope.toggle = !$scope.toggle; 
	};
	
});

//Create a controller with name placesListController to bind to the grid section.
app.controller('resourceListController', function ($scope, $rootScope, resourceService, resourceServiceSearch) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.resource = {currentPage: 1};
    
    $scope.gridOptions = {
        data: 'resource.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'description', displayName: 'Description',enableCellEdit: true },
            { field: 'resourceType.description', displayName: 'Resource Type' },
            { field: 'place.description', displayName: 'Place' },
            { field: 'dependentIndependent', displayName: 'Dependence', cellFilter: 'resource_dependence_filter' },
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('resourceSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listResourceArgs = {
            page: $scope.resource.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        resourceService.get(listResourceArgs, function (data) {
            $scope.resource = data;
        })
    };
    

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteResource', row.entity.id);
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
        $scope.resource = data;
    });
    
    $scope.apiSearch = function() {
    	
    	var search = "null";
    	
    	if ($scope.searchModel === ''){
    		search = "null";
    	}
    	else if(typeof $scope.searchModel !== 'undefined'){
    		search = $scope.searchModel ;
    	}
    	else{};
    	
        var listResourceArgs = {
                page: $scope.resource.currentPage,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0],
                words: search ,
                isArray: true 
        };
            //console.log($scope.search.value);
        resourceServiceSearch.get(listResourceArgs, function (data) {
            	
           	$rootScope.$broadcast('search', data);
        });
        
    };
});

//Create a controller with name placesFormController to bind to the form section.
app.controller('resourceFormController', function ($scope, $rootScope, resourceService,$http) {
	  	
	$http({ 
    	method: 'GET',
    	url: 'service/resourcetype/all'
    }).
    success(function(data) {
  	
        $scope.resourceTypes = angular.fromJson(data);
    });
    
    $http({ 
    	method: 'GET',
    	url: 'service/place/all'
    }).
    success(function(data) {
  	
        $scope.places = angular.fromJson(data);
    });
	
    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.resource = null;
        // For some reason, I was unable to clear field values with type 'url' if the value is invalid.
        // This is a workaroud. Needs proper investigation.
        //document.getElementById('imageUrl').value = null;
        // Resets the form validation state.
        $scope.resourceForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a place.
    $scope.updateResource = function () {
        resourceService.save($scope.resource).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a save message.
                $rootScope.$broadcast('resourceSaved');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    };

    // Picks up the event broadcasted when the place is selected from the grid and perform the place load by calling
    // the appropiate rest service.
    $scope.$on('resourceSelected', function (event, id) {
        $scope.resource = resourceService.get({id: id});
    });

    // Picks us the event broadcasted when the place is deleted from the grid and perform the actual place delete by
    // calling the appropiate rest service.
    $scope.$on('deleteResource', function (event, id) {
        resourceService.delete({id: id}).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a delete message.
                $rootScope.$broadcast('resourceDeleted');
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
    $scope.$on('resourceSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('resourceDeleted', function () {
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
app.factory('resourceService', function ($resource) {
    return $resource('service/resource/:id');
});

// Service that provides places operations of search
app.factory('resourceServiceSearch', function ($resource) {
    //return $resource('service/place-api/search/:words');
    return $resource('service/resource/search/:words');
});
