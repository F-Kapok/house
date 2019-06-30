package com.fans.service.impl;

import com.fans.mapper.AgencyMapper;
import com.fans.model.User;
import com.fans.page.PageParams;
import com.fans.service.interfaces.AgencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AgencyServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-06-30 10:06
 * @Version 1.0
 **/
@Service(value = "agencyService")
public class AgencyServiceImpl implements AgencyService {

    @Resource(type = AgencyMapper.class)
    private AgencyMapper agencyMapper;
    @Value(value = "${file.prefix}")
    private String filePrefix;

    @Override
    public User getAgentDetail(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setType(2);
        List<User> list = agencyMapper.selectAgency(user, PageParams.build(1, 1));
        //添加用户头像
        list.forEach(u -> {
            u.setAvatar(filePrefix + u.getAvatar());
        });
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
