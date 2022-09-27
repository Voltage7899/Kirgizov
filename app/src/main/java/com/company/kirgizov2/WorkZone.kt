package com.company.kirgizov2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company.kirgizov2.databinding.ActivityWorkZoneBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WorkZone : AppCompatActivity() {
    private lateinit var binding: ActivityWorkZoneBinding
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWorkZoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMovie.setOnClickListener {


            val noteModel=NoteModel(binding.addSpec.text.toString(),binding.addNameDoctor.text.toString(),binding.addTime.text.toString(),binding.addData.text.toString())

            database.getReference("Note").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.child(noteModel.specDoctor.toString()).child(noteModel.timeDoctor.toString()).exists()){
                        Toast.makeText(this@WorkZone,"Фильм на данное время уже существует", Toast.LENGTH_SHORT).show()
                    }
                    else{

                        database.getReference("Note").child(noteModel.specDoctor.toString()).child(noteModel.timeDoctor.toString()).setValue(noteModel)
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

    }
}