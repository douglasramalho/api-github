package br.com.douglasmotta.desafioconcrete.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.douglasmotta.desafioconcrete.R;
import br.com.douglasmotta.desafioconcrete.data.network.response.RepositoryResponse;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainReyclerViewHolder> {

    private List<RepositoryResponse> repositoryResponseList;
    private OnClickItem onClickItem;

    public MainRecyclerAdapter(List<RepositoryResponse> repositoryResponseList, OnClickItem onClickItem) {
        this.repositoryResponseList = repositoryResponseList;
        this.onClickItem = onClickItem;
    }

    @Override
    public MainReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_main, parent, false);

        return new MainReyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainReyclerViewHolder holder, int position) {
        if (repositoryResponseList != null && repositoryResponseList.size() > 0) {
            bind(repositoryResponseList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (repositoryResponseList != null && repositoryResponseList.size() > 0) {
            return repositoryResponseList.size();
        }

        return 0;
    }

    class MainReyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textRepositoryName;
        TextView textDescription;
        TextView textForksCount;
        TextView textStarsCount;
        TextView textUsername;
        ImageView imageUsername;

        RepositoryResponse repositoryResponse;

        public MainReyclerViewHolder(View itemView) {
            super(itemView);

            textRepositoryName = itemView.findViewById(R.id.text_repository_name);
            textDescription = itemView.findViewById(R.id.text_description);
            textForksCount = itemView.findViewById(R.id.text_forks_number);
            textStarsCount = itemView.findViewById(R.id.text_star_number);
            textUsername = itemView.findViewById(R.id.text_username);
            imageUsername = itemView.findViewById(R.id.image_username);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickItem != null) {
                        onClickItem.onItemClicked(repositoryResponse);
                    }
                }
            });
        }

        public void bind(RepositoryResponse repositoryResponse) {
            this.repositoryResponse = repositoryResponse;

            textRepositoryName.setText(repositoryResponse.getRepositoryName());
            textDescription.setText(repositoryResponse.getRepositoryDescription());
            textForksCount.setText(String.valueOf(repositoryResponse.getForksCount()));
            textStarsCount.setText(String.valueOf(repositoryResponse.getNumberStars()));
            textUsername.setText(repositoryResponse.getOwnerResponse().getLogin());

            Picasso.with(imageUsername.getContext())
                    .load(repositoryResponse.getOwnerResponse().getAvatarUrl())
                    .into(imageUsername);


        }
    }

    interface OnClickItem {
        void onItemClicked(RepositoryResponse repositoryResponse);
    }
}
