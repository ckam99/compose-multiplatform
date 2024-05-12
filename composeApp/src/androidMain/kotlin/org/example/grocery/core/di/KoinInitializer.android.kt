package org.example.grocery.core.di

import android.content.Context
import org.example.grocery.features.product.di.productModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
       startKoin {
           androidContext(context)
//           androidLogger(Level.DEBUG)
           modules(androidAppModule + productModule)
       }
    }

}