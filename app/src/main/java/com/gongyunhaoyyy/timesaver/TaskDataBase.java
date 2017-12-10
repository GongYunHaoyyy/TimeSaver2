package com.gongyunhaoyyy.timesaver;

import org.litepal.crud.DataSupport;

/**
 * Created by acer on 2017/12/7.
 */

public class TaskDataBase extends DataSupport{
    private int id;
    private String content;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
