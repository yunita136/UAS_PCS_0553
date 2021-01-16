package com.example.uas_18120553.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_18120553.R;
import com.example.uas_18120553.adapter.AdapterLastEvent;
import com.example.uas_18120553.adapter.AdapterNextEvent;
import com.example.uas_18120553.httpclient.ApiInterface;
import com.example.uas_18120553.httpclient.RetrofitClient;
import com.example.uas_18120553.model.ResponseLastEvent;
import com.example.uas_18120553.model.ResponseNextEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LastEventFragment extends Fragment {
    String idLeague;
@BindView(R.id.rvnextevent)
    RecyclerView rvlastevent;
AdapterLastEvent adapter;
    ApiInterface apiInterface;

    public LastEventFragment() {
        // Required empty public constructor
    }

    public static LastEventFragment newInstance(String idLeague) {
        LastEventFragment fragment = new LastEventFragment();
        Bundle args = new Bundle();
        args.putString("id", idLeague);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idLeague = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_next_event, container, false);

        ButterKnife.bind(this, view);

        apiInterface= RetrofitClient.getRetrofitClient().create(ApiInterface.class);

        adapter=new AdapterLastEvent(getContext());
        rvlastevent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvlastevent.setAdapter(adapter);

        nextEvent();

        return view;
    }
    public void nextEvent(){
        Call<ResponseLastEvent> api=apiInterface.getLastEventByLeague(idLeague);

        api.enqueue(new Callback<ResponseLastEvent>() {
            @Override
            public void onResponse(Call<ResponseLastEvent> call, Response<ResponseLastEvent> response) {
                if(response.isSuccessful()){
                    adapter.setItems(response.body().getEvents());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseLastEvent> call, Throwable t) {

            }
        });
    }
}