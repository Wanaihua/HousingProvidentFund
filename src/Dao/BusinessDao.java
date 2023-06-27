package Dao;

import Bean.Business;
import Mapper.BusinessMapper;
import MybatisUtils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BusinessDao {
    public void insertOneBusiness(Business business) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BusinessMapper businessMapper = sqlSession.getMapper(BusinessMapper.class);
        businessMapper.insertOneBusiness(business);
        sqlSession.commit();
        sqlSession.close();
    }

    public List<Business> selectAllBusinessByPage(int page,int limit) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BusinessMapper businessMapper = sqlSession.getMapper(BusinessMapper.class);
        List<Business> list = businessMapper.selectAllBusinessByPage(page,limit);
        sqlSession.close();
        return list;
    }

    public List<Business> selectAllBusiness() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BusinessMapper businessMapper = sqlSession.getMapper(BusinessMapper.class);
        List<Business> list = businessMapper.selectAllBusiness();
        sqlSession.close();
        return list;
    }
}
