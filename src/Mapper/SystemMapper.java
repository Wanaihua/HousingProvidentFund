package Mapper;

import Bean.SelectValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SystemMapper {
    @Insert("insert into t_system(SEQ,SEQNAME,SEQVALUE) values(#{SEQ},#{SEQNAME},#{SEQVALUE})")
    int insertOneSystem(Bean.System system);
    @Update("update t_system set SEQNAME=#{SEQNAME},SEQ=#{SEQ},MAXSEQ=#{MAXSEQ},DESC=#{DESC},FREEUSE1=#{FREEUSE1} where id=#{id}")
    int updateOneSystem(Bean.System system);
    @Delete("delete from t_system where SEQNAME=#{SEQNAME}")
    int deleteOneSystemBySEQNAME(String SEQNAME);
    @Delete("delete from t_system where SEQ=#{SEQ}")
    int deleteOneSystemBySEQ(String SEQ);
    @Select("select count(*) from t_system")
    int selectSystemNumber();
    @Select("select * from t_system")
    List<Bean.System> selectAllSystem();
    @Select("select * from t_system where SEQ=#{SEQ}")
    List<Bean.System> selectAllSystemBySEQ(Integer SEQ);
    @Select("select * from t_system where SEQNAME=#{SEQNAME}")
    List<Bean.System> selectSystemBySomething(SelectValue systemSelectValue);
    @Select("select MAXSEQ from t_system where SEQNAME=#{param1}")
    String findMAXSEQ(String SEQNAME);
    @Update("update t_system set SEQ=#{param1} where SEQNAME=#{param2}")
    void updateSEQ(String nextNumber, String unitaccnum);
    @Select("select MAX(SEQ) from t_system where SEQNAME=#{param1}")
    String findMAXNUM(String unitaccnum);
}
