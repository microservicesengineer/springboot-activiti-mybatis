package com.ibm.vms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.impl.form.TaskFormDataImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.vms.api.FlowtaskApi;
import com.ibm.vms.models.CandidateUserVO;
import com.ibm.vms.models.ClaimTaskReqVO;
import com.ibm.vms.models.CompleteTaskReqVO;
import com.ibm.vms.models.DelegateTaskReqVO;
import com.ibm.vms.models.QueryTaskReqVO;
import com.ibm.vms.models.StandardResponse;
import com.ibm.vms.models.WithdrawVO;
import com.ibm.vms.service.TaskFlowService;
import com.ibm.vms.util.HttpResponseBuilder;

import lombok.Data;

@RestController
public class TaskFlowController implements FlowtaskApi {

	@Autowired
	TaskFlowService mTaskFlowService;

	@Override
	public ResponseEntity<StandardResponse> queryTask(@Valid QueryTaskReqVO body) {
		try {
			List tasks = mTaskFlowService.queryTask(body.getOwner(), body.getAssignee(), body.getCandidateUser(),
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
		try {
			if (completeTaskReqVO.getIsReviewPass() == 1) { // pass the process
				String data = mTaskFlowService.completeTask(completeTaskReqVO.getTaskId(), completeTaskReqVO.getAssignee(),
						completeTaskReqVO.getComment(), completeTaskReqVO.getVariables(), param);
				return HttpResponseBuilder.success(HttpStatus.OK.value(), "complete to approve the task successfully",
						data);
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
	public ResponseEntity<StandardResponse> deleteTaskByID(String id) {
		// TODO Auto-generated method stub
		try {
			mTaskFlowService.deleteTask(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "delete task success", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> delegateTask(DelegateTaskReqVO body) {
		// TODO Auto-generated method stub
		try {
			mTaskFlowService.delegateTask(body.getTaskId(), body.getAssignee());
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "unclaim task success", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> unclaimTaskByID(String id) {
		// TODO Auto-generated method stub		
		try {
			mTaskFlowService.unclaimTask(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "unclaim task success", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> addCandidateUser(String id, CandidateUserVO body) {
		// TODO Auto-generated method stub
		try {
			mTaskFlowService.addCandidateUser(id,body.getCandidateUser());
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "add CandidateUser success", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> getTaskCandidate(String id) {
		// TODO Auto-generated method stub
		return FlowtaskApi.super.getTaskCandidate(id);
	}

	@Override
	public ResponseEntity<StandardResponse> getVariablesById(String id) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> data = mTaskFlowService.queryVariables(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "query variables success", data);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> jump(String id, WithdrawVO body) {
		// TODO Auto-generated method stub
		try {
			mTaskFlowService.withdraw(id, body.getProcessid());
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "withdraw to "+body.getProcessid()+" success", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
		
		
	}

	@Override
	public ResponseEntity<StandardResponse> getformbyid(String id) {
		// TODO Auto-generated method stub
		try {
			List<FormProperty> Data = mTaskFlowService.getforminfo(id);

			return HttpResponseBuilder.success(HttpStatus.OK.value(), "get form success", Data);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}


}
