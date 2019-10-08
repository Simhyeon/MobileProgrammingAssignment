package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.gson_test.*
import com.example.assignment.UserDataManager
import kotlinx.android.synthetic.main.content_main.*
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {

    private val dataManager = UserDataManager()
    // var userDataSets = UserDataManager.loadData(applicationContext)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataManager.initialFileCheck(applicationContext)

        loginBtn.setOnClickListener {
            if (UserDataManager.dataSets?.validate(idInputText.text.toString(), pwdInputText.text.toString()) != null) {
                startActivity (Intent(applicationContext, CalculatorActivity::class.java))
            } else { // not found _ value is null
                Toast.makeText(applicationContext, "아이디나 비밀번호를 확인해 주세요.", Toast.LENGTH_LONG).show()
            }
        }

        signUpBtn.setOnClickListener {
            startActivity (Intent(applicationContext, SignUpActivity::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
        idInputText.text.clear()
        pwdInputText.text.clear()
    }
}