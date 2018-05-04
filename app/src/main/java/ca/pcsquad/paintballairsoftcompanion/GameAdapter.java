package ca.pcsquad.paintballairsoftcompanion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    ImageLoader imgLoader;

    List<Information> data = Collections.emptyList();
    private Context context;

    public GameAdapter (Context context, List<Information> data){
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.data = data;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        imgLoader.displayImage("drawable://" + current.iconId, holder.icon);
        //holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (getPosition()) {
                case 0:
                    context.startActivity(new Intent(context, AirSupport.class));
                    break;
                case 1:
                    context.startActivity(new Intent(context, CaptureFlag.class));
                    break;
                case 2:
                    context.startActivity(new Intent(context, DDay.class));
                    break;
                case 3:
                    context.startActivity(new Intent(context, DefuseBomb.class));
                    break;
                case 4:
                    context.startActivity(new Intent(context, Engineer.class));
                    break;
                case 5:
                    context.startActivity(new Intent(context, FireTeam.class));
                    break;
                case 6:
                    context.startActivity(new Intent(context, Infected.class));
                    break;
                case 7:
                    context.startActivity(new Intent(context, Pilot.class));
                    break;
                case 8:
                    context.startActivity(new Intent(context, President.class));
                    break;
                case 9:
                    context.startActivity(new Intent(context, PrisonBreak.class));
                    break;
                case 10:
                    context.startActivity(new Intent(context, SearchDestroy.class));
                    break;
                case 11:
                    context.startActivity(new Intent(context, SquadDeathmatch.class));
                    break;
                case 12:
                    context.startActivity(new Intent(context, SupplyCrate.class));
                    break;
                case 13:
                    context.startActivity(new Intent(context, TeamDeathMatch.class));
                    break;
                default:
                    break;
            }
        }
    }
}
