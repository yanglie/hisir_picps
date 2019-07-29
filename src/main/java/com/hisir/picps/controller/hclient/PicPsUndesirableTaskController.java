package com.hisir.picps.controller.hclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.common.ApiVersion;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.enums.PsStatusEnum;
import com.hisir.picps.enums.TaskStatusEnum;
import com.hisir.picps.exception.BaseException;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.messaging.PicPsGateway;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.entity.PicPsTask;
import com.hisir.picps.mongo.entity.Picture;
import com.hisir.picps.mongo.service.PicPsOrderService;


@RestController
public class PicPsUndesirableTaskController {

	private static final Log log = LogFactory.getLog(PicPsUndesirableTaskController.class);

	@Autowired
	PicPsOrderService picPsOrderService;
	@Autowired
	PicPsGateway picPsGateway;
	
	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_undesirable_task", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Integer> picPsRedoTask(
			@RequestParam(value = "principal_id", required = true) Integer uid,
			@RequestParam(value = "order_no", required = true) String orderNo,
			@RequestParam(value = "task_id", required = true) String taskId,
			@RequestParam(value = "user_comment", required = true) String userComment,
			@RequestParam(value = "pics", required = true) String pics) {
		
		log.info("uid:" + uid + ", orderNo:" + orderNo + ", taskId:" + taskId + ", userComment:" + userComment + ", pics:" + pics);
		try {
			PicPsOrder order = picPsOrderService.findByUidAndOrderNo(uid, orderNo);
			if (order == null) {
				return new BaseResponse<Integer>(ErrCodeEnum.M05_INVALID_ORDER_NO);
			}
			List<PicPsTask> psTasks = order.getPsTasks();
			List<PicPsTask> tmpTasks = new ArrayList<PicPsTask>();
			for (PicPsTask task: psTasks) {
				if (task.getTaskId().equals(taskId)) {
					if (! userComment.equals("")) {
						task.setUserComment(userComment);
					}
					task.setSrcPics(Picture.toPictures(new JSONArray(pics))); //图片不符合要求
					task.setTaskStatus(TaskStatusEnum.UNDO.getIdx());
				}
				tmpTasks.add(task);
			}
			order.setPsTasks(tmpTasks);
			order.setPsStatus(PsStatusEnum.DOING.getIdx());
			picPsOrderService.save(order);
			picPsGateway.sendNewOrderNo(order.getOrderNo());
		} catch (Exception e) {
			log.error("uid:" + uid + ", orderNo:" + orderNo + ", taskId:" + taskId + ", error:" + BaseException.getAllExInfo(e));
			return new BaseResponse<Integer>(ErrCodeEnum.SYS_ERR_UNKNOWN);
		}
		return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
	}
	
}