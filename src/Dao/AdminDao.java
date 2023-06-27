package Dao;

import Bean.Admin;
import Mapper.AdminMapper;
import MybatisUtils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.ResultSet;

public class AdminDao {
    public Admin LoginAdmin(String username, String userpass) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = adminMapper.LoginAdmin(username, userpass);
        sqlSession.close();
        return admin;
    }

    public boolean updateAdmin(String id, String password) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        boolean flag = adminMapper.updateAdmin(id, password);
        sqlSession.commit();
        sqlSession.close();
        return flag;
    }

    public boolean LoginAdminTest(String id, String oldPassword) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin= adminMapper.LoginAdminTest(id, oldPassword);
        sqlSession.close();
        return  admin!=null;
    }
}
