package com.caoliyuan.travelGuide.service;

import com.caoliyuan.travelGuide.domain.UserModel;

public interface UserService {

    public UserModel findUserByUsername(String username);
    public boolean addUser(UserModel userModel);
    //public UserModel updateUserByUsername(String username);


}
