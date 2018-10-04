package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SkeletonErrorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Integer mLayoutReference;
    private Integer mErrorActionReference;
    private View.OnClickListener mErrorActionClickListener;

    SkeletonErrorAdapter(int mErrorLayoutID) {
        this.mLayoutReference = mErrorLayoutID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RecyclerView.ViewHolder(inflater.inflate(mLayoutReference, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mErrorActionReference != null && mErrorActionClickListener != null) {
            holder.itemView.findViewById(mErrorActionReference).setOnClickListener(mErrorActionClickListener);
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
