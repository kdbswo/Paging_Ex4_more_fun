package com.loci.paging_ex4_more_fun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val inputBtn = findViewById<Button>(R.id.inputBtn)

        inputBtn.setOnClickListener {
            val inputText = findViewById<TextInputEditText>(R.id.inputText)

            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("inputText",inputText.text.toString())
            startActivity(intent)

        }


    }



}