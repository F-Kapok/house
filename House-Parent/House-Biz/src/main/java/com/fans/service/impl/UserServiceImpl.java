package com.fans.service.impl;

import com.fans.mapper.UserMapper;
import com.fans.model.User;
import com.fans.service.interfaces.MailService;
import com.fans.service.interfaces.UserService;
import com.fans.utils.BeanHelper;
import com.fans.utils.FileUtils;
import com.fans.utils.HashUtils;
import com.google.common.collect.Lists;
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

}
