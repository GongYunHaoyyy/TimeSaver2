package com.gongyunhaoyyy.timesaver;

/**
 * Created by acer on 2017/12/7.
 */

public class TaskClass {

    private int id;
    private String content;
    private String time;
    private int starttime,endtime;

    public TaskClass(int id, String content, String time, int starttime, int endtime) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.starttime = starttime;
        this.endtime = endtime;
    }

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

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }
}
