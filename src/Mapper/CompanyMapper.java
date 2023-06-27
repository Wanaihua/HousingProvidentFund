package Mapper;

import Bean.Company;
import Bean.SelectValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CompanyMapper {
    @Insert("insert into t_company(UNITACCNUM,UNITACCNAME,UNITADDR,ORGCODE,UNITCHAR,UNITKIND,SALARYDATE,UNITPHONE,UNITLINKMAN,UNITAGENTPAPNO,ACCSTATE,BALANCE,BASENUMBER,UNITPROP,PERPROP,UNITPAYSUM,PERPAYSUM,PERSNUM,LASTPAYDATE,INSTCODE,OP,CREATDATE,REMARK) values(#{UNITACCNUM},#{UNITACCNAME},#{UNITADDR},#{ORGCODE},#{UNITCHAR},#{UNITKIND},#{SALARYDATE},#{UNITPHONE},#{UNITLINKMAN},#{UNITAGENTPAPNO,jdbcType=VARCHAR},#{ACCSTATE},#{BALANCE},#{BASENUMBER},#{UNITPROP},#{PERPROP},#{UNITPAYSUM},#{PERPAYSUM},#{PERSNUM},#{LASTPAYDATE},#{INSTCODE},#{OP},#{CREATDATE},#{REMARK,jdbcType=VARCHAR})")
    int insertOneCompany(Company company);
    @Update("update t_company set UNITACCNAME=#{UNITACCNAME},UNITADDR=#{UNITADDR},ORGCODE=#{ORGCODE},UNITCHAR=#{UNITCHAR},UNITKIND=#{UNITKIND},SALARYDATE=#{SALARYDATE},UNITPHONE=#{UNITPHONE},UNITLINKMAN=#{UNITLINKMAN},UNITAGENTPAPNO=#{UNITAGENTPAPNO,jdbcType=VARCHAR},ACCSTATE=#{ACCSTATE},BALANCE=#{BALANCE},BASENUMBER=#{BASENUMBER},UNITPROP=#{UNITPROP},PERPROP=#{PERPROP},UNITPAYSUM=#{UNITPAYSUM},PERPAYSUM=#{PERPAYSUM},PERSNUM=#{PERSNUM},LASTPAYDATE=#{LASTPAYDATE},INSTCODE=#{INSTCODE},OP=#{OP},CREATDATE=#{CREATDATE},REMARK=#{REMARK,jdbcType=VARCHAR} where UNITACCNUM=#{UNITACCNUM}")
    int updateOneCompany(Company company);
    @Delete("delete from t_company where UNITACCNUM=#{UNITACCNUM}")
    int deleteOneCompany(String UNITACCNUM);
    @Select("select count(*) from t_company")
    int selectCompanyNumber();
    @Select("select * from t_company where ACCSTATE='0'")
    List<Company> selectAllCompany();
    @Select("select * from t_company where ${param1} like #{param2}")
    List<Company> selectCompanyBySomething(String param1,String param2);
    @Select("select * from t_company where ${param1} between #{param2} and #{param3}")
    List<Company> selectCompanyByLike(SelectValue selectValue);
    @Select("select * from t_company where ${param1} between #{param2} and #{param3}")
    List<Company> selectCompanyByBetween(SelectValue selectValue);
    @Select("select * from t_company where UNITACCNUM=#{UNITACCNUM} and UNITACCNAME=#{UNITACCNAME}")
    List<Company> selectCompanyByUNITACCNUMAndUNITACCNAME(String UNITACCNUM,String UNITACCNAME);
    @Select("select * from t_company where UNITACCNUM=#{param1} and PASS=#{param2}")
    List<Company> selectCompanyByUNITACCNUMAndPASS(String num, String pass);
    @Select("select * from t_company where ACCSTATE='0' limit #{param1},#{param2}")
    List<Company> selectAllCompanyByPage(int page, int limit);
    @Select("select * from t_company where UNITACCNUM=#{param1} and ACCSTATE='0'")
    List<Company> selectCompanyByNUM(String unitaccnum);
    @Update("update t_company set LASTPAYDATE=#{param1} where UNITACCNUM=#{param2}")
    void updateCompanyPayDate(String nowDate, String unitaccnum);
    @Update("update t_company set ACCSTATE='9' where UNITACCNUM=#{param1}")
    void logoutCompany(String num);
    @Select("select * from t_company where UNITACCNUM=#{param1}")
    Company selectCompanyByUNITACCNUM(int unitaccnum);
    @Update("update t_company set PERSNUM=#{PERSNUM} where UNITACCNUM=#{UNITACCNUM}")
    void updateCompanyPERSNUM(Company company);
}
