package br.com.douglasmotta.desafioconcrete.data.network.response;

import com.google.gson.annotations.SerializedName;

public class OwnerResponse {

    @SerializedName("login")
    String login;

    @SerializedName("avatar_url")
    String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
