package com.fans.service.interfaces;

import com.fans.model.Comment;

import java.util.List;

/**
 * @InterfaceName CommentService
 * @Description:
 * @Author fan
 * @Date 2019-07-02 11:49
 * @Version 1.0
 **/
public interface CommentService {

    List<Comment> getHouseComments(long houseId, int size);
}
