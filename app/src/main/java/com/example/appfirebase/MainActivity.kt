package com.example.appfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val userRef = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btSave: Button = findViewById<Button>(R.id.button)
        btSave.setOnClickListener { saveMarkFromForm() }


        userRef.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val value = dataSnapshot.getValue()

                if (value is String) {
                } else if (value is User) {
                    val usuario = value

                    if(usuario!=null) {writeMark(usuario)}
                }
            }
        })
    }

    private fun saveMarkFromForm() {
        val name: EditText = findViewById(R.id.editText1)
        val lastName: EditText = findViewById(R.id.editText2)
        val age: EditText = findViewById(R.id.editText3)

        val usuario = User(
            name.text.toString(),
            lastName.text.toString(),
            age.text.toString()
        )

        userRef.push().setValue(usuario)
    }

    private fun writeMark(mark: User) {
        val listV: TextView = findViewById(R.id.textView2)
        val text = "${listV.text}\n${mark.toString()}"
        listV.text = text
    }
}