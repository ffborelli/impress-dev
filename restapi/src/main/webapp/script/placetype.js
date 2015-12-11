var placetype = angular.module('placetype', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

app.controller('placeTypeSimpleToggle', function($scope){
	
	$scope.toggle = false;
	
	$scope.Show1 = function(){
		$scope.toggle = !$scope.toggle; 
	};
	
	$scope.Show2 = function(){
		if (!$scope.toggle) $scope.toggle = !$scope.toggle; 
	};
	
});

//Create a controller with name placesListController to bind to the grid section.
app.controller('placeTypeListController', function ($scope, $rootScope, placeTypeService, placeTypeServiceSearch) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.placeType = {currentPage: 1};
    
    $scope.gridOptions = {
        data: 'placeType.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'description', displayName: 'Description',enableCellEdit: true },
            //{ field: 'place.description', displayName: 'Place Type' },
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('placeTypeSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listPlaceTypeArgs = {
            page: $scope.placeType.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        placeTypeService.get(listPlaceTypeArgs, function (data) {
            $scope.placeType = data;
        })
    };
    

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deletePlaceType', row.entity.id);
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
        $scope.placeType = data;
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
    	
        var listPlaceTypeArgs = {
                page: $scope.placeType.currentPage,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0],
                words: search ,
                isArray: true 
        };
            //console.log($scope.search.value);
        placeTypeServiceSearch.get(listPlaceTypeArgs, function (data) {
            	
           	$rootScope.$broadcast('search', data);
        });
        
    };
});

//Create a controller with name placesFormController to bind to the form section.
app.controller('placeTypeFormController', function ($scope, $rootScope, placeTypeService,$http) {
	  	
    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.placeType = null;
        // For some reason, I was unable to clear field values with type 'url' if the value is invalid.
        // This is a workaroud. Needs proper investigation.
        //document.getElementById('imageUrl').value = null;
        // Resets the form validation state.
        $scope.placeTypeForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a place.
    $scope.updatePlaceType = function () {
        placeTypeService.save($scope.placeType).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a save message.
                $rootScope.$broadcast('placeTypeSaved');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    };

    // Picks up the event broadcasted when the place is selected from the grid and perform the place load by calling
    // the appropiate rest service.
    $scope.$on('placeTypeSelected', function (event, id) {
        $scope.placeType = placeTypeService.get({id: id});
    });

    // Picks us the event broadcasted when the place is deleted from the grid and perform the actual place delete by
    // calling the appropiate rest service.
    $scope.$on('deletePlaceType', function (event, id) {
        placeTypeService.delete({id: id}).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a delete message.
                $rootScope.$broadcast('placeTypeDeleted');
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
    $scope.$on('placeTypeSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('placeTypeDeleted', function () {
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
app.factory('placeTypeService', function ($resource) {
    return $resource('service/placetype/:id');
});

// Service that provides places operations of search
app.factory('placeTypeServiceSearch', function ($resource) {
    //return $resource('service/place-api/search/:words');
    return $resource('service/placetype/search/:words');
});
