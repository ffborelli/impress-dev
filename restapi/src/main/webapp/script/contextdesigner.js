var app = angular.module('contextdesigner', [ 'ngResource', 'ui.bootstrap', 'impressApp' ]);

// Create a controller with name placesListController to bind to the grid
// section.
app.controller('contextDesignerController', function($scope, $rootScope, $http, contextCountService) {
	
	$scope.addNodesDiv = true;
	$scope.addSensorDiv = false;
	$scope.addFusionDiv = false;
	$scope.addRuleDiv = false;
	$scope.addActuatorDiv = false;
	
	$scope.showSensors = function(){
		$scope.addNodesDiv = false;
		$scope.addSensorDiv = true;
	};
	
	$scope.showFusions = function(){
		$scope.addNodesDiv = false;
		$scope.addFusionDiv = true;
	};
	
	$scope.showRules = function(){
		$scope.addNodesDiv = false;
		$scope.addRuleDiv = true;
	};
	
	$scope.showActuators = function(){
		$scope.addNodesDiv = false;
		$scope.addActuatorDiv = true;
	};
	
	$scope.cancel = function(){
		$scope.addSensorDiv = false;
		$scope.addFusionDiv = false;
		$scope.addRuleDiv = false;
		$scope.addActuatorDiv = false;
		$scope.addNodesDiv = true;
	};
	
    	var nodes, edges, network;
		var colors = [ "#E6E6FA", "#DCDCDC", "#ADD8E2", "#FA8072" ];
		var entities = [ "Sensor", "Fusion", "Rule", "Actuator" ];
		var id_global_node = 1;
		var id_global_edge = 1;
		var count_rule = 1;
		var count_sensor = 1;
		var count_fusion = 1;
		var count_actuator = 1;
		var relation = [];
		
		var selected = null;
		
		$scope.types = ['Sensor', 'Fusion', 'Rules', 'Actuator'];

		// convenience method to stringify a JSON object
		$scope.toJSON = function(obj) {
			return JSON.stringify(obj, null, 4);
		}
		
		 $scope.rule_edges = [];
		 $scope.fusion_edges = [];
		 $scope.sensor_edges = [];
		 $scope.actuator_edges = [];
		
		
		//get rules
	    $http({ 
	    	method: 'GET',
	    	url: 'service/rule/all'
	    }).
	    success(function(data) {
	  	
	        $scope.rules = angular.fromJson(data);
	    });
	    
		//get fusion
	    $http({ 
	    	method: 'GET',
	    	url: 'service/fusion/all'
	    }).
	    success(function(data) {
	  	
	        $scope.fusions = angular.fromJson(data);
	    });
	    
		//get sensors
	    $http({ 
	    	method: 'GET',
	    	url: 'service/resource/sensors'
	    }).
	    success(function(data) {
	  	
	        $scope.sensors = angular.fromJson(data);
	    });
	    
		//get actuators
	    $http({ 
	    	method: 'GET',
	    	url: 'service/resource/actuators'
	    }).
	    success(function(data) {
	  	
	        $scope.actuators = angular.fromJson(data);
	    });
	    
	    $scope.addSensor = function(){
			
	    	var node_id;
			var node_label;
			var color;
			
			try {
		    	nodes.add({id : id_global_node,
						   label : 'Sensor ' + $scope.sensor.description + ' ' + id_global_node,
							color : {background : colors[0]},
							title : 'I am a Sensor!'

					      });
		    	
		    	var r = {local:$scope.sensor.id, global: id_global_node, type:"SENSOR" };
		    	relation.push(r);
		    	
			} catch (err) {
				alert(err);
			}
			
			$scope.sensor_edges.push({ name: "Sensor " + $scope.sensor.description + ' ' + id_global_node , local:$scope.sensor.id, global: id_global_node, type:"SENSOR" });
			id_global_node++;
			count_sensor++;
			
			//reset select box
			$scope.sensor = null;
			
			$scope.addSensorDiv = false;
			$scope.addNodesDiv = true;
	    	
		};
		
		$scope.addFusion = function(){
			
			var node_id;
			var node_label;
			var color;
			
			try {
		    	nodes.add({id : id_global_node,
						   label : 'Fusion ' + $scope.fusion.fusionName + ' ' + id_global_node,
							color : {background : colors[1]},
							title : 'I am a Fusion!'

					      });
		    	
		    	var r = {local:$scope.fusion.id, global: id_global_node, type:"FUSION" };
		    	relation.push(r);
		    
			} catch (err) {
				alert(err);
			}
			
			$scope.fusion_edges.push({ name: "Fusion " + $scope.fusion.fusionName + ' ' + id_global_node, local:$scope.fusion.id, global: id_global_node, type:"FUSION" });
			id_global_node++;
			count_fusion++;
			
			//reset select box
			$scope.fusion = null;
			
			$scope.addFusionDiv = false;
			$scope.addNodesDiv = true;
			
		};
		
		$scope.addRule = function(){
			
			var node_id;
			var node_label;
			var color;
			
			try {
		    	nodes.add({id : id_global_node,
						   label : 'Rule ' + $scope.rule.ruleName +  ' ' + id_global_node,
							color : {background : colors[2]},
							title : 'I am a Rule!'

					      });
		    	
		    	var r = {local:$scope.rule.id, global: id_global_node, type:'RULE'};
		    	relation.push(r);
		    	
			} catch (err) {
				alert(err);
			}
			
			$scope.rule_edges.push({ name: "Rule " + $scope.rule.ruleName +  ' ' + id_global_node, local: $scope.rule.id, global: id_global_node, type:"RULE" });
			id_global_node++;
			count_rule++;
			
			//reset select box
			$scope.rule = null;
			
			$scope.addRuleDiv = false;
			$scope.addNodesDiv = true;
			
		};
		
		$scope.addActuator = function(){
			
			var node_id;
			var node_label;
			var color;
			
			try {
		    	nodes.add({id : id_global_node,
						   label : 'Actuator ' + $scope.actuator.description + ' ' + id_global_node,
							color : {background : colors[3]},
							title : 'I am a actuator!'

					      });
		    	
		    	var r = {local:$scope.actuator.id, global: id_global_node, type:"ACTUATOR" };
		    	relation.push(r);

			} catch (err) {
				alert(err);
			}
			
			$scope.actuator_edges.push({ name: "Actuator " + $scope.actuator.description + ' ' + id_global_node, local:$scope.actuator.id , global: id_global_node, type:"ACTUATOR"  });
			id_global_node++;
			count_actuator++;
			
			//reset select box
			$scope.actuator = null;
			
			$scope.addActuatorDiv = false;
			$scope.addNodesDiv = true;
			
		};
		
		$scope.removeNode = function() {
			try {
				nodes.remove({
					id : selected.nodes[0],
				});
			} catch (err) {
				alert(err);
			}
		}
		
		$scope.Connect = function(){
			
			var sensorId = null;
			var fusionId = [];
			var ruleId = null;
			var actuatorId = null;
			
			if(network.getSelectedNodes().length == 0){
				alert('There\'s not a selected node.');
			}else if(network.getSelectedNodes().length == 1){
				alert('Select one more node to connect with this.');
			}else if(network.getSelectedNodes().length > 2){
				alert('Select just two nodes to be connected.');
			}else{
				
				for(var i=0; i<relation.length; i++){
					
					if(relation[i].global == network.getSelectedNodes()[0] ||
							relation[i].global == network.getSelectedNodes()[1]){
						
						if(relation[i].type == 'SENSOR'){
							sensorId = relation[i].global;
						}else if(relation[i].type == 'FUSION'){
							if(fusionId.length == 0){
								fusionId[0] = relation[i].global;
							}else{
								fusionId[1] = relation[i].global;
							}
						}else if(relation[i].type == 'RULE'){
							ruleId = relation[i].global;
						}else if(relation[i].type == 'ACTUATOR'){
							actuatorId = relation[i].global;
						}
						
					}
					
				}
				
				if(sensorId != null && fusionId.length == 1){
					addEdgeVis(sensorId, fusionId[0]);
				}else if(fusionId.length == 1 && ruleId != null){
					addEdgeVis(fusionId[0], ruleId);
				}else if(ruleId != null && actuatorId != null){
					addEdgeVis(ruleId, actuatorId);
				}else if(fusionId.length == 2){
					addEdgeVis(fusionId[0], fusionId[1]);
				}else{
					alert('Is not possible to connect this nodes.');
				}
				
			}
			
		};
		
		$scope.clean = function(){
			
			for(var i=1; i<=id_global_edge; i++){
				try {
					edges.remove({
						id: i
					});
		 	    } catch (err) {
		 	    	alert(err);
				}
			}
			
			for(var i=1; i<=id_global_node; i++){
				try {
					nodes.remove({
						id: i
					});
				} catch (err) {
					alert(err);
				}
			}
			
		};
		
		$scope.addEdge = function() {
			
					
			if ($scope.sensor_edge != null && $scope.fusion_edge != null){
				addEdgeVis($scope.sensor_edge.global, $scope.fusion_edge.global );

			}
			
			if ($scope.fusion_edge != null && $scope.rule_edge != null){
				addEdgeVis($scope.fusion_edge.global, $scope.rule_edge.global );
 
			}
			
			if ($scope.rule_edge != null && $scope.actuator_edge != null){
				addEdgeVis($scope.rule_edge.global, $scope.actuator_edge.global );

			}
			
			$scope.sensor_edge = null;
			$scope.fusion_edge = null; 
			$scope.rule_edge = null;
			$scope.actuator_edge = null;
			
		}
		
		function addEdgeVis(id_from, id_to){
			
			try {
				edges.add({
							id : id_global_edge,
							from : id_from ,
							to :  id_to,
							arrows : 'to'
					});
				
				id_global_edge++;
				
			} catch (err) {
				alert(err);
			}
		}
		
		function addNodeVis(text_node, color_node){
			
   			try {
   				nodes.add({id : id_global_node,
   					label : text_node,
   					color : {background : color_node},
   					title : 'I am a node!'

   				});

   				id_global_node++;
	    		    	
   			} catch (err) {
   				alert(err);
   			}
		}

		$scope.removeEdge = function() {
		 	    
				try {
					edges.remove({
					id : selected.edges[0]
					});
		 	    } catch (err) {
		 	    	alert(err);
				}
		}
			
			function findLocalID(global){
				var r = [];
				
				for (j = 0; j < relation.length; j++) {
					if (relation[j].global == global){
						
						r.push(relation[j].local);
						r.push(relation[j].type);
						break;
					} 
				}
				return r;
			}
			
			$scope.save = function ($scope, $http){
				
				//console.log(edges);
				//console.log(relation);
				
				var sensors = '';
				var fusions = '';
				var rules = '';
				var actuators = '';
				
				//At first we have to find all sensors node -- the order is real -- virtual
				
				for (i = 1; i <= edges.length; i++) {

					var f = findLocalID(edges._data[i].from);
					var t = findLocalID(edges._data[i].to);
					
					if(t[1] == 'SENSOR'){
						sensors += edges._data[i].from + '*' + f[0]  + ',' + f[1] + ':' +edges._data[i].to + '*' + t[0] + ',' + t[1] + ';'
					}
					else if(t[1] == 'RULE'){
						rules += edges._data[i].from + '*' + f[0] + ',' + f[1] + ':'  +edges._data[i].to + '*' + t[0] + ',' + t[1] + ';' 
					}
					else if(t[1] == 'FUSION'){
						fusions += edges._data[i].from + '*' + f[0] + ',' + f[1] + ':' +edges._data[i].to + '*' + t[0] + ',' + t[1] + ';'
					}
					else if(t[1] == 'ACTUATOR'){
						actuators += edges._data[i].from + '*' + f[0] + ',' + f[1] + ':' +edges._data[i].to + '*' + t[0] + ',' + t[1] + ';'
					}
					else{
						
					}					
				}
				
				var rel = sensors+fusions+rules+actuators;
				
				sendData($scope, rel);
				
				
			}
			
			function sendData($scope, rel) {
			    $http({
			        url: 'service/contextcount/',
			        method: "POST",
			        data: {contextSequence : rel,
   						   contextCount : 0,
						   contextRegistered : 0
			        	  }
			    })
			    .then(function(response) {
			            // success
			    }, 
			    function(response) { // optional
			            // failed
			    });
			}
			
			function checkRepetition (entity){
				
				var checkReturn = false;
				
				for (p = 0; p < relation.length; p++) {
					if (entity.type == relation[p].type && entity.local == relation[p].local  ){
						//break;
						checkReturn = entity.local;
						break;
					}
				}
				
			
				return checkReturn;				
				
			}
			
			$scope.apiSearch = function (){
				//alert ('a');
				
				if ($scope.searchModelID != null || $scope.searchModelID != ''){
				
		        var listContextArgs = {
		                id: $scope.searchModelID
		        };
		            //console.log($scope.search.value);
		        contextCountService.get(listContextArgs, function (data) {
		            	
		           	//alert('a');
		           	var graphStr = data;
		           	
		           	var graph = graphStr.contextSequence.split(";");
		           	
		           	//if (typeof graph[0] == "undefined") {
		           		
		           	//}
		           	
		           	for (a = 0; a < graph.length - 1; a++ ){
		           	
		           	//var nodesJS = graph[a].split(":");
		           	
		           	// var real;
		           	// var virtual;
		           		var tmp = graph[a].split(":");
		           		//for (b = 0; b < nodesJS.length; b++){
		           		
		           		
		           			var tmp1 = tmp[0].split("*");
		           			var tmp2 = tmp[1].split("*");
		           		
		           			var tmp3 = tmp1[1].split(",");
		           			
		           			var realFrom = tmp3[0];
		           			var virtualFrom = tmp1[0];
		           			var typeFrom = tmp3[1];
		           		
		           		

		           			var tmp4 = tmp2[1].split(",");
		           		
		           			var realTo = tmp4[0];
		           			var virtualTo = tmp2[0];
		           			var typeTo = tmp4[1];
		           			
		           			var id_tmp_to;
		           			var id_tmp_from;
		           		
		           			// 	var r = {local:$scope.rule.id, global:
		           			// id_global_node, type:'RULE'};
		           			// relation.push(r);
		           		
		           			var colorFrom, colorTo, textFrom, textTo;
		           		// color FROM
		           			if (typeFrom == "SENSOR"){
		           				colorFrom = colors[0];
		           				textFrom = "Sensor ";
		           			
		           			
		           				var r = {local:realFrom, global: id_global_node, type:'SENSOR'};
					    	
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.sensor_edges.push({ name: "Sensor " + realFrom, local: realFrom, global: id_global_node, type:'SENSOR' });
		           					id_tmp_from = id_global_node;
		           					//id_global_node++;
		           					addNodeVis(textFrom + realFrom, colorFrom);
		           					count_sensor++;
		           				}
		           				else{
		           					id_tmp_from = checkRepetition (r);
		           				}
		           			
		           			}
		           			else if (typeFrom == "FUSION"){
		           				colorFrom = colors[1];
		           				textFrom = "Fusion ";
		           				
		           				var r = {local:realFrom, global: id_global_node, type:'FUSION'};
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.fusion_edges.push({ name: "Fusion " + realFrom, local: realFrom, global: id_global_node, type:'FUSION' });
		           					id_tmp_from = id_global_node;
		           					//id_global_node++;
		           					addNodeVis(textFrom + realFrom, colorFrom);
		           					count_fusion++;
		           				}
		           				else{
		           					id_tmp_from = checkRepetition (r);
		           				}
		           			}
		           			else if(typeFrom == "RULE"){
		           				colorFrom = colors[2];
		           				textFrom = "Rule ";
		           			
		           				var r = {local:realFrom, global: id_global_node, type:'RULE'};
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.rule_edges.push({ name: "Rule " + realFrom, local: realFrom, global: id_global_node, type:"RULE" });
		           					id_tmp_from = id_global_node;
		           					//id_global_node++;
		           					addNodeVis(textFrom + realFrom, colorFrom);
		           					count_rule++;
		           				}
		           				else{
		           					id_tmp_from = checkRepetition (r);
		           				}
		           			}
		           			else if(typeFrom == "ACTUATOR"){
		           				colorFrom = colors[3];
		           				textFrom = "Actuator ";
		           			
		           				var r = {local:realFrom, global: id_global_node, type:'ACTUATOR'};
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.actuator_edges.push({ name: "Actuator " + realFrom, local: realFrom, global: id_global_node, type:'ACTUATOR'});
		           					id_tmp_from = id_global_node;
		           					//id_global_node++;
		           					addNodeVis(textFrom + realFrom, colorFrom);
		           					count_actuator++;
		           				}
		           				else{
		           					id_tmp_from = checkRepetition (r);
		           				}
		           			}
		           			else{}
		           		
		           			// 	color TO
		           			if (typeTo == "SENSOR"){
		           				colorTo = colors[0];
		           				textTo = "Sensor ";
		           			
		           				var r = {local:realTo, global: id_global_node, type:'SENSOR'};
					    	
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.sensor_edges.push({ name: "Sensor " + realTo, local: realTo, global: id_global_node, type:'SENSOR' });
		           					id_tmp_to = id_global_node;
		           					//id_global_node++;
		           					addNodeVis(textTo + realTo, colorTo);
		           					count_sensor++;
		           				}
		           				else{
		           					id_tmp_to = checkRepetition (r);
		           				}
		           			}
		           			else if (typeTo == "FUSION"){
		           				colorTo = colors[1];
		           				textTo = "Fusion ";
		           			
		           				var r = {local:realTo, global: id_global_node, type:'FUSION'};
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.fusion_edges.push({ name: "Fusion " + realTo, local: realTo, global: id_global_node, type:'FUSION' });
		           					id_tmp_to = id_global_node;
		           					//id_global_node++;
		           					addNodeVis(textTo + realTo, colorTo);
		           					count_fusion++;
		           				}
		           				else{
		           					id_tmp_to = checkRepetition (r);
		           				}
		           			}
		           			else if(typeTo == "RULE"){
		           				colorTo = colors[2];
		           				textTo = "Rule ";
		           			
		           				var r = {local:realTo, global: id_global_node, type:'RULE'};
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.rule_edges.push({ name: "Rule " + realTo, local: realTo, global: id_global_node, type:"RULE" });
		           					id_tmp_to = id_global_node;
		           					//id_global_node++;
		           					
		           					addNodeVis(textTo + realTo, colorTo);
		           					
		           					count_rule++;
		           				}
		           				else{
		           					id_tmp_to = checkRepetition (r);
		           				}
		           			}
		           			else if(typeTo == "ACTUATOR"){
		           				colorTo = colors[3];
		           				textTo = "Actuator ";
		           			
		           				var r = {local:realTo, global: id_global_node, type:'ACTUATOR'};
		           				if (checkRepetition (r) == false ){
		           					relation.push(r);
					    		
		           					$scope.actuator_edges.push({ name: "Actuator " + realTo, local: realTo, global: id_global_node, type:'ACTUATOR'});
		           					id_tmp_to = id_global_node;
		           					//id_global_node++;
		           					
		           					addNodeVis(textTo + realTo, colorTo);
		           					
		           					count_actuator++;
		           				}
		           				else{
		           					id_tmp_to = checkRepetition (r);
		           				}
		           			}
		           			else{}
		           			
		           			addEdgeVis(id_tmp_from, id_tmp_to);
		           		

		           		}
		           	
		           	});
					}
				//}
		            
			}
			
			

			$scope.draw = function() {
				// create an array with nodes
				nodes = new vis.DataSet();

				// create an array with edges
				edges = new vis.DataSet();
				
				// create a network
				var container = document.getElementById('network');
				var data = {
					nodes : nodes,
					edges : edges
				};
				// var options = {};
				var options = {
					interaction: {
						navigationButtons: true,
						keyboard: true,
						multiselect: true
					},
					edges: {
						smooth: {
							enabled: false
						}
					},
					nodes: {
						physics: false
					}
				};

				network = new vis.Network(container, data, options);

				network.on("select", function(params) {
					console.log('select Event:', params);
					selected = params;
				});

			}
		});

//Service that provides places operations
app.factory('sensorService', function ($resource) {
    return $resource('service/resource/:id');
});

//Service that provides sensor operations
app.factory('actuatorService', function ($resource) {
    return $resource('service/resource/:id');
});

//Service that provides fusion operations
app.factory('fusionService', function ($resource) {
    return $resource('service/fusion/:id');
});

//Service that provides rules operations
app.factory('rulesService', function ($resource) {
    return $resource('service/rules/:id');
});

//Service that provides contextCount operations
app.factory('contextCountService', function ($resource) {
    return $resource('service/contextcount/:id');
});