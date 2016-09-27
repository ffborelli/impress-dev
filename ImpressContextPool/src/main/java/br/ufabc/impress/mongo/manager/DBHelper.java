/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufabc.impress.mongo.manager;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author Fabrizio
 */
abstract class DBHelper implements MongoInterface {

    private static final Mongo mongoDTO = new Mongo();
    private static MongoClient mongoClient;
    private static DB db;

    protected DBHelper(String schema, String DBURL, Integer portNumber) {
        try {
            if (schema != null && !schema.isEmpty()) {
                mongoDTO.setDatabaseSchema(schema);
            }
            if (DBURL != null && !DBURL.isEmpty()) {
                mongoDTO.setDatabaseUrl(DBURL);
            }
            if (portNumber != null) {
                mongoDTO.setDatabasePort(portNumber);
            }

            createInstance(mongoDTO);
        } catch (UnknownHostException un) {
            un.printStackTrace();
        }
    }

    private void createInstance(Mongo mongoDTO) throws UnknownHostException {
        mongoClient = new MongoClient(mongoDTO.getDatabaseUrl(), mongoDTO.getDatabasePort());
        db = mongoClient.getDB(mongoDTO.getDatabaseSchema());
        
    }

    @Override
    public String add(String tableName, String json) {
        if (tableName == null || tableName.equals("") || json == null || json.equals("")) {
            return "501";
        }
        DBCollection table = db.getCollection(tableName);
        DBObject dbObject = (DBObject) JSON.parse(json);
        WriteResult wRes = table.insert(dbObject);
        return ((ObjectId) dbObject.get("_id")).toString();
    }

    @Override
    public String addByMap(String tableName, Map columns) {
        if (tableName == null || tableName.equals("") || columns == null || columns.equals("")) {
            return "501";
        }
        DBCollection table = db.getCollection(tableName);
        BasicDBObject document = new BasicDBObject(columns);
        WriteResult wRes = table.insert(document);
        return ((ObjectId) document.get("_id")).toString();
    }

    @Override
    public String addDefault(String tableName, String json) {
        if (tableName == null || tableName.equals("") || json == null || json.equals("")) {
            return "501";
        }
        DBCollection table = db.getCollection(tableName);
        DBObject dbObject = (DBObject) JSON.parse(json);
        dbObject.put("status", "active");
        dbObject.put("createDate", System.currentTimeMillis() + "");
        dbObject.put("updateDate", System.currentTimeMillis() + "");
        WriteResult wRes = table.insert(dbObject);
        return ((ObjectId) dbObject.get("_id")).toString();
    }

    @Override
    public String addDefaultMap(String tableName, Map columns) {
        if (tableName == null || tableName.equals("") || columns == null || columns.equals("")) {
            return "501";
        }
        columns.put("status", "active");
        columns.put("createDate", System.currentTimeMillis() + "");
        columns.put("updateDate", System.currentTimeMillis() + "");
        DBCollection table = db.getCollection(tableName);
        BasicDBObject document = new BasicDBObject(columns);
        WriteResult wRes = table.insert(document);
        return ((ObjectId) document.get("_id")).toString();
    }

    @Override
    public String getAll(String tableName) {
        if (tableName == null || tableName.equals("")) {
            return "501";
        }
        String row = "";
        DBCollection table = db.getCollection(tableName);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("status", "active");
        DBCursor cursor = table.find(searchQuery);
        if (cursor.size() > 0) {
            JSON json = new JSON();
            row = json.serialize(cursor);
            cursor.close();
            return row;
        } else {
            cursor.close();
            return null;
        }
    }

    @Override
    public String getByKey(String tableName, String _id) {
        if (tableName == null || tableName.equals("") || _id == null || _id.equals("")) {
            return "501";
        }
        String row = null;
        DBCursor cursor = null;
        DBCollection table = db.getCollection(tableName);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(_id));
        cursor = table.find(searchQuery);
        if (cursor.size() > 0) {
            JSON json = new JSON();
            row = json.serialize(cursor);
            cursor.close();
            return row;
        } else {
            cursor.close();
            return null;
        }
    }

    @Override
    public String getByCondition(String tableName, Map condition) {
        if (tableName == null || tableName.equals("") || condition == null || condition.equals("")) {
            return "501";
        }
        String row = "";
        DBCollection table = db.getCollection(tableName);
        BasicDBObject searchQuery = new BasicDBObject(condition);
        BasicDBObject andQuery = new BasicDBObject();
        andQuery.put("$and", searchQuery);
        DBCursor cursor = table.find(searchQuery);
        if (cursor.size() > 0) {
            JSON json = new JSON();
            row = json.serialize(cursor);
            cursor.close();
            return row;
        } else {
            cursor.close();
            return null;
        }
    }

    @Override
    public boolean modify(String tableName, String json, String _id) {
        if (tableName == null || tableName.equals("") || json == null || json.equals("") || _id == null || _id.equals("")) {
            return false;
        }
        DBCollection table = db.getCollection(tableName);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(_id));
        DBObject dbObject = (DBObject) JSON.parse(json);
        dbObject.put("update", System.currentTimeMillis() + "");
        WriteResult result = table.update(searchQuery, dbObject);
        return result.isUpdateOfExisting();
    }

    @Override
    public boolean modifyByCondition(String tableName, String json, Map condition) {
        if (tableName == null || tableName.equals("") || json == null || json.equals("") || condition == null || condition.isEmpty()) {
            return false;
        }
        DBCollection table = db.getCollection(tableName);
        BasicDBObject searchQuery = new BasicDBObject(condition);
        DBObject dbObject = (DBObject) JSON.parse(json);
        dbObject.put("update", System.currentTimeMillis() + "");
        WriteResult result = table.update(searchQuery, dbObject);
        return result.isUpdateOfExisting();
    }

    @Override
    public List<Boolean> modifyByConditionNOtOveride(String tableName, Map condition) {
        return null;
    }

    @Override
    public List<Boolean> modifyNotOveride(String tableName, String json, String _id) {
        if (tableName == null || tableName.equals("") || json == null || json.equals("") || _id == null || _id.equals("")) {
            return null;
        }
        List<Boolean> resList = new ArrayList();
        DBCollection table = db.getCollection(tableName);
        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            DBObject updateDocument = cursor.next();
            DBObject searchQuery = cursor.next();
            DBObject dbObject = (DBObject) JSON.parse(json);
            updateDocument.putAll(dbObject.toMap());
            updateDocument.removeField("_id");
            WriteResult result = table.update(searchQuery, updateDocument);
            resList.add(result.isUpdateOfExisting());
        }
        return resList;
    }

}
