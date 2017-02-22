package com.example.manasaa.remindertask;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manasaa on 20-02-2017.
 * Recycle Adapter for Recycle view
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private static final String TAG="RecycleAdapterclass";
    private ArrayList<Reminder> mDataset;
    private Context mContext;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTitleTextView;
        public TextView mTimeTextView;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTitleTextView = (TextView) v.findViewById(R.id.title_text_reminder);
            mTimeTextView =(TextView) v.findViewById(R.id.time_text_reminder);
        }
    }
        //Constructor
    public RecycleAdapter(Context context){
      this.mContext=context;
        MySQLiteHelper database = new MySQLiteHelper(mContext);
        List<Reminder> reminderList=database.getAllReminders();
        this.mDataset= (ArrayList<Reminder>) reminderList;
    }
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reminder_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(RecycleAdapter.MyViewHolder holder, int position) {
        holder.mTitleTextView.setText(mDataset.get(position).getNameTask());
        holder.mTimeTextView.setText(mDataset.get(position).getTimeTask());
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    //remove item on swipe
    public void removeItem(int position) {
        Log.d(TAG," called removeItem");
        Reminder dataItem=mDataset.get(position);
        int id=dataItem.getId();
        Log.d(TAG,dataItem.getNameTask()+" called removeItem id");
        mDataset.remove(position);
        MySQLiteHelper database = new MySQLiteHelper(mContext);
        database.deleteReminder(id);
        //notifyItemChanged(position);
       notifyItemRangeChanged(position, getItemCount());
        notifyDataSetChanged();
    }
    //adding an item
    public void addItem(String title_reminder, String time_remider){
        MySQLiteHelper database = new MySQLiteHelper(mContext);
        //insert into table
        database.add(new Reminder(title_reminder,time_remider));
        notifyDataSetChanged();
    }

}
