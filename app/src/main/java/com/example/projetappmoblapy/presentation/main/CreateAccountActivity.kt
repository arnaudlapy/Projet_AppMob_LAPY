package com.example.projetappmoblapy.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.projetappmoblapy.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class CreateAccountActivity:AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        mainViewModel.createLiveData.observe(this, Observer {
            when(it){
                is CreateSucces -> {
                    mainViewModel.onClickedCreate(new_login_edit.text.toString().trim(), new_password_edit.text.toString())
                    var toast: Toast = Toast.makeText(this,"The account has been created!",Toast.LENGTH_LONG)
                    toast.show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                CreateError -> {
                    MaterialAlertDialogBuilder(this)
                            .setTitle("Error")
                            .setMessage("User already exist or unknown character!")
                            .setPositiveButton("Ok!") {dialog, which -> dialog.dismiss()}
                            .show()
                }
            }
        })
        valid_account_button.setOnClickListener {
            mainViewModel.onClickedExist(new_login_edit.text.toString().trim(), new_password_edit.text.toString())
        }
    }
}