package com.company.kirgizov2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company.kirgizov2.databinding.ActivitySignInBinding
import com.google.firebase.database.*
import com.google.firebase.database.R

class SignIn : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.register.setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
        }
        binding.sing.setOnClickListener {
            database.child("User").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.child(binding.phone.text.toString()).exists()) {
                        val userCurrentData: userModel? = snapshot.child(binding.phone.text.toString()).getValue(userModel::class.java)



                        if (userCurrentData?.phone.equals(binding.phone.text.toString()) && userCurrentData?.pass.equals(
                                binding.password.text.toString()) && userCurrentData?.status=="User")
                        {
                            Toast.makeText(this@SignIn, "Вы вошли как юзер", Toast.LENGTH_SHORT).show()
                            userModel.currentuser=userCurrentData
                            val intent = Intent(this@SignIn, MainActivity::class.java)
                            startActivity(intent)
                        }
                        else if(userCurrentData?.phone.equals(binding.phone.text.toString()) && userCurrentData?.pass.equals(
                                binding.password.text.toString()) && userCurrentData?.status=="Admin"){
                            Toast.makeText(this@SignIn, "Вы вошли как админ", Toast.LENGTH_SHORT).show()
                            userModel.currentuser=userCurrentData
                            val intent = Intent(this@SignIn, WorkZone::class.java)
                            startActivity(intent)

                        }
                        else {
                            Toast.makeText(this@SignIn, "Неверные данные", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this@SignIn, "Номера не существует", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })

        }
    }
}