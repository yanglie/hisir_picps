package com.hisir.picps.mongo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hisir.picps.mongo.entity.PicPsWatermark;

@RepositoryRestResource(collectionResourceRel = "PicPsWatermark", path = "PicPsWatermark")
public interface PicPsWatermarkService extends MongoRepository<PicPsWatermark, String> {
	
	@Query("?#{[0]}")
	Page<PicPsWatermark> query(@Param("query")String query, @Param("page")Pageable page);
		
	PicPsWatermark findByUid(@Param("uid") Integer uid);

}
