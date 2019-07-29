package com.hisir.picps.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.controller.entity.ProcOrderBizEntity;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.service.PicPsOrderService;


@RestController
public class PicPsUnprocOrderController {

	private static final Log log = LogFactory.getLog(PicPsUnprocOrderController.class);

	@Autowired
	PicPsOrderService picPsOrderService;
	
	@RequestMapping(value = "/inpicps/picps_unprocessed_order", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<ProcOrderBizEntity> picPsUndoOrder(
			@RequestParam(value = "order_no", required = true) String orderNo) {
		log.info("orderNo:" + orderNo);
		PicPsOrder order = picPsOrderService.findByOrderNo(orderNo);
		if (order == null) {
			return new BaseResponse<ProcOrderBizEntity>(ErrCodeEnum.SUCCESS);
		}
		BaseResponse<ProcOrderBizEntity> resp = new BaseResponse<ProcOrderBizEntity>(ErrCodeEnum.SUCCESS);
		resp.setData(new ProcOrderBizEntity(order));
		return resp;
		
//		log.info("pageNum:" + pageNum + ", pageSize:" + pageSize);

	}
	

}