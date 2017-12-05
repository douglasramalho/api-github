package br.com.douglasmotta.desafioconcrete.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import br.com.douglasmotta.desafioconcrete.R;
import br.com.douglasmotta.desafioconcrete.data.ApiService;
import br.com.douglasmotta.desafioconcrete.data.network.response.RepositoryListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_main);

        Call<RepositoryListResponse> call = ApiService.getInstance().repositoryList("java", "stars", 1);

        call.enqueue(new Callback<RepositoryListResponse>() {
            @Override
            public void onResponse(Call<RepositoryListResponse> call, Response<RepositoryListResponse> response) {
                MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(response.body().getRepositoryResponseList());
                recyclerView.setAdapter(mainRecyclerAdapter);
                RecyclerView.LayoutManager layoutManager
                        = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<RepositoryListResponse> call, Throwable t) {
                Log.e("MainActivity", t.getMessage());
            }
        });
    }
}
