package com.wipro.facts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wipro.data.db.model.FactsRow;
import com.wipro.databinding.FragmentFactsBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerFragment;

public class FactsFragment extends DaggerFragment implements IFactsContract.IFactsView {

    @Inject
    Lazy<IFactsContract.IFactsPresenter> mPresenterLazy_;
    private FactsAdapter mAdapter_;
    private FragmentFactsBinding mBinding_;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding_ = FragmentFactsBinding.inflate(inflater, container, false);
        return mBinding_.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenterLazy_.get().init();
    }

    @Override
    public void initUI() {
        initToolbar();
        initRecyclerView();
        initSwipe();
    }

    private void initSwipe() {
        mBinding_.srlFacts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenterLazy_.get().refresh();
            }
        });
    }

    private void initRecyclerView() {
        mAdapter_ = new FactsAdapter(LayoutInflater.from(getContext()));
        mBinding_.rvFacts.setAdapter(mAdapter_);
        mBinding_.rvFacts.setHasFixedSize(false);
    }

    private void initToolbar() {
        if (getActivity() == null) {
            return;
        }
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding_.toolbar);
    }

    @Override
    public void showLoadingScreen(boolean show) {
        mBinding_.srlFacts.setRefreshing(show);
    }

    @Override
    public void showLoadingError(String message) {
        if (isDetached()) {
            return;
        }
        mBinding_.srlFacts.setRefreshing(false);
        Snackbar.make(mBinding_.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showFacts(List<FactsRow> rows) {
        if (isDetached()) {
            return;
        }
        mAdapter_.updateRows(rows);
        showEmpty(false);
    }

    @Override
    public void showEmpty() {
        showEmpty(true);
    }

    private void showEmpty(boolean show) {
        if (isDetached()) {
            return;
        }
        mBinding_.rvFacts.setVisibility(show ? View.GONE : View.VISIBLE);
        mBinding_.tvEmptyFacts.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showTitle(String title) {
        if (isDetached()) {
            return;
        }
        if (getActivity() == null) {
            return;
        }
        if (((AppCompatActivity) getActivity()).getSupportActionBar() == null) {
            return;
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }
}
