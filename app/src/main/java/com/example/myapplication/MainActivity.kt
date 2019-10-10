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
    private lateinit var myText:TextView
    private lateinit var myButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        myText=findViewById(R.id.textView2)
        myButton=findViewById(R.id.btnIncrement)
        var cont=0
        myButton.setOnClickListener {
            cont++
            val message=getString(R.string.button_pressed,cont,cont)
            myText.text=message
            Toast.makeText(MainActivity@this,"Button pressed!",Toast.LENGTH_SHORT).show()
        }
    }
}