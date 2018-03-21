package com.mybatis.dao;

import com.mybatis.pojo.Kinds;
import com.mybatis.pojo.KindsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KindsMapper {
    long countByExample(KindsExample example);

    int deleteByExample(KindsExample example);

    int deleteByPrimaryKey(Integer kId);

    int insert(Kinds record);

    int insertSelective(Kinds record);

    List<Kinds> selectByExample(KindsExample example);

    Kinds selectByPrimaryKey(Integer kId);

    int updateByExampleSelective(@Param("record") Kinds record, @Param("example") KindsExample example);

    int updateByExample(@Param("record") Kinds record, @Param("example") KindsExample example);

    int updateByPrimaryKeySelective(Kinds record);

    int updateByPrimaryKey(Kinds record);
}