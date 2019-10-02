package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val myText:TextView=findViewById(R.id.textView2)
        myText.text="Hola U-tad"
        val myButton:Button=findViewById(R.id.button)
        myButton.setOnClickListener {
            Toast.makeText(MainActivity@this,"Button pressed!",Toast.LENGTH_SHORT).show()
        }
    }
}
