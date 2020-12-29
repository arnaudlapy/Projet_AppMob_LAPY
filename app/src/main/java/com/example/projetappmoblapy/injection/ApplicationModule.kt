package com.example.projetappmoblapy.injection

import android.content.Context
import androidx.room.Room
import com.example.projetappmoblapy.data.local.models.AppDatabase
import com.example.projetappmoblapy.data.local.models.DatabaseDao
import com.example.projetappmoblapy.data.repository.UserRepository
import com.example.projetappmoblapy.domain.usercase.CreateUserUseCase
import com.example.projetappmoblapy.domain.usercase.GetUserUseCase
import com.example.projetappmoblapy.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import java.security.AccessControlContext

val presentationModule = module {
    factory { MainViewModel(get(), get()) }
}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule = module {
    single { UserRepository(get()) }
    single { createDatabase(androidContext()) }
}

fun createDatabase(context: Context): DatabaseDao {
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return appDatabase.databaseDao()
}
