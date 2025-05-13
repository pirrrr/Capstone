package com.example.capstone.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.data.models.Request

class RequestAdapter(private var requestList: List<Request>) :
    RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCustomerName: TextView = itemView.findViewById(R.id.tvCustomerName)
        val tvSackQty: TextView = itemView.findViewById(R.id.tvSackQty)
        val tvServices: TextView = itemView.findViewById(R.id.tvServices)
        val tvSchedule: TextView = itemView.findViewById(R.id.tvSchedule)
        val tvPaymentMethod: TextView = itemView.findViewById(R.id.tvPaymentMethod)
        val tvComment: TextView = itemView.findViewById(R.id.tvComment)
        val tvSubmittedAt: TextView = itemView.findViewById(R.id.tvSubmittedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_request, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requestList[position]

        Log.d("RequestAdapter", "Request ID: ${request.requestID}")
        Log.d("RequestAdapter", "Customer Name: ${request.customerName}")
        Log.d("RequestAdapter", "Service Name: ${request.serviceName}")
        Log.d("RequestAdapter", "Payment Method: ${request.paymentMethod}")
        Log.d("RequestAdapter", "SubmittedAt: ${request.submittedAt}")

        holder.tvCustomerName.text = "Customer: ${request.customerName}"
        holder.tvSackQty.text = "Sacks: ${request.sackQuantity}"
        holder.tvServices.text = "Services: ${request.serviceName}"
        holder.tvSchedule.text = "Schedule: ${request.schedule}"
        holder.tvPaymentMethod.text = "Payment: ${request.paymentMethod}"
        holder.tvComment.text = "Comment: ${request.comment}"
        holder.tvSubmittedAt.text = "Submitted: ${request.submittedAt}"
    }

    override fun getItemCount(): Int = requestList.size

    fun updateRequests(newRequests: List<Request>) {
        requestList = newRequests
        notifyDataSetChanged()
    }
}
