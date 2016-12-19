var rulelog = angular.module('rulelog', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

//Create a controller with name placesListController to bind to the grid section.
app.controller('ruleLogListController', function ($scope, $rootScope, ruleLogService, ruleLogServiceSearch) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.ruleLog = {currentPage: 1};
    
    $scope.gridOptions = {
        data: 'ruleLog.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'rule.ruleName', displayName: 'Rule',enableCellEdit: true },
            { field: 'creationDate', displayName: 'Timestamp', cellFilter: 'date: "yyyy/MM/dd - HH:mm"' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('ruleLogSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listRuleLogArgs = {
            page: $scope.ruleLog.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        ruleLogService.get(listRuleLogArgs, function (data) {
            $scope.ruleLog = data;
        });
    };
    

    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteRuleLog', row.entity.id);
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
        $scope.ruleLog = data;
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
    	
        var listRuleLogArgs = {
                page: $scope.ruleLog.currentPage,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0],
                words: search ,
                isArray: true 
        };
            //console.log($scope.search.value);
        ruleLogServiceSearch.get(listRuleLogArgs, function (data) {
            	
           	$rootScope.$broadcast('search', data);
        });
        
    };
});

//Create a controller with name placesFormController to bind to the form section.
app.controller('ruleLogFormController', function ($scope, $rootScope, ruleLogService,$http) {
	  	
    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.ruleLog = null;
        // For some reason, I was unable to clear field values with type 'url' if the value is invalid.
        // This is a workaroud. Needs proper investigation.
        //document.getElementById('imageUrl').value = null;
        // Resets the form validation state.
        $scope.ruleLogForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    // Calls the rest method to save a place.
    $scope.updateRuleLog = function () {
    	ruleLogService.save($scope.ruleLog).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a save message.
                $rootScope.$broadcast('ruleLogSaved');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    };

    // Picks up the event broadcasted when the place is selected from the grid and perform the place load by calling
    // the appropiate rest service.
    $scope.$on('ruleLogSelected', function (event, id) {
        $scope.ruleLog = ruleLogService.get({id: id});
    });

    // Picks us the event broadcasted when the place is deleted from the grid and perform the actual place delete by
    // calling the appropiate rest service.
    $scope.$on('deleteRuleLog', function (event, id) {
    	ruleLogService.delete({id: id}).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a delete message.
                $rootScope.$broadcast('ruleLogDeleted');
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
    $scope.$on('ruleLogSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('ruleLogDeleted', function () {
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
app.factory('ruleLogService', function ($resource) {
    return $resource('service/ruleactionlog/:id');
});

// Service that provides places operations of search
app.factory('ruleLogServiceSearch', function ($resource) {
    //return $resource('service/place-api/search/:words');
    return $resource('service/ruleactionlog/search/:words');
});