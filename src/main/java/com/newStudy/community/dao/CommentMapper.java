package com.newStudy.community.dao;

import com.newStudy.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-27-21:24
 */
@Mapper
public interface CommentMapper {
    //根据实体类型、实体Id查找评论
    List<Comment> selectCommentByEntity(int entityType,int entityId,int offset,int limit);
    //根据实体类型、实体ID查找评论的数量
    int selectCountByEntity(int entityType,int entityId);
    //添加评论
    int insertComment(Comment comment);

    Comment selectCommentById(int id);
}
