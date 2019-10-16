package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var myText: TextView
    private lateinit var myButton: Button
    private lateinit var profileButton: Button
    private lateinit var DetailsButton: Button
    private  lateinit var message:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        myText = findViewById(R.id.textView2)
        myButton = findViewById(R.id.btnIncrement)
        profileButton = findViewById(R.id.profileButton)
        DetailsButton = findViewById(R.id.DetailsButton)
        DetailsButton.setOnClickListener {
            val intent = Intent(this,MovieDetailsActivity::class.java)
            startActivity(intent)
        }
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("name", "Saitama")
            intent.putExtra("image", R.drawable.saitama)
            intent.putExtra("birth_date", "24/2/1994")
            intent.putExtra("city", "Ciudad-Z")
            intent.putExtra(
                "description",

                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer lectus ante, molestie eget blandit vitae, maximus sed libero. Vestibulum efficitur, eros nec luctus congue, magna urna pretium ipsum, ut porttitor ipsum ipsum et nulla. Mauris nec ex et tellus pellentesque euismod. Nam lobortis mi odio, in finibus lectus luctus at. Donec mollis enim tellus, et suscipit quam pharetra vitae. Etiam dignissim venenatis odio quis condimentum. Nunc cursus nisl at turpis porttitor finibus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nunc non sem ac lectus fermentum hendrerit et eu ipsum. Curabitur venenatis lorem sit amet facilisis mollis.\n" +
                        "\n" +
                        "Fusce vitae odio vel mauris suscipit dignissim. Integer sit amet urna vel nulla cursus ornare. Vivamus suscipit diam et augue tincidunt volutpat. Suspendisse auctor nibh et tellus laoreet, ac sodales dolor condimentum. Maecenas ante erat, tempus cursus luctus nec, pellentesque nec eros. In hac habitasse platea dictumst. Vivamus eget accumsan nunc. Curabitur pellentesque pulvinar ligula, id blandit elit vestibulum ac. Pellentesque eleifend tempor dui id cursus. Aliquam blandit, arcu ut pretium pretium, eros sapien consequat est, sed luctus felis felis vel ex. Morbi sit amet elementum dui. Nam semper justo id leo elementum consectetur non non diam."
            )
            startActivity(intent)
        }
        var cont = 0
        myButton.setOnClickListener {
            cont++
             message = getString(R.string.button_pressed, cont, cont)
            myText.text = message
            Toast.makeText(MainActivity@ this, "Button pressed!", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.e("MainActivity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity","onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.e("MainActivity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity","onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity","onDestroy")
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("textView2",message)


    }
}