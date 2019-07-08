package com.ibm.vms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.vms.service.TaskFlowService;
import com.ibm.vms.util.HttpResponseBuilder;
import com.vms.controller.FlowtaskApi;
import com.vms.model.ClaimTaskReqVO;
import com.vms.model.CompleteTaskReqVO;
import com.vms.model.QueryTaskReqVO;
import com.vms.model.StandardResponse;

@RestController
public class TaskFlowController implements FlowtaskApi {

	@Autowired
	TaskFlowService mTaskFlowService;

	@Override
	public ResponseEntity<StandardResponse> queryTask(@Valid QueryTaskReqVO body) {
		try {
			List tasks = mTaskFlowService.queryTask(body.getAssignee(), body.getCandidateUser(),
					body.getCandidateGroup(), body.getFirstResult(), body.getMaxResults());
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "fetch task list success", tasks);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> claimTaskByID(@Valid ClaimTaskReqVO claimTaskReq) {
		try {
			mTaskFlowService.claimTask(claimTaskReq.getTaskId(), claimTaskReq.getAssignee());
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "claim task success", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> completeTask(@Valid CompleteTaskReqVO completeTaskReqVO) {
		Map<String, Object> param = new HashMap<>();
		param.put("isFinish", false);// the flow is completed?
		try {
			if (completeTaskReqVO.getIsReviewPass() == 1) { // pass the process
				mTaskFlowService.completeTask(completeTaskReqVO.getTaskId(), completeTaskReqVO.getAssignee(),
						completeTaskReqVO.getComment(), completeTaskReqVO.getVariables(), param);
				return HttpResponseBuilder.success(HttpStatus.OK.value(), "complete to approve the task successfully",
						null);
			}

			if (completeTaskReqVO.getIsReviewPass() == 0) { // reject the process
				mTaskFlowService.rejectTask(completeTaskReqVO.getTaskId(), completeTaskReqVO.getAssignee(),
						completeTaskReqVO.getComment(), completeTaskReqVO.getReturnStart() == 1);
				return HttpResponseBuilder.success(HttpStatus.OK.value(), "complete to reject the task successfully",
						null);
			}
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), "failed to complete the task", null);
		}
		return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), "failed to complete the task", null);
	}

	@Override
	public ResponseEntity<StandardResponse> deleteTaskByID(Integer id) {
		// TODO Auto-generated method stub
		return FlowtaskApi.super.deleteTaskByID(id);
	}

	@Override
	public ResponseEntity<StandardResponse> unclaimTaskByID(Integer id) {
		// TODO Auto-generated method stub
		return FlowtaskApi.super.unclaimTaskByID(id);
	}

}
