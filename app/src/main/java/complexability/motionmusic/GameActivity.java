package complexability.motionmusic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class GameActivity extends AppCompatActivity implements View.OnTouchListener{
    OurView v;
    Bitmap aimBall;
    float x, y;
    boolean draw;
    int audioSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new OurView(this);
        v.setOnTouchListener(this);
        aimBall = BitmapFactory.decodeResource(getResources(), R.drawable.aim_ball);
        x = y = 0;
        setContentView(v);
    }

    @Override
    protected void onPause() {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        v.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                draw = true;
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                draw = false;
                break;

            case MotionEvent.ACTION_MOVE:
                draw = true;
                x = event.getX();
                y = event.getY();
                break;

        }
        return true;
    }

    public class OurView extends SurfaceView implements Runnable{
        Thread t;
        SurfaceHolder holder;

        boolean isOkay = false;
        public OurView(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public void run() {
            while(isOkay == true){
                if(!holder.getSurface().isValid()){
                    continue;
                }
                Paint color = new Paint();
                color.setStyle(Paint.Style.FILL);


                Canvas c = holder.lockCanvas();
                //c.drawARGB(255, 0, 0, 0);
                int gridWidth = getWidth()/5;
                int gridHeight = getHeight()/5;
                for(int i = 0 ; i < 8 ; i++){
                    for (int j = 0 ; j < 8 ; j++) {
                        int left = i * (gridWidth);
                        int top = j * (gridHeight);
                        int right = left + gridWidth;
                        int bottom = top + gridHeight;
                        Log.d("Game touch", "x:" + x + "     y: " + y);
                        if ((x > left) && (x < right)  && (y < bottom) && (y > top) && draw){
                            color.setColor(Color.RED);
                        }
                        else if ((i+j)%2 == 1) {
                            color.setColor(Color.BLACK);
                            audioSelector = 1;

                        }
                        else {
                            color.setColor(Color.WHITE);
                            audioSelector = 2;
                        }

                        c.drawRect(left, top, right, bottom, color);
                        if(draw) {
                            c.drawBitmap(aimBall, x - (aimBall.getWidth() / 2), y - (aimBall.getHeight() / 2), null);
                        }
                    }
                }
                //c.drawBitmap(aimBall, x - (aimBall.getWidth()/2), y - (aimBall.getHeight()/2), null);
                holder.unlockCanvasAndPost(c);
            }
        }
        public void pause(){
            isOkay = false;
            while(true){
                try {
                    t.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        public void resume(){
            isOkay = true;
            t = new Thread(this);
            t.start();
        }
    }
}
