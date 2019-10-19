package com.example.wildfire.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wildfire.Massaging_activity;
import com.example.wildfire.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ForumFragment extends Fragment {

    private ForumViewModel forumViewModel;
    ListView listView;
    DatabaseReference databaseReference;
    List<forum> forumList;
    String conv_name;#
    Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum_list, container, false);

        listView = root.findViewById(R.id.forum);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        forumList = new ArrayList<>();
        intent = new Intent(getContext(), Massaging_activity.class);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        listView.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listView.setAdapter(null);
                forumList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                         forum forum_obj = ds.getValue(forum.class);
                         forumList.add(forum_obj);
                }
                listAdapter listAdapter_obj = new listAdapter(getActivity(),forumList);
                listView.setAdapter(listAdapter_obj);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                conv_name = forumList.get(position).getLocation();
                intent.putExtra("location_name",conv_name);

            }
        });
    }
}