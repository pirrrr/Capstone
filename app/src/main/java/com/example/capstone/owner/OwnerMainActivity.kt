package com.example.capstone.owner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.capstone.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class OwnerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.owner_activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val fragmenthome = OwnerFragmentHome()
        val fragmentrequest = OwnerFragmentRequest()
        val fragmenttrack = OwnerFragmentTrack()
        val fragmenthistory = OwnerFragmentHistory()


        setCurrentFragment(fragmenthome)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> setCurrentFragment(fragmenthome)
                R.id.request -> setCurrentFragment(fragmentrequest)
                R.id.track -> setCurrentFragment(fragmenttrack)
                R.id.history -> setCurrentFragment(fragmenthistory)
            }
            true
        }


    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

}