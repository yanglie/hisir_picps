package com.hisir.picps.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.controller.entity.ProcTaskResultBizEntity;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.enums.PsStatusEnum;
import com.hisir.picps.enums.TaskStatusEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.entity.PicPsTask;
import com.hisir.picps.mongo.service.PicPsOrderService;


@RestController
public class PicPsProcOrderController {

	private static final Log log = LogFactory.getLog(PicPsProcOrderController.class);

	@Autowired
	PicPsOrderService picPsOrderService;
	
	@RequestMapping(value = "/inpicps/picps_processed_order", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Integer> picPsQueryOrder(
			@RequestParam(value = "order_no", required = true) String orderNo,
			@RequestParam(value = "tasks", required = true) String tasks) {
		
		log.info("orderNo:" + orderNo + ", tasks:" + tasks);
		
		PicPsOrder order = picPsOrderService.findByOrderNo(orderNo);
		if (order == null) {
			log.error("orderNo:" + orderNo + " is null");
			return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
		}
		JSONArray jsonArray = new JSONArray(tasks);
		Map<String, ProcTaskResultBizEntity> mapTaskResult = ProcTaskResultBizEntity.toMapProcTaskResult(jsonArray);
		
		List<PicPsTask> picPsTasks = order.getPsTasks();
		Boolean bIsFinish = true;
		List<PicPsTask> tmpTasks = new ArrayList<PicPsTask>(); 
		for (PicPsTask task: picPsTasks) {
			String taskId = task.getTaskId();
			if (mapTaskResult.containsKey(taskId)) {
				task.getPsUsers().addAll(mapTaskResult.get(taskId).picPsUsers);
				task.setDesPic(mapTaskResult.get(taskId).picture);
				task.setTaskStatus(mapTaskResult.get(taskId).taskStatus);
				task.setWorkerComment(mapTaskResult.get(taskId).workerComment);
			} else {
				log.error("orderNo:" + orderNo + ", taskId:" + task.getTaskId() + " not return proc result");
			}
			if (task.getTaskStatus() != TaskStatusEnum.DONE.getIdx()) {
				bIsFinish = false;
			}
			tmpTasks.add(task);
		}
		if (bIsFinish) {
			order.setPsStatus(PsStatusEnum.DONE.getIdx());
		}
		order.setPsTasks(tmpTasks);
		picPsOrderService.save(order);
		
		return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
	}
	

}