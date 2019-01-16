package com.wipro.facts;

import com.wipro.data.db.model.FactsRow;

import java.util.List;

public interface IFactsContract {

    interface IFactsView {

        void showLoadingScreen(boolean show);

        void showLoadingError(String message);

        void showFacts(List<FactsRow> rows);

        void initUI();

        void showTitle(String title);

        void showEmpty();
    }

    interface IFactsPresenter {

        void init();

        void bindView(IFactsView view);

        void refresh();
    }

}
