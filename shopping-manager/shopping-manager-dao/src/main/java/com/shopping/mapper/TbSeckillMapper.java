package com.shopping.mapper;

import com.shopping.pojo.TbSeckill;
import com.shopping.pojo.TbSeckillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSeckillMapper {
    int countByExample(TbSeckillExample example);

    int deleteByExample(TbSeckillExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbSeckill record);

    int insertSelective(TbSeckill record);

    List<TbSeckill> selectByExample(TbSeckillExample example);

    TbSeckill selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbSeckill record, @Param("example") TbSeckillExample example);

    int updateByExample(@Param("record") TbSeckill record, @Param("example") TbSeckillExample example);

    int updateByPrimaryKeySelective(TbSeckill record);

    int updateByPrimaryKey(TbSeckill record);
}