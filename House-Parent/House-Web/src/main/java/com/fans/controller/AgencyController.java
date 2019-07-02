package com.fans.controller;

import com.fans.common.CommonConstants;
import com.fans.model.House;
import com.fans.model.User;
import com.fans.page.PageData;
import com.fans.page.PageParams;
import com.fans.service.interfaces.AgencyService;
import com.fans.service.interfaces.HouseService;
import com.fans.service.interfaces.RecommendService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AgencyController
 * @Description:
 * @Author fan
 * @Date 2019-07-02 18:56
 * @Version 1.0
 **/
@RestController
public class AgencyController {

    @Resource(name = "agencyService")
    private AgencyService agencyService;
    @Resource(name = "houseService")
    private HouseService houseService;
    @Resource(name = "recommendService")
    private RecommendService recommendService;

    @RequestMapping(value = "/agency/agentList")
    public ModelAndView agentList(Integer pageSize, Integer pageNum, ModelMap modelMap) {
        if (pageSize == null) {
            pageSize = 6;
        }
        PageData<User> ps = agencyService.getAllAgent(PageParams.build(pageSize, pageNum));
        List<House> hotHouse = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", hotHouse);
        modelMap.put("ps", ps);
        return new ModelAndView("/user/agent/agentList", modelMap);
    }

    @RequestMapping(value = "/agency/agentDetail")
    public ModelAndView agentDetail(Long id, ModelMap modelMap) {
        User user = agencyService.getAgentDetail(id);
        List<House> houses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        House query = new House();
        query.setUserId(id);
        query.setBookmarked(false);
        PageData<House> bindHouse = houseService.queryHouse(query, new PageParams(3, 1));
        if (bindHouse != null) {
            modelMap.put("bindHouses", bindHouse.getList());
        }
        modelMap.put("recomHouses", houses);
        modelMap.put("agent", user);
        modelMap.put("agencyName", user.getAgencyName());
        return new ModelAndView("/user/agent/agentDetail", modelMap);
    }
}
