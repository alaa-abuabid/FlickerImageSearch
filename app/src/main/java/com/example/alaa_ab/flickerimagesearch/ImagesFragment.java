package com.example.alaa_ab.flickerimagesearch;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class ImagesFragment extends Fragment
{
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private final ArrayList<Images> imagesArrayList = new ArrayList<>();
    private RecyclerView recyclerView_image_list ;
    Adapter adapter ;
    String text ;
    ProgressDialog progress ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("text");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.images,container,false);
        recyclerView_image_list = (RecyclerView) view.findViewById(R.id.recycler_images);
        configureRecyclerView();
        Intent intent = new Intent(getContext(),HttpRequestHandler.class);
        Bundle bundle = new Bundle();
        bundle.putString("text",text);
        bundle.putSerializable("adapter",adapter);
        intent.putExtras(bundle);
        getActivity().startService(intent);
        progress = new ProgressDialog(getContext());
        progress.setMessage("Downloading...");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        return view;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getStringExtra("update").equals("update"))
            {
                Images [] temp = (Images[]) intent.getSerializableExtra("list");
                imagesArrayList.addAll(Arrays.asList(temp));
                adapter.setImagesArrayList(imagesArrayList);
                adapter.notifyDataSetChanged();
                if(progress.isShowing())
                {
                    progress.hide();
                }
            }

        }
    };

    @Override
    public void onStart()
    {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(HttpRequestHandler.ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver,intentFilter);

    }
    private void configureRecyclerView()
    {
        adapter =  new Adapter(imagesArrayList);
        recyclerView_image_list.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView_image_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_image_list.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView_image_list, new IclickListner()
        {
            @Override
            public  void onClick(View view, final int position)
            {
                 openImageFragment(adapter.getImagesArrayList().get(position));

            }

            @Override
            public void onLongClick(View view, int position)
            {
            }
        }));
        recyclerView_image_list.setAdapter(adapter);
    }

    private void openImageFragment(Images image)
    {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("image",image);
        descriptionFragment.setArguments(bundle);
        transaction.hide(this);
        transaction.add(R.id.fragment_container, descriptionFragment,"IMAGE_FRAGMENT");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
