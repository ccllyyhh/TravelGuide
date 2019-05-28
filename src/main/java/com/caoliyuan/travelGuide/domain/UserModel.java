package com.caoliyuan.travelGuide.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    /**
     * 用户id
     */
    private Integer USER_ID;

    /**
     * 用户名
     */
    private String NAME;

    /*
    * 角色
    * */
    private int ROLE;

    /**
     * 手机号
     */
    private String PHONE;

    /**
     * 密码
     */
    private String PASSWORD;

    /*
    * 用户头像
    * */
    private String PHOTO;

    public static RowMapper<UserModel> userMapper = new RowMapper<UserModel>() {
        @Override
        public UserModel mapRow(ResultSet resultSet, int i) throws SQLException {
            UserModel user = new UserModel(
                    resultSet.getInt("USER_ID"),
                    resultSet.getString("NAME"),
                    resultSet.getInt("ROLE"),
                    resultSet.getString("PHONE"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getString("PHOTO")
            );
            return user;
        }
    };


    public int getROLE() {
        return ROLE;
    }

    public void setROLE(int ROLE) {
        this.ROLE = ROLE;
    }

    public UserModel(Integer USER_ID, String NAME, int ROLE, String PHONE, String PASSWORD, String PHOTO) {
        this.USER_ID = USER_ID;
        this.NAME = NAME;
        this.ROLE = ROLE;
        this.PHONE = PHONE;
        this.PASSWORD = PASSWORD;
        this.PHOTO = PHOTO;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

	@Override
	public String toString() {
		return "UserModel [USER_ID=" + USER_ID + ", NAME=" + NAME + ", PHONE=" + PHONE + ", PASSWORD=" + PASSWORD + "]";
	}
    
    
}
