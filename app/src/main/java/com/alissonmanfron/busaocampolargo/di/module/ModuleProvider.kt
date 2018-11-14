package com.alissonmanfron.busaocampolargo.di.module

import android.arch.persistence.room.Room
import com.alissonmanfron.busaocampolargo.fragment.favoritos.FragFavoritosContract
import com.alissonmanfron.busaocampolargo.fragment.favoritos.FragFavoritosInteractorImpl
import com.alissonmanfron.busaocampolargo.fragment.favoritos.FragFavoritosPresenterImpl
import com.alissonmanfron.busaocampolargo.fragment.linhas.FragLinhasContract
import com.alissonmanfron.busaocampolargo.fragment.linhas.FragLinhasInteractorImpl
import com.alissonmanfron.busaocampolargo.fragment.linhas.FragLinhasPresenterImpl
import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

var appModule = module {

    // Room Database
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "bus-db")
                .build()
    }

    // LinhaDAO
    single { get<AppDatabase>().linhaDao() }


    // define a submodule
    module("view") {

        // FragLinhas
        factory<FragLinhasContract.LinhasInteractor> { FragLinhasInteractorImpl(get()) }

        factory<FragLinhasContract.LinhasPresenter> { FragLinhasPresenterImpl(get()) }


        // FragLinhasFavoritos
        factory<FragFavoritosContract.LinhasInteractor> { FragFavoritosInteractorImpl(get()) }

        factory<FragFavoritosContract.LinhasPresenter> { FragFavoritosPresenterImpl(get()) }
    }

}