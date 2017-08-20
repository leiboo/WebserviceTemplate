package com.istuary.webserviceTemplate.api.dal.generated;

import com.istuary.webserviceTemplate.api.dal.generated.UserDO;
import com.istuary.webserviceTemplate.api.dal.generated.UserDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserDOMapper {
    int countByExample(UserDOCriteria example);

    int deleteByExample(UserDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    List<UserDO> selectByExampleWithRowbounds(UserDOCriteria example, RowBounds rowBounds);

    List<UserDO> selectByExample(UserDOCriteria example);

    UserDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDO record, @Param("example") UserDOCriteria example);

    int updateByExample(@Param("record") UserDO record, @Param("example") UserDOCriteria example);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}