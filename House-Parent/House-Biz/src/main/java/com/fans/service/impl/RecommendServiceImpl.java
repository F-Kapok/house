package com.fans.service.impl;

import com.fans.model.House;
import com.fans.service.interfaces.RecommendService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RecommendServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-07-02 11:35
 * @Version 1.0
 **/
@Service(value = "recommendService")
public class RecommendServiceImpl implements RecommendService {

    @Override
    public void increase(Long id) {
        int i=0;
    }

    @Override
    public List<House> getHotHouse(Integer size) {
        return null;
    }
}
