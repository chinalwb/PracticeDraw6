package com.hencoder.hencoderpracticedraw6.practice.practice08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.hencoder.hencoderpracticedraw6.Utils.dpToPixel;

public class Practice08ObjectAnimatorView extends View {
    final float radius = dpToPixel(80);

    RectF arcRectF = new RectF();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // TODO 为 progress 添加 getter 和 setter 方法（setter 方法记得加 invalidate()）
    float progress = 0;

    public Practice08ObjectAnimatorView(Context context) {
        super(context);
    }

    public Practice08ObjectAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice08ObjectAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(dpToPixel(40));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(dpToPixel(15));
        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        paint.setColor(Color.GRAY);
        canvas.drawArc(arcRectF, 135, 270, false, paint);
        paint.setColor(Color.parseColor("#E91E63"));
        canvas.drawArc(arcRectF, 135, progress * 2.7f, false, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText((int) progress * 5 + "", centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint);

        int offsetX = (int) (radius * Math.sin(Math.PI * 45 / 180));
        int offsetY = (int) (radius * Math.sin(Math.PI * 45 / 180));
        float zeroLeft = centerX - offsetX;
        float zeroTop = centerY + offsetY + 80;

        float endLeft = centerX + offsetX - 50;
        float endTop = zeroTop;

        Paint sidePaint = new Paint();
        sidePaint.setTextSize(dpToPixel(15));
        sidePaint.setColor(Color.WHITE);
        canvas.drawText("0", zeroLeft, zeroTop, sidePaint);
        canvas.drawText("500", endLeft, endTop, sidePaint);
    }
}

