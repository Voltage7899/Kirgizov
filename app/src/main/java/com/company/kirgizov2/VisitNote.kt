package com.company.kirgizov2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.company.kirgizov2.databinding.ActivityVisitNoteBinding
import com.google.firebase.database.*

class VisitNote : AppCompatActivity() {
    lateinit var binding: ActivityVisitNoteBinding
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    private var note:NoteModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisitNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doctor = intent.getStringExtra("doctor").toString()
        val time=intent.getStringExtra("time").toString()

        getData(doctor,time)



        binding.makeNote.setOnClickListener {
            makeNote(doctor,time)
        }


    }
    fun getData(doctor:String,time:String){

        database.child("Note").child(doctor).child(time).get().addOnSuccessListener {
            note=it.getValue(NoteModel::class.java)


            binding.name.setText(note?.nameDoctor)
            binding.time.setText(note?.timeDoctor)
            binding.data.setText(note?.dataDoctor)
        }
    }

    fun makeNote(doctor:String,time:String){
        val id = userModel.currentuser?.phone.toString()
        val myNote=MyNoteModel(id,note?.nameDoctor,note?.timeDoctor,note?.dataDoctor)
        database.child("MyNote").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child(id).child(note?.nameDoctor.toString()).exists()){
                    Toast.makeText(this@VisitNote,"Запись уже есть", Toast.LENGTH_LONG).show()
                }
                else{
                    database.child("MyNote").child(id).child(note?.nameDoctor.toString()).setValue(myNote)
                    Toast.makeText(this@VisitNote,"Вы записались к врачу", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}