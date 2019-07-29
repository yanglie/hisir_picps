package com.hisir.picps.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hisir.picps.mongo.entity.PicPsPriority;

@RepositoryRestResource(collectionResourceRel = "PicPsPriority", path = "PicPsPriority")
public interface PicPsPriorityService extends MongoRepository<PicPsPriority, String> {
	
	@Query("?#{[0]}")
	Page<PicPsPriority> query(@Param("query")String query, @Param("page")Pageable page);
	
	PicPsPriority findByBitInt(Integer bitInt);
	
	List<PicPsPriority> findByStatus(@Param("status") Integer status);
	
	@SuppressWarnings("unchecked")
	@Override
	PicPsPriority save(PicPsPriority entity);
	

}
