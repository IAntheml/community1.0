package com.newStudy.community.dao.elasticsearch;

import com.newStudy.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shkstart
 * @create 2020-03-04-17:13
 */

@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {
    
}
