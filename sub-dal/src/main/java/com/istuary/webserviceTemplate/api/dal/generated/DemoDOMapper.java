package com.istuary.webserviceTemplate.api.dal.generated;

import com.istuary.webserviceTemplate.api.dal.generated.DemoDO;
import com.istuary.webserviceTemplate.api.dal.generated.DemoDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DemoDOMapper {
    int countByExample(DemoDOCriteria example);

    int deleteByExample(DemoDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(DemoDO record);

    int insertSelective(DemoDO record);

    List<DemoDO> selectByExampleWithRowbounds(DemoDOCriteria example, RowBounds rowBounds);

    List<DemoDO> selectByExample(DemoDOCriteria example);

    DemoDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DemoDO record, @Param("example") DemoDOCriteria example);

    int updateByExample(@Param("record") DemoDO record, @Param("example") DemoDOCriteria example);

    int updateByPrimaryKeySelective(DemoDO record);

    int updateByPrimaryKey(DemoDO record);
}