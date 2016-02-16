package com.example.abon2.clockdial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by abon2 on 1/31/2016.
 */
public class ClockDialView extends View{

    private int hh;
    private int mm;
    private int ss;
    private Paint handPaint;
    private Paint dialPaint;
    private Path handPath;

    public ClockDialView(Context c, AttributeSet att){
        super(c, att);
        construct();
    }

    public ClockDialView(Context c){
        super(c);
        construct();
    }

    private void construct(){
        handPaint = new Paint();
        handPaint.setStyle(Paint.Style.FILL); //fill shape

        dialPaint = new Paint();
        dialPaint.setColor(Color.YELLOW);
        dialPaint.setStyle(Paint.Style.STROKE);
        dialPaint.setStrokeWidth(10); //draw a line with width 10px

        //Create path
        handPath = new Path();
    }

    protected void setTime(int h, int m, int s){
        //redraw with new values
        hh = h;
        mm = m;
        ss = s;
        invalidate();
    }

    private void drawHand(Canvas c, float cx, float cy,  float degrees,
                          float handLength, int handColor){
        //Draw triangle hand shape
        handPath.reset();
        handPath.moveTo(0,0);
        handPath.lineTo(0, handLength - (handLength * 2));
        handPath.lineTo(2, handLength - (handLength * 2));
        handPath.lineTo(0,0);
        //Draw hand on the canvas
        c.save();
        c.translate(cx, cy);
        //rotate hand
        c.rotate(degrees);
        //format hand
        handPaint.setColor(handColor);
        c.drawPath(handPath, handPaint);
        c.restore();
    }

    @Override
    public void onDraw(Canvas c){
        //get dial height and width
        float dw = getWidth();
        float dh = getHeight();

        //center x and center y
        float cx = dw / 2; float cy = dh / 2;
        float radius = 0.9f * (cx / 2);

        //draw the circle
        c.drawCircle(cx, cy, radius, dialPaint);

        //calculate angles
        float hourDeg = hh * 30 + (mm * 0.5F);
        float minDeg = mm  * 6;
        float secDeg = ss * 6;

        //draw hands
        drawHand(c, cx, cy,  hourDeg,
                 radius * 0.7F, Color.RED);
        drawHand(c, cx, cy,  minDeg,
                 radius * 0.8F, Color.GREEN);
        drawHand(c, cx, cy,  secDeg,
                 radius * 0.9F, Color.BLUE);
    }
}
