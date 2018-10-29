package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SkeletonErrorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View mErrorView;
    private Integer mErrorActionReference;
    private View.OnClickListener mErrorActionClickListener;

    SkeletonErrorAdapter(View mErrorView) {
        this.mErrorView = mErrorView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(mErrorView) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                .MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        if (mErrorActionReference != null && mErrorActionClickListener != null) {
            holder.itemView.findViewById(mErrorActionReference).setOnClickListener
                    (mErrorActionClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    void setErrorActionReference(int mErrorActionReference) {
        this.mErrorActionReference = mErrorActionReference;
    }

    void setErrorActionClickListener(View.OnClickListener mErrorActionClickListener) {
        this.mErrorActionClickListener = mErrorActionClickListener;
    }
}
