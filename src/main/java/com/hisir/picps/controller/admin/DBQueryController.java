package com.hisir.picps.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.service.PicPsOrderService;


@RestController
public class DBQueryController {
	
	private static Log log = LogFactory.getLog(DBQueryController.class);
	
	private int maxCount = 100;
	
	@Autowired
	PicPsOrderService picPsOrderService;

	@RequestMapping(value = "/inpicps/rest/PicPsOrder/query")
	public @ResponseBody Page<PicPsOrder> queryTsTUserWithdrawOrderView(
			@RequestParam(value = "query") String query, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sort", required = false) String sort) {
		log.info("query:" + query + ", page:" + page + ", size:" + size + ", sort:" + sort);
		
		PageRequest pageAble = getPageRequest(page, size, sort);
		Page<PicPsOrder> users = picPsOrderService.query(query, pageAble);
		return users;
	}
	
	private PageRequest getPageRequest(Integer page, Integer size, String sort) {
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 20;
		}
		if (size > maxCount) {
			size = maxCount;
		}
		List<Order> orders = new ArrayList<>();
		Sort sorts = null;
		if (sort != null) {
			try {
				JSONArray sortJsonArray = new JSONArray(sort);
				// for(Object obj : sortJsonArray){
				for (int i = 0; i < sortJsonArray.length(); i++) {
					Object obj = sortJsonArray.get(i);
					JSONArray propertyJsonArray = new JSONArray(obj.toString());
					Direction direction = Direction.fromString(propertyJsonArray.getString(1));
					String property = propertyJsonArray.getString(0);
					Order order = new Order(direction, property);
					orders.add(order);
				}
				sorts = new Sort(orders);
			} catch (Exception e) {
				// throw new ErrorExceptionManager.QueryMongodbQueryError();
				return null;
			}
		}
		PageRequest pageAble = new PageRequest(page, size, sorts);
		return pageAble;
	}
}
