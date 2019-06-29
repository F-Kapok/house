package com.fans.controller;

import com.fans.model.House;
import com.fans.page.PageData;
import com.fans.page.PageParams;
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

    @RequestMapping(value = "/house/list")
    public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap) {
        PageData<House> housePageData = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        modelMap.put("ps", housePageData);
        modelMap.put("vo", query);
        return "house/listing";
    }
}
