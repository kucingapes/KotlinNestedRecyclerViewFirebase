package com.kucingapes.kotlinrecyclerviewfirebase.Adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kucingapes.kotlinrecyclerviewfirebase.R
import kotlinx.android.synthetic.main.item_card.view.*

class ParentAdapter(var context: Context, var listCard: List<String>) : RecyclerView.Adapter<ParentAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        context = p0.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listCard.size
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        val title = listCard.get(p1)
        val listChild = ArrayList<String>()
        val childRecycler = p0.childRecycler
        val adapter = ChildAdapter(context, listChild)
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("foto/"+title)

        p0.title.text = title
        childRecycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        reference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {
                    val url = snapshot.getValue()
                    listChild.add(url as String)
                    childRecycler.adapter = adapter
                }
            }

        })
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title_grup
        var childRecycler = itemView.list_item

    }
}
