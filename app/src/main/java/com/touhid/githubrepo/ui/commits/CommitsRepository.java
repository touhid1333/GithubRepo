package com.touhid.githubrepo.ui.commits;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.touhid.githubrepo.network.ApiClient;
import com.touhid.githubrepo.network.ApiInterface;
import com.touhid.githubrepo.pojo.commitsresponse.CommitsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitsRepository {

    private static final int PER_PAGE_COUNT = 10;
    private static final int PAGE_NUMBER = 1;

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private MutableLiveData<Integer> errorLiveData;
    private MutableLiveData<List<CommitsResponse>> commitsLiveData;


    public CommitsRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        errorLiveData = new MutableLiveData<>();
        commitsLiveData = new MutableLiveData<>();

        //calling method to get data
        getLastCommits();
    }


    //get last 10 commits from flutter/flutter...
    private void getLastCommits(){

        Call<List<CommitsResponse>> call = apiInterface.getLastTenCommits(PER_PAGE_COUNT, PAGE_NUMBER);

        //calling the api
        call.enqueue(new Callback<List<CommitsResponse>>() {
            @Override
            public void onResponse(Call<List<CommitsResponse>> call, Response<List<CommitsResponse>> response) {

                if (response.code() == 200){

                    errorLiveData.postValue(200);
                    commitsLiveData.postValue(response.body());

                }else {

                    errorLiveData.postValue(response.code());

                }

            }

            @Override
            public void onFailure(Call<List<CommitsResponse>> call, Throwable t) {

                errorLiveData.postValue(500);

            }
        });

    }

    public MutableLiveData<Integer> getErrorLiveData() {
        return errorLiveData;
    }

    public MutableLiveData<List<CommitsResponse>> getCommitsLiveData() {
        return commitsLiveData;
    }
}
