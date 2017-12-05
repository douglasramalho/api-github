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

    public MainRecyclerAdapter(List<RepositoryResponse> repositoryResponseList) {
        this.repositoryResponseList = repositoryResponseList;
    }

    @Override
    public MainReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_main, parent, false);

        return new MainReyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainReyclerViewHolder holder, int position) {
        if (repositoryResponseList != null && repositoryResponseList.size() > 0) {
            holder.textRepositoryName.setText(repositoryResponseList.get(position).getRepositoryName());
            holder.textDescription.setText(repositoryResponseList.get(position).getRepositoryDescription());
            holder.textForksCount.setText(String.valueOf(repositoryResponseList.get(position).getForksCount()));
            holder.textStarsCount.setText(String.valueOf(repositoryResponseList.get(position).getNumberStars()));
            holder.textUsername.setText(repositoryResponseList.get(position).getOwnerResponse().getLogin());

            Picasso.with(holder.imageUsername.getContext())
                    .load(repositoryResponseList.get(position).getOwnerResponse().getAvatarUrl())
                    .into(holder.imageUsername);
        }
    }

    @Override
    public int getItemCount() {
        if (repositoryResponseList != null && repositoryResponseList.size() > 0) {
            return repositoryResponseList.size();
        }

        return 0;
    }

    static class MainReyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textRepositoryName;
        TextView textDescription;
        TextView textForksCount;
        TextView textStarsCount;
        TextView textUsername;
        ImageView imageUsername;

        public MainReyclerViewHolder(View itemView) {
            super(itemView);

            textRepositoryName = itemView.findViewById(R.id.text_repository_name);
            textDescription = itemView.findViewById(R.id.text_description);
            textForksCount = itemView.findViewById(R.id.text_forks_number);
            textStarsCount = itemView.findViewById(R.id.text_star_number);
            textUsername = itemView.findViewById(R.id.text_username);
            imageUsername = itemView.findViewById(R.id.image_username);
        }
    }
}
