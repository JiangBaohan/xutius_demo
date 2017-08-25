package com.example.demo20_xutils;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

/**
 * data:2017/8/19
 * author:汉堡(Administrator)
 * function:
 */

public class MyApp extends Application {

    private DbManager.DaoConfig daoConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化XUtils
        x.Ext.init(this);
        // 设置日志的级别
        x.Ext.setDebug(BuildConfig.DEBUG);
        // 在初始化中创建数据库
        //表名
//表的版本（用于升级）
//是否允许开启事务（操作数据快）
//打开数据库监听
        daoConfig = new DbManager.DaoConfig()
                .setDbName("users.db")//表名
                .setDbVersion(1)//表的版本（用于升级）
                .setAllowTransaction(true)//是否允许开启事务（操作数据快）
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override                   //打开数据库监听
                    public void onDbOpened(DbManager db) {

                    }
                })
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override//表创建成功的监听
                    public void onTableCreated(DbManager db, TableEntity<?> table) {

                    }
                });
    }

    // 将创建好的数据库传到MainActivity里进行具体的操作
    public DbManager.DaoConfig daoConfig() {
        return daoConfig;
    }
}
