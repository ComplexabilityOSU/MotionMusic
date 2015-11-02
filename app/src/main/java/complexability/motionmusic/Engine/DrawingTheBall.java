package complexability.motionmusic.Engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import complexability.motionmusic.R;

/**
 * Created by Sorawis on 11/1/2015.
 */
public class DrawingTheBall extends View {
    Bitmap aimBall;
    int x, y;
    public DrawingTheBall(Context context) {
        super(context);
        //TODO add function to ball
        aimBall = BitmapFactory.decodeResource(getResources(), R.drawable.aim_ball);
        x = 0;
        y = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Create Rectangle object
        Rect grid = new Rect();
        grid.set(0, 0, canvas.getWidth(), canvas.getHeight());

        //Rect mainPlane = new Rect();
        Paint black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);
        //Draw the rectangle with paint attribute
        canvas.drawRect(grid, black);
        if( x < canvas.getWidth()) {
            x += 10;
        }
        else {
            x = 0;
        }
        if (y  < canvas.getHeight()) {
            y += 10;
        }
        else{
            y = 10;
        }
        canvas.drawBitmap(aimBall, x, y, new Paint());
        invalidate();
    }


}
