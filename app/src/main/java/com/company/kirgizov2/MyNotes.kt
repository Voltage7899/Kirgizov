package com.company.kirgizov2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.kirgizov2.databinding.ActivityMyNotesBinding
import com.google.firebase.database.FirebaseDatabase

class MyNotes : AppCompatActivity() {
    lateinit var binding: ActivityMyNotesBinding
    var ListAdapter:MyNoteAdapter?=null
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recMyNote.layoutManager= LinearLayoutManager(this)
        ListAdapter = MyNoteAdapter()
        binding.recMyNote.adapter=ListAdapter
        ListAdapter?.loadListToAdapter(getData())
    }
    fun getData():ArrayList<MyNoteModel>{



        val List=ArrayList<MyNoteModel>()
        database.getReference("MyNote").child(userModel.currentuser?.phone.toString()).get().addOnSuccessListener {
            for (i in it.children){
                var myNote=i.getValue(MyNoteModel::class.java)
                if(myNote!=null){
                    List.add(myNote)

                }

            }
            ListAdapter?.loadListToAdapter(List)
        }
        return List
    }

    override fun onStart() {
        super.onStart()
        ListAdapter?.loadListToAdapter(getData())
    }
}