package com.company.kirgizov2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.kirgizov2.databinding.TimeDataElementBinding

class MyNoteAdapter  : RecyclerView.Adapter<MyNoteAdapter.ViewHolder>() {

    private var ListInAdapter = ArrayList<MyNoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.time_data_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyNoteAdapter.ViewHolder, position: Int) {
        holder.bind(ListInAdapter[position])
    }

    override fun getItemCount(): Int {
        return ListInAdapter.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TimeDataElementBinding.bind(itemView)
        fun bind(myNote: MyNoteModel) {
            binding.nameDoctor.text = myNote.nameDoctor
            binding.timeDoctor.text = myNote.timeDoctor
            binding.dataDoctor.text = myNote.dataDoctor



        }
    }

    fun loadListToAdapter(productList: ArrayList<MyNoteModel>) {
        ListInAdapter = productList
        notifyDataSetChanged()
    }




}