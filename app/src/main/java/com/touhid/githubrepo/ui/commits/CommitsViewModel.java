package com.touhid.githubrepo.ui.commits;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.touhid.githubrepo.pojo.commitsresponse.CommitsResponse;

import java.util.List;

public class CommitsViewModel extends ViewModel {

    private CommitsRepository repository;
    private LiveData<Integer> errorLiveData;
    private LiveData<List<CommitsResponse>> commitsLiveData;

    public void initViewModel(Context context){
        errorLiveData = new MutableLiveData<>();
        commitsLiveData = new MutableLiveData<>();
        repository = new CommitsRepository(context);
    }

    public LiveData<Integer> getErrorLiveData() {
        return repository.getErrorLiveData();
    }

    public LiveData<List<CommitsResponse>> getCommitsLiveData() {
        return repository.getCommitsLiveData();
    }
}