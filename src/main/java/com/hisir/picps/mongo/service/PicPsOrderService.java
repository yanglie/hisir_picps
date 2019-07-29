package com.hisir.picps.mongo.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hisir.picps.mongo.entity.PicPsOrder;


@RepositoryRestResource(collectionResourceRel = "PicPsOrder", path = "PicPsOrder")
public interface PicPsOrderService extends MongoRepository<PicPsOrder, String> {
	
	@Query("?#{[0]}")
	Page<PicPsOrder> query(@Param("query")String query, @Param("page")Pageable page);
	
	PicPsOrder findById(String id);
	
	@SuppressWarnings("unchecked")
	@Override
	PicPsOrder save(PicPsOrder entity);
	
	PicPsOrder findByOrderNo(@Param("orderNo") String orderNo);
	PicPsOrder findByUidAndOrderNo(@Param("hUserId") Integer hUserId, @Param("orderNo") String orderNo);
	
	Page<PicPsOrder> findByUidAndPsStatusInAndPayStatusNotAndCreateTimeBetween(@Param("hUserId") Integer hUserId, 
			@Param("psStatus") Set<Integer> psStatus, @Param("payStatus") Integer payStatus, 
			@Param("stime") Long stime, @Param("etime") Long etime, @Param("page") Pageable page);
	
	Page<PicPsOrder> findByPsStatusInAndPayStatus(@Param("psStatus") Set<Integer> psStatus, @Param("payStatus") Integer payStatus, 
			@Param("page") Pageable page);
}
