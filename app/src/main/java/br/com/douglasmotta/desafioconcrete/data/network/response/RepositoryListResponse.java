package br.com.douglasmotta.desafioconcrete.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryListResponse {

    @SerializedName("items")
    List<RepositoryResponse> repositoryResponseList;

    public List<RepositoryResponse> getRepositoryResponseList() {
        return repositoryResponseList;
    }
}
