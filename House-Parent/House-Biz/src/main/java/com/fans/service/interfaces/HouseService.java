package com.fans.service.interfaces;

import com.fans.model.House;
import com.fans.model.HouseUser;
import com.fans.model.UserMsg;
import com.fans.page.PageData;
import com.fans.page.PageParams;

/**
 * @InterfaceName HouseService
 * @Description:
 * @Author fan
 * @Date 2019-06-29 23:42
 * @Version 1.0
 **/
public interface HouseService {

    PageData<House> queryHouse(House query, PageParams build);

    House queryHouseById(Long id);

    void addUserMsg(UserMsg userMsg);

    HouseUser getHouseUser(Long id);
}
