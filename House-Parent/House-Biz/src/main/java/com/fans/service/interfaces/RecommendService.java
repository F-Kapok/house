package com.fans.service.interfaces;

import com.fans.model.House;

import java.util.List;

/**
 * @InterfaceName RecommendService
 * @Description:
 * @Author fan
 * @Date 2019-07-02 11:35
 * @Version 1.0
 **/
public interface RecommendService {

    void increase(Long id);

    List<House> getHotHouse(Integer size);
}
