package com.touhid.githubrepo.ui.commits;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.button.MaterialButton;
import com.touhid.githubrepo.R;
import com.touhid.githubrepo.adapter.CommitsAdapter;
import com.touhid.githubrepo.databinding.FragmentCommitsBinding;
import com.touhid.githubrepo.pojo.commitsresponse.CommitsResponse;
import com.touhid.githubrepo.utils.ChildClickListner;
import com.touhid.githubrepo.utils.Loader;
import com.touhid.githubrepo.utils.Variables;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.dmoral.toasty.Toasty;

public class CommitsFragment extends Fragment {

    private CommitsViewModel commitsViewModel;
    private FragmentCommitsBinding binding;
    private Context context;
    private ExecutorService executorService;
    private Handler handler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        commitsViewModel =
                new ViewModelProvider(this).get(CommitsViewModel.class);

        binding =
                FragmentCommitsBinding.inflate(inflater, container, false);

        executorService = Executors.newFixedThreadPool(3);
        handler = new Handler(Looper.getMainLooper());

        //getting last 10 commits
        Loader.showLoader(context, "");
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                //save data in liveData on background thread
                commitsViewModel.initViewModel(context);

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //remove the background thread to maintain memory leaks and out of memory exception
                        executorService.shutdownNow();

                        //changes in ui in main thread
                        commitsViewModel.getCommitsLiveData().observe(getViewLifecycleOwner(), new Observer<List<CommitsResponse>>() {
                            @Override
                            public void onChanged(List<CommitsResponse> commitsResponses) {

                                if (commitsResponses != null){

                                    CommitsAdapter adapter = new CommitsAdapter(context, commitsResponses, new ChildClickListner() {
                                        @Override
                                        public void onChildClick(View view, int position) {

                                            String selectedUserLoginName = commitsResponses.get(position).getAuthor().getLogin();

                                            Variables.userLoginName = selectedUserLoginName;

                                            //show a dialog to provide navigate info

                                            Dialog dialog = new Dialog(context);
                                            dialog.setContentView(R.layout.dialog_layout);
                                            MaterialButton okBTN = dialog.findViewById(R.id.ok_btn);

                                            okBTN.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    dialog.dismiss();
                                                }
                                            });

                                            dialog.show();

                                            //Navigation.findNavController(view).navigate(CommitsFragmentDirections.actionNavigationCommitsToNavigationUserProfile(selectedUserLoginName));

                                        }
                                    });
                                    LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                                    binding.commitListRv.setLayoutManager(manager);
                                    binding.commitListRv.setAdapter(adapter);
                                    //cancel progress
                                    Loader.cancelLoader();
                                }

                            }
                        });

                        //check errors
                        commitsViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
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