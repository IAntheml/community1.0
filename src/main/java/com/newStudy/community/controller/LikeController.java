package com.newStudy.community.controller;

import com.newStudy.community.entity.Comment;
import com.newStudy.community.entity.DiscussPost;
import com.newStudy.community.entity.Event;
import com.newStudy.community.entity.User;
import com.newStudy.community.event.EventProducer;
import com.newStudy.community.service.LikeService;
import com.newStudy.community.util.CommunityConstant;
import com.newStudy.community.util.CommunityUtil;
import com.newStudy.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-03-02-13:57
 */
@Controller
public class LikeController implements CommunityConstant {

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/like" ,method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType,int entityId,int entityUserId,int postId){
        User user = hostHolder.getUser();

        //实现点赞
        likeService.like(user.getId(),entityType,entityId,entityUserId);

        //统计点赞的数量和点赞的状态返回给页面
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);

        //返回的结果封装传给页面
        Map<String,Object> map = new HashMap<>();
        map.put("likeCount",likeCount);
        map.put("likeStatus",likeStatus);

        //出发点赞事件
        if(likeStatus == 1){
            Event event = new Event().setTopic(TOPIC_LIKE).setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType).setEntityId(entityId).setEntityUserId(entityUserId)
                    .setData("postId",postId);
            //
            eventProducer.fireEvent(event);
        }

        return CommunityUtil.getJSONString(0,null,map);
    }
}
