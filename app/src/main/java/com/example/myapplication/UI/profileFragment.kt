package com.example.myapplication.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.myapplication.R

class profileFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileDescription: TextView
    private lateinit var profileCity: TextView
    private lateinit var profileBirth: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        profileImage = view.findViewById(R.id.imageView)
        profileName = view.findViewById(R.id.name)
        profileCity = view.findViewById(R.id.city)
        profileBirth = view.findViewById(R.id.birth)
        profileDescription = view.findViewById(R.id.description)
        profileName.text = "Paco"
        profileBirth.text = "24/10/19"
        profileCity.text = "Madrid"
        profileDescription.text =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ac nisi id purus placerat accumsan. Ut scelerisque eros ut elementum vestibulum. Maecenas ac bibendum orci, in sollicitudin magna. Integer non nisi eu risus vestibulum sodales. Vivamus mollis magna imperdiet felis bibendum ultrices. Aenean quis sem ullamcorper, hendrerit ex cursus, elementum libero. Curabitur ac leo sit amet ante laoreet consequat at quis arcu."
        profileImage.setImageResource(R.drawable.saitama)
        return view
    }
}
