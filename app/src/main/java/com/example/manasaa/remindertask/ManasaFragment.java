package com.example.manasaa.remindertask;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;


public class ManasaFragment extends Fragment {
    private static final String TAG=ManasaFragment.class.getSimpleName();
    private RecycleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"called onCreateView");
        final View v=inflater.inflate(R.layout.fragment_manasa, container, false);

        //Recycler View
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mAdapter=new RecycleAdapter(getContext());//setting layout for recyclerView
        mRecyclerView.setAdapter( mAdapter);
        mAdapter.notifyDataSetChanged();

        //delete on swipe
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d(TAG, "called simpleItemTouchCallback onSwiped");
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT){
                    mAdapter.removeItem(position);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);

        //FAB Button
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabAlert);
            fab.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"fab.setOnClickListener");
                    //custom dialog to enter Remaider Details
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.input_dailog);
                    final EditText title_reminder_edittxt = (EditText) dialog.findViewById(R.id.taskName);
                    final EditText time_reminder_edittxt = (EditText) dialog.findViewById(R.id.taskTime);
                    time_reminder_edittxt.setHint("HH:MM");
                    Button saveButton =(Button) dialog.findViewById(R.id.saveButton);
                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG," called saveButton.setOnClickListener");
                            String title_reminder = title_reminder_edittxt.getText().toString();
                            String time_remider = time_reminder_edittxt.getText().toString();
                            Log.d(TAG,title_reminder+" --**-- "+time_remider);
                            if (time_remider.length()!=0 && time_remider.length()!=0){
                                saveToDatabase(title_reminder,time_remider);
                               //adapter.addItem(title_reminder,time_remider);
                              resetAdpter();
    //                             adapter=new RecycleAdapter(getContext());
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
                private void resetAdpter() {
                    mAdapter=new RecycleAdapter(getContext());
                    RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_recycler_view);
                    rv.setHasFixedSize(true);
                    rv.setAdapter( mAdapter);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(llm);
                }
            });
        return v;
    }

    //inserting into table
    private void saveToDatabase(String title_reminder, String time_remider) {
        Log.d(TAG," called saveToDatabase");
        MySQLiteHelper database = new MySQLiteHelper(getContext());
        database.add(new Reminder(title_reminder,time_remider));
        List<Reminder> r = database.getAllReminders();
        for (Reminder rem : r) {
            String data = "Id: " + rem.getId() + "Name: " + rem.getNameTask() + " ,Time: " + rem.getTimeTask() + "\n";
            Log.d(TAG , data);
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG," called onStart() ");
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.d(TAG," called onPause()() ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG," called onResume() ");
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.d(TAG," called onResume() ");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG," called onDestroy ");
        super.onDestroy();
    }
}
