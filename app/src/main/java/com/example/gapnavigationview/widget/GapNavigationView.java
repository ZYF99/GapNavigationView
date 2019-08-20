package com.example.gapnavigationview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.RequiresApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GapNavigationView extends BottomNavigationView {

    Context context;


    public GapNavigationView(Context context) {
        super(context);
        this.context = context;
    }

    public GapNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GapNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //将中间类圆区域间距设置为总高度的 3/4
        int centerRadius = getHeight() * 3/4;
        //设置阴影大小
        float shadowLength = 5f;

        //创建画笔
        Paint paint = new Paint();
        //画笔抗锯齿
        paint.setAntiAlias(true);
        //创建路径
        Path path = new Path();

        //开始画View

        //将起点设置在阴影之下
        path.moveTo(0, shadowLength);

        //凹陷部分
        path.lineTo(getWidth() / 2f - centerRadius, shadowLength);
        path.lineTo(getWidth()/2f - centerRadius/3f * 2f ,shadowLength + centerRadius/4f);
        path.lineTo(getWidth()/2f - centerRadius/4f ,shadowLength + centerRadius * 3/4f);

        path.lineTo(getWidth()/2f + centerRadius/4f ,shadowLength + centerRadius * 3/4f);
        path.lineTo(getWidth()/2f + centerRadius/3f * 2f ,shadowLength + centerRadius/4f);
        path.lineTo(getWidth()/2f + centerRadius,shadowLength);

        //封闭区域
        path.lineTo(getWidth(), shadowLength);
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.lineTo(0, shadowLength);
        path.close();

        //设置挂角处的圆角角度
        paint.setPathEffect(new CornerPathEffect(centerRadius / 4f));


        //画阴影
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1);
        paint.setMaskFilter(new BlurMaskFilter(shadowLength - 1, BlurMaskFilter.Blur.NORMAL));
        canvas.drawPath(path, paint);


        //填充背景
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);
        paint.setMaskFilter(null);
        canvas.drawPath(path, paint);

    }
}
