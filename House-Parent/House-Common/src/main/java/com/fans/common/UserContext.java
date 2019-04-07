package com.fans.common;

import com.fans.model.User;

/**
 * @ClassName UserContext
 * @Description:
 * @Author fan
 * @Date 2019-04-07 12:21
 * @Version 1.0
 **/
public class UserContext {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static void remove() {
        userThreadLocal.remove();
    }

    public static User getUser() {
        return userThreadLocal.get();
    }
}
