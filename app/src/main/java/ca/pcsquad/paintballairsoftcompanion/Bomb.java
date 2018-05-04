package ca.pcsquad.paintballairsoftcompanion;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Bomb extends Activity {

    static final int UPDATE_INTERVAL = 1000;
    private TextView bombTimer;
    ImageLoader imgLoader;

    MediaPlayer min5;
    MediaPlayer min4;
    MediaPlayer min3;
    MediaPlayer min2;
    MediaPlayer min1;
    MediaPlayer sec10;
    MediaPlayer sec9;
    MediaPlayer sec8;
    MediaPlayer sec7;
    MediaPlayer sec6;
    MediaPlayer sec5;
    MediaPlayer sec4;
    MediaPlayer sec3;
    MediaPlayer sec2;
    MediaPlayer sec1;
    MediaPlayer secbip;
    MediaPlayer youlose;

    String timerStop;
    private Button timerResetButton;
    private Button timerStartButton;
    private ImageButton btnBack;

    //Public var for Bomb

    private long mStartTime = 0L;
    private Handler mHandler = new Handler();
    int bombSec = 0;
    int bombMin = 0;
    int bombTime = 300;
    int DEFAULT_CLICK = 25;
    int click = DEFAULT_CLICK;
    int paused = 300;
    boolean timerRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomb);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        btnBack = (ImageButton) findViewById(R.id.btnBomb);
        imgLoader.displayImage("drawable://" + R.drawable.btntimebombwhite, btnBack);
        btnBack.setOnClickListener(imgButtonHandler);

        //Sounds

        min5 = MediaPlayer.create(Bomb.this, R.raw.min5);
        min4 = MediaPlayer.create(Bomb.this, R.raw.min4);
        min3 = MediaPlayer.create(Bomb.this, R.raw.min3);
        min2 = MediaPlayer.create(Bomb.this, R.raw.min2);
        min1 = MediaPlayer.create(Bomb.this, R.raw.min1);

        sec10 = MediaPlayer.create(Bomb.this, R.raw.sec10);
        sec9 = MediaPlayer.create(Bomb.this, R.raw.sec9);
        sec8 = MediaPlayer.create(Bomb.this, R.raw.sec8);
        sec7 = MediaPlayer.create(Bomb.this, R.raw.sec7);
        sec6 = MediaPlayer.create(Bomb.this, R.raw.sec6);
        sec5 = MediaPlayer.create(Bomb.this, R.raw.sec5);
        sec4 = MediaPlayer.create(Bomb.this, R.raw.sec4);
        sec3 = MediaPlayer.create(Bomb.this, R.raw.sec3);
        sec2 = MediaPlayer.create(Bomb.this, R.raw.sec2);
        sec1 = MediaPlayer.create(Bomb.this, R.raw.sec1);

        secbip = MediaPlayer.create(Bomb.this, R.raw.secbip);
        youlose = MediaPlayer.create(Bomb.this, R.raw.youlose);






        //Bomb Timer...............................................................................

        bombTimer = (TextView) findViewById(R.id.textBomb);
        Typeface font = Typeface.createFromAsset(getAssets(), "digital.ttf");
        bombTimer.setTypeface(font);


        final ImageButton minUp = (ImageButton) findViewById(R.id.btnAddMin);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_up, minUp);
        minUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime+60 <= 3600)
                    bombTime+=60;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });

        final ImageButton minTUp = (ImageButton) findViewById(R.id.btnAddTMin);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_up, minTUp);
        minTUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime+600 <= 3600)
                    bombTime+=600;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });


        final ImageButton minDown = (ImageButton) findViewById(R.id.btnSubMin);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_down, minDown);
        minDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime >= 60)
                    bombTime-=60;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });

        final ImageButton minTDown = (ImageButton) findViewById(R.id.btnSubTMin);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_down, minTDown);
        minTDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime >= 600)
                    bombTime-=600;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });

        final ImageButton secUp = (ImageButton) findViewById(R.id.btnAddSec);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_up, secUp);
        secUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime+1 <= 3600)
                    bombTime += 1;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });

        final ImageButton secTUp = (ImageButton) findViewById(R.id.btnAddTSec);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_up, secTUp);
        secTUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime+10 <= 3600)
                    bombTime += 10;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });

        final ImageButton secDown = (ImageButton) findViewById(R.id.btnSubSec);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_down, secDown);
        secDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime >= 1)
                    bombTime -= 1;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });

        final ImageButton secTDown = (ImageButton) findViewById(R.id.btnSubTSec);
        imgLoader.displayImage("drawable://" + R.drawable.btn_bomb_arrow_down, secTDown);
        secTDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                if(bombTime >= 10)
                    bombTime -= 10;

                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 100);

            }
        });

        final TextView clickTV = (TextView)findViewById(R.id.clickTV);
        final Button btn25 = (Button)findViewById(R.id.btn25);
        final Button btn50 = (Button)findViewById(R.id.btn50);
        final Button btn100 = (Button)findViewById(R.id.btn100);
        final Button btn200 = (Button)findViewById(R.id.btn200);
        final Button btn400 = (Button)findViewById(R.id.btn400);
        btn25.setEnabled(false);

        btn25.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DEFAULT_CLICK = 25;
                clickTV.setText("Number of clicks to DEFUSE: " + DEFAULT_CLICK);
                btn25.setEnabled(false);
                btn50.setEnabled(true);
                btn100.setEnabled(true);
                btn200.setEnabled(true);
                btn400.setEnabled(true);
            }
        });

        btn50.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                DEFAULT_CLICK = 50;
                clickTV.setText("Number of clicks to DEFUSE: " + DEFAULT_CLICK);
                btn25.setEnabled(true);
                btn50.setEnabled(false);
                btn100.setEnabled(true);
                btn200.setEnabled(true);
                btn400.setEnabled(true);
            }
        });

        btn100.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                DEFAULT_CLICK = 100;
                clickTV.setText("Number of clicks to DEFUSE: " + DEFAULT_CLICK);
                btn25.setEnabled(true);
                btn50.setEnabled(true);
                btn100.setEnabled(false);
                btn200.setEnabled(true);
                btn400.setEnabled(true);
            }
        });

        btn200.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DEFAULT_CLICK = 200;
                clickTV.setText("Number of clicks to DEFUSE: " + DEFAULT_CLICK);
                btn25.setEnabled(true);
                btn50.setEnabled(true);
                btn100.setEnabled(true);
                btn200.setEnabled(false);
                btn400.setEnabled(true);
            }
        });

        btn400.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DEFAULT_CLICK = 400;
                clickTV.setText("Number of clicks to DEFUSE: " + DEFAULT_CLICK);
                btn25.setEnabled(true);
                btn50.setEnabled(true);
                btn100.setEnabled(true);
                btn200.setEnabled(true);
                btn400.setEnabled(false);
            }
        });

        timerStartButton = (Button) findViewById(R.id.btnArm);
        timerStartButton.setText("ARM");
        timerStartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (!timerRunning) {
                    click = DEFAULT_CLICK;
                    timerStartButton.setText(String.valueOf(click));
                    Typeface font = Typeface.createFromAsset(getAssets(), "digital.ttf");
                    timerStartButton.setTypeface(font);
                    timerStartButton.setTextSize(53F);
                    timerResetButton.setEnabled(false);
                    btnBack.setClickable(false);
                    minUp.setEnabled(false);
                    minTUp.setEnabled(false);
                    minDown.setEnabled(false);
                    minTDown.setEnabled(false);
                    secUp.setEnabled(false);
                    secTUp.setEnabled(false);
                    secDown.setEnabled(false);
                    secTDown.setEnabled(false);
                    btn25.setEnabled(false);
                    btn50.setEnabled(false);
                    btn100.setEnabled(false);
                    btn200.setEnabled(false);
                    btn400.setEnabled(false);
                    mStartTime = SystemClock.uptimeMillis();
                    mHandler.removeCallbacks(mUpdateTimeTask);
                    mHandler.postDelayed(mUpdateTimeTask, 0L);
                    timerRunning = true;
                } else if (click > 0) {
                    click--;
                    timerStartButton.setText(String.valueOf(click));
                    if (click == 0) {
                        timerStartButton.setText("DEFUSE");
                        timerStartButton.setTypeface(Typeface.DEFAULT);
                        timerStartButton.setTextSize(35F);
                    }
                } else {
                    timerStartButton.setText("DEFUSED");
                    timerResetButton.setEnabled(true);
                    timerStartButton.setEnabled(false);
                    mHandler.removeCallbacks(mUpdateTimeTask);
                    timerRunning = false;
                    mHandler.removeCallbacks(convert);
                }

            }
        });

        timerResetButton = (Button) findViewById(R.id.btnReset);
        timerResetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                timerStartButton.setText("ARM");
                timerStartButton.setTypeface(Typeface.DEFAULT);
                timerStartButton.setTextSize(35F);
                timerStartButton.setEnabled(true);
                timerResetButton.setEnabled(false);
                btnBack.setClickable(true);
                minUp.setEnabled(true);
                minDown.setEnabled(true);
                secUp.setEnabled(true);
                secDown.setEnabled(true);
                minTUp.setEnabled(true);
                minTDown.setEnabled(true);
                secTUp.setEnabled(true);
                secTDown.setEnabled(true);

                mHandler.removeCallbacks(mUpdateTimeTask);
                bombTimer.setText(timerStop);
                mStartTime = 0L;
                mHandler.removeCallbacks(convert);
                mHandler.postDelayed(convert, 0L);

                switch (DEFAULT_CLICK){
                    case 25: btn50.setEnabled(true);
                             btn100.setEnabled(true);
                             btn200.setEnabled(true);
                             btn400.setEnabled(true);
                             break;
                    case 50: btn25.setEnabled(true);
                             btn100.setEnabled(true);
                             btn200.setEnabled(true);
                             btn400.setEnabled(true);
                             break;
                    case 100: btn25.setEnabled(true);
                              btn50.setEnabled(true);
                              btn200.setEnabled(true);
                              btn400.setEnabled(true);
                              break;
                    case 200: btn25.setEnabled(true);
                              btn50.setEnabled(true);
                              btn100.setEnabled(true);
                              btn400.setEnabled(true);
                              break;
                    case 400: btn25.setEnabled(true);
                              btn50.setEnabled(true);
                              btn100.setEnabled(true);
                              btn200.setEnabled(true);
                              break;
                    default: break;
                }
            }
        });




    } // OnCreate

    private Runnable convert = new Runnable(){

        public void run() {

            if(!timerRunning)
                paused = bombTime;

            bombMin = bombTime / 60;
            bombSec = bombTime % 60;

            bombTimer.setText("" + String.format("%02d", bombMin) + ":"
                    + String.format("%02d", bombSec));
        }
    };


    private Runnable mUpdateTimeTask = new Runnable(){

        public void run() {

            final long start = mStartTime;

            long millis = SystemClock.uptimeMillis()- start;

            int seconds = bombTime - (int) (millis / UPDATE_INTERVAL);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            if(minutes==5 && seconds==0)
                min5.start();
            else if(minutes==4 && seconds==0)
                min4.start();
            else if (minutes==3 && seconds==0)
                min3.start();
            else if(minutes==2 && seconds==0)
                min2.start();
            else if(minutes==1 && seconds==0)
                min1.start();
            else if(seconds==10 && minutes==0)
                sec10.start();
            else if(seconds==9 && minutes==0)
                sec9.start();
            else if(seconds==8 && minutes==0)
                sec8.start();
            else if(seconds==7 && minutes==0)
                sec7.start();
            else if(seconds==6 && minutes==0)
                sec6.start();
            else if(seconds==5 && minutes==0)
                sec5.start();
            else if(seconds==4 && minutes==0)
                sec4.start();
            else if(seconds==3 && minutes==0)
                sec3.start();
            else if(seconds==2 && minutes==0)
                sec2.start();
            else if(seconds==1 && minutes==0)
                sec1.start();
            else if(seconds!=0 || minutes!=0)
                secbip.start();



            bombTimer.setText("" + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            timerStop = String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds);

            if (bombTime - (int) (millis / UPDATE_INTERVAL) > 0)
                mHandler.postDelayed(this, 1000);
            else {
                youlose.start();
                bombTimer.setText("BOOM!");
                timerStartButton.setEnabled(false);
                timerResetButton.setEnabled(true);
                timerRunning = false;
            }


        }
    };

    public void onPause()
    {
        super.onPause();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.removeCallbacks(convert);
        mHandler.postDelayed(convert, 0L);
        finish();
    }

    View.OnClickListener imgButtonHandler;

    {
        imgButtonHandler = new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        };
    }

} // class