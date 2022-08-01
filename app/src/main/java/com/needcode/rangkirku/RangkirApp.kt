package com.needcode.rangkirku

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.needcode.rangkirku.database.preferences.RangkirPreferences
import com.needcode.rangkirku.network.ApiService
import com.needcode.rangkirku.network.RajaOngkirEndPoint
import com.needcode.rangkirku.network.RangkirRepository
import com.needcode.rangkirku.ui.city.CityViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import timber.log.Timber

class RangkirApp : Application(), KodeinAware {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@RangkirApp))

        bind() from singleton {
            RangkirPreferences(instance())
        }

        bind<RajaOngkirEndPoint>() with singleton { ApiService.getClient() }
        bind() from singleton { RangkirRepository(instance(), instance()) }
        bind() from singleton { CityViewModelFactory(instance()) }
    }

}