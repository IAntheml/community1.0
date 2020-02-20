package com.newStudy.community.dao;

import com.newStudy.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**数据访问层，从数据库中获取数据
 * @author shkstart
 * @create 2020-02-20-16:04
 */
@Mapper
public interface DiscussPostMapper {
    //多个讨论帖组成一个待返回的List，开始索引，数据条数
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>里使用(在sql中需要动态的使用),则必须加别名.
    int selectDiscussPostRows(@Param("userId") int userId);
}
