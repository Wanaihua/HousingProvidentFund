package Dao;

import Bean.Company;
import Bean.SelectValue;
import Bean.User;
import Bean.UserForExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import Mapper.CompanyMapper;
import Mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import MybatisUtils.MybatisUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDao {
    public void insertUsersByExcel(String filePath, final Company company){
        EasyExcel.read(filePath, UserForExcel.class, new AnalysisEventListener<UserForExcel>() {
            public void invoke(UserForExcel o, AnalysisContext analysisContext) {
                UserDao dao=new UserDao();
                List<User> list=dao.selectUserBySomething("NUM",o.getNUM());
                //System.out.println(o.getNUM()+":"+list.size());
                if(list.size()==0){
                    User user = new User(
                            dao.selectUserNextNumber(),
                            company.getUNITACCNUM(),
                            o.getNAME(),
                            o.getTYPE(),
                            o.getNUM(),
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            "0.00",
                            "0",
                            o.getBASENUMBER(),
                            company.getUNITPROP(),
                            company.getPERPROP(),
                            "1899-12-01",
                            company.getUNITPAYSUM(),
                            company.getPERPAYSUM(),
                            null,
                            null,
                            null,
                            "0110",
                            "1111",
                            null
                    );
                    dao.insertOneUser(user);
                }else {
                    list.get(0).setPERACCSTATE("0");
                    dao.updateOneUser(list.get(0));
                }
            }

            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("public void insertUsersByExcel(String filePath) ---> Finish");
            }
        }).sheet().doRead();
    }
    public int insertOneUser(User User) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int n = userMapper.insertOneUser(User);
        sqlSession.commit();
        sqlSession.close();
        return n;
    }

    public int updateOneUser(User User) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int n = userMapper.updateOneUser(User);
        sqlSession.commit();
        sqlSession.close();
        return n;
    }
    public void updateCompanyPERSNUM(int  UNITACCNUM){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CompanyMapper companyMapper = sqlSession.getMapper(CompanyMapper.class);
        Company company = companyMapper.selectCompanyByUNITACCNUM(UNITACCNUM);
        String persnum = company.getPERSNUM();
        int n = Integer.parseInt(persnum)-1;
        company.setPERSNUM(String.valueOf(n));
        companyMapper.updateOneCompany(company);
        sqlSession.commit();
        sqlSession.close();
    }
    public int deleteOneUser(String ACCNUM) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int n = userMapper.deleteOneUser(ACCNUM);
        sqlSession.commit();
        sqlSession.close();
        return n;
    }

    public List<User> selectAllUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectAllUser();
        sqlSession.close();
        return list;
    }

    public List<User> selectUserBySomething(String tag, String va) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUserBySomething(tag, va);
        sqlSession.close();
        return list;
    }
    public List<User> selectUserByLike(SelectValue selectValue,Company company) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUserByLike(selectValue.getTypename(),selectValue.getValue1(),company.getUNITACCNUM());
        sqlSession.close();
        return list;
    }
    public List<User> selectUserByBetween(SelectValue selectValue,Company company) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUserByBetween(selectValue.getTypename(),selectValue.getValue1(),selectValue.getValue2(),company.getUNITACCNUM());
        sqlSession.close();
        return list;
    }
    public Integer selectUserNumber() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int n = userMapper.selectUserNumber();
        sqlSession.close();
        return n;
    }

    public int selectExictUserNumber(String param1,String param2){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int n = userMapper.selectExictUserNumber(param1,param2);
        sqlSession.close();
        return n;
    }
    public String changeIntegerToString(Object number) {
        String re = String.valueOf(number);
        SystemDao dao=new SystemDao();
        int len=dao.selectAllSystem().get(0).getMAXSEQ().length();
        while (re.length() < len) {
            re = "0".concat(re);
        }
        return re;
    }

    public String selectUserNextNumber() {
        int n = this.selectUserNumber() + 1;
        String re = this.changeIntegerToString(n);
        for (int i = 1; i <= n; i++) {
            String number = this.changeIntegerToString(i);
            List<User> list = this.selectUserBySomething("ACCNUM", number);
            if (list.size() == 0) {
                re = number;
            }
        }
        return re;
    }

    public User selectUserByACCNUMAndNAME(String ACCNUM, String NAME) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUserByACCNUMAndNAME(ACCNUM, NAME);
        sqlSession.close();
        return list.size() == 0 ? null : list.get(0);
    }

    public User selectUserByACCNUMAndPASS(String num, String pass) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUserByACCNUMAndPASS(num, pass);
        sqlSession.close();
        return list.size() == 0 ? null : list.get(0);
    }

    public List<User> selectAllUserByPage(int i, int limit) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectAllUserByPage(i, limit);
        sqlSession.close();
        return list;
    }

    public int findUnitAccnumByAccnum(String accnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int n = userMapper.findUnitAccnumByAccnum(accnum);
        sqlSession.close();
        return n;
    }

    public User selectUserByACCNUM(String accnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUserByACCNUM(accnum);
        sqlSession.close();
        return list.size() == 0 ? null : list.get(0);
    }

    public User selectUserByNUM(String accnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUserByNUM(accnum);
        sqlSession.close();
        return list.size() == 0 ? null : list.get(0);
    }

    public void lockUser(int accnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.lockUser(accnum);
        sqlSession.commit();
        sqlSession.close();
    }

    public void openUser(int accnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.openUser(accnum);
        sqlSession.commit();
        sqlSession.close();
    }

    public String selectUserNameByAccnum(int accnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String name = userMapper.selectUserNameByAccnum(accnum);
        sqlSession.close();
        return name;
    }

    public String selectBalanceByAccnum(int accnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String balance = userMapper.selectBalanceByAccnum(accnum);
        sqlSession.close();
        return balance;
    }

    public void setBalanceByAccnum(int accnum, int i) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.setBalanceByAccnum(accnum, i);
        sqlSession.commit();
        sqlSession.close();
    }
}
