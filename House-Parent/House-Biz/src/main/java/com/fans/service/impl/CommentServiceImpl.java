package com.fans.service.impl;

import com.fans.mapper.CommentMapper;
import com.fans.model.Comment;
import com.fans.model.User;
import com.fans.service.interfaces.CommentService;
import com.fans.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-07-02 11:49
 * @Version 1.0
 **/
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Resource(type = CommentMapper.class)
    private CommentMapper commentMapper;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    public List<Comment> getHouseComments(long houseId, int size) {
        List<Comment> comments = commentMapper.selectComments(houseId, size);
        comments.forEach(comment -> {
            User user = userService.getUserById(comment.getUserId());
            comment.setAvatar(user.getAvatar());
            comment.setUserName(user.getName());
        });
        return comments;
    }
}
