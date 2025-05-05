package com.example.capstone.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.capstone.R

class CustomerFragmentHome : Fragment(R.layout.customer_fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_fragment_home, container, false)

        val servicerateBtn = view.findViewById<Button>(R.id.serviceRateBtn)

        servicerateBtn.setOnClickListener {
            showServiceRatesDialog()
        }
        return view
    }

    private fun showServiceRatesDialog(){
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.servicerateslayout, null)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(true)

        val dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }
}