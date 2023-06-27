package Dao;

import Bean.Company;
import Bean.Teller;
import Mapper.CompanyMapper;
import Mapper.TellerMapper;
import MybatisUtils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TellerDao {
    public Teller LoginTeller(String username, String userpass) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        Teller teller = tellerMapper.LoginTeller(username, userpass);
        sqlSession.close();
        return teller;
    }

    public String selectTellerNextNumber() {
        int n = this.selectCompanyNumber() + 1;
        String re = this.changeIntegerToString(n);
        for (int i = 1; i <= n; i++) {
            String number = this.changeIntegerToString(i);
            List<Teller> list = this.selectCompanyBySomething("TELLERACCNUM", number);
            if (list.size() == 0) {
                re = number;
            }
        }
        return re;
    }

    private List<Teller> selectCompanyBySomething(String telleraccnum, String number) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        List<Teller> list = tellerMapper.selectTellerBySomething(telleraccnum, number);
        sqlSession.close();
        return list;
    }

    public Integer selectCompanyNumber() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        int n = tellerMapper.selectTellerNumber();
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

    public void insertOneTeller(Teller teller) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        tellerMapper.insertOneTeller(teller);
        sqlSession.commit();
        sqlSession.close();
    }

    public Teller selectOneTellerByNUM(String telleraccnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        Teller teller = tellerMapper.selectOneTellerByNUM(telleraccnum);
        sqlSession.close();
        return teller;
    }

    public void deleteOneTellerByNUM(String telleraccnum) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        tellerMapper.deleteOneTellerByNUM(telleraccnum);
        sqlSession.commit();
        sqlSession.close();
    }

    public List<Teller> selectAllTeller() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        List<Teller> list = tellerMapper.selectAllTeller();
        sqlSession.close();
        return list;
    }

    public List<Teller> selectAllTellerByPage(int i, int limit) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        List<Teller> list = tellerMapper.selectAllTellerByPage(i, limit);
        sqlSession.close();
        return list;
    }

    public void updateOneTeller(Teller teller) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        tellerMapper.updateOneTeller(teller);
        sqlSession.commit();
        sqlSession.close();
    }

    public String findNameByid(String id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        String name = tellerMapper.findNameByid(id);
        sqlSession.close();
        return name;
    }

    public boolean LoginTellerTest(String id, String oldPassword) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        String password = tellerMapper.LoginTellerTest(id,oldPassword);
        sqlSession.close();
        if (password.equals(oldPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateTeller(String id, String password) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TellerMapper tellerMapper = sqlSession.getMapper(TellerMapper.class);
        tellerMapper.updateTeller(id,password);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
