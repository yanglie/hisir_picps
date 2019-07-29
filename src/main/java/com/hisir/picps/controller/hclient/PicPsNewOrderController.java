package com.hisir.picps.controller.hclient;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.common.ApiVersion;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.enums.PayStatusEnum;
import com.hisir.picps.enums.PayTypeEnum;
import com.hisir.picps.enums.PsStatusEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.entity.PicPsTask;
import com.hisir.picps.mongo.entity.Picture;
import com.hisir.picps.mongo.service.PicPsOrderService;
import com.hisir.picps.rpc.HisirOrderService;


@RestController
public class PicPsNewOrderController {

	private static final Log log = LogFactory.getLog(PicPsNewOrderController.class);
	
	@Autowired
	PicPsOrderService tsPicPsOrderRep;
	@Autowired
	HisirOrderService hisirOrderService;

	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_new_order", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<NewOrderBizData> picPsNewOrder(
			@RequestParam(value = "principal_id", required = true) Integer principalId,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "proc_pics", required = true) String procPics,
			@RequestParam(value = "remark", required = false) String remark) {
		
		log.info("principalId:" + principalId + ", title:" + title + ", procPics:" + procPics + ", remark:" + remark);
		
		Long nowTs = new Date().getTime();
		
		PicPsOrder order = new PicPsOrder();
		order.setUid(principalId);
		
		order.setTitle(title);
		order.setRemark(remark);
		order.setCreateTime(nowTs);	
		order.setModifyTime(nowTs);
		//费用计算未定
		order.setPayStatus(PayStatusEnum.WAIT.getIdx());
		order.setPsStatus(PsStatusEnum.UNDO.getIdx());
		//JSONObject json = new JSONObject(picList);
		JSONArray picJsonArray = new JSONArray(procPics);
		for (int i=0; picJsonArray!=null && i<picJsonArray.length(); i++) {
			JSONObject jsonObj = picJsonArray.getJSONObject(i);
			order.getPsTasks().add(new PicPsTask(jsonObj));
		}
		
		Integer currencyType = 1;
		Integer payAmount = 100;
		String payNo = hisirOrderService.getPayOrderNo(principalId, PayTypeEnum.TYPE3.getIdx(), currencyType, payAmount);
		if (payNo.equals("")) {
			return new BaseResponse<NewOrderBizData>(ErrCodeEnum.SYS_ERR_SPRING_CLOUD);
		}
		order.setOrderNo(payNo);
		order.setCurrencyType(currencyType);
		order.setPayAmount(payAmount);
		order = tsPicPsOrderRep.save(order);
		
		BaseResponse<NewOrderBizData> resp = new BaseResponse<NewOrderBizData>(ErrCodeEnum.SUCCESS);
		
		NewOrderBizData orderData = new NewOrderBizData();
		orderData.order_no = order.getOrderNo();
		for (PicPsTask task: order.getPsTasks()) {
			NewTaskBizData taskData = new NewTaskBizData();
			taskData.task_id = task.getTaskId();
			for (Picture pic :task.getSrcPics()) {
				taskData.pic_ids.add(pic.getPicId());
			}
			orderData.tasks.add(taskData);
		}
		resp.setData(orderData);
		return resp;
	}
	
	public class NewOrderBizData {
		public String order_no = "";
		public List<NewTaskBizData> tasks = new ArrayList<NewTaskBizData>();
	}
	public class NewTaskBizData {
		public String task_id = "";
		public List<String> pic_ids = new ArrayList<String>();;
	}

}