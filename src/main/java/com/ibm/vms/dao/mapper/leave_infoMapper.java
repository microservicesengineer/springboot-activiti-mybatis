package com.ibm.vms.dao.mapper;

import com.ibm.vms.entity.db.leave_info;

public interface leave_infoMapper {
    int deleteByPrimaryKey(String id);

    int insert(leave_info record);

    int insertSelective(leave_info record);

    leave_info selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(leave_info record);

    int updateByPrimaryKey(leave_info record);
}