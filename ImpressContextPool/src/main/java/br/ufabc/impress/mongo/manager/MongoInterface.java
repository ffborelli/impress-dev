/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufabc.impress.mongo.manager;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Fabrizio
 */
 public interface MongoInterface {
     String add(String tableName,String json);
     String addByMap(String tableName,Map column);
     String addDefault(String tableName,String json);
     String addDefaultMap(String tableName,Map column);
     String getAll(String tableName);
     String getByKey(String tableName,String _id);
     String getByCondition(String tableName,Map condition);
     boolean modify(String tableName,String json,String _id);
     boolean modifyByCondition(String tableName,String json,Map condition);
     List<Boolean> modifyNotOveride(String tableName,String json,String _id);
     List<Boolean> modifyByConditionNOtOveride(String tableName,Map condition);
     
}
