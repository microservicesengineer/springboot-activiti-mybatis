package com.ibm.vms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ibm.vms.service.ActivitiService;
import com.vms.controller.UserTaskManagmentApi;
import com.vms.model.ClaimTaskReqVO;
import com.vms.model.CompleteTaskReqVO;
import com.vms.model.QueryTaskReqVO;
import com.vms.model.StandardResponse;

public class UserTaskController implements UserTaskManagmentApi{
	
    @Autowired
    ActivitiService activitiService;

	@Override
	public ResponseEntity<StandardResponse> claimTaskByID(@Valid ClaimTaskReqVO claimTaskReq) {
		// TODO Auto-generated method stub
		activitiService.claimTask(claimTaskReq.getTaskId(), claimTaskReq.getAssignee());
		return UserTaskManagmentApi.super.claimTaskByID(claimTaskReq);
	}

	@Override
	public ResponseEntity<StandardResponse> completeTask(@Valid CompleteTaskReqVO completeTaskReqVO) {
		// TODO Auto-generated method stub
		return UserTaskManagmentApi.super.completeTask(completeTaskReqVO);
	}

	@Override
	public ResponseEntity<StandardResponse> deleteTaskByID(Integer id) {
		// TODO Auto-generated method stub
		return UserTaskManagmentApi.super.deleteTaskByID(id);
	}

	@Override
	public ResponseEntity<StandardResponse> queryTask(@Valid QueryTaskReqVO queryTaskReq) {
		// TODO Auto-generated method stub
		return UserTaskManagmentApi.super.queryTask(queryTaskReq);
	}

	@Override
	public ResponseEntity<StandardResponse> unclaimTaskByID(Integer id) {
		// TODO Auto-generated method stub
		return UserTaskManagmentApi.super.unclaimTaskByID(id);
	}
    


}
