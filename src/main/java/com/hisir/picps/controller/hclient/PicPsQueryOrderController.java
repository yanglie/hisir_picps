package com.hisir.picps.controller.hclient;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.common.ApiVersion;
import com.hisir.picps.controller.entity.PicPsOrderBizEntity;
import com.hisir.picps.controller.entity.PicPsTaskBizEntity;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.enums.PayStatusEnum;
import com.hisir.picps.enums.PsStatusEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.service.PicPsOrderService;

@RestController
public class PicPsQueryOrderController {

	private static final Log log = LogFactory.getLog(PicPsQueryOrderController.class);

	@Autowired
	PicPsOrderService picPsOrderService;

	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_query_order", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<List<PicPsOrderBizEntity>> picPsQueryOrder(
			@RequestParam(value = "principal_id", required = true) Integer uid,
			@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "since", required = false) Long since, 
			@RequestParam(value = "until", required = false) Long until, 
			@RequestParam(value = "count", required = false) Integer count) {
		log.info("principal_id:" + uid + ", status:" + status + ", since:" + since + ", until:" + until + ", until:" + count);
		Long stime = until;
		Long etime = since;
		Integer pageNum = 0;
		Integer pageSize = count;
		
		if (etime == null) {
			etime = new Date().getTime();
		}
		if (stime == null) {
			stime = (long) 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		// status 0-待付款，1-处理中，2-已退回，3-已完成
		Set<Integer> setPsStatus = new HashSet<Integer>();
		switch (status) {
		case 0:
			setPsStatus.add(PsStatusEnum.UNDO.getIdx());
			break;
		case 1:
			setPsStatus.add(PsStatusEnum.DOING.getIdx());
			setPsStatus.add(PsStatusEnum.DONE.getIdx());
			setPsStatus.add(PsStatusEnum.NOPIC.getIdx());
			break;
		case 2:
			setPsStatus.add(PsStatusEnum.REJECT.getIdx());
			break;
		case 3:
			setPsStatus.add(PsStatusEnum.ACCEPT.getIdx());
			break;
		default:
			return new BaseResponse<List<PicPsOrderBizEntity>>(ErrCodeEnum.M04_INVALID_INPUT_PARAM);
		}
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		Pageable pageable = new PageRequest(pageNum, pageSize, sort);

		Page<PicPsOrder> orders = picPsOrderService.findByUidAndPsStatusInAndPayStatusNotAndCreateTimeBetween(
				uid, setPsStatus, PayStatusEnum.DELETE.getIdx(),stime, etime, pageable);

		List<PicPsOrderBizEntity> ordersBiz = new ArrayList<>();
		for (PicPsOrder order : orders.getContent()) {
			PicPsOrderBizEntity orderBiz = new PicPsOrderBizEntity();
			orderBiz.order_no = order.getOrderNo();
			orderBiz.title = order.getTitle();
			orderBiz.remark = order.getRemark();
			orderBiz.currency_type = order.getCurrencyType();
			orderBiz.pay_amount = order.getPayAmount();
			orderBiz.pay_status = order.getPayStatus();
			orderBiz.ps_status = order.getPsStatus();
			orderBiz.tasks = PicPsTaskBizEntity.toTasks(order.getPsTasks());
			orderBiz.create_time = order.getCreateTime();
			ordersBiz.add(orderBiz);
		}

		log.info("principal_id:" + uid + ", status:" + status + ", total_pages:" + orders.getTotalPages()
				+ ", total_size:" + orders.getTotalElements());

		BaseResponse<List<PicPsOrderBizEntity>> resp = new BaseResponse<List<PicPsOrderBizEntity>>(ErrCodeEnum.SUCCESS);
		resp.setData(ordersBiz);
		return resp;
	}

}