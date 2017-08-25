package com.example.demo20_xutils;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * data:2017/8/23
 * author:汉堡(Administrator)
 * function:
 */
@Table(name = "bean")
public class DataBean {
    //这个才是我真正想要的数据
//    决定把id（数据库肯定会有id自增字段） pic 和 title写进数据库 同样通过注解来实现创建数据库的表
//    这里的字段和注解的字段不一定要相同
    @Column(name="id",isId = true)
    private String news_id;
    @Column(name="title")
    private String news_title;
    private String news_summary;
    @Column(name="img_url")
    private String pic_url;

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_summary() {
        return news_summary;
    }

    public void setNews_summary(String news_summary) {
        this.news_summary = news_summary;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
