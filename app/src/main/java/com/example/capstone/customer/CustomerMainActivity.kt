package com.example.capstone.customer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.capstone.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class CustomerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.customer_activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val fragmenthome = CustomerFragmentHome()
        val fragmentrequest = CustomerFragmentRequest()
        val fragmenttrack = CustomerFragmentTrack()
        val fragmenthistory = CustomerFragmentHistory()


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