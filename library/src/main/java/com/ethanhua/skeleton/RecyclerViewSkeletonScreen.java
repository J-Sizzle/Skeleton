package com.ethanhua.skeleton;

import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class RecyclerViewSkeletonScreen implements SkeletonScreen {

    private final RecyclerView mRecyclerView;
    private final RecyclerView.Adapter mActualAdapter;
    private final SkeletonAdapter mSkeletonAdapter;
    private final SkeletonErrorAdapter mSkeletonErrorAdapter;
    private final boolean mRecyclerViewFrozen;

    private RecyclerViewSkeletonScreen(Builder builder) {
        mRecyclerView = builder.mRecyclerView;
        mActualAdapter = builder.mActualAdapter;
        mSkeletonErrorAdapter = new SkeletonErrorAdapter(builder.mErrorView);
        mSkeletonErrorAdapter.setErrorActionReference(builder.mErrorActionViewId);
        mSkeletonErrorAdapter.setErrorActionClickListener(builder.mErrorActionClickListener);
        mSkeletonAdapter = new SkeletonAdapter();
        mSkeletonAdapter.setItemCount(builder.mItemCount);
        mSkeletonAdapter.setLayoutReference(builder.mItemResID);
        mSkeletonAdapter.shimmer(builder.mShimmer);
        mSkeletonAdapter.setShimmerColor(builder.mShimmerColor);
        mSkeletonAdapter.setShimmerAngle(builder.mShimmerAngle);
        mSkeletonAdapter.setShimmerDuration(builder.mShimmerDuration);
        mRecyclerViewFrozen = builder.mFrozen;
    }

    @Override
    public void show() {
        mRecyclerView.setAdapter(mSkeletonAdapter);
        if (!mRecyclerView.isComputingLayout() && mRecyclerViewFrozen) {
            mRecyclerView.setLayoutFrozen(true);
        }
    }

    @Override
    public void hide() {
        if (!mRecyclerView.getAdapter().equals(mActualAdapter)) {
            mRecyclerView.setAdapter(mActualAdapter);
        }
    }

    @Override
    public void error() {
        mRecyclerView.setAdapter(mSkeletonErrorAdapter);
    }

    public static class Builder {
        private RecyclerView.Adapter mActualAdapter;
        private final RecyclerView mRecyclerView;
        private boolean mShimmer = true;
        private int mItemCount = 10;
        private int mItemResID = R.layout.layout_default_item_skeleton;
        private int mShimmerColor;
        private int mShimmerDuration = 1000;
        private int mShimmerAngle = 20;
        private View mErrorView;
        private int mErrorActionViewId;
        private View.OnClickListener mErrorActionClickListener;
        private boolean mFrozen = true;

        public Builder(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
            this.mShimmerColor = ContextCompat.getColor(recyclerView.getContext(), R.color
                    .shimmer_color);
            this.mErrorView = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout
                    .layout_default_item_skeleton, null, false);
        }

        /**
         * @param adapter the target recyclerView actual adapter
         */
        public Builder adapter(RecyclerView.Adapter adapter) {
            this.mActualAdapter = adapter;
            return this;
        }

        /**
         * @param errorLayoutId the layout to show on error
         */
        public Builder error(int errorLayoutId) {
            this.mErrorView = LayoutInflater.from(mRecyclerView.getContext()).inflate
                    (errorLayoutId, null, false);
            return this;
        }

        /**
         * @param errorView the view to show on error
         */
        public Builder error(View errorView) {
            this.mErrorView = errorView;
            return this;
        }

        /**
         * @param errorActionViewId the view that is clickable in error screen
         */
        public Builder errorActionViewId(int errorActionViewId) {
            this.mErrorActionViewId = errorActionViewId;
            return this;
        }

        /**
         * @param errorActionClickListener will be called for the view set in errorActionViewId
         */
        public Builder errorActionClickListener(View.OnClickListener errorActionClickListener) {
            this.mErrorActionClickListener = errorActionClickListener;
            return this;
        }

        /**
         * @param itemCount the child item count in recyclerView
         */
        public Builder count(int itemCount) {
            this.mItemCount = itemCount;
            return this;
        }

        /**
         * @param shimmer whether show shimmer animation
         */
        public Builder shimmer(boolean shimmer) {
            this.mShimmer = shimmer;
            return this;
        }

        /**
         * the duration of the animation , the time it will take for the highlight to move from
         * one end of the layout
         * to the other.
         *
         * @param shimmerDuration Duration of the shimmer animation, in milliseconds
         */
        public Builder duration(int shimmerDuration) {
            this.mShimmerDuration = shimmerDuration;
            return this;
        }

        /**
         * @param shimmerColor the shimmer color
         */
        public Builder color(@ColorRes int shimmerColor) {
            this.mShimmerColor = ContextCompat.getColor(mRecyclerView.getContext(), shimmerColor);
            return this;
        }

        /**
         * @param shimmerAngle the angle of the shimmer effect in clockwise direction in degrees.
         */
        public Builder angle(@IntRange(from = 0, to = 30) int shimmerAngle) {
            this.mShimmerAngle = shimmerAngle;
            return this;
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        public Builder load(@LayoutRes int skeletonLayoutResID) {
            this.mItemResID = skeletonLayoutResID;
            return this;
        }

        /**
         * @param frozen whether frozen recyclerView during skeleton showing
         * @return
         */
        public Builder frozen(boolean frozen) {
            this.mFrozen = frozen;
            return this;
        }

        public RecyclerViewSkeletonScreen show() {
            RecyclerViewSkeletonScreen recyclerViewSkeleton = new RecyclerViewSkeletonScreen(this);
            recyclerViewSkeleton.show();
            return recyclerViewSkeleton;
        }
    }
}
