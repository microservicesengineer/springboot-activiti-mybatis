package com.ibm.vms.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ibm.vms.entity.db.runoob_tbl;
import com.ibm.vms.entity.db.runoob_tblExample;

public interface runoob_tblMapper {
    int deleteByExample(runoob_tblExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(runoob_tbl record);

    int insertSelective(runoob_tbl record);

    List<runoob_tbl> selectByExample(runoob_tblExample example);

    runoob_tbl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") runoob_tbl record, @Param("example") runoob_tblExample example);

    int updateByExample(@Param("record") runoob_tbl record, @Param("example") runoob_tblExample example);

    int updateByPrimaryKeySelective(runoob_tbl record);

    int updateByPrimaryKey(runoob_tbl record);
}