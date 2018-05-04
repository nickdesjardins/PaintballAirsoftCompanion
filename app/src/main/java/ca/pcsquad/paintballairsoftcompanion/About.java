package ca.pcsquad.paintballairsoftcompanion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class About extends Activity {

    ImageLoader imgLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnAbout);
        imgLoader.displayImage("drawable://" + R.drawable.btnaboutwhite, btnBack);
        btnBack.setOnClickListener(imgButtonHandler);

        ImageView imgSoldiers = (ImageView) findViewById(R.id.imgSoldiers);
        imgLoader.displayImage("drawable://" + R.drawable.dkone, imgSoldiers);
    }

    View.OnClickListener imgButtonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
}
