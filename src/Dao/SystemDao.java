package Dao;

import Bean.SelectValue;
import Bean.System;
import Mapper.SystemMapper;
import org.apache.ibatis.session.SqlSession;
import MybatisUtils.MybatisUtils;

import java.util.List;

public class SystemDao {

    public int insertOneSystem(System system) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        int n = systemMapper.insertOneSystem(system);
        sqlSession.close();
        return n;
    }

    public int updateOneSystem(System system) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        int n = systemMapper.updateOneSystem(system);
        sqlSession.close();
        return n;

    }

    public int deleteOneSystemBySEQNAME(String SEQNAME) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        int n = systemMapper.deleteOneSystemBySEQNAME(SEQNAME);
        sqlSession.close();
        return n;
    }

    public int deleteOneSystemBySEQ(String SEQ) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        int n = systemMapper.deleteOneSystemBySEQ(SEQ);
        sqlSession.close();
        return n;
    }

    public List<System> selectAllSystem() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        List<System> list = systemMapper.selectAllSystem();
        sqlSession.close();
        return list;
    }

    public List<Bean.System> selectSystemBySomething(SelectValue systemSelectValue){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        List<System> list = systemMapper.selectSystemBySomething(systemSelectValue);
        sqlSession.close();
        return list;
    }

    public int selectSystemNumber() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        int n = systemMapper.selectSystemNumber();
        sqlSession.close();
        return n;
    }

    public Integer selectSystemNextNumber() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int n = this.selectSystemNumber() + 1;
        for (int i = 1; i <= n; i++) {
            SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
            List<System> list = systemMapper.selectAllSystemBySEQ(i);
            if (list.size() == 0) {
                n = i;
            }
        }
        return n;
    }

    public boolean checkNumber(String unitaccnum, String nextNumber) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SystemMapper systemMapper = sqlSession.getMapper(SystemMapper.class);
        String MAXSEQ=systemMapper.findMAXSEQ(unitaccnum);
        if(Integer.parseInt(nextNumber)<=Integer.parseInt(MAXSEQ)){
            String MAXNUM=systemMapper.findMAXNUM(unitaccnum);
            if(Integer.parseInt(nextNumber)<=Integer.parseInt(MAXNUM)) {
                systemMapper.updateSEQ(MAXNUM,unitaccnum);
            }else{
                systemMapper.updateSEQ(nextNumber,unitaccnum);
            }
            sqlSession.commit();
            sqlSession.close();
            return true;
        }else{
            sqlSession.close();
            return false;
        }

    }
}
