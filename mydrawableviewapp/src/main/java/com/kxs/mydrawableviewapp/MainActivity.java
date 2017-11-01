package com.kxs.mydrawableviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kxs.mydrawableview.MyDrawableView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(new RecyclerView.Adapter<IndicatorHolder>() {
            @Override
            public IndicatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = getLayoutInflater().inflate(R.layout.item_mydrawable, parent, false);
                return new IndicatorHolder(itemView);
            }

            @Override
            public void onBindViewHolder(IndicatorHolder holder, final int position) {
                holder.myDrawable.setDrawable(DRAWABLE_IDS[position]);
            }

            @Override
            public int getItemCount() {
                return DRAWABLE_IDS.length;
            }
        });
    }

    final static class IndicatorHolder extends RecyclerView.ViewHolder {

        public MyDrawableView myDrawable;
        public View itemLayout;

        public IndicatorHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item_layout);
            myDrawable = itemView.findViewById(R.id.my_drawable);
        }
    }


    private static final int[] DRAWABLE_IDS = new int[]{0, 1, 2};
}
