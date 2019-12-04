package com.ibm.vms.dao.mapper;

import com.ibm.vms.entity.db.order_info;
import com.ibm.vms.entity.db.order_infoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface order_infoMapper {
    int deleteByExample(order_infoExample example);

    int deleteByPrimaryKey(String id);

    int insert(order_info record);

    int insertSelective(order_info record);

    List<order_info> selectByExample(order_infoExample example);

    order_info selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") order_info record, @Param("example") order_infoExample example);

    int updateByExample(@Param("record") order_info record, @Param("example") order_infoExample example);

    int updateByPrimaryKeySelective(order_info record);

    int updateByPrimaryKey(order_info record);
}