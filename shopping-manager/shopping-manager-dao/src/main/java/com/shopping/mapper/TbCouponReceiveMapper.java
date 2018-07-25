package com.shopping.mapper;

import com.shopping.pojo.TbCouponReceive;
import com.shopping.pojo.TbCouponReceiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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