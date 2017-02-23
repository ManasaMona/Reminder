package com.example.manasaa.remindertask;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class SailiFragment extends Fragment implements LoaderManager.LoaderCallbacks  {
    private static final String TAG="SAiliFragmentclass";
    private static final String PROVIDER_NAME = "com.example.sailik.contentprovidertask_20_feb";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME);
    CursorLoader cursorLoader;
    private SimpleCursorAdapter mAdapter;
    private ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"called onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"called onCreateView");
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_saili, container, false);
        //RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rv_recycler_view);
        mListView = (ListView) v.findViewById(R.id.lstViewAlbums);

        mAdapter = new SimpleCursorAdapter(getActivity().getBaseContext(),
                R.layout.vinay_item,
                null,
                new String[] { "Album_name", "Track_name"},
                new int[] { R.id.name , R.id.phNumber }, 0);
        mListView.setAdapter(mAdapter);// populates the ListView.

        getActivity().getSupportLoaderManager().initLoader(2, null, this);
        return v;
    }

    //gets the data from the SQLite database.
    @Override
    public Loader onCreateLoader(int id, Bundle args) {  Log.d(TAG,"called onCreateLoader");
        cursorLoader= new CursorLoader(getActivity(), CONTENT_URI, null, null, null, null);
        return  cursorLoader;
    }

    @Override//gets the data
    public void onLoadFinished(Loader loader, Object data) { Log.d(TAG,"called onLoadFinished");
        Cursor c=(Cursor) data;
        String column_name = c.getColumnName(2);
            Log.d(TAG,c.getCount()+ column_name);
        mAdapter.swapCursor(c);
    }

    @Override//if loader is changed
    public void onLoaderReset(Loader loader) { Log.d(TAG,"called onLoadFinished");
        mAdapter.swapCursor(null);

    }
}
