package com.gongyunhaoyyy.timesaver;

/**
 * Created by acer on 2017/12/7.
 */

public class TaskClass {

    private int id;
    private String content;
    private String starttime,endtime;
    double startY,endY;

    public TaskClass(int id,String content, String starttime, String endtime, double startY, double endY) {
        this.id=id;
        this.content = content;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startY = startY;
        this.endY = endY;
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndY() {
        return endY;
    }
}
