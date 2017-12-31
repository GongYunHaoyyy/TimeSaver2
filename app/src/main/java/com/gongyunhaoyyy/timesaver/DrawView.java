package com.gongyunhaoyyy.timesaver;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.provider.CalendarContract;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by acer on 2017/12/10.
 */

public class DrawView extends View {
    double Y=0,startY=0,endY=0;
    String T="",starttime,content;
    List<TaskClass> drawlist=new ArrayList<>();
    int[] randomcolors={
            Color.argb( 100,244,67,54 ),Color.argb( 100,33,150,243 ),
            Color.argb( 100,76,175,80 ),Color.argb( 100,255,245,59 ),
            Color.argb( 100,255,152,0 ),Color.argb( 100,102,54,186 ),
            Color.argb( 100,239,36,105 ),Color.argb(100,81,214,208 )};
    int count;

    public DrawView(Context context,double Y,String T,List<TaskClass> drawlist,int n) {
        super( context );
        this.Y=Y;
        this.T=T;
        this.drawlist=drawlist;
        this.count=n;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 创建画笔
        Paint p = new Paint();
//        //清屏
//        p.setXfermode(new PorterDuffXfermode( PorterDuff.Mode.CLEAR));
//        canvas.drawPaint(p);
//        p.setXfermode(new PorterDuffXfermode( PorterDuff.Mode.SRC));
//        //这个一定要加，不然画布背景为黑色
//        canvas.drawColor(Color.WHITE);

        //这里设置paint并开始自己的画图
        p.setTextSize( 30 );
        p.setStrokeWidth( 5 );
        canvas.drawText( T, 10, (float) Y, p );
        p.setColor( Color.RED );
        canvas.drawLine( 100, (float) Y, 1500, (float) Y, p );// 画线
        //画每个item
        for (int i=0;i<drawlist.size();i++){
            p.setColor(randomcolors[getRandomIndex()]);
            p.setStyle(Paint.Style.FILL);//设置填满
            // 这个距离粗糙就粗糙吧，不管了，反正大概能画出来
            canvas.drawRect(0, (float) drawlist.get( i ).getStartY(), 1400, (float)drawlist.get( i ).getEndY(), p);
            p.setColor(Color.BLACK);
            p.setTextSize( 40 );
            canvas.drawText(drawlist.get( i ).getStarttime(), 50, (float) (drawlist.get( i ).getStartY()+drawlist.get( i ).getEndY())/2, p);
            p.setTextSize( 45 );
            canvas.drawText(drawlist.get( i ).getContent(), 300, (float) (drawlist.get( i ).getStartY()+drawlist.get( i ).getEndY())/2-5, p);
        }
    }
    private int getRandomIndex(){
        Random random=new Random();
        return random.nextInt(8);
    }
}
