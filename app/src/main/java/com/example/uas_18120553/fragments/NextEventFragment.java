package com.example.uas_18120553.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.uas_18120553.httpclient.ApiInterface;
import com.example.uas_18120553.R;
import com.example.uas_18120553.adapter.AdapterNextEvent;
import com.example.uas_18120553.httpclient.ApiInterface;
import com.example.uas_18120553.httpclient.RetrofitClient;
import com.example.uas_18120553.model.ResponseNextEvent;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NextEventFragment extends Fragment {
    String idLeague;
@BindView(R.id.rvnextevent)
    RecyclerView rvnextevent;
AdapterNextEvent adapter;
    ApiInterface apiInterface;

    public NextEventFragment() {
        // Required empty public constructor
    }

    public static NextEventFragment newInstance(String idLeague) {
        NextEventFragment fragment = new NextEventFragment();
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

        adapter=new AdapterNextEvent(getContext());
        rvnextevent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvnextevent.setAdapter(adapter);

        nextEvent();

        return view;
    }
    public void nextEvent(){
        Call<ResponseNextEvent> api=apiInterface.getNextEventByLeague(idLeague);

        api.enqueue(new Callback<ResponseNextEvent>() {
            @Override
            public void onResponse(Call<ResponseNextEvent> call, Response<ResponseNextEvent> response) {
                if(response.isSuccessful()){
                    adapter.setItems(response.body().getEvents());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseNextEvent> call, Throwable t) {

            }
        });
    }
}