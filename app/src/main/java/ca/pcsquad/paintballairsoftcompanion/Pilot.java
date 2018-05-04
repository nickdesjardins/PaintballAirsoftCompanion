package ca.pcsquad.paintballairsoftcompanion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Pilot extends Activity {

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
        btnBack.setImageResource(R.drawable.label_gm_pilot);
        btnBack.setOnClickListener(imgButtonHandler);

        FillPage fill = new FillPage();
        fill.Desc("There is a plane crash and the pilot is still alive. The pilot only have a pistol with one magazine and the army are on their way to go save him. The pilot will be hiding in the woods somewhere, the army knows where he is and the rebels don't. The pilot can't move from his hiding spot until a member of the army team touches him.");
        fill.Goals("Army: Save the pilot from the debris and bring him at the starting point.\n" +
                "Rebels: Kill the pilot.");
        fill.Respawn("No respawn allowed.");
        fill.Team("2 Teams");
        fill.Players("6 Players");
        fill.Items("No items required.");

        rule1 = (ImageView) findViewById(R.id.imgrule1);
        imgLoader.displayImage("drawable://" + R.drawable.rules_respawn_no, rule1);

        rule2 = (ImageView) findViewById(R.id.imgrule2);
        imgLoader.displayImage("drawable://" + R.drawable.rules_team_2, rule2);

        rule3 = (ImageView) findViewById(R.id.imgrule3);
        imgLoader.displayImage("drawable://" + R.drawable.rules_players_6, rule3);

        rule4 = (ImageView) findViewById(R.id.imgrule4);
        imgLoader.displayImage("drawable://" + R.drawable.rules_item_no_item_required, rule4);

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
