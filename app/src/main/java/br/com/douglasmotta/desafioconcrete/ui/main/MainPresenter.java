package br.com.douglasmotta.desafioconcrete.ui.main;

import android.util.Log;

import br.com.douglasmotta.desafioconcrete.data.GitHubService;
import br.com.douglasmotta.desafioconcrete.data.network.response.RepositoryListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private GitHubService service;

    public MainPresenter(MainContract.View view, GitHubService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void getRepositoryList(String language, String sort, int page) {
        Call<RepositoryListResponse> call = service.repositoryList("java", "stars", 1);

        call.enqueue(new Callback<RepositoryListResponse>() {
            @Override
            public void onResponse(Call<RepositoryListResponse> call, Response<RepositoryListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getRepositoryResponseList().size() > 0) {
                        view.setupAdapter(response.body().getRepositoryResponseList());
                    } else {
                        view.displayError();
                    }
                }
            }

            @Override
            public void onFailure(Call<RepositoryListResponse> call, Throwable t) {
                Log.e("MainActivity", t.getMessage());
            }
        });
    }
}
