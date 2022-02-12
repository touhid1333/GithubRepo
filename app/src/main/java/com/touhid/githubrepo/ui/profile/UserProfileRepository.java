package com.touhid.githubrepo.ui.profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.touhid.githubrepo.network.ApiClient;
import com.touhid.githubrepo.network.ApiInterface;
import com.touhid.githubrepo.pojo.userprofile.UserProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private MutableLiveData<Integer> errorLiveData;
    private MutableLiveData<UserProfileResponse> profileLiveData;

    public UserProfileRepository(Context context, String userLoginName) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        errorLiveData = new MutableLiveData<>();
        profileLiveData = new MutableLiveData<>();
        getUserProfile(userLoginName);
    }

    private void getUserProfile(String userLoginName){

        Call<UserProfileResponse> call = apiInterface.getUserProfile(userLoginName);

        call.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {

                if (response.code() == 200){

                    errorLiveData.postValue(200);
                    profileLiveData.postValue(response.body());

                }else {

                    errorLiveData.postValue(response.code());

                }

            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                errorLiveData.postValue(500);
            }
        });

    }

    public MutableLiveData<Integer> getErrorLiveData() {
        return errorLiveData;
    }

    public MutableLiveData<UserProfileResponse> getProfileLiveData() {
        return profileLiveData;
    }
}
