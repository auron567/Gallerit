package com.example.imagegallery.di

import com.example.imagegallery.contextprovider.DefaultDispatcherProvider
import com.example.imagegallery.contextprovider.DispatcherProvider
import org.koin.dsl.module

val appModule = module {

    // DispatcherProvider instance
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}
