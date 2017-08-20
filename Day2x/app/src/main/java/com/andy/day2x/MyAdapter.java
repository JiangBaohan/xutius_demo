package com.andy.day2x;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/2 11:20
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<DataBean> list;
    private ImageOptions mOptions;

    public MyAdapter(Context context, List<DataBean> list) {
        mContext = context;
        this.list = list;
        mOptions=new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
//                .setCircular(true)
                .setSquare(true)
                .setRadius(15)
                .build();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext,R.layout.item , null);
            holder = new ViewHolder();

            x.view().inject(holder,view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        DataBean bean = list.get(i);

        holder.title.setText(bean.getNews_title());
        x.image().bind(holder.image,bean.getPic_url(),mOptions);
        return view;
    }

    class ViewHolder {
        @ViewInject(R.id.title)
        TextView title;
        @ViewInject(R.id.image)
        ImageView image;
    }
}
