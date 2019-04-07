package com.fans.service.interfaces;

import com.fans.model.User;

import java.util.List;

/**
 * @InterfaceName UserService
 * @Description:
 * @Author fan
 * @Date 2019-04-04 13:56
 * @Version 1.0
 **/
public interface UserService {

    boolean addAccount(User user);

    boolean enable(String key);

    User auth(String username, String password);

    void updateUser(User updateUser, String email);

    List<User> getUserByQuery(User query);
}
