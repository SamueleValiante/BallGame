package com.example.BallGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleView extends View
{
    private float x = 200;
    private float y = 200;
    private Paint paintCerchio;
    private Paint paintOstacolo;
    private List<Rect> ostacoli = new ArrayList<>();
    private final int WIDTH_OST = 150;
    private final int HEIGHT_OST = 80;
    private boolean ostacoliPiazzati = false;

    public CircleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paintCerchio = new Paint();
        paintCerchio.setColor(Color.RED);

        paintOstacolo = new Paint();
        paintOstacolo.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        if (!ostacoliPiazzati) {
            x = w / 2f;
            y = h / 2f;

            piazzaOstacoli(6);
            ostacoliPiazzati = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, 50, paintCerchio);

        for (Rect r : ostacoli)
        {
            canvas.drawRect(r, paintOstacolo);
        }
    }

    public void move(float dx, float dy)
    {
        float nuovaX = x + dx;
        float nuovaY = y + dy;
        float raggio = 50;

        boolean collisione = false;

        for (Rect r : ostacoli)
        {
            float vicinoX = Math.max(r.left, Math.min(nuovaX, r.right));
            float vicinoY = Math.max(r.top, Math.min(nuovaY, r.bottom));

            float distanzaX = nuovaX - vicinoX;
            float distanzaY = nuovaY - vicinoY;

            if ((distanzaX * distanzaX) + (distanzaY * distanzaY) < (raggio * raggio))
            {
                collisione = true;
                break;
            }
        }

        if (!collisione)
        {
            if((x+dx)>40 && (y+dy)>40 && (x+dx)<(getWidth()-40) && (y+dy)<(getHeight()-40))
            {
                x += dx;
                y += dy;
                invalidate();
            }
        }
    }

    public void reset()
    {
        x = 100;
        y = 100;
        piazzaOstacoli(6);
        invalidate();
    }

    public void piazzaOstacoli(int numero)
    {
        int viewW = getWidth();
        int viewH = getHeight();
        if (viewW == 0 || viewH == 0) return;

        ostacoli.clear();
        Random r = new Random();

        for (int i = 0; i < numero; i++)
        {
            int left = r.nextInt(viewW - WIDTH_OST);
            int top = r.nextInt(viewH - HEIGHT_OST);

            ostacoli.add(new Rect(left, top, left + WIDTH_OST, top + HEIGHT_OST));
        }
        invalidate();
    }
}