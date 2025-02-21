package org.isnov.training.app.models;

import lombok.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class UserProperty implements RowMapper<UserProperty> {
    private Long userProperTyId;
    private Long userId;
    private String key;
    private String value;

    @Override
    public UserProperty mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserProperty userProperty = new UserProperty();
        userProperty.setUserId(rs.getLong("user_id"));
        userProperty.setUserProperTyId(rs.getLong("user_property_id"));
        userProperty.setKey(rs.getString("key"));
        userProperty.setValue(rs.getString("value"));

        return userProperty;
    }
}
