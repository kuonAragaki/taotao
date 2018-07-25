package com.shopping.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuon.pojo.TbCouponReceive;
import org.kuon.pojo.TbCouponReceiveExample;

import java.util.List;

public interface TbCouponReceiveMapper {
    int countByExample(TbCouponReceiveExample example);

    int deleteByExample(TbCouponReceiveExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbCouponReceive record);

    int insertSelective(TbCouponReceive record);

    List<TbCouponReceive> selectByExample(TbCouponReceiveExample example);

    TbCouponReceive selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbCouponReceive record, @Param("example") TbCouponReceiveExample example);

    int updateByExample(@Param("record") TbCouponReceive record, @Param("example") TbCouponReceiveExample example);

    int updateByPrimaryKeySelective(TbCouponReceive record);

    int updateByPrimaryKey(TbCouponReceive record);
}