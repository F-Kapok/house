package com.fans.service.impl;

import com.fans.mapper.CommunityMapper;
import com.fans.mapper.HouseMapper;
import com.fans.model.*;
import com.fans.page.PageData;
import com.fans.page.PageParams;
import com.fans.service.interfaces.AgencyService;
import com.fans.service.interfaces.HouseService;
import com.fans.service.interfaces.MailService;
import com.fans.utils.BeanHelper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName HouseServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-06-29 23:43
 * @Version 1.0
 **/
@Service(value = "houseService")
public class HouseServiceImpl implements HouseService {
    @Value(value = "${file.prefix}")
    private String filePrefix;
    @Resource(type = HouseMapper.class)
    private HouseMapper houseMapper;
    @Resource(type = CommunityMapper.class)
    private CommunityMapper communityMapper;
    @Resource(name = "agencyService")
    private AgencyService agencyService;
    @Resource(name = "mailService")
    private MailService mailService;

    @Override
    public PageData<House> queryHouse(House query, PageParams pageParams) {
        List<House> houses;
        if (!Strings.isNullOrEmpty(query.getName())) {
            Community community = new Community();
            community.setName(query.getName());
            List<Community> communities = communityMapper.selectCommunity(community);
            if (!communities.isEmpty()) {
                query.setCommunityId(communities.get(0).getId());
            }
        }
        houses = queryAndSetImage(query, pageParams);
        Long count = houseMapper.selectPageCount(query);
        return PageData.buildPage(houses, count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    @Override
    public House queryHouseById(Long id) {
        House query = new House();
        query.setId(id);
        List<House> houses = queryAndSetImage(query, PageParams.build(1, 1));
        if (!houses.isEmpty()) {
            return houses.get(0);
        }
        return null;
    }

    @Override
    public void addUserMsg(UserMsg userMsg) {
        BeanHelper.onInsert(userMsg);
        houseMapper.insertUserMsg(userMsg);
        User user = agencyService.getAgentDetail(userMsg.getAgentId());
        mailService.sendMail("来自用户" + userMsg.getEmail() + "的留言", userMsg.getMsg(), user.getEmail());
    }

    @Override
    public HouseUser getHouseUser(Long id) {
        return houseMapper.selectSaleHouseUser(id);
    }

    private List<House> queryAndSetImage(House query, PageParams pageParams) {
        List<House> houses = houseMapper.selectPageHouse(query, pageParams);
        houses.forEach(house -> {
            house.setFirstImg(filePrefix + house.getFirstImg());
            house.setImageList(house.getImageList().stream().map(img -> filePrefix + img).collect(Collectors.toList()));
            house.setFloorPlanList(house.getFloorPlanList().stream().map(pic -> filePrefix + pic).collect(Collectors.toList()));
        });
        return houses;
    }
}
