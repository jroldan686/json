package acdat.jroldan.json;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 6/02/18.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private ArrayList<Repo> mRepos;

    public RepositoryAdapter() {
        this.mRepos = new ArrayList<>();
    }

    public void setRepos(ArrayList<Repos> repos) {
        this.mRepos = repos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txvName;
        public TextView txvDescription;
        public TextView txvCreatedAt;
    }

    public class ViewHolder(View itemView) {
        super(itemView);

        txvName = (TextView) itemView.findViewById(R.id.textView1);
        txvDescription = (TextView) itemView.findViewById(R.id.textView2);
        txvCreateAt = (TextView) itemView.findViewById(R.id.textView3);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        Repo repo = mRepos.get(position);

        holder.txvName.setText(repo.getName());
        holder.txvDescription.setText(repo.getDescription());
        holder.txvCreatedAt.setText(repo.getCreatedAt());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
