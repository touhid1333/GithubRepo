package com.touhid.githubrepo.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.touhid.githubrepo.databinding.FragmentUserProfileBinding;
import com.touhid.githubrepo.pojo.userprofile.UserProfileResponse;
import com.touhid.githubrepo.utils.Loader;
import com.touhid.githubrepo.utils.Variables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.dmoral.toasty.Toasty;

public class UserProfileFragment extends Fragment {

    private Context context;
    private UserProfileViewModel userProfileViewModel;
    private FragmentUserProfileBinding binding;
    private ExecutorService executorService;
    private Handler handler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                new ViewModelProvider(this).get(UserProfileViewModel.class);

        binding = FragmentUserProfileBinding.inflate(inflater, container, false);

        //userLoginName = UserProfileFragmentArgs.fromBundle(getArguments()).getUserLoginName();

        executorService = Executors.newFixedThreadPool(3);
        handler = new Handler(Looper.getMainLooper());

        //get user info
        Loader.showLoader(context, "");
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                //get data on background thread
                userProfileViewModel.initLiveData(context, Variables.userLoginName);

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //remove the background thread to maintain memory leaks and out of memory exception
                        executorService.shutdownNow();

                        //changes in the main thread
                        userProfileViewModel.getProfileLiveData().observe(getViewLifecycleOwner(), new Observer<UserProfileResponse>() {
                            @Override
                            public void onChanged(UserProfileResponse userProfileResponse) {

                                if (userProfileResponse != null){

                                    if (userProfileResponse.getAvatarUrl() != null){

                                        String avatarURL = userProfileResponse.getAvatarUrl();
                                        Glide.with(context)
                                                .load(avatarURL)
                                                .override(168,168)
                                                .into(binding.userAvatarIv);

                                    }

                                    if (userProfileResponse.getName() != null){

                                        binding.userNameTv.setText(String.valueOf(userProfileResponse.getName()));

                                    }

                                    if (userProfileResponse.getLogin() != null){

                                        String loginName = "@" + userProfileResponse.getLogin();
                                        binding.userLoginNameTv.setText(loginName);

                                    }

                                    if (userProfileResponse.getBio() != null){

                                        binding.userBioTv.setText(String.valueOf(userProfileResponse.getBio()));

                                    }


                                    binding.userReposTv.setText(String.valueOf(userProfileResponse.getPublicRepos()));
                                    binding.userGistTv.setText(String.valueOf(userProfileResponse.getPublicGists()));

                                }
                                //remove progress
                                Loader.cancelLoader();

                            }
                        });

                        //check errors
                        userProfileViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                            @Override
                            public void onChanged(Integer integer) {

                                if (integer == 200){

                                    //do nothing

                                }else if (integer == 400){

                                    Loader.cancelLoader();
                                    Toasty.warning(context, Variables.error_400).show();

                                }else if (integer == 404){

                                    Loader.cancelLoader();
                                    Toasty.warning(context, Variables.error_404).show();

                                }else if (integer == 409){

                                    Loader.cancelLoader();
                                    Toasty.warning(context, Variables.error_409).show();

                                }else if (integer == 500){

                                    Loader.cancelLoader();
                                    Toasty.warning(context, Variables.error_500).show();

                                }else {

                                    Loader.cancelLoader();

                                }

                            }
                        });

                    }
                });

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        if (!executorService.isShutdown()){
            executorService.shutdownNow();
        }
        super.onDestroyView();
    }
}