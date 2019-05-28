package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.domain.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
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
        List<UserModel> users =  jdbcTemplate.query(sql,UserModel.userMapper,username);
        if(users.size()==0)return null;
        else return users.get(0);

    }

    @Override
    public UserModel findUserByUserid(int id) {
        String sql = "select * from user where USER_ID = ?";
        List<UserModel> users =  jdbcTemplate.query(sql,UserModel.userMapper,id);
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
        String sql = "insert into user value(NULL,?,?,?,0,?)";
        int rows = jdbcTemplate.update(sql, userModel.getNAME(), userModel.getPHONE(), userModel.getPASSWORD(),userModel.getPHOTO());
        return rows>0;
    }

    @Override
    public List<UserModel> findAllUser(int num, int page) {
        String sql = "select * from user where ROLE = 0 limit ?,?";
        return jdbcTemplate.query(sql, UserModel.userMapper, (page - 1) * num, num);
    }

    @Override
    public int getUserNum() {
        String sql = "select count(*) from user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public boolean deleteUser(int userid) {
        String sql = "delete from user where USER_ID = ?";
        int r = jdbcTemplate.update(sql,userid);
        return r>0;
    }

}
