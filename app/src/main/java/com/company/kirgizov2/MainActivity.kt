package com.company.kirgizov2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.company.kirgizov2.databinding.ActivityMainBinding

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity(),DoctorAdapter.ClickListener {
    lateinit var binding: ActivityMainBinding
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ListAdapter:DoctorAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.navLeftMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.myNotes -> {
                    startActivity(Intent(this, MyNotes::class.java))
                }
                R.id.signIn -> {
                    startActivity(Intent(this, SignIn::class.java))
                }

            }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }


        binding.recyclerDoctor.layoutManager = LinearLayoutManager(this)
        ListAdapter = DoctorAdapter(this)
        binding.recyclerDoctor.adapter = ListAdapter
        ListAdapter?.loadListToAdapter(getData())

    }

    fun getData():ArrayList<String>{



        val List=ArrayList<String>()
        database.getReference("Note").get().addOnSuccessListener {
            for (el in it.children){
                if(el.key!=null){
                    List.add(el.key.toString())

                }

            }
            ListAdapter?.loadListToAdapter(List)
        }
        return List
    }
    override fun onClick(doctor: String) {
        startActivity(Intent(this, TimeAndData::class.java).apply {
            putExtra("doctor",doctor)

        })
    }


    override fun onStart() {
        super.onStart()
        ListAdapter?.loadListToAdapter(getData())
    }
}