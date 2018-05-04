package ca.pcsquad.paintballairsoftcompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends Activity {

    ImageLoader imgLoader;

    ImageView frame;
    ImageView btnGame;
    ImageView btnBattle;
    ImageView btnBomb;
    ImageView btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        frame = findViewById(R.id.frame);
        imgLoader.displayImage("drawable://" + R.drawable.alpha_background, frame);

        btnGame = findViewById(R.id.gamemode);
        imgLoader.displayImage("drawable://" + R.drawable.btngamemodewhite, btnGame);
        View.OnClickListener imgButtonHandler = new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameModes.class));
            }
        };
        btnGame.setOnClickListener(imgButtonHandler);

        btnBattle = findViewById(R.id.battle);
        imgLoader.displayImage("drawable://" + R.drawable.btnbattleplanwhite, btnBattle);
        View.OnClickListener imgButtonHandler2 = new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BattlePlan.class));
            }
        };
        btnBattle.setOnClickListener(imgButtonHandler2);

        btnBomb = findViewById(R.id.bomb);
        imgLoader.displayImage("drawable://" + R.drawable.btntimebombwhite, btnBomb);
        View.OnClickListener imgButtonHandler3 = new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Bomb.class));
            }
        };
        btnBomb.setOnClickListener(imgButtonHandler3);

        btnAbout = findViewById(R.id.about);
        imgLoader.displayImage("drawable://" + R.drawable.btnaboutwhite, btnAbout);
        View.OnClickListener imgButtonHandler4 = new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, About.class));
            }
        };
        btnAbout.setOnClickListener(imgButtonHandler4);
    }
}
