package com.mti.coordinated_motion_animation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

 /*
  **Minimum Sdk recomended 21 or lolilpop
  * Need two transition animation : curve and move
  * For recycler View need to declare in gradle
  * Need a inflater view : message.xml
  * Need a detail activity : activity_curved_motion
  * declare new activity in manifest*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new MessageAdapter(this));
        list.setHasFixedSize(true);
    }




    public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {

        private final int[] COLORS = new int[] { 0xff956689, 0xff80678A, 0xff6A6788,
                0xff546683, 0xff3F657B, 0xff3F657B };  //colors for avatar image
        private Activity host;
        private final LayoutInflater inflater;

        public MessageAdapter(Activity activity) { //constructor for using in setAdapter()
            host = activity;
            inflater = LayoutInflater.from(host);
        }

        @Override
        public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MessageHolder(inflater.inflate(R.layout.message, parent, false));
        }

        @Override
        public void onBindViewHolder(final MessageHolder holder, final int position) {
            final int color = COLORS[position % COLORS.length];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.avatar.setBackgroundTintList(ColorStateList.valueOf(color));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(host, CurvedMotionDetail.class);
                    boolean curve = (position % 2 == 0);
                    intent.putExtra(CurvedMotionDetail.EXTRA_COLOR, color);
                    intent.putExtra(CurvedMotionDetail.EXTRA_CURVE, curve);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        host.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                                host, holder.avatar, holder.avatar.getTransitionName()).toBundle());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return 64; //as our recycler view has dummy data so we add 64 data in it
        }
    }

    static class MessageHolder extends RecyclerView.ViewHolder {

        View avatar;
        View title;
        View subtitle;

        public MessageHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}
