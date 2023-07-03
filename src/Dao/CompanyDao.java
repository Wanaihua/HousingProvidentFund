package Dao;

import Bean.Company;
import Bean.CompanyForExcel;
import Bean.SelectValue;
import Mapper.CompanyMapper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.ibatis.session.SqlSession;
import MybatisUtils.MybatisUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CompanyDao {

    public int insertOneCompany(Company company) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        int n = companyMapper.insertOneCompany(company);
        sqlSession.commit();
        sqlSession.close();
        return n;
    }

    public int updateOneCompany(Company company) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        int n = companyMapper.updateOneCompany(company);
        sqlSession.commit();
        sqlSession.close();
        return n;
    }

    public int deleteOneCompany(String UNITACCNUM) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        int n = companyMapper.deleteOneCompany(UNITACCNUM);
        sqlSession.close();
        return n;
    }

    public List<Company> selectAllCompany() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectAllCompany();
        sqlSession.close();
        return list;
    }

    public List<Company> selectCompanyBySomething(String param1, String param2) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectCompanyBySomething(param1, param2);
        sqlSession.close();
        return list;
    }

    public List<Company> selectCompanyByLike(SelectValue selectValue) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectCompanyByLike(selectValue);
        sqlSession.close();
        return list;
    }

    public List<Company> selectCompanyByBetween(SelectValue selectValue) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectCompanyByBetween(selectValue);
        sqlSession.close();
        return list;
    }

    public Integer selectCompanyNumber() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        int n = companyMapper.selectCompanyNumber();
        sqlSession.close();
        return n;
    }

    public String changeIntegerToString(Object number) {
        String re = String.valueOf(number);
        SystemDao dao=new SystemDao();
        int len=dao.selectAllSystem().get(0).getMAXSEQ().length();
        while (re.length() < len) {
            re = "0" + re;
        }
        return re;
    }

    public String selectCompanyNextNumber() {
        int n = this.selectCompanyNumber() + 1;
        String re = this.changeIntegerToString(n);
        for (int i = 1; i <= n; i++) {
            String number = this.changeIntegerToString(i);
            List<Company> list = this.selectCompanyBySomething("UNITACCNUM", number);
            if (list.size() == 0) {
                re = number;
            }
        }
        return re;
    }

    public Company selectCompanyByUNITACCNUMAndUNITACCNAME(String UNITACCNUM, String UNITACCNAME) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectCompanyByUNITACCNUMAndUNITACCNAME(UNITACCNUM, UNITACCNAME);
        sqlSession.close();
        return list.size() == 0 ? null : list.get(0);
    }

    public Company selectCompanyByUNITACCNUMAndPASS(String num, String pass) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectCompanyByUNITACCNUMAndPASS(num, pass);
        sqlSession.close();
        return list.size() == 0 ? null : list.get(0);
    }

    public List<Company> selectAllCompanyByPage(int page, int limit) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectAllCompanyByPage(page, limit);
        sqlSession.close();
        return list;
    }

    public void insertCompanysByExcel(String companyfilePath) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        EasyExcel.read(companyfilePath, CompanyForExcel.class, new AnalysisEventListener<CompanyForExcel>() {
            @Override
            public void invoke(CompanyForExcel o, AnalysisContext analysisContext) {
               CompanyDao companyDao=new CompanyDao();
               Company company=new Company(
                       companyDao.selectCompanyNextNumber(),
                       o.getUNITACCNAME(),
                       o.getUNITADDR(),
                       o.getORGCODE(),
                       "1",
                       "110",
                       "1",
                       o.getUNITPHONE(),
                       o.getUNITLINKMAN(),
                       "200100198825262827",
                       "0",
                       "0.00",
                       "0.00",
                       "0.000",
                       "0.000",
                       "0.00",
                       "0.00",
                       "0",
                       "1899-12-01",
                       "0110",
                       "111111",
                       new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                       ""
               );
                companyMapper.insertOneCompany(company);
                sqlSession.commit();
            }
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("public void insertUsersByExcel(String filePath) ---> Finish");
            }
        }).sheet().doRead();
    }

    public Company selectCompanyByNUM(String unitaccnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        List<Company> list = companyMapper.selectCompanyByNUM(unitaccnum);
        sqlSession.close();
        return list.size() == 0 ? null : list.get(0);
    }

    public void updateCompanyPayDate(String nowDate, String unitaccnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        companyMapper.updateCompanyPayDate(nowDate, unitaccnum);
        sqlSession.commit();
        sqlSession.close();
    }

    public void logoutCompany(String num) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        companyMapper.logoutCompany(num);
        sqlSession.commit();
        sqlSession.close();
    }

    public Company selectCompanyByUNITACCNUM(int unitaccnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        Company company = companyMapper.selectCompanyByUNITACCNUM(unitaccnum);
        sqlSession.close();
        return company;
    }

    public void updateCompanyPERSNUM(Company company) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        companyMapper.updateCompanyPERSNUM(company);
        sqlSession.commit();
        sqlSession.close();
    }

    public void updateOldCompanyPERSNUM(String oldUnitAccnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        String PERSNUM = companyMapper.findNowPERSNUM(oldUnitAccnum);
        PERSNUM=String.valueOf(Integer.parseInt(PERSNUM)-1);
        companyMapper.updateOldCompanyPERSNUM(PERSNUM,oldUnitAccnum);
        sqlSession.commit();
        sqlSession.close();
    }

    public void updateNewCompanyPERSNUM(String unitaccnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        String PERSNUM = companyMapper.findNowPERSNUM(unitaccnum);
        PERSNUM=String.valueOf(Integer.parseInt(PERSNUM)+1);
        companyMapper.updateNewCompanyPERSNUM(PERSNUM,unitaccnum);
        sqlSession.commit();
        sqlSession.close();
    }
}
