package com.example.palworldgo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
//Adaptador serve para pegar a informação dos items e definir o item coletado como visível ou invisível
class InventoryAdapter(private val context: Context, private var items: List<Item>) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.invitem, parent, false)
        val imageView: ImageView = view.findViewById(R.id.itemImage)

        val item = getItem(position) as Item
        imageView.setImageResource(item.imageResource)
        imageView.visibility = if (item.isCollected) View.VISIBLE else View.INVISIBLE

        return view
    }


    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}
