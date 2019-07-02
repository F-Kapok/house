package com.fans.service.interfaces;

import com.fans.model.Agency;
import com.fans.model.User;

import java.util.List;

/**
 * @InterfaceName AgencyService
 * @Description:
 * @Author fan
 * @Date 2019-06-30 10:04
 * @Version 1.0
 **/
public interface AgencyService {

    User getAgentDetail(Long userId);

    List<Agency> getAllAgency();
}
