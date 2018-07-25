package com.shopping.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuon.pojo.TbCouponLogs;
import org.kuon.pojo.TbCouponLogsExample;

import java.util.List;

public interface TbCouponLogsMapper {
    int countByExample(TbCouponLogsExample example);

    int deleteByExample(TbCouponLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbCouponLogs record);

    int insertSelective(TbCouponLogs record);

    List<TbCouponLogs> selectByExample(TbCouponLogsExample example);

    TbCouponLogs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbCouponLogs record, @Param("example") TbCouponLogsExample example);

    int updateByExample(@Param("record") TbCouponLogs record, @Param("example") TbCouponLogsExample example);

    int updateByPrimaryKeySelective(TbCouponLogs record);

    int updateByPrimaryKey(TbCouponLogs record);
}