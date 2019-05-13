package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.domain.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    //用户名找用户
    public UserModel findUserByUsername(String username){
        String sql = "select * from user where NAME = ?";
        List<UserModel> users =  jdbcTemplate.query(sql,new RowMapper<UserModel>() {
            @Override
            public UserModel mapRow(ResultSet resultSet, int i) throws SQLException {
                UserModel user = new UserModel(
                        resultSet.getInt("USER_ID"),
                        resultSet.getString("NAME"),
                        resultSet.getString("PHONE"),
                        resultSet.getString("PASSWORD")
                );
                return user;
            }
        },username);
        if(users.size()==0)return null;
        else return users.get(0);

    }

    /**
     * 注册
     * @param userModel 要新增的用户
     * @return 是否成功
     * */
    @Override
    public boolean addUser(UserModel userModel) {
        String sql = "insert into user value(NULL,?,?,?)";
        int rows = jdbcTemplate.update(sql, userModel.getNAME(), userModel.getPHONE(), userModel.getPASSWORD());
        return rows>0;
    }
}
