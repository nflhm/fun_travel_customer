package id.co.fun.travel.customer.repository;

import id.co.fun.travel.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Customer> selectAll() {
        String query = "SELECT * FROM customer";
        return jdbcTemplate.query(query, new ResultSetExtractor<List<Customer>>() {
            @Override
            public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Customer> customerList = new ArrayList<>();
                while (rs.next()) {
                    Customer customer = new Customer(
                            rs.getInt("customer_id"), rs.getString("username"),
                            rs.getString("name"), rs.getString("password"),
                            rs.getString("email"), rs.getInt("delete_flag"));
                    customerList.add(customer);
                }
                return customerList;
            }
        });
    }
    public Customer selectUnique(int id) {
        String query = "SELECT * FROM customer WHERE customer_id=?";
        List<Customer> customerList = jdbcTemplate.query(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        }, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer model = new Customer(
                        rs.getInt("customer_id"), rs.getString("username"),
                        rs.getString("name"), rs.getString("password"),
                        rs.getString("email"), rs.getInt("delete_flag"));
                return model;
            }
        });
        return customerList.get(0);
    }
    public void add(Customer customer) {
        String query = "INSERT INTO customer (username, name, password, email, delete_flag) VALUES (?,?,?,?,0)";
        jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement (PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, customer.getUsername());
                ps.setString(2, customer.getName());
                ps.setString(3, customer.getPassword());
                ps.setString(4, customer.getEmail());
                return ps.execute();
            }
        });
    }
    public void update(Customer customer) {
        String query = "UPDATE customer " +
                "SET username=:username, " +
                "name=:name, password=:password, " +
                "email=:email " +
                "WHERE customer_id=:customerId";
        Map<String, Object> map = new HashMap<>();
        map.put("customer_id", customer.getCustomerId());
        map.put("username", customer.getUsername());
        map.put("name", customer.getName());
        map.put("password", customer.getPassword());
        map.put("email", customer.getEmail());

        namedParameterJdbcTemplate.execute(query, map, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                return ps.execute();
            }
        });
    }
    public void delete(int id) {
        String query = "UPDATE customer " +
                "SET delete_flag=1 " +
                "WHERE customer_id=?";
        jdbcTemplate.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        });
    }
}
