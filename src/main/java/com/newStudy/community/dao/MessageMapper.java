package com.newStudy.community.dao;

import com.newStudy.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-28-16:29
 */
@Mapper
public interface MessageMapper {
    //分页查询用户的会话列表
    List<Message> selectConversations(int userId,int offset,int limit);
    //查询用户的会话数量,传入的userId可能是发送者或者收到方
    int selectConversationCount(int userId);
    //查询某个会话所有的消息列表
    List<Message> selectLetters(String conversationId,int offset,int limit);
    //查询某个会话所有的消息总数
    int selectLetterCount(String conversationId);
    //查询未读私信的数量
    int selectLetterUnreadCount(int userId,String conversationId);

    //增加一个消息
    int insertMessage(Message message);

    //更新消息状态
    int updateStatus(List<Integer> ids,int status);

    //查询某个主题下最新的通知
    Message selectLatestNotice(int userId,String topic);
    //某个主题所包含的通知数量
    int selectNoticeCount(int userId,String topic);
    //未读的通知数量
    int selectNoticeUnreadCount(int userId,String topic);
}
