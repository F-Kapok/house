package com.fans.service.impl;

import com.fans.mapper.UserMapper;
import com.fans.model.User;
import com.fans.service.interfaces.MailService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MailServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-04-04 15:34
 * @Version 1.0
 **/
@Service(value = "mailService")
public class MailServiceImpl implements MailService {
    @Resource(type = JavaMailSender.class)
    private JavaMailSender mailSender;

    @Resource(type = UserMapper.class)
    private UserMapper userMapper;

    private final Cache<String, String> registerCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(15, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    userMapper.deleteByEmail(notification.getValue());
                }
            }).build();

    @Value(value = "${domain.name}")
    private String domainName;

    @Value(value = "${spring.mail.username}")
    private String from;

    @Override
    public void sendMail(String title, String url, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setText(url);
        mailSender.send(message);
    }

    @Override
    @Async
    public void registerNotify(String email) {
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey, email);
        String url = "http://" + domainName + "/accounts/verify?key=" + randomKey;
        sendMail("房产平台激活邮件", url, email);
    }

    @Override
    public boolean enable(String key) {
        String email = registerCache.getIfPresent(key);
        if (StringUtils.isBlank(email)) {
            return false;
        }
        User user = new User();
        user.setEmail(email);
        user.setEnable(1);
        userMapper.updateByEmailSelective(user);
        registerCache.invalidate(key);
        return true;
    }
}
