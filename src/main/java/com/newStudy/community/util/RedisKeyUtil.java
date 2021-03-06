package com.newStudy.community.util;

/**
 * @author shkstart
 * @create 2020-03-02-13:41
 */


public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_User_LIKE = "like:user";
    //关注功能的前缀
    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_FOLLOWER = "follower";
    //定义验证码的前缀
    private static final String PREFIX_KAPTCHA = "kaptcha";
    //登录凭证的前缀
    private static final String PREFIX_TICKET = "ticket";
    //用户信息缓存的前缀
    private static final String PREFIX_USER = "user";

    //生成某个实体的赞
    //like:entity:entityType:entityId -> set(userId)
    public static String getEntityLikeKey(int entityType,int entityId){
        return PREFIX_ENTITY_LIKE+SPLIT+entityType+SPLIT+entityId;
    }

    //生成某个用户的赞
    //like:user:userId -> int
    public static String getUserLikeKey(int userId){
        return PREFIX_User_LIKE+SPLIT+userId;
    }

    //某个用户关注的实体
    //followee:userId:entityType -> zset(entityId,nowdate)
    public static String getFolloweeKey(int userId,int entityType){
        return PREFIX_FOLLOWEE+SPLIT+userId+SPLIT+entityType;
    }

    //某个实体拥有的粉丝
    //follower:entityType:entityId -> zset(userId,nowdate)
    public static String getFollowerKey(int entityType,int entityId){
        return PREFIX_FOLLOWER+SPLIT+entityType+SPLIT+entityId;
    }

    //生成登录验证码的key
    public static String getKaptchaKey(String owner){
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    //生成登录凭证的key
    public static String getTikcketKey(String ticket){
        return PREFIX_TICKET + SPLIT + ticket;
    }

    //用户
    public static String getUserKey(int userId){
        return PREFIX_USER + SPLIT + userId;
    }

}
