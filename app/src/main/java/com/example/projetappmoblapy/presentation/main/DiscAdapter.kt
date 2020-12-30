package com.example.projetappmoblapy.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetappmoblapy.R
import com.example.projetappmoblapy.data.local.models.Disc
import kotlinx.android.synthetic.main.activity_api.view.*

class DiscAdapter(private var discs: MutableList<Disc>, private val context: Context): RecyclerView.Adapter<DiscAdapter.ViewHolder>() {

    class ViewHolder(var layout: View) : RecyclerView.ViewHolder(layout) {
        var txtHeader: TextView = layout.firstLine
        var txtFooter: TextView = layout.secondLine
        var imageView: ImageView = layout.icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.activity_api, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = discs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val disc = discs[position]
        holder.txtHeader.text = disc.name
        holder.txtFooter.text = disc.artist
        Glide.with(context).load(disc.imgURL).into(holder.imageView)
    }
}