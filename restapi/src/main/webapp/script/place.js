var app = angular.module('places', ['ngResource', 'ngGrid', 'ui.bootstrap', 'toggles', 'impressApp']);

app.filter('place_dependence_filter', function(){
	return function(text, length, end){
		if(text){
			return 'Independent';
		}
		return 'Dependent';
	};
});

app.controller('placeSimpleToggle', function($scope){
	
	$scope.toggle = false;
	
	$scope.Show1 = function(){
		$scope.toggle = !$scope.toggle; 
	};
	
	$scope.Show2 = function(){
		if (!$scope.toggle) $scope.toggle = !$scope.toggle; 
	};
	
});

// Create a controller with name placesListController to bind to the grid section.
app.controller('placesListController', function ($scope, $rootScope, placeService, placeServiceSearch) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.places = {currentPage: 1};
    
    $scope.gridOptions = {
        data: 'places.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'description', displayName: 'Description',enableCellEdit: true },
            { field: 'placeType.description', displayName: 'Place Type' },
            { field: 'place.description', displayName: 'Place Location' },
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('placeSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listPlacesArgs = {
            page: $scope.places.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        placeService.get(listPlacesArgs, function (data) {
            $scope.places = data;
        })
    };
    

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deletePlace', row.entity.id);
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
        $scope.places = data;
    });
    
    $scope.apiSearch = function() {

    	//$rootScope.$broadcast('search');
    	
    	var search = "null";
    	
    	if ($scope.searchModel === ''){
    		search = "null";
    	}
    	else if(typeof $scope.searchModel !== 'undefined'){
    		search = $scope.searchModel ;
    	}
    	else{};
    	
        var listPlacesArgs = {
                page: $scope.places.currentPage,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0],
                words: search ,
                isArray: true 
        };
            //console.log($scope.search.value);
        placeServiceSearch.get(listPlacesArgs, function (data) {
            	
           	$rootScope.$broadcast('search', data);
        });
            
            
    };
});

// Create a controller with name placesFormController to bind to the form section.
app.controller('placesFormController', function ($scope, $rootScope, placeService,$http) {
//	$scope.custom = true;
//	$scope.toggleCustom = function() {
//        $scope.custom = $scope.custom === false ? true: false;
//    };
	
	//get type places
    $http({ 
    	method: 'GET',
    	url: 'service/placetype/all'
    }).
    success(function(data) {
  	
        $scope.placeTypes = angular.fromJson(data);
    });
    
	//get places
    $http({ 
    	method: 'GET',
    	url: 'service/place/all'
    }).
    success(function(data) {
  	
        $scope.places = angular.fromJson(data);
    });
  	
    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.place = null;
        // For some reason, I was unable to clear field values with type 'url' if the value is invalid.
        // This is a workaroud. Needs proper investigation.
        //document.getElementById('imageUrl').value = null;
        // Resets the form validation state.
        $scope.placeForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a place.
    $scope.updatePlace = function () {
        placeService.save($scope.place).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a save message.
                $rootScope.$broadcast('placeSaved');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    };

    // Picks up the event broadcasted when the place is selected from the grid and perform the place load by calling
    // the appropiate rest service.
    $scope.$on('placeSelected', function (event, id) {
        $scope.place = placeService.get({id: id});
        //alert(placeService.get({'id': 1}).description);
        
        /*var placeType = document.getElementById('placeType').options; // .options.text = placeService.get({id: id}).placeType.description;
        var place = document.getElementById('place').options; //.options.text = placeService.get({id: id}).placeType.description;
        
        for(var i=0; i<placeType.length; i++){
        	if(placeType[i].text == $scope.place.placeType.Description){
        		placeType[i].selected = true;
        		break;
        	}
        }
        
        for(var i=0; i<place.length; i++){
        	if(place[i].text == $scope.place.placeType.Description){
        		place[i].selected = true;
        		break;
        	}
        }*/
        
    });

    // Picks us the event broadcasted when the place is deleted from the grid and perform the actual place delete by
    // calling the appropiate rest service.
    $scope.$on('deletePlace', function (event, id) {
        placeService.delete({id: id}).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a delete message.
                $rootScope.$broadcast('placeDeleted');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    });
    
});

// Create a controller with name alertMessagesController to bind to the feedback messages section.
app.controller('alertMessagesController', function ($scope) {
    // Picks up the event to display a saved message.
    $scope.$on('placeSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('placeDeleted', function () {
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

// Service that provides places operations
app.factory('placeService', function ($resource) {
    //return $resource('service/place/:id');
	return $resource('service/place/:id');
});
// Service that provides places operations of search
app.factory('placeServiceSearch', function ($resource) {
    //return $resource('service/place-api/search/:words');
    return $resource('service/place/search/:words');
});