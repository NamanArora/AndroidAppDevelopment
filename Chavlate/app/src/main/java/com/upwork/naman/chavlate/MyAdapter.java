package com.upwork.naman.chavlate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

/**
 * Created by naman_3uwwmg4 on 20-06-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mDataset;
    public int lastposition;
    int totalitems=0;

    public MyAdapter(List<String> received) {

         mDataset=received;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView  said,explain;;
        public ViewHolder(View view) {
            super(view);
            said = (TextView) view.findViewById(R.id.title);
            explain = (TextView) view.findViewById(R.id.genre);

        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rows, parent, false);

        ViewHolder vh= new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {


        holder.said.setText(mDataset.get(position));
        holder.explain.setText(SpeechHelper.meaning(mDataset.get(position)));
        setAnimation(holder.itemView,position);


    }

    private void setAnimation(View viewToAnimate, int position)
    {

            if(position==0 && totalitems<getItemCount()) {
                Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.push_left_in);
                viewToAnimate.startAnimation(animation);
                totalitems=getItemCount();
            }


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
