package Mapper;

import Bean.Business;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BusinessMapper {
    @Insert("insert into t_business (businessMessage,time,user) values (#{businessMessage},#{time},#{user})")
    void insertOneBusiness(Business business);
    @Select("select * from t_business limit #{param1},#{param2}")
    List<Business> selectAllBusinessByPage(int page, int limit);
    @Select("select * from t_business")
    List<Business> selectAllBusiness();
}
