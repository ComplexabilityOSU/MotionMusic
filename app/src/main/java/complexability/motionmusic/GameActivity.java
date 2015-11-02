package complexability.motionmusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import complexability.motionmusic.Engine.DrawingTheBall;


public class GameActivity extends AppCompatActivity {
    DrawingTheBall v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        v = new DrawingTheBall(GameActivity.this);
        setContentView(v);
    }
}
