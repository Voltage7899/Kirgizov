package com.company.kirgizov2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.kirgizov2.databinding.TimeDataElementBinding

class TimaAndDataAdapter(val clickListener: ClickListener) : RecyclerView.Adapter<TimaAndDataAdapter.ViewHolder>() {

    private var ListInAdapter = ArrayList<NoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimaAndDataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.time_data_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimaAndDataAdapter.ViewHolder, position: Int) {
        holder.bind(ListInAdapter[position], clickListener)
    }

    override fun getItemCount(): Int {
        return ListInAdapter.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TimeDataElementBinding.bind(itemView)
        fun bind(note: NoteModel, clickListener: ClickListener) {
            binding.nameDoctor.text = note.nameDoctor
            binding.timeDoctor.text = note.timeDoctor
            binding.dataDoctor.text = note.dataDoctor


            itemView.setOnClickListener {

                clickListener.onClick(note)
            }

        }
    }

    fun loadListToAdapter(testList: ArrayList<NoteModel>) {
        ListInAdapter = testList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(note: NoteModel) {

        }
    }
}