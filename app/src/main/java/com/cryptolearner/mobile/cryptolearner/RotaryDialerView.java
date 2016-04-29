package com.cryptolearner.mobile.cryptolearner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Adapted from a tutorial at http://www.ssaurel.com/blog/how-to-create-a-rotary-dialer-application-for-android/
 */
public class RotaryDialerView extends View {
    private float rotorAngle;
    private final Drawable rotorDrawable;
    private final int r1 = 50;
    private final int r2 = 400;
    private double lastFi;

    public RotaryDialerView(Context context) {
        this(context, null);
    }

    public RotaryDialerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotaryDialerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        rotorDrawable = context.getResources().getDrawable(R.drawable.cipher_circle_front);
    }

    private void fireDialListenerEvent(int number) {
        // TODO fire dial event
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int availableWidth = getRight() - getLeft();
        int availableHeight = getBottom() - getTop();

        int x = availableWidth / 2;
        int y = availableHeight / 2;

        canvas.save();

        int imageWidth = (availableWidth/100)*81;
        int imageHeight = (availableHeight/100)*81;
        int left = (availableWidth-imageWidth)/2;
        int top = (availableHeight-imageHeight)/2;

        rotorDrawable.setBounds(left, top, availableWidth-left,//rotorDrawable.getIntrinsicWidth(),
                availableHeight-top);//rotorDrawable.getIntrinsicHeight());


        if (rotorAngle != 0) {
            canvas.rotate(rotorAngle, x, y);
        }

        rotorDrawable.draw(canvas);

        canvas.restore();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x0 = getWidth() / 2;
        final float y0 = getHeight() / 2;
        float x1 = event.getX();
        float y1 = event.getY();
        float x = x0 - x1;
        float y = y0 - y1;
        double r = Math.sqrt(x * x + y * y);
        double sinfi = y / r;
        double fi = Math.toDegrees(Math.asin(sinfi));



        if (x1 > x0 && y0 > y1) {
            fi = 180 - fi;
        } else if (x1 > x0 && y1 > y0) {
            fi = 180 - fi;
        } else if (x0 > x1 && y1 > y0) {
            fi += 360;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (r > r1 && r < r2) {
                    if (fi<lastFi) {
                        rotorAngle -= Math.abs(fi - lastFi) + 0.25f;
                    } else {
                        rotorAngle += Math.abs(fi - lastFi) + 0.25f;
                    }
                    rotorAngle %= 360;
                    lastFi = fi;
                    invalidate();
                    return true;
                }

            case MotionEvent.ACTION_DOWN:
                //rotorAngle = 0;
                lastFi = fi;
                return true;

            case MotionEvent.ACTION_UP:
                if (r > r1 && r < r2) {
                    if (fi<lastFi) {
                        rotorAngle -= Math.abs(fi - lastFi) + 0.25f;
                    } else {
                        rotorAngle += Math.abs(fi - lastFi) + 0.25f;
                    }
                    double division = 13.8461538462;
                    rotorAngle = (float) division*(Math.round(rotorAngle/division));
                    rotorAngle %= 360;
                    lastFi = fi;
                    invalidate();
                    return true;
                }
            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}