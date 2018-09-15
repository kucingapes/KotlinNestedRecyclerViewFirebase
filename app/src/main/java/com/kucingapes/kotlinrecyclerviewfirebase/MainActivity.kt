package com.kucingapes.kotlinrecyclerviewfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.firebase.database.*
import com.kucingapes.kotlinrecyclerviewfirebase.Adapter.ParentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listgrup = ArrayList<String>()
    val adapter = ParentAdapter(this, listgrup)
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("foto")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {
                    val title = snapshot.key
                    listgrup.add(title!!)

                    card_list.layoutManager = LinearLayoutManager(baseContext)
                    card_list.adapter = adapter
                    progressbar.visibility = View.GONE
                }
            }

        })

    }
}