package com.fans.service.impl;

import com.fans.mapper.CommunityMapper;
import com.fans.mapper.HouseMapper;
import com.fans.model.Community;
import com.fans.model.House;
import com.fans.page.PageData;
import com.fans.page.PageParams;
import com.fans.service.interfaces.HouseService;
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

    @Override
    public PageData<House> queryHouse(House query, PageParams pageParams) {
        List<House> houses = Lists.newArrayList();
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
