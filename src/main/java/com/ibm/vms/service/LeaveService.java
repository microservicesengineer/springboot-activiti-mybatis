package com.ibm.vms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.vms.entity.auto.leave_info;


@Service
public interface LeaveService {
	
	
	/**
	 * 新增一条请假单记录
	 * @param entity
	 */
	leave_info addLeaveAInfo(String msg);
	/**
	 * 查询待办流程
	 * @param userId
	 * @return
	 */
	List<leave_info> getPersonalByUserId(String userId);
	List<leave_info> getGroupByUserId(String userId);
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param userId
	 * @param audit
	 */
	void completeTaskByUser(String taskId,String userId,String audit);
	 /**
     * 向组任务中添加成员
     *
     */
	void addGroupUser(String taskId,String userId);

}
