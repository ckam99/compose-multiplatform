package org.example.grocery.core.di

import org.example.grocery.core.database.DatabaseDriverFactory
import org.koin.dsl.module


val iosAppModule = module {
    single { DatabaseDriverFactory() }
}