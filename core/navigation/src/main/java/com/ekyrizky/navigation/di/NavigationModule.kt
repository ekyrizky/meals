package com.ekyrizky.navigation.di

import com.ekyrizky.navigation.AppComposeNavigator
import com.ekyrizky.navigation.MealsComposeNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun provideComposeNavigator(
        composeNavigator: MealsComposeNavigator
    ): AppComposeNavigator
}