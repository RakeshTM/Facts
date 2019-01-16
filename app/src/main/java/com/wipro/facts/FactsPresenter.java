package com.wipro.facts;

import com.wipro.data.IDataManager;
import com.wipro.data.db.model.Facts;
import com.wipro.data.db.model.FactsRow;
import com.wipro.di.annotations.FragmentScoped;
import com.wipro.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

@FragmentScoped
public class FactsPresenter implements IFactsContract.IFactsPresenter {

    private final IDataManager mDataManager_;
    private IFactsContract.IFactsView mView_;

    @Inject
    FactsPresenter(IDataManager dataManager) {
        mDataManager_ = dataManager;
    }


    @Inject
    @Override
    public void bindView(IFactsContract.IFactsView view) {
        mView_ = view;
    }

    @Override
    public void init() {
        mView_.initUI();
        boolean appInitialised = mDataManager_.isAppInitialised();
        fetchFacts(!appInitialised);
    }

    private void fetchFacts(boolean forceLoad) {
        mView_.showLoadingScreen(true);
        mDataManager_.fetchFacts(forceLoad, new IDataManager.LoadFactsCallback() {

            @Override
            public void onFactsLoaded(List<Facts> facts) {
                mDataManager_.setAppInitialised();
                updateUI(facts);
            }

            @Override
            public void onDataNotAvailable(String message) {
                mView_.showLoadingError(message);
            }
        });
    }

    private void updateUI(List<Facts> facts) {
        mView_.showLoadingScreen(false);

        if (facts == null || facts.isEmpty()) {
            mView_.showEmpty();
            return;
        }

        Facts fact = facts.get(0);
        String title = fact.getFactsTitle().getTitle();

        mView_.showTitle(StringUtils.isEmpty(title) ? "" : title);

        List<FactsRow> rows = fact.getFactsRows();
        if (rows.isEmpty()) {
            mView_.showEmpty();
            return;
        }

        mView_.showFacts(rows);
    }

    @Override
    public void refresh() {
        fetchFacts(true);
    }
}
