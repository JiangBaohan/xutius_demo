package com.andy.day2x;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.lv)
    private ListView lv;

    private MyAdapter mAdapter;
    private List<DataBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        lv= find(R.id.lv);

        list=new ArrayList<>();
        mAdapter=new MyAdapter(this,list);
        lv.setAdapter(mAdapter);

        loadData();
    }

    private void loadData() {
        RequestParams params=new RequestParams("http://api.expoon.com/AppNews/getNewsList/type/1/p/1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("onSuccess", "onSuccess: "+result );
                Gson gson = new Gson();
                Data data = gson.fromJson(result,Data.class);
                Log.e("onSuccess", "onSuccess: "+data );

                App app = (App) getApplication();
                DbManager dbManager = app.getManager();
                try {
                    dbManager.save(data.getData());
//                    dbManager.findAll(DataBean.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }

                list.addAll(data.getData());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError", "onError: "+ex.getMessage() );
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

//    public <T> T find(int id){
//        return (T)findViewById(id);
//    }
}
