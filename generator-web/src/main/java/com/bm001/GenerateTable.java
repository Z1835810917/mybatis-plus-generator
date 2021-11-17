package com.bm001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 创建基表
 * 
 * @program: mybatis-plus-generator
 * @description:
 * @author: chenmingjun
 * @create: 2021-11-17 15:49
 */

public class GenerateTable {

    // 主键类型枚举
    private static final String VARCHAR = "varchar";
    private static final String INT = "int";
    private static final String BIGINT = "bigint";

    // 表名称
    private static final String TABLE_NAME = "pc_table_test2";
    // 表注释
    private static final String COMMENT = "测试创建表";
    // 主键类型
    private static final String PRIMARY_KEY_TYPE = VARCHAR;

    private static final String URL =
        "jdbc:mysql://172.19.70.38:3306/supdb?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        createTable();
    }

    public static void createTable() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            Statement statement=connection.createStatement();
            statement.executeUpdate("CREATE TABLE `" + TABLE_NAME + "`  (" + "  `id` " + PRIMARY_KEY_TYPE
                + "(32)  NOT NULL COMMENT '编号',"
                + "  `opt_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人编号',"
                + "  `opt_user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人姓名',"
                + "  `opt_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',"
                + "  `add_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人编号',"
                + "  `add_user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '添加人姓名',"
                + "  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',"
                + "  `delete_at` bigint(14) NULL DEFAULT 0 COMMENT '删除时间'," + "  PRIMARY KEY (`id`) USING BTREE"
                + ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '" + COMMENT
                + "' ROW_FORMAT = Compact;");

            System.out.println("创建基表成功！表名称："+TABLE_NAME);
        } catch (Exception e) {
            System.out.println("创建基表错误：原因：");
            e.printStackTrace();

        }
    }
}
