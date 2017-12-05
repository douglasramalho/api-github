package br.com.douglasmotta.desafioconcrete.data.network.response;

import com.google.gson.annotations.SerializedName;

public class RepositoryResponse {

    @SerializedName("name")
    String repositoryName;

    @SerializedName("description")
    String repositoryDescription;

    @SerializedName("forks_count")
    int forksCount;

    @SerializedName("stargazers_count")
    int numberStars;

    @SerializedName("owner")
    OwnerResponse ownerResponse;

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getRepositoryDescription() {
        return repositoryDescription;
    }

    public int getForksCount() {
        return forksCount;
    }

    public int getNumberStars() {
        return numberStars;
    }

    public OwnerResponse getOwnerResponse() {
        return ownerResponse;
    }
}
