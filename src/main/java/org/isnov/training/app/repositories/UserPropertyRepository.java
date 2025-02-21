package org.isnov.training.app.repositories;

import org.isnov.training.app.models.UserProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserPropertyRepository {
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public List<UserProperty> getAllUserProperties(){
        return simpleJdbcInsert.getJdbcTemplate().query("SELECT * FROM user_property", new UserProperty());
    }

    public List<UserProperty> getUserPropertiesByUser(long userId){
        return simpleJdbcInsert.getJdbcTemplate().query("SELECT * FROM user_property WHERE user_id = "+userId, new UserProperty());
    }

    public List<UserProperty> getAllUserPropertiesPage(int page, int size){
        return simpleJdbcInsert.getJdbcTemplate().query("SELECT * FROM user_property LIMIT "+size+" OFFSET "+page+" ORDER BY user_property_id", new UserProperty());
    }

    public UserProperty getUsePropertyByID(long userPropertyId){
        List<UserProperty> tmpList = simpleJdbcInsert.getJdbcTemplate().query("SELECT * FROM user_property WHERE user_property_id = "+userPropertyId, new UserProperty());
        if(!tmpList.isEmpty())
            return tmpList.get(0);

        return null;
    }

    public UserProperty insertUserProperty(UserProperty userProperty){
        StringBuilder sqlInsert = new StringBuilder()
                .append(" INSERT INTO user_property (user_property_id, user_id, key, value)")
                .append(" VALUES ((SELECT COALESCE(MAX(user_property_id), 0)+1 FROM user_property), :userid, :key, :value)");
        Map<String, Object> params = new HashMap<>();
        params.put("userid", userProperty.getUserId());
        params.put("key", userProperty.getKey());
        params.put("value", userProperty.getValue());

        for (String k : params.keySet()){
            Object v = params.get(k);
            if(v instanceof String){
                sqlInsert = new StringBuilder(sqlInsert.toString().replaceAll(":"+k, "'"+v+"'"));
            }else{
                sqlInsert = new StringBuilder(sqlInsert.toString().replaceAll(":"+k, ""+v));
            }
        }


       int numberInserted = simpleJdbcInsert.getJdbcTemplate().update(sqlInsert.toString());
       if(numberInserted > 0) {
           List<UserProperty> tmpList = simpleJdbcInsert.getJdbcTemplate().query(
                   //"SELECT * FROM user_property WHERE user_property_id = (SELECT MAX(user_property_id) FROM user_property)",
                   "SELECT * FROM user_property WHERE user_id ="+userProperty.getUserId()+" AND Key = '"+userProperty.getKey()+"'",

                   new UserProperty()
           );
           if(!tmpList.isEmpty())
               return tmpList.get(0);
       }

       return null;
    }
}
