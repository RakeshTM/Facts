package com.wipro.facts;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wipro.data.db.model.FactsRow;
import com.wipro.databinding.RowFactBinding;
import com.wipro.utils.FactsDiffUtil;

import java.util.ArrayList;
import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactsViewHolder> {

    private final LayoutInflater mInflater_;
    private final List<FactsRow> mFactsRows_;

    FactsAdapter(LayoutInflater inflater) {
        this.mInflater_ = inflater;
        this.mFactsRows_ = new ArrayList<>();
    }

    @NonNull
    @Override
    public FactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int vt) {
        return new FactsViewHolder(RowFactBinding.inflate(mInflater_, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FactsViewHolder viewHolder, int pos) {
        viewHolder.bind(mFactsRows_.get(pos));
    }

    @Override
    public int getItemCount() {
        return mFactsRows_.size();
    }

    void updateRows(List<FactsRow> factsRows) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new FactsDiffUtil(mFactsRows_, factsRows));
        this.mFactsRows_.clear();
        this.mFactsRows_.addAll(factsRows);
        diffResult.dispatchUpdatesTo(this);
    }

    static class FactsViewHolder extends RecyclerView.ViewHolder {

        private final RowFactBinding mBinding_;

        FactsViewHolder(@NonNull RowFactBinding binding) {
            super(binding.getRoot());
            this.mBinding_ = binding;
        }

        void bind(FactsRow row) {
            mBinding_.setModel(row);
            mBinding_.executePendingBindings();
        }
    }

}
