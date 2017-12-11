package br.com.douglasmotta.desafioconcrete.ui.main;

import java.io.Serializable;
import java.util.List;

import br.com.douglasmotta.desafioconcrete.data.network.response.RepositoryResponse;

public interface MainContract {
    interface View {
        void setupAdapter(List<RepositoryResponse> repositoryResponseList);

        void displayError();
    }

    interface Presenter extends Serializable {
        void getRepositoryList(String language, String sort, int page);
    }
}
