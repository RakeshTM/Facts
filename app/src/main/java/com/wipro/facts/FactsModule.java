package com.wipro.facts;

import dagger.Binds;
import dagger.Module;

@Module
public interface FactsModule {

    @Binds
    IFactsContract.IFactsView bindView(FactsFragment factsFragment);

    @Binds
    IFactsContract.IFactsPresenter bindPresenter(FactsPresenter presenter);

}
