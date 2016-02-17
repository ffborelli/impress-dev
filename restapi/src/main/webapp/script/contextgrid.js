var app = angular.module('contextgrid', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

//Create a controller with name placesListController to bind to the grid section.
app.controller('contextGridController', function ($scope, $rootScope, $window, contextGridService, contextGridServiceSearch) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.contextGrid = {currentPage: 1};
    
    $scope.gridOptions = {
        data: 'contextGrid.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'contextSequence', displayName: 'Sequence',enableCellEdit: false },
            { field: 'contextCount', displayName: 'Count',enableCellEdit: false },
            { field: 'contextRegistred', displayName: 'Registered',enableCellEdit: false },
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('contextGridSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listResourceActionTypeArgs = {
            page: $scope.contextGrid.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        contextGridService.get(listResourceActionTypeArgs, function (data) {
            $scope.contextGrid = data;
        })
    };
    

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteContextGrid', row.entity.id);
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
        $scope.contextGrid = data;
    });
    
    $scope.$on('contextGridSelected', function (event, id) {
    	$window.location.href = '#/contextdesigner/'+id;
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
    	
        var listResourceActionTypeArgs = {
                page: $scope.contextGrid.currentPage,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0],
                words: search ,
                isArray: true 
        };
            //console.log($scope.search.value);
        contextGridServiceSearch.get(listResourceActionTypeArgs, function (data) {
            	
           	$rootScope.$broadcast('search', data);
        });
        
    };
});

//Service that provides places operations
app.factory('contextGridService', function ($resource) {
    return $resource('service/contextcount/:id');
});

// Service that provides places operations of search
app.factory('contextGridServiceSearch', function ($resource) {
    //return $resource('service/place-api/search/:words');
    return $resource('service/contextcount/search/:words');
});
