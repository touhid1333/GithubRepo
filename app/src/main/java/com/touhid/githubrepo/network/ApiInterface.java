package com.touhid.githubrepo.network;

import com.touhid.githubrepo.pojo.commitsresponse.CommitsResponse;
import com.touhid.githubrepo.pojo.userprofile.UserProfileResponse;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    /**
     * Get
     * Last
     * 10
     * Commits
     * From
     * Flutter Master
     * **/

    @GET("repos/flutter/flutter/commits")
    Call<List<CommitsResponse>> getLastTenCommits(@Query("per_page") int perPageCount, @Query("page") int pageNumber);


    /**
     * Get
     * Public
     * User
     * Profile
     * From
     * Github
     * */

    @GET("users/{user_login_name}")
    Call<UserProfileResponse> getUserProfile(@Path("user_login_name") String userLoginName);

}
