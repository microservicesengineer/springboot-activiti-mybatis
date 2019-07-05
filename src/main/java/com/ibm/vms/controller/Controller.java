package com.ibm.vms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ibm.vms.entity.auto.leave_info;
import com.ibm.vms.service.LeaveService;

@RestController
public class Controller {
    @Autowired
    private LeaveService leaveService;

    /**
     * 发起申请，新增信息
     *
     * @param msg
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/addLeaveInfo")
    public leave_info addLeaveInfo(@RequestParam("msg") String msg) {
        return leaveService.addLeaveAInfo(msg);
        
    }
    /**
     * 向组任务中添加成员
     *
     */
    @RequestMapping("/addGroupUser")
    public String addGroupUser(@RequestParam("taskId")String taskId,@RequestParam("userId") String userId) {
         leaveService.addGroupUser(taskId,userId);
         return "添加成功";
    }
    /**
     * 查询当前用户的个人任务列表
     *
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping("/getPersonalTaskByUserId")
    public List<leave_info> getPersonalTaskByUserId(@RequestParam("userId")String userId) {
        //System.out.println();
        return leaveService.getPersonalByUserId(userId);
    }
    /**
     * 查询当前用户的组任务列表
     *
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping("/getGroupTaskByUserId")
    public List<leave_info> getGroupTaskByUserId(@RequestParam("userId")String userId) {
        //System.out.println();
        return leaveService.getGroupByUserId(userId);
    }
    /**
     * 处理完成任务
     *
     * @param taskId
     * @param userId
     * @param audit
     * @param request
     * @return
     */
    @RequestMapping("/completeTask")
    public String completeTask(@RequestParam("taskId")String taskId,@RequestParam("userId") String userId,@RequestParam("audit") String audit) {
        leaveService.completeTaskByUser(taskId, userId, audit);
        return "审批完成...";
    }
}
