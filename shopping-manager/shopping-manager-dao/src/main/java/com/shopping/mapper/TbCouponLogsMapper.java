package com.shopping.mapper;

import com.shopping.pojo.TbCouponLogs;
import com.shopping.pojo.TbCouponLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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