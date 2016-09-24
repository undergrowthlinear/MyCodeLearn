package mycodelearn.undergrowth.bteevolutionprovidersqlmapper.mapper;

import java.math.BigDecimal;
import java.util.List;
import mycodelearn.undergrowth.bteevolutionprovidersqlmapper.model.UnderTest;
import mycodelearn.undergrowth.bteevolutionprovidersqlmapper.model.UnderTestExample;
import org.apache.ibatis.annotations.Param;

public interface UnderTestMapper {
    int countByExample(UnderTestExample example);

    int deleteByExample(UnderTestExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(UnderTest record);

    int insertSelective(UnderTest record);

    List<UnderTest> selectByExample(UnderTestExample example);

    UnderTest selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") UnderTest record, @Param("example") UnderTestExample example);

    int updateByExample(@Param("record") UnderTest record, @Param("example") UnderTestExample example);

    int updateByPrimaryKeySelective(UnderTest record);

    int updateByPrimaryKey(UnderTest record);
}