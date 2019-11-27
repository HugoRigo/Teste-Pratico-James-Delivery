package com.example.hugoanjos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainSplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash)

        //Todo======================================================================================
        //Chama o MainActivity e remove esta activity da pilha
        Handler().postDelayed({
            val i = Intent(this@MainSplashActivity, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
            finish()
        }, 1000)
        //Todo======================================================================================
    }
}
