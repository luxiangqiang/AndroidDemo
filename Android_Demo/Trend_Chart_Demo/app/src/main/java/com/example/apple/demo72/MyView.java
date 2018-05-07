package com.example.apple.demo72;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by apple on 2017/9/7.
 */
//统计图表绘制类
class MyView extends View
{
    //描点画圆的半径
    private static final int CIRCLE_SIZE = 20;
    //枚举画线类型
    private static enum Linestyle
    {
        Line, Curve
    }
    //上下文
    private Context mContext;
    //画笔
    private Paint mPaint;
    private Resources res;
    //DisplayMetrics 类提供了一种关于显示的通用信息，如显示大小，分辨率和字体
    private DisplayMetrics dm;
    //定义画线的样式
    private Linestyle mStyle = Linestyle.Curve;
    //画布高度
    private int canvasHeight;
    //画布宽度
    private int canvasWidth;
    //图表的高度
    private int bheight = 0;
    //图表的宽度
    private int blwidh;
    private boolean isMeasure = true;
    // Y轴最大值
    private int maxValue;
    //Y轴间距值
    private int averageValue;
    //顶端间距
    private int marginTop = 20;
    //低端间距
    private int marginBottom = 40;
    //曲线上总点数
    private Point[] mPoints;
    //纵坐标值
    private ArrayList<Double> yRawData;
     //横坐标值
    private ArrayList<String> xRawDatas;
    // 记录每个x的值
    private ArrayList<Integer> xList = new ArrayList<Integer>();
    private int spacingHeight;
    public MyView(Context context)
    {
        this(context, null);
    }
    public MyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        //调用初始化视图
        initView();
    }
    //初始化视图函数
    private void initView()
    {
        this.res = mContext.getResources();
        // 定义画笔，消除锯齿
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
    }
    //视图的大小发生改变后的回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        if (isMeasure)
        {
            this.canvasHeight = getHeight();
            this.canvasWidth = getWidth();
            if (bheight == 0)
                bheight = (int) (canvasHeight - marginBottom);
            blwidh = dip2px(30);
            isMeasure = false;
        }
    }
    //绘图方法
    @Override
    protected void onDraw(Canvas canvas)
    {
        //设置画笔的颜色为灰色
        mPaint.setColor(Color.GRAY);
        // 画直线（横向）
        drawAllXLine(canvas);
        // 画直线（纵向）
        drawAllYLine(canvas);
        // 获取数据点位置的操作
        mPoints = getPoints();
        //设置画线的颜色为红色，用来画数据点和曲线
        mPaint.setColor(Color.RED);
        //设置画线的宽度
        mPaint.setStrokeWidth(dip2px(2.5f));
        //设置画笔类型
        mPaint.setStyle(Paint.Style.STROKE);
        //根据mStyle的类型决定绘制数据点之间的线段的类型
        if (mStyle == Linestyle.Curve)
        {
            //绘制曲线链接线
            drawScrollLine(canvas);
        }
        else
        {
            //绘制直线连接线
            drawLine(canvas);
        }
        //设置画笔为填充模式
        mPaint.setStyle(Paint.Style.FILL);
        //根据数据点的数量和位置绘制数据点
        for (int i = 0; i < mPoints.length; i++)
        {
            //绘制数据点
            canvas.drawCircle(mPoints[i].x, mPoints[i].y, CIRCLE_SIZE / 2, mPaint);
        }
    }
    //画所有横向表格，包括X轴以及X轴上的文本
    private void drawAllXLine(Canvas canvas)
    {
        for (int i = 0; i < spacingHeight + 1; i++)
        {
            canvas.drawLine(blwidh, bheight - (bheight / spacingHeight) * i + marginTop-20, (canvasWidth - blwidh),
                    bheight - (bheight / spacingHeight) * i + marginTop-20, mPaint);// Y坐标
            drawText(String.valueOf(averageValue * i), blwidh / 2, bheight - (bheight / spacingHeight) * i + marginTop,
                    canvas);
        }
    }
    //画所有纵向表格，包括Y轴以及Y轴上的文本
    private void drawAllYLine(Canvas canvas)
    {
        for (int i = 0; i < yRawData.size(); i++)
        {
            xList.add(blwidh + (canvasWidth - blwidh) / yRawData.size() * i);

            canvas.drawLine(blwidh + (canvasWidth - blwidh) / yRawData.size() * i, marginTop-20, blwidh
                    + (canvasWidth - blwidh) / yRawData.size() * i, bheight + marginTop-20, mPaint);
            drawText(xRawDatas.get(i), blwidh + (canvasWidth - blwidh) / yRawData.size() * i, bheight+ marginTop+10,
                    canvas);// X坐标
        }
    }
    //绘制数据点之间的曲线
    private void drawScrollLine(Canvas canvas)
    {
        Point startp = new Point();
        Point endp = new Point();
        for (int i = 0; i < mPoints.length - 1; i++)
        {
            startp = mPoints[i];
            endp = mPoints[i + 1];
            int wt = (startp.x + endp.x) / 2;
            Point p3 = new Point();
            Point p4 = new Point();
            p3.y = startp.y;
            p3.x = wt;
            p4.y = endp.y;
            p4.x = wt;

            Path path = new Path();
            path.moveTo(startp.x, startp.y);
            path.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            canvas.drawPath(path, mPaint);
        }
    }
    //绘制数据点之间的链接线段
    private void drawLine(Canvas canvas)
    {
        Point startp = new Point();
        Point endp = new Point();
        for (int i = 0; i < mPoints.length - 1; i++)
        {
            startp = mPoints[i];
            endp = mPoints[i + 1];
            canvas.drawLine(startp.x, startp.y, endp.x, endp.y, mPaint);
        }
    }
    //绘制文本方法
    private void drawText(String text, int x, int y, Canvas canvas)
    {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(dip2px(12));
        p.setColor(Color.GRAY);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(text, x, y, p);
    }
    //获取各数据点位置的方法
    private Point[] getPoints()
    {
        Point[] points = new Point[yRawData.size()];
        for (int i = 0; i < yRawData.size(); i++)
        {
            int ph = bheight - (int) (bheight * (yRawData.get(i) / maxValue));

            points[i] = new Point(xList.get(i), ph + marginTop);
        }
        return points;
    }
    //获取本类绘制图表所需的各数据
    public void setData(ArrayList<Double> yRawData, ArrayList<String> xRawData, int maxValue, int averageValue)
    {
        this.maxValue = maxValue;
        this.averageValue = averageValue;
        this.mPoints = new Point[yRawData.size()];
        this.xRawDatas = xRawData;
        this.yRawData = yRawData;
        this.spacingHeight = maxValue / averageValue;
    }
    //以下为本类成员变量的设置方法
    public void setTotalvalue(int maxValue)
    {
        this.maxValue = maxValue;
    }

    public void setPjvalue(int averageValue)
    {
        this.averageValue = averageValue;
    }

    public void setMargint(int marginTop)
    {
        this.marginTop = marginTop;
    }

    public void setMarginb(int marginBottom)
    {
        this.marginBottom = marginBottom;
    }

    public void setMstyle(Linestyle mStyle)
    {
        this.mStyle = mStyle;
    }

    public void setBheight(int bheight)
    {
        this.bheight = bheight;
    }

    // 根据手机的分辨率从 dp 的单位 转成为 px(像素)
    private int dip2px(float dpValue)
    {
        return (int) (dpValue * dm.density + 0.5f);
    }

}
