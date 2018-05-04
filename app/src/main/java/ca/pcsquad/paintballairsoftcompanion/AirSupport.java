package ca.pcsquad.paintballairsoftcompanion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class AirSupport extends Activity {

    ImageLoader imgLoader;

    ImageButton btnBack;
    ImageView rule1;
    ImageView rule2;
    ImageView rule3;
    ImageView rule4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_page);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        btnBack = (ImageButton) findViewById(R.id.btnback);
        imgLoader.displayImage("drawable://" + R.drawable.label_gm_air_support, btnBack);
        btnBack.setOnClickListener(imgButtonHandler);

        FillPage fill = new FillPage();
        fill.Desc("To play this game make small teams of 2 or 3 players. Place your device in the middle of the field as a radio tower. The respawn point will be designated around the radio tower.");
        fill.Goals("The goal is to activate this the radio tower for 2 min straight without letting the other teams deactivate it.");
        fill.Respawn("Respawn allowed.");
        fill.Team("Players / 2 (e.g.: 6 players = 3 teams)");
        fill.Players("6 players");
        fill.Items("Portable Speaker\nAndroid Device");

        rule1 = (ImageView) findViewById(R.id.imgrule1);
        imgLoader.displayImage("drawable://" + R.drawable.rules_respawn_unlimited, rule1);

        rule2 = (ImageView) findViewById(R.id.imgrule2);
        imgLoader.displayImage("drawable://" + R.drawable.rules_team_3, rule2);

        rule3 = (ImageView) findViewById(R.id.imgrule3);
        imgLoader.displayImage("drawable://" + R.drawable.rules_players_6, rule3);

        rule4 = (ImageView) findViewById(R.id.imgrule4);
        imgLoader.displayImage("drawable://" + R.drawable.rules_item_speaker, rule4);

    }

    View.OnClickListener imgButtonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    class FillPage {

        void Desc(String newValue) {
            TextView txt = (TextView) findViewById(R.id.txtdesc);
            txt.setText(newValue);
        }
        void Goals(String newValue) {
            TextView txt = (TextView) findViewById(R.id.txtgoals);
            txt.setText(newValue);
        }
        void Respawn(String newValue) {
            TextView txt = (TextView) findViewById(R.id.txtrespawn);
            txt.setText(newValue);
        }
        void Team(String newValue) {
            TextView txt = (TextView) findViewById(R.id.txtteams);
            txt.setText(newValue);
        }
        void Players(String newValue) {
            TextView txt = (TextView) findViewById(R.id.txtplayers);
            txt.setText(newValue);
        }
        void Items(String newValue) {
            TextView txt = (TextView) findViewById(R.id.txtitems);
            txt.setText(newValue);
        }

    }
}
