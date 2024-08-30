package com.example.palworldgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
//Classe para o menu de inventário
class Inventario : AppCompatActivity() {

    private lateinit var inventoryGridView: GridView
    private lateinit var inventoryAdapter: InventoryAdapter
    private lateinit var volta: Button

    private val inventoryItems = mutableListOf(
        Item(R.drawable.item1, isCollected = false),
        Item(R.drawable.item2, isCollected = false),
        Item(R.drawable.item3, isCollected = false),
        Item(R.drawable.item4, isCollected = false),
        Item(R.drawable.item5, isCollected = false),
        Item(R.drawable.item6, isCollected = false),
        Item(R.drawable.item7, isCollected = false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menuin)

        volta = findViewById(R.id.volta)
        volta.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        inventoryGridView = findViewById(R.id.inventoryGridView)
        inventoryAdapter = InventoryAdapter(this, inventoryItems)
        inventoryGridView.adapter = inventoryAdapter

        // Atualiza a UI com base nas variáveis compartilhadas
        updateInventoryUI()
    }

    fun updateInventoryUI() {
        inventoryItems[0].isCollected = SharedVariables.sharedVariable1 == "true"
        inventoryItems[1].isCollected = SharedVariables.sharedVariable2 == "true"
        inventoryItems[2].isCollected = SharedVariables.sharedVariable3 == "true"
        inventoryItems[3].isCollected = SharedVariables.sharedVariable4 == "true"
        inventoryItems[4].isCollected = SharedVariables.sharedVariable5 == "true"
        inventoryItems[5].isCollected = SharedVariables.sharedVariable6 == "true"
        inventoryItems[6].isCollected = SharedVariables.sharedVariable7 == "true"


        inventoryAdapter.updateItems(inventoryItems)
    }
}
