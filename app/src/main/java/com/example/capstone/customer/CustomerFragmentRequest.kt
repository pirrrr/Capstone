package com.example.capstone.customer

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.data.api.ApiService
import com.example.capstone.data.models.CreateRequest
import com.example.capstone.network.ApiClient
import com.example.capstone.repository.RequestRepository
import com.example.capstone.viewmodel.RequestViewModel
import com.example.capstone.viewmodel.RequestViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale


class CustomerFragmentRequest : Fragment(R.layout.customer_fragment_request) {


    private lateinit var btnDatePicker: Button
    private lateinit var tvQuantity: TextView
    private lateinit var cbPickup: CheckBox
    private lateinit var cbDelivery: CheckBox
    private lateinit var cbFeedsCon: CheckBox

    private var quantity = 1
    private val calendarView = Calendar.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val btnMinus = view.findViewById<Button>(R.id.btnMinus)
        tvQuantity = view.findViewById(R.id.tvQuantity)
        cbPickup = view.findViewById(R.id.cbPickup)
        cbDelivery = view.findViewById(R.id.cbDelivery)
        cbFeedsCon = view.findViewById(R.id.cbFeedsCon)
        val rgPayment = view.findViewById<RadioGroup>(R.id.rgPayment)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnDatePicker = view.findViewById(R.id.btnDatePicker)
        val comment = view.findViewById<EditText>(R.id.commentEditText)

        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", null)

        if (token.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Missing auth token", Toast.LENGTH_SHORT).show()
            return
        }

        val authedApiService = ApiClient.getApiService{token}
        val requestRepository = RequestRepository(authedApiService)
        val factory = RequestViewModelFactory(requestRepository)
        val requestViewModel = ViewModelProvider(this, factory)[RequestViewModel::class.java]


        tvQuantity.text = quantity.toString()
        btnAdd.setOnClickListener {
            quantity++
            tvQuantity.text = quantity.toString()
        }
        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tvQuantity.text = quantity.toString()
            }
        }


        val dateBox = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            calendarView.set(Calendar.YEAR, year)
            calendarView.set(Calendar.MONTH, month)
            calendarView.set(Calendar.DAY_OF_MONTH, day)
            updateBtnText(calendarView)
        }
        btnDatePicker.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateBox,
                calendarView.get(Calendar.YEAR),
                calendarView.get(Calendar.MONTH),
                calendarView.get(Calendar.DAY_OF_MONTH))
                .show()
        }


        requestViewModel.submitResult.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                Toast.makeText(requireContext(), "Request submitted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Request submission failed", Toast.LENGTH_SHORT).show()
            }
        }

        btnSubmit.setOnClickListener {
            val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            val customerID = sharedPreferences.getLong("userID", -1L)

            if (customerID == -1L) {
                Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pickupChecked = cbPickup.isChecked
            val deliveryChecked = cbDelivery.isChecked
            val feedsConChecked = cbFeedsCon.isChecked // Reserved for future use?

            val selectedDate = btnDatePicker.text.toString()

            // Prevent default text from being sent as a date
            if ((pickupChecked || deliveryChecked) && selectedDate == "Pickup Date") {
                Toast.makeText(requireContext(), "Please select a pickup date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Calculate serviceID
            val serviceID = when {
                pickupChecked && deliveryChecked -> 3L
                pickupChecked -> 1L
                deliveryChecked -> 2L
                else -> 0L
            }

            if (serviceID == 0L) {
                Toast.makeText(requireContext(), "Please select a service type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedPaymentId = rgPayment.checkedRadioButtonId
            val paymentMethod = view.findViewById<RadioButton>(selectedPaymentId)?.text?.toString() ?: ""

            val request = CreateRequest(
                ownerID = 2L,
                customerID = customerID,
                serviceID = serviceID,
                statusID = 1L,
                courierID = 1L, // temporary
                pickupDate = if (pickupChecked) selectedDate else null,
                deliveryDate = null,
                sackQuantity = quantity,
                comment = comment.text.toString(),
                paymentMethod = paymentMethod
            )

            requestViewModel.submitRequest(request)
        }


    }


    private fun updateBtnText(calendar: Calendar){
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        btnDatePicker.setText(sdf.format(calendar.time))

    }
    
}