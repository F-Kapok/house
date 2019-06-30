package com.fans.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserMsg
 * @Description:
 * @Author fan
 * @Date 2019-06-30 10:32
 * @Version 1.0
 **/
@Data
public class UserMsg {
    private Long id;
    private String msg;
    private Long userId;
    private Date createTime;
    private Long agentId;
    private Long houseId;
    private String email;
    private String userName;
}
