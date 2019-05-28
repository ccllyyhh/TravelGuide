package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.domain.UserModel;

import java.util.List;

public interface UserService {

    public UserModel findUserByUsername(String username);
    public UserModel findUserByUserid(int id);
    public boolean addUser(UserModel userModel);
    public List<UserModel> findAllUser(int num,int page);
    public int getUserNum();
    public boolean deleteUser(int userid);
    //public UserModel updateUserByUsername(String username);


}
