package Servlet;

import Bean.*;
import Bean.System;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

public class GsonUtil {
    public static String simpleObjToJson(Object obj) {
        if (Objects.isNull(obj)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String simpleCompanyListToJson(List<Company> list) {
        if (Objects.isNull(list)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String simpleUserListToJson(List<User> list){
        if (Objects.isNull(list)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String simpleSystemListToJson(List<System> list){
        if (Objects.isNull(list)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String simpleBusinessListToJson(List<Business> list) {
        if (Objects.isNull(list)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String simpleTellerListToJson(List<Teller> list) {
        if (Objects.isNull(list)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
