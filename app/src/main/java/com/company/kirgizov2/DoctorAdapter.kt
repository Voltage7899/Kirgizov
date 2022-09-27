package com.company.kirgizov2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.kirgizov2.databinding.ElementBinding

class DoctorAdapter(val clickListener: ClickListener) : RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    private var ListInAdapter = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorAdapter.ViewHolder, position: Int) {
        holder.bind(ListInAdapter[position], clickListener)
    }

    override fun getItemCount(): Int {
        return ListInAdapter.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ElementBinding.bind(itemView)
        fun bind(doctor: String, clickListener: ClickListener) {
            binding.elDoctor.text = doctor

            itemView.setOnClickListener {

                clickListener.onClick(doctor)
            }

        }
    }

    fun loadListToAdapter(productList: ArrayList<String>) {
        ListInAdapter = productList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(doctor: String) {

        }
    }

    
}