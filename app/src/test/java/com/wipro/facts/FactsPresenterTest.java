package com.wipro.facts;

import com.wipro.data.IDataManager;
import com.wipro.data.db.model.Facts;
import com.wipro.data.db.model.FactsRow;
import com.wipro.data.db.model.FactsTitle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class FactsPresenterTest {

    private List<Facts> mFacts_ = new ArrayList<>();

    @Mock
    private IDataManager mDataManager_;

    @Mock
    private IFactsContract.IFactsView mFactsView_;

    @Captor
    private ArgumentCaptor<IDataManager.LoadFactsCallback> mLoadFactsCallbackCaptor;

    private FactsPresenter mFactsPresenter_;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mFactsPresenter_ = new FactsPresenter(mDataManager_);
        mFactsPresenter_.bindView(mFactsView_);

        mFacts_.add(getFacts());

    }

    @Test
    public void loadFacts(){
        mFactsPresenter_.init();

        Mockito.verify(mDataManager_).fetchFacts(eq(true), mLoadFactsCallbackCaptor.capture());
        mLoadFactsCallbackCaptor.getValue().onFactsLoaded(mFacts_);

        Mockito.verify(mFactsView_).showLoadingScreen(true);
        Mockito.verify(mFactsView_).showLoadingScreen(false);
        ArgumentCaptor<List> showTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(mFactsView_).showFacts(showTasksArgumentCaptor.capture());
        assertEquals(1, showTasksArgumentCaptor.getValue().size());
    }



    private Facts getFacts() {
        FactsTitle factsTitle = new FactsTitle();
        factsTitle.setTitle("Title");
        Facts facts = new Facts();
        facts.setFactsTitle(factsTitle);
        FactsRow row = new FactsRow();
        row.setRowTitle("Row Title1");
        row.setRowDescription("Row Desc1");

        List<FactsRow> factsRows = new ArrayList<>();
        factsRows.add(row);
        facts.setFactsRows(factsRows);
        return facts;
    }


}
