package com.gongyunhaoyyy.timesaver;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by acer on 2017/12/10.
 */

public class DrawView extends View {
    double Y=20;
    String T="哈哈";

    public DrawView(Context context, double Y, String T) {
        super( context );
        this.Y=Y;
        this.T=T;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 创建画笔
        Paint p = new Paint();

        p.setTextSize( 30 );
        p.setStrokeWidth( 5 );
        canvas.drawText( T, 10, (float) Y, p );
        p.setColor( Color.RED );
        canvas.drawLine( 100, (float) Y, 1500, (float) Y, p );// 画线
//        canvas.drawText("画矩形：", 10, 80, p);
//        p.setColor(Color.GRAY);// 设置灰色
//        p.setStyle(Paint.Style.FILL);//设置填满
//        canvas.drawRect(10, 120, 500, 400, p);// 长方形

    }

}
