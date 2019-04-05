package com.fans.service.interfaces;

import com.fans.model.User;

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
}
