package com.amirhusseinsoori.drinkapp.di

import com.amirhusseinsoori.drinkapp.resposiory.DefaultCocktailRepository
import com.amirhusseinsoori.drinkapp.resposiory.DrinkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: DefaultCocktailRepository): DrinkRepository
}