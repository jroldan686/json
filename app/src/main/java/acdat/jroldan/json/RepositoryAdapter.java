package acdat.jroldan.json;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by usuario on 6/02/18.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private ArrayList<Git> mRepos;

    public RepositoryAdapter() {
        this.mRepos = new ArrayList<>();
    }

    public Git get(int position) {
        return mRepos.get(position);
    }

    public void set(ArrayList<Git> repos) {
        this.mRepos = repos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txvName;
        public TextView txvDescription;
        public TextView txvCreatedAt;

        public ViewHolder(View itemView) {
            super(itemView);
            txvName = itemView.findViewById(R.id.txvName);
            txvDescription = itemView.findViewById(R.id.txvDescription);
            txvCreatedAt = itemView.findViewById(R.id.txvCreatedAt);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txvName.setText(mRepos.get(position).getName());
        holder.txvDescription.setText(mRepos.get(position).getDescription().toString());
        holder.txvCreatedAt.setText(mRepos.get(position).getCreatedAt());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }
}
