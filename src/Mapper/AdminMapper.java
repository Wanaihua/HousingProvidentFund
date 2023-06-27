package Mapper;

import Bean.Admin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
    @Select("select * from t_admin where username=#{param1} and userpass=#{param2}")
    Admin LoginAdmin(String username, String userpass);

    @Update("update t_admin set userpass=#{param2} where id=#{param1}")
    boolean updateAdmin(String id, String password);

    @Select("select * from t_admin where userpass=#{param2} and id=#{param1}")
    Admin LoginAdminTest(String id, String oldPassword);
}
