package com.hardy.jaffa.mycustomdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hardy.jaffa.mycustomdemo.model.PieEntity;

import java.util.ArrayList;


/**
 * 步骤分析
 * 1、最内测的扇形组成的圆形区域的绘制
 * 2、中间的短线的绘制
 * 3、最外侧文本的绘制
 */

public class CustomPieChart extends View {
    private ArrayList<PieEntity> pieEntities;
    private int width;
    private int height;
    private int radius;
    private RectF rectF;
    private Paint paint;
    private Path path;
    private Paint linePaint;

    public CustomPieChart(Context context) {
        this(context, null);
    }

    public CustomPieChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rectF = new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
        path = new Path();
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(3);
        linePaint.setTextSize(30);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        int min = Math.min(w, h);

        radius = (int) (min * 0.7f / 2);

        rectF.left = -radius;
        rectF.top = -radius;
        rectF.right = radius;
        rectF.bottom = radius;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        //平移画布  到屏幕中间
        canvas.translate(width / 2, height / 2);
        //画饼状图
        drawPie(canvas);
        canvas.restore();
    }


    private float startAngle = 0;
    private float totalSweepAngle;

    private void drawPie(Canvas canvas) {
        for (int i = 0; i < pieEntities.size(); i++) {
            paint.setColor(pieEntities.get(i).color);
            path.moveTo(0, 0);
            PieEntity entity = pieEntities.get(i);
            //获取每一个扇形对应的角度
            float sweepAngle = entity.value / totalSweepAngle * 360;
            path.arcTo(rectF, startAngle, sweepAngle);
            canvas.drawPath(path, paint);


            //绘制直线
            double a = Math.toRadians(startAngle + sweepAngle / 2);
            float startX = (float) ((radius+10) * Math.cos(a));
            float startY = (float) ((radius+10) * Math.sin(a));
            float endX = (float) ((radius+40)* Math.cos(a));
            float endY = (float) ((radius+40) * Math.sin(a));
             canvas.drawLine(startX,startY,endX,endY,linePaint);
            //canvas.drawArc(rectF,startAngle,sweepAngle,true,paint);
            //每次每个区域的起始点就是上一个扇形的终点
            startAngle += sweepAngle + 1;
            //每次绘制扇形之后要对path进行重置操作，这样就可以清楚上一次绘制path使用的画笔的相关记录
            path.reset();

            //绘制文本
            @SuppressLint("DefaultLocale")
            String percent = String.format("%.1f",pieEntities.get(i).value/totalSweepAngle*100);
            percent = percent+"%";
            //90.0f%360.0f = 90
            if(startAngle%360.0f>=90.0f && startAngle%360.0f<=270.0f){
                float textWidth = linePaint.measureText(percent);
                canvas.drawText(percent,endX-textWidth,endY,linePaint);
            }else{
                canvas.drawText(percent,endX,endY,linePaint);
            }
        }
    }

    public void setDatas(ArrayList<PieEntity> pieEntities) {
        this.pieEntities = pieEntities;

        for (PieEntity entity : pieEntities) {
            totalSweepAngle += entity.value;
        }
    }
}
