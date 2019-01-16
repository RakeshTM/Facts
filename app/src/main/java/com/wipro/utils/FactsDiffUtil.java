package com.wipro.utils;

import android.support.v7.util.DiffUtil;

import com.wipro.data.db.model.FactsRow;

import java.util.List;

public class FactsDiffUtil extends DiffUtil.Callback {

    private final List<FactsRow> mOldData_;
    private final List<FactsRow> mNewData_;

    public FactsDiffUtil(List<FactsRow> oldData, List<FactsRow> newData) {
        this.mOldData_ = oldData;
        this.mNewData_ = newData;
    }

    @Override
    public int getOldListSize() {
        return mOldData_.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData_.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return mOldData_.get(i).getRowId() == mNewData_.get(i1).getRowId();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return mOldData_.get(i).check(mNewData_.get(i1));
    }
}