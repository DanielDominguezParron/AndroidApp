package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileImage: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileDescription: TextView
    private lateinit var profileCity: TextView
    private lateinit var profileBirth: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profileImage = findViewById(R.id.imageView)
        profileName = findViewById(R.id.name)
        profileCity = findViewById(R.id.city)
        profileBirth = findViewById(R.id.birth)
        profileDescription = findViewById(R.id.description)
        var intentImage = intent.getIntExtra("image", 1)
        var intentName = intent.extras?.getString("name")
        var intentCity = intent.extras?.getString("city")
        var intentBirth = intent.extras?.getString("birth_date")
        var intentDscrp = intent.extras?.getString("description")
        profileName.text = intentName
        profileBirth.text = intentBirth
        profileCity.text = intentCity
        profileDescription.text = intentDscrp
        profileImage.setImageResource(intentImage)


    }
}
