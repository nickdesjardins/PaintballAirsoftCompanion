package ca.pcsquad.paintballairsoftcompanion;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameModes extends Activity { //implements AdapterView.OnItemClickListener

    private RecyclerView recyclerView;
    private GameAdapter adapter;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_modes);

        ImageView btnBack = (ImageView) findViewById(R.id.btnback);
        View.OnClickListener imgButtonHandler = new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        };
        btnBack.setOnClickListener(imgButtonHandler);

        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.gamelist);
        adapter = new GameAdapter(context, getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
    }

    public static List<Information> getData(){
        List<Information> data= new ArrayList<>();
        int[] icons = {R.drawable.btn_gm_air_support, R.drawable.btn_gm_capture_the_flag, R.drawable.btn_gm_dday, R.drawable.btn_gm_defuse_the_bomb, R.drawable.btn_gm_engineer, R.drawable.btn_gm_fireteam_partner, R.drawable.btn_gm_infected, R.drawable.btn_gm_pilot, R.drawable.btn_gm_president, R.drawable.btn_gm_prison_break, R.drawable.btn_gm_search_and_destroy, R.drawable.btn_gm_squad_deathmatch, R.drawable.btn_gm_supply_crate, R.drawable.btn_gm_team_deathmatch};
        String[] titles = {"1","1","1","1","1","1","1","1","1","1","1","1","1","1"};
        for(int i = 0; i < titles.length && i < icons.length; i++){
            Information current = new Information();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

}

