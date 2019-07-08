package com.ibm.vms.entity.db;

public class leave_info {
    private String id;

    private String status;

    private String leavemsg;

    private String taskid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLeavemsg() {
        return leavemsg;
    }

    public void setLeavemsg(String leavemsg) {
        this.leavemsg = leavemsg == null ? null : leavemsg.trim();
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }
}