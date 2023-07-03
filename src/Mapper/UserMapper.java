package Mapper;

import Bean.Company;
import Bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Insert("insert into t_user(ACCNUM,UNITACCNUM,NAME,TYPE,NUM,OPENDATE,BALANCE,PERACCSTATE,BASENUMBER,UNITPROP,INDIPROP,YPAYAMT,LASTPAYDATE,UNITMONPAYSUM,PERMONPAYSUM,YDRAWAMT,YINTERESTBAL,INSTCODE,OP,REMARK) values (#{ACCNUM},#{UNITACCNUM},#{NAME},#{TYPE},#{NUM},#{OPENDATE,jdbcType=VARCHAR},#{BALANCE,jdbcType=VARCHAR},#{PERACCSTATE,jdbcType=VARCHAR},#{BASENUMBER,jdbcType=VARCHAR},#{UNITPROP,jdbcType=VARCHAR},#{INDIPROP,jdbcType=VARCHAR},#{YPAYAMT,jdbcType=VARCHAR},#{LASTPAYDATE,jdbcType=VARCHAR},#{UNITMONPAYSUM,jdbcType=VARCHAR},#{PERMONPAYSUM,jdbcType=VARCHAR},#{YDRAWAMT,jdbcType=VARCHAR},#{YINTERESTBAL,jdbcType=VARCHAR},#{INSTCODE},#{OP},#{REMARK,jdbcType=VARCHAR})")
    int insertOneUser(User user);
    @Update("update t_user set UNITACCNUM=#{UNITACCNUM},NAME=#{NAME},TYPE=#{TYPE},NUM=#{NUM},OPENDATE=#{OPENDATE,jdbcType=VARCHAR},BALANCE=#{BALANCE,jdbcType=VARCHAR},PERACCSTATE=#{PERACCSTATE,jdbcType=VARCHAR},BASENUMBER=#{BASENUMBER,jdbcType=VARCHAR},UNITPROP=#{UNITPROP,jdbcType=VARCHAR},INDIPROP=#{INDIPROP,jdbcType=VARCHAR},LASTPAYDATE=#{LASTPAYDATE,jdbcType=VARCHAR},UNITMONPAYSUM=#{UNITMONPAYSUM,jdbcType=VARCHAR},PERMONPAYSUM=#{PERMONPAYSUM,jdbcType=VARCHAR},YPAYAMT=#{YPAYAMT,jdbcType=VARCHAR},YDRAWAMT=#{YDRAWAMT,jdbcType=VARCHAR},YINTERESTBAL=#{YINTERESTBAL,jdbcType=VARCHAR},INSTCODE=#{INSTCODE},OP=#{OP},REMARK=#{REMARK,jdbcType=VARCHAR} where ACCNUM=#{ACCNUM}")
    int updateOneUser(User user);
    @Delete("delete from t_user where ACCNUM=#{ACCNUM}")
    int deleteOneUser(String ACCNUM);
    @Select("select count(*) from t_user")
    int selectUserNumber();
    @Select("select count(*) from t_user where #{param1}=#{param2}")
    int selectExictUserNumber(String param1,String param2);
    @Select("select * from t_user")
    List<User> selectAllUser();
    @Select("select * from t_user where UNITACCNUM=#{param1}")
    List<User> selectUserBySomething(String param);
    @Select("select * from t_user where #{param1}=#{param2} and UNITACCNUM=#{param3}")
    List<User> selectUserByLike(String typename,String value1, String UNITACCNUM);
    @Select("select * from t_user where #{param1} between #{param2} and #{param3} and UNITACCNUM=#{param4}")
    List<User> selectUserByBetween(String typename,String value1,String value2, String UNITACCNUM);
    @Select("select * from t_user where ACCNUM=#{param1} and NAME=#{param2}")
    List<User> selectUserByACCNUMAndNAME(String ACCNUM,String NAME);
    @Select("select * from t_user where ACCNUM=#{param1} and PASS=#{param2}")
    List<User> selectUserByACCNUMAndPASS(String num, String pass);
    @Select("select * from t_user limit #{param1},#{param2}")
    List<User> selectAllUserByPage(int i, int limit);
    @Select("select UNITACCNUM from t_user where ACCNUM=#{param1}")
    int findUnitAccnumByAccnum(String accnum);
    @Select("select * from t_user where ACCNUM=#{param1}")
    List<User> selectUserByACCNUM(String accnum);
    @Select("select * from t_user where ACCNUM=#{param1}")
    List<User> selectUserByNUM(String accnum);
    @Update("update t_user set PERACCSTATE='9' where ACCNUM=#{param1}")
    void lockUser(int accnum);
    @Update("update t_user set PERACCSTATE='0' where ACCNUM=#{param1}")
    void openUser(int accnum);
    @Select("select NAME from t_user where ACCNUM=#{param1}")
    String selectUserNameByAccnum(int accnum);
    @Select("select BALANCE from t_user where ACCNUM=#{param1}")
    String selectBalanceByAccnum(int accnum);
    @Update("update t_user set BALANCE=#{param2} where ACCNUM=#{param1}")
    void setBalanceByAccnum(int accnum, int i);
    @Select("select UNITACCNUM from t_user where ACCNUM=#{param1}")
    String selectOldUnitAccnumByACCNUM(String accnum);
}
