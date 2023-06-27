package Mapper;

import Bean.Teller;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TellerMapper {
    @Select("select * from t_teller where TELLERNAME=#{param1} and TELLERPASS=#{param2}")
    Teller LoginTeller(String username, String userpass);
    @Select("select max(TELLERACCNUM) from t_teller")
    int selectTellerNumber();
    @Select("select * from t_teller where ${param1}=#{param2}")
    List<Teller> selectTellerBySomething(String TELLEACCRNUM, String number);
    @Insert("insert into t_teller(TELLERACCNUM,TELLERNAME,TELLERCARD,TELLERPHONE,TELLERREMAKE,TELLERPASS) values(#{TELLERACCNUM},#{TELLERNAME},#{TELLERCARD},#{TELLERPHONE},#{TELLERREMAKE},#{TELLERPASS})")
    void insertOneTeller(Teller teller);
    @Select("select * from t_teller where TELLERACCNUM=#{param1}")
    Teller selectOneTellerByNUM(String telleraccnum);
    @Delete("delete from t_teller where TELLERACCNUM=#{param1}")
    void deleteOneTellerByNUM(String telleraccnum);
    @Select("select * from t_teller")
    List<Teller> selectAllTeller();
    @Select("select * from t_teller limit #{param1},#{param2}")
    List<Teller> selectAllTellerByPage(int i, int limit);
    @Update("update t_teller set TELLERNAME=#{TELLERNAME},TELLERCARD=#{TELLERCARD},TELLERPHONE=#{TELLERPHONE},TELLERREMAKE=#{TELLERREMAKE} where TELLERACCNUM=#{TELLERACCNUM}")
    void updateOneTeller(Teller teller);
    @Select("select TELLERNAME from t_teller where TELLERACCNUM=#{param1}")
    String findNameByid(String id);
    @Select("select TELLERPASS from t_teller where TELLERACCNUM=#{param1} and TELLERPASS=#{param2}")
    String LoginTellerTest(String id, String oldPassword);
    @Update("update t_teller set TELLERPASS=#{param2} where TELLERACCNUM=#{param1}")
    void updateTeller(String id, String password);
}
