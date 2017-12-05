package br.com.douglasmotta.desafioconcrete.data;

import br.com.douglasmotta.desafioconcrete.data.network.response.RepositoryListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("search/repositories")
    Call<RepositoryListResponse> repositoryList(
            @Query("q") String language,
            @Query("sort") String sort,
            @Query("page") int page);
}
