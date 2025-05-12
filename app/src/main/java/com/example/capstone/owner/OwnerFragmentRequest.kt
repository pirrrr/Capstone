package com.example.capstone.owner

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.adapter.RequestAdapter
import com.example.capstone.network.ApiClient
import com.example.capstone.repository.RequestRepository
import com.example.capstone.viewmodel.OwnerRequestViewModel
import com.example.capstone.viewmodel.OwnerRequestViewModelFactory

class OwnerFragmentRequest : Fragment(R.layout.owner_fragment_request) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var requestAdapter: RequestAdapter
    private lateinit var ownerRequestViewModel: OwnerRequestViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewOwnerRequests)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        requestAdapter = RequestAdapter(emptyList())
        recyclerView.adapter = requestAdapter

        // Retrieve token
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", null)

        if (token.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Missing auth token", Toast.LENGTH_SHORT).show()
            return
        }

        // Set up ViewModel with repository
        val authedApiService = ApiClient.getApiService { token }
        val repository = RequestRepository(authedApiService)
        val viewModelFactory = OwnerRequestViewModelFactory(repository)
        ownerRequestViewModel = ViewModelProvider(this, viewModelFactory)[OwnerRequestViewModel::class.java]

        // Observe LiveData from ViewModel
        ownerRequestViewModel.ownerRequests.observe(viewLifecycleOwner) { requests ->
            if (requests != null && requests.isNotEmpty()) {
                requestAdapter.updateRequests(requests)
            } else {
                Toast.makeText(requireContext(), "No requests found", Toast.LENGTH_SHORT).show()
            }
        }

        // Fetch owner requests from API
        ownerRequestViewModel.fetchOwnerRequests()
    }
}
