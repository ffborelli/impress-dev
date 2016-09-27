package br.ufabc.impress.mongo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;



public class MongoUtil {

	private final static Logger logger = LoggerFactory
			.getLogger(MongoUtil.class);

	private static final int port = 27017;
	private static final String host = "localhost";
	private static MongoClient mongoClient = null;
	private static MongoDatabase db;

	public static MongoClient getMongo() {
		if (mongoClient == null) {
			try {
				mongoClient = new MongoClient(host, port);
				logger.debug("New Mongo created with [" + host + "] and ["
						+ port + "]");
			} catch (MongoException e) {
				logger.error(e.getMessage());
			}
		}
		return mongoClient;
	}
	
	public MongoDatabase getDataBase(String database){
		
		db = getMongo().getDatabase(database);
		return db;
	}
}
