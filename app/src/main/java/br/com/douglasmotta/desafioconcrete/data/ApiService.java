package br.com.douglasmotta.desafioconcrete.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static GitHubService service;

    public static GitHubService getInstance() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(GitHubService.class);
        }

        return service;
    }
}
