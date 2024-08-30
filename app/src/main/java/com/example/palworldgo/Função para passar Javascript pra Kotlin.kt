package com.example.palworldgo

import android.content.Context
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity
//Passa a variável compartilhada para os items
class WebAppInterface(private val context: Context) {
    @JavascriptInterface
    fun updateVariable(variable: String, value: String) {
        // Atualize a variável global específica
        when (variable) {
            "item1" -> SharedVariables.sharedVariable1 = value
            "item2" -> SharedVariables.sharedVariable2 = value
            "item3" -> SharedVariables.sharedVariable3 = value
            "item4" -> SharedVariables.sharedVariable4 = value
            "item5" -> SharedVariables.sharedVariable5 = value
            "item6" -> SharedVariables.sharedVariable6 = value
            "item7" -> SharedVariables.sharedVariable7 = value

        }

        // Atualize a UI no Inventário se necessário
        if (context is Inventario) {
            context.updateInventoryUI()
        }
    }
}
