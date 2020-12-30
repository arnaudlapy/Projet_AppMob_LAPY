package com.example.projetappmoblapy.presentation.main

sealed class CreateStatus

data class CreateSucces(val email : String, val password : String) : CreateStatus()
object CreateError : CreateStatus()