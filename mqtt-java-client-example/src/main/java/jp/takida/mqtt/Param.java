/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.takida.mqtt;

public class Param {

    // Metricas 
    public static String name_experiment = "Experiment 87";
    public static final int number_of_devices = 600; // 50 - 200 - 600
    
    public static final int number_of_topics = 1;
    public static final int number_of_events = 3; // 100
    public static final int qos = 0; // 0 - 1 - 2
    public static final int maxTimeBetweenEvents = 3000; // 120000 - 30000 - 5000 ms
    public static final int minTimeBetweenEvents = 1000; // 60000 - 10000 - 1000 ms
    public static final int number_of_messages = 3;
    public static final String path = "pub";


    public static final String address = "tcp://localhost:1884";
    public static final String topic = "impress/demo0";
    public static final String topic_publish = "impress/action";

    public static final String module_fusion_status = "fusion status";
    public static final String module_fusion_presence = "fusion presence";
    public static final String module_fusion_temperature = "fusion temperature";
    public static final String module_rule_status = "rule status";
    public static final String module_rule_presence = "rule presence";
    public static final String module_rule_temperature = "rule temperature";

    public static final int sensor_android = 13;
    public static final int sensor_avg_temperature = 15;
    public static final int sensor_sum_presence = 16;

    public static final boolean is_eval = true;

}
