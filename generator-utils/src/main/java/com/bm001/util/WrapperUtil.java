package com.bm001.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bm001.annotation.TableLike;
import com.bm001.annotation.TableList;
import com.bm001.annotation.TableMax;
import com.bm001.annotation.TableMin;
import java.lang.reflect.Field;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * @program: ehome-sup
 * @description: 查询条件构造器工具类
 * @author: chenmingjun
 * @create: 2021-10-23 10:24
 */

public class WrapperUtil {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static <T> QueryWrapper<T> entityToWrapper(Object condition, Class<T> t) throws Exception {
        // 生成wrapper对象
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        // 反射出所有变量
        Field[] fields = condition.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String fieldName = field.getName();
            // 添加逻辑删除字段条件
            if (fieldName.equals("deleteAt")) {
                queryWrapper.eq("delete_at", 0);
                continue;
            }
            if (fieldName.equals("delFlag")) {
                queryWrapper.eq("del_flag", 0);
                continue;
            }
            Object obj = field.get(condition);
            if (obj != null) {
                    // 生成like语句
                if (field.getAnnotation(TableLike.class) != null) {
                    String colName = field.getAnnotation(TableLike.class).value();
                    queryWrapper.like(colName, obj);
                    // 生成in语句
                } else if (field.getAnnotation(TableList.class) != null) {
                    String colName = field.getAnnotation(TableList.class).value();
                    queryWrapper.in(colName, (List)obj);
                    //生成 >子句
                } else if (field.getAnnotation(TableMin.class) != null) {
                    String colName = field.getAnnotation(TableMin.class).value();
                    queryWrapper.gt(colName, obj);
                    // 生成< 子句
                } else if (field.getAnnotation(TableMax.class) != null) {
                    String colName = field.getAnnotation(TableMax.class).value();
                    queryWrapper.lt(colName, obj);
                    // 生成 = 子句
                } else if (t.getDeclaredField(fieldName) != null) {
                    queryWrapper.eq(humpToLine(fieldName), obj);
                }
            }
        }
        return queryWrapper;
    }

    /**
     * 方法描述 首字母转小写
     *
     * @param: [sourse]
     * @return: java.lang.String
     * @author: chenmingjun
     * @date: 2021/10/24
     */
    private static String lowerFirst(String sourse) {
        if (StringUtils.isEmpty(sourse)) {
            return null;
        }
        char[] chars = sourse.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 方法描述 驼峰转下划线
     * 
     * @param: [str]
     * @return: java.lang.String
     * @author: chenmingjun
     * @date: 2021/11/12
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
