package com.bm001.util;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    public static final String DESC = "DESC";

    public static final String ASC = "ASC";

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
                // 如果字段以Like结尾,PO中有相对于属性，并且是String类型，生成Like
                if (fieldName.length() > 4 && fieldName.startsWith("Like", fieldName.length() - 4)
                    && t.getDeclaredField(fieldName.substring(0, fieldName.length() - 4)) != null
                    && obj instanceof String) {
                    String colName = fieldName.substring(0, fieldName.length() - 4);
                    queryWrapper.like(humpToLine(colName), obj);
                    // 以List结尾并且是List类型，PO中有相对于属性，生成in
                } else if (fieldName.length() > 4 && fieldName.startsWith("List", fieldName.length() - 4)
                    && t.getDeclaredField(fieldName.substring(0, fieldName.length() - 4)) != null
                    && obj instanceof List) {
                    String colName = fieldName.substring(0, fieldName.length() - 4);
                    queryWrapper.in(humpToLine(colName), (List)obj);
                    // 如果去除min/max前缀后再po中有对应属性，且存在另外一个max/min的属性才生成大于/小于子句
                    // 成立例子:po中存在变量 date，form/condition中有变量maxDate与minDate才生成大于/小于子句
                } else if (fieldName.length() > 3 && fieldName.startsWith("min")
                    && condition.getClass().getDeclaredField(fieldName.replaceFirst("min", "max")) != null
                    && t.getDeclaredField(lowerFirst(fieldName.replaceFirst("min", ""))) != null) {
                    String colName = fieldName.replaceFirst("min", "");
                    queryWrapper.gt(humpToLine(colName), obj);
                } else if (fieldName.length() > 3 && fieldName.startsWith("max")
                    && condition.getClass().getDeclaredField(fieldName.replaceFirst("max", "min")) != null
                    && t.getDeclaredField(lowerFirst(fieldName.replaceFirst("max", ""))) != null) {
                    String colName = fieldName.replaceFirst("max", "");
                    queryWrapper.lt(humpToLine(colName), obj);
                    // 生成普通的eq子句
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
