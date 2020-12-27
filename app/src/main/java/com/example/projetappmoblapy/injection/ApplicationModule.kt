package com.example.projetappmoblapy.injection

import com.example.projetappmoblapy.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel() }
}