/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.takida.mqtt;

/**
 *
 * @author fabrizio
 */
public class Param {
    
    	//public static final String address = "tcp://impressufabc.cloudapp.net:1883";
	public static final String address = "tcp://localhost:1883";
	public static final String topic = "impress/demo";
	public static final String topic_publish = "impress/action";
	public static final int number_of_topics = 1;
        public static int number_of_devices = 1023; 

	public static final String module_fusion_status = "fusion status";
	public static final String module_fusion_presence = "fusion presence";
	public static final String module_fusion_temperature = "fusion temperature";
	public static final String module_rule_status = "rule status";
	public static final String module_rule_presence = "rule presence";
	public static final String module_rule_temperature = "rule temperature";
	public static final String name_experiment = "Experiment 01";
	
	public static final int sensor_android = 13;
	public static final int sensor_avg_temperature = 15;
	public static final int sensor_sum_presence = 16;
	
	public static final boolean is_eval = true;
    
}
