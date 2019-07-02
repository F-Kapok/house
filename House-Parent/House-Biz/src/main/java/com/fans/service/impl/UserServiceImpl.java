package com.fans.service.impl;

import com.fans.mapper.UserMapper;
import com.fans.model.User;
import com.fans.service.interfaces.MailService;
import com.fans.service.interfaces.UserService;
import com.fans.utils.BeanHelper;
import com.fans.utils.FileUtils;
import com.fans.utils.HashUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-04-04 13:56
 * @Version 1.0
 **/
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource(type = UserMapper.class)
    private UserMapper userMapper;
    @Resource(name = "fileUtils")
    private FileUtils fileUtils;
    @Resource(name = "mailService")
    private MailService mailService;
    @Value(value = "${file.prefix}")
    private String filePrefix;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addAccount(User user) {
        user.setPasswd(HashUtils.encryPassword(user.getPasswd()));
        List<String> imgPathList = fileUtils.getImgPath(Lists.newArrayList(user.getAvatarFile()));
        if (!imgPathList.isEmpty()) {
            user.setAvatar(imgPathList.get(0));
        }
        BeanHelper.setDefaultProp(user, User.class);
        BeanHelper.onInsert(user);
        user.setEnable(0);
        userMapper.insert(user);
        mailService.registerNotify(user.getEmail());
        return true;
    }

    @Override
    public boolean enable(String key) {
        return mailService.enable(key);
    }

    @Override
    public User auth(String username, String password) {
        User user = new User();
        user.setEmail(username);
        user.setPasswd(HashUtils.encryPassword(password));
        user.setEnable(1);
        List<User> userList = getUserByQuery(user);
        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(User updateUser, String email) {
        updateUser.setEmail(email);
        BeanHelper.onUpdate(updateUser);
        userMapper.update(updateUser);
    }

    @Override
    public List<User> getUserByQuery(User user) {
        List<User> userList = userMapper.query(user);
        userList.forEach(u -> u.setAvatar(filePrefix + u.getAvatar()));
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        User queryUser = new User();
        queryUser.setId(id);
        List<User> users = getUserByQuery(queryUser);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

}
