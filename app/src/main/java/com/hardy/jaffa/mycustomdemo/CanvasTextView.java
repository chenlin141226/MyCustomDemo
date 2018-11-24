package com.hardy.jaffa.mycustomdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasTextView extends View {

    private Paint paint;

    //主要用于创建自定义控件样式
    public CanvasTextView(Context context) {
        this(context,null);
    }

    //主要用于用户将当前自定义控件申明在布局文件中
    public CanvasTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    //主要用于用户将当前自定义控件申明在布局文件中，并且加入样式（defStyleAttr）
    public CanvasTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    //用于封装x,y坐标得坐标点
    private PointF facePoint = new PointF(240,400);
    private  int faceRadius = 200;
    //在绘制阶段调用这个方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(facePoint.x,facePoint.y,faceRadius,paint);
    }
}
