package com.wipro.di;

import com.wipro.di.annotations.FragmentScoped;
import com.wipro.facts.FactsFragment;
import com.wipro.facts.FactsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = FactsModule.class)
    FactsFragment bindFactsFragment();

}
