package io.jyotirmay.usermanagementservice.repository;

import io.jyotirmay.usermanagementservice.entity.AddressEntity;
import io.jyotirmay.usermanagementservice.entity.UserEntity;
import io.jyotirmay.usermanagementservice.model.AddressModel;
import io.jyotirmay.usermanagementservice.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRegistrationRepository {

    public static final String SELECT_USER_BY_ID = "select.user.by.id";
    private static final Logger LOGGER = LogManager.getLogger(UserRegistrationRepository.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    private Environment env;

    @Autowired
    public UserRegistrationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate, Environment env) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.env = env;
    }

    public UserEntity insertUser(UserEntity userEntity) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user")
                .usingGeneratedKeyColumns("user_id");

        Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(userEntity));
        userEntity.setUserId(number.longValue());

        return userEntity;
    }

    public AddressEntity insertAddress(AddressEntity addressEntity) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("address")
                .usingGeneratedKeyColumns("address_id");

        Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(addressEntity));
        addressEntity.setAddressId(number.longValue());

        return addressEntity;
    }

    public List<UserModel> getUserDetailsById(Long userId) {

        return namedParameterJdbcTemplate.query(env.getProperty(SELECT_USER_BY_ID),
                new MapSqlParameterSource("userId", userId), new UserRowMapper());
    }

    private class UserRowMapper implements RowMapper<UserModel> {

        @Override
        public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            AddressModel address = new AddressModel();
            address.setAddressId(rs.getLong("address_id"));
            address.setBuilding(rs.getString("building"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setCountry(rs.getString("country"));
            address.setPincode(rs.getString("pincode"));
            address.setCreatedOn(rs.getTimestamp("addr.created_on"));
            address.setUpdatedOn(rs.getTimestamp("addr.updated_on"));

            UserModel user = new UserModel();
            user.setUserId(rs.getLong("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setGender(rs.getString("gender"));
            user.setAddress(address);
            user.setCreatedOn(rs.getTimestamp("usr.created_on"));
            user.setUpdatedOn(rs.getTimestamp("usr.updated_on"));
            return user;
        }
    }
}
