package com.example.projetappmoblapy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.projetappmoblapy.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSucces -> TODO()
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                            .setTitle("Erreur")
                            .setMessage("Login ou password inconnu")
                            .setPositiveButton("Ok!") {dialog, which -> dialog.dismiss()}
                            .show()
                }
            }
        })
        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(), password_edit.text.toString())
        }
    }
}

