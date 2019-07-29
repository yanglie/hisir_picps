package com.hisir.picps.mongo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hisir.picps.mongo.entity.PicPsLog;

@RepositoryRestResource(collectionResourceRel = "PicPsLog", path = "PicPsLog")
public interface PicPsLogService extends MongoRepository<PicPsLog, String> {
	
	@Query("?#{[0]}")
	Page<PicPsLog> query(@Param("query")String query, @Param("page")Pageable page);
	
	PicPsLog findById(String id);
	
	@SuppressWarnings("unchecked")
	@Override
	PicPsLog save(PicPsLog entity);
	
	PicPsLog findByOrderNo(@Param("orderNo") String orderNo);

}
