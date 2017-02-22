package com.hookah.roma.hookahmix.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hookah.roma.hookahmix.R;
import com.hookah.roma.hookahmix.adapters.RecyclerViewAdapter;
import com.hookah.roma.hookahmix.models.objects.Tabak;

import java.util.ArrayList;

/**
 * Created by Roma on 22.02.2017.
 */

@SuppressWarnings("ALL")
public class FragmentTabakList extends Fragment {
    private static final String LIST_DATA = "LIST_DATA";

    private FragmentItemClickCallback callback;
    private ArrayList<Tabak> listData;
    private RecyclerView tabakList;

    public FragmentTabakList(){

    }

    public static FragmentTabakList newInstance(ArrayList<Tabak> listData){
        FragmentTabakList fragment = new FragmentTabakList();
        Bundle  args = new Bundle();
        args.putParcelableArrayList(LIST_DATA, listData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.listData = getArguments().getParcelableArrayList(LIST_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabaks_list,container,false);
        tabakList = (RecyclerView) v.findViewById(R.id.lst_tabaks);
        return  v;
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listData,getActivity());
        tabakList.setAdapter(adapter);
        adapter.setItemClickCallback(new RecyclerViewAdapter.ItemClickCallback() {
            @Override
            public void onItemClick(int p) {
                callback.onListItemClicked(p);
            }
        });
        tabakList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper((createHelperCallback()));
        itemTouchHelper.attachToRecyclerView(tabakList);
        super.onActivityCreated(savedInstanceState);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
            }
        };
        return simpleItemTouchCallback;
    }

    public interface FragmentItemClickCallback {
        void onListItemClicked(int position);
    }

}
