package com.fans.controller;

import com.fans.model.House;
import com.fans.model.HouseUser;
import com.fans.model.UserMsg;
import com.fans.page.PageData;
import com.fans.page.PageParams;
import com.fans.service.interfaces.AgencyService;
import com.fans.service.interfaces.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @ClassName HouseController
 * @Description: 房产控制层
 * @Author fan
 * @Date 2019-06-29 23:42
 * @Version 1.0
 **/
@Controller
public class HouseController {
    @Resource(name = "houseService")
    private HouseService houseService;

    @Resource(name = "agencyService")
    private AgencyService agencyService;

    @RequestMapping(value = "/house/list")
    public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap) {
        PageData<House> housePageData = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        modelMap.put("ps", housePageData);
        modelMap.put("vo", query);
        return "house/listing";
    }

    @RequestMapping(value = "house/detail")
    public String houseDetail(Long id, ModelMap modelMap) {
        House house = houseService.queryHouseById(id);
        HouseUser houseUser = houseService.getHouseUser(id);
        if (houseUser.getUserId() != null && !houseUser.getUserId().equals(0)) {
            modelMap.put("agent", agencyService.getAgentDetail(house.getUserId()));
        }
        modelMap.put("house", house);
        return "/house/detail";
    }

    @RequestMapping(value = "house/leaveMsg")
    public String houseMsg(UserMsg userMsg) {
        houseService.addUserMsg(userMsg);
        return "redirect:/house/detail?id=" + userMsg.getHouseId() + "";
    }
}
