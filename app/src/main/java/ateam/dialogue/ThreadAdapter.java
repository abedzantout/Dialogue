package ateam.dialogue;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.MyViewHolder> {
    private List<Thread> threadList;

    public ThreadAdapter(List<Thread> threadList) {
        this.threadList = threadList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thread_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Thread t = threadList.get(position);
        holder.title.setText(t.getTitle());
        holder.poster.setText(t.getPoster());
    }

    @Override
    public int getItemCount() {
        return threadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, poster;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            poster = (TextView) view.findViewById(R.id.poster);
        }
    }
}
