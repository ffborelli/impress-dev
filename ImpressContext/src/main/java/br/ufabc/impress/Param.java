package br.ufabc.impress;

public class Param {
	
	//public static final String address = "tcp://impressufabc.cloudapp.net:1883";
	//public static final String address = "tcp://localhost:1883";
	//public static final String topic = "impress/demo";
	//public static final String topic_publish = "impress/action";
	
	//public static int number_of_topics = 1;
	
	public static int number_of_devices = 10;
	
	//public static final String module_fusion_status = "fusion status";
	//public static final String module_fusion_presence = "fusion presence";
	//public static final String module_fusion_temperature = "fusion temperature";
	//public static final String module_rule_status = "rule status";
	//public static final String module_rule_presence = "rule presence";
	//public static final String module_rule_temperature = "rule temperature";
	//public static String name_experiment = "Experiment 1";
	
	public static final int sensor_android = 13;
	public static final int sensor_avg_temperature = 12;
	public static final int sensor_sum_presence = 13;
	
	public static final int fusion_presence = 2;
	public static final int fusion_temperature = 1;
	
	public static final int threshold_distance1 = 150;
	public static final int threshold_distance2 = 150;
	public static final int threshold_distance3 = 150;
	public static final int threshold_distance4 = 150;
	
	public static final boolean is_eval = false;
	
	//public static final String address_rai_rest = "http://130.192.86.221:8888/";
	public static final String address_rai_rest = "http://192.168.1.200:8888/";
	public static final String address_rai_mqtt = "tcp://130.192.85.32";
	public static final String address_ufabc_mqtt = "tcp://localhost:1883";
	//public static final String address_grm = "ws://130.188.58.101:10011/";
	public static final String address_grm = "ws://192.168.1.222:10011/";
	
	public static final int sensor_light = 1;
	public static final int sensor_air = 2;
	public static final int sensor_traffic = 3;
	public static final int sensor_noise = 4;
	public static final int sensor_structural = 5;
	public static final int sensor_waste = 6;
	
	public static boolean grm = false;
	public static boolean rai = false;
	public static boolean tracker = false;
	public static boolean esper = true;
	public static boolean mqtt_ufabc = false;
	public static boolean mqtt_ivan = false;
	
	public static String address_db = "jdbc:postgresql://localhost/impress_recife_test?user=postgres&password=postgres";

}
