package com.touhid.githubrepo.ui.profile;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.touhid.githubrepo.pojo.userprofile.UserProfileResponse;

public class UserProfileViewModel extends ViewModel {

    private UserProfileRepository repository;
    private LiveData<Integer> errorLiveData;
    private LiveData<UserProfileResponse> profileLiveData;

    public void initLiveData(Context context, String userLoginName){
        errorLiveData = new MutableLiveData<>();
        profileLiveData = new MutableLiveData<>();
        repository = new UserProfileRepository(context, userLoginName);
    }

    public LiveData<Integer> getErrorLiveData() {
        return repository.getErrorLiveData();
    }

    public LiveData<UserProfileResponse> getProfileLiveData() {
        return repository.getProfileLiveData();
    }
}