var calendar = angular.module('calendar', ['ngResource', 'ngGrid', 'ui.bootstrap','impressApp']);

app.directive('contextCalendar', function(){
	return {
		link: function(scope, element, attr){
			
			$('startDate').datepicker({
				showOtherMonths: true,
				selectOtherMonths: true,
				changeMonth: true,
				changeYear: true,
				showButtonPanel: true,
				dateFormat: 'yy/mm/dd'
			});
			
		}
	};
});

app.controller('calendarController', function ($scope, $http, scheduleService){	
	
	$scope.schedule = {};
	
	$http({ 
    	method: 'GET',
    	url: 'service/place/all'
    }).
    success(function(data) {
        $scope.places = angular.fromJson(data);
    });
	
	$http({ 
    	method: 'GET',
    	url: 'service/rule/all'
    }).
    success(function(data) {
        $scope.rules = angular.fromJson(data);
    });
	
	/*$scope.cancel = function(){
		$('#add-event-form').dialog('close');
	};
	
	$scope.addEvent = function(){
		
		$scope.schedule.id = null;
		
		var startTimestamp = new Date($('#startDate').val());
		var finalTimestamp = new Date($('#endDate').val());
		
		if($('#startMeridiem').val() == 'AM') startTimestamp.setHours(parseInt($('#startHour').val()));
		else startTimestamp.setHours(parseInt($('#startHour').val()) + 12);
		
		startTimestamp.setMinutes(parseInt($('#startMin').val()));
		
		if($('#endMeridiem').val() == 'AM') finalTimestamp.setHours(parseInt($('#endHour').val()));
		else finalTimestamp.setHours(parseInt($('#endHour').val()) + 12);
		
		finalTimestamp.setMinutes(parseInt($('#endMin').val()));
		
		$scope.schedule.initialDate = startTimestamp.getTime();
		$scope.schedule.finalDate = finalTimestamp.getTime();
		
		scheduleService.save($scope.schedule).$promise.then(
			function(){
				alert("Success!");
				$('#add-event-form').dialog('close');
				$('#addEventForm').each(function(){
					this.reset();
				});
			},
			function(){
				alert("Error!");
			}
		);
		
	};*/

});

app.factory('scheduleService', function ($resource) {
	return $resource('service/schedule/:id');
});
