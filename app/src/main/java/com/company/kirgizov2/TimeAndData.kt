package com.company.kirgizov2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.kirgizov2.databinding.ActivityTimeAndDataBinding
import com.google.firebase.database.*

class TimeAndData : AppCompatActivity(),TimaAndDataAdapter.ClickListener {
    lateinit var binding: ActivityTimeAndDataBinding
    var ListAdapter: TimaAndDataAdapter? = null
    private var doctor:String=""
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Note")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeAndDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doctor= intent.getStringExtra("doctor").toString()

        binding.recTimeAndData.layoutManager= LinearLayoutManager(this)
        ListAdapter= TimaAndDataAdapter(this)
        binding.recTimeAndData.adapter=ListAdapter
        ListAdapter?.loadListToAdapter(getList(doctor))
    }
    fun getList(doctor:String):ArrayList<NoteModel>{



        val commonList=ArrayList<NoteModel>()
        database.child(doctor).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    val note = i.getValue(NoteModel::class.java)
                    if (note != null) {
                        commonList.add(note)
                    }
                }
                ListAdapter?.loadListToAdapter(commonList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return commonList
    }

    override fun onClick(note: NoteModel) {
        super.onClick(note)
        val intent= Intent(this,VisitNote::class.java)
        intent.putExtra("doctor",doctor)
        intent.putExtra("time",note.timeDoctor)
        startActivity(intent)
    }
}