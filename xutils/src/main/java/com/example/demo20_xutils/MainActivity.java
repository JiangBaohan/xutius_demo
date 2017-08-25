package com.example.demo20_xutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.sn.xlistviewlibrary.XListView;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;

@ContentView(R.layout.activity_main)

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.myxlist)
    XListView myxlist;
    private  String path="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private MyApp app;
    private DbManager db;
    private List<DataBean> list;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       x.view().inject(this);
//        这个和实例对象很相似只有实例化才能调用里面的方法
//        到这里数据库已经创建成功只是没有数据
        list=new ArrayList<>();
        db = x.getDb(app.daoConfig());
        adapter=new MyAdapter(this,list);
        myxlist.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        RequestParams params = new RequestParams("http://api.expoon.com/AppNews/getNewsList/type/1/p/1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("onSuccess", "onSuccess: " + result);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                Log.e("onSuccess", "onSuccess: " + bean);

                app= (MyApp) getApplication();
                db = x.getDb(app.daoConfig());
                try {
                    db.save(bean.getData());
//                    dbManager.findAll(DataBean.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }

               // list.addAll(bean.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError", "onError: " + ex.getMessage());
                //                出现错误是
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //             取消时
            }

            @Override
            public void onFinished() {
//          完成时
            }
        });
    }

}
