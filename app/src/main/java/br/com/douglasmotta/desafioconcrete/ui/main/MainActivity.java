package br.com.douglasmotta.desafioconcrete.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewFlipper;

import java.io.Serializable;
import java.util.List;

import br.com.douglasmotta.desafioconcrete.R;
import br.com.douglasmotta.desafioconcrete.data.ApiService;
import br.com.douglasmotta.desafioconcrete.data.network.response.RepositoryResponse;

public class MainActivity extends AppCompatActivity implements MainContract.View, MainRecyclerAdapter.OnClickItem {

    private static final String BUNDLE_LANGUAGE = "BUNDLE_LANGUAGE";
    private static final String BUNDLE_SORT = "BUNDLE_SORT";
    private static final String BUNDLE_PAGE = "BUNDLE_PAGE";
    private static final String BUNDLE_PRESENTER = "BUNDLE_PRESENTER";
    private static final String BUNDLE_REPOSITORY_LIST = "BUNDLE_REPOSITORY_LIST";


    private RecyclerView recyclerView;
    private ViewFlipper viewFlipper;
    private MainContract.Presenter presenter;

    private String language = "java";
    private String sort = "stars";
    private int page = 1;
    private List<RepositoryResponse> repositoryResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_main);
        viewFlipper = findViewById(R.id.view_flipper);

        attachPresenter();
        setupBundle(savedInstanceState);
    }

    private void setupBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            language = savedInstanceState.getString(BUNDLE_LANGUAGE);
            sort = savedInstanceState.getString(BUNDLE_SORT);
            page = savedInstanceState.getInt(BUNDLE_PAGE);
            repositoryResponseList = (List<RepositoryResponse>) savedInstanceState.getSerializable(BUNDLE_REPOSITORY_LIST);
            setupAdapter(repositoryResponseList);
        } else {
            language = "php";
            sort = "forks";
            page = 1;
        }
    }

    private void attachPresenter() {
        presenter = (MainContract.Presenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new MainPresenter(this, ApiService.getInstance());
            presenter.getRepositoryList(language, sort, page);
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(BUNDLE_LANGUAGE, language);
        outState.putString(BUNDLE_SORT, sort);
        outState.putInt(BUNDLE_PAGE, page);
        outState.putSerializable(BUNDLE_REPOSITORY_LIST, (Serializable) repositoryResponseList);
        outState.putSerializable(BUNDLE_PRESENTER, presenter);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void setupAdapter(List<RepositoryResponse> repositoryResponseList) {
        this.repositoryResponseList = repositoryResponseList;

        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(repositoryResponseList, this);
        recyclerView.setAdapter(mainRecyclerAdapter);
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void displayError() {
        viewFlipper.setDisplayedChild(1);
    }

    @Override
    public void onItemClicked(RepositoryResponse repositoryResponse) {
        //startActivity(new Intent(this, ).putExtra(EXTRA_REPOSITORY, repositoryResponse));
    }
}
