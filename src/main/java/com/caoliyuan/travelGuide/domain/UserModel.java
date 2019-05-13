package com.caoliyuan.travelGuide.domain;

public class UserModel {
    /**
     * 用户id
     */
    private Integer USER_ID;

    /**
     * 用户名
     */
    private String NAME;

    /**
     * 手机号
     */
    private String PHONE;

    /**
     * 密码
     */
    private String PASSWORD;


    public UserModel() {
    	
    }

    public UserModel(String NAME, String PHONE, String PASSWORD) {
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.PASSWORD = PASSWORD;
    }

    public UserModel(Integer USER_ID, String NAME, String PHONE, String PASSWORD) {
        this.USER_ID = USER_ID;
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.PASSWORD = PASSWORD;
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
