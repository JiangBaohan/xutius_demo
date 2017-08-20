package com.andy.day2x;

import android.app.Application;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/2 10:49
 */
public class App extends Application{
    private DbManager mManager;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        DbManager.DaoConfig config=new DbManager.DaoConfig()
                .setDbName("day2")
                .setDbVersion(1)
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table) {
                        Log.e("onTableCreated", "onTableCreated: "+table.getName());
                    }
                })
                .setAllowTransaction(true);
        mManager = x.getDb(config);
    }

    public DbManager getManager() {
        return mManager;
    }
}
