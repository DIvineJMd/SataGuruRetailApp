package com.example.billingapp.workspace.data
import android.content.Context
import android.widget.Toast
import android.content.ContextWrapper
data class Quantity(var quantity: Int)

data class Cartdata(
    var cartPhone: MutableList<Pair<Pair<phonedata, InventoryPhone>, Quantity>> = mutableListOf(),
    var cartAss: MutableList<Pair<Pair<AccessoriesItem, assdata>, Quantity>> = mutableListOf()
) {
    var numOfItems: Int = 0

    private fun updateNumOfItems() {
        numOfItems = cartPhone.size + cartAss.size
    }

    fun addToCartPhone(phoneData: Pair<phonedata, InventoryPhone>,context: Context):Boolean {
        val existingItem = cartPhone.find { it.first.first == phoneData.first && it.first.second == phoneData.second }
        if(existingItem!=null){

            Toast.makeText(
                context,
                "Item already in Cart",
                Toast.LENGTH_LONG
            ).show()
        return false}else
        {Toast.makeText(
            context,
            "Item Added to Cart",
            Toast.LENGTH_LONG
        ).show()
            cartPhone.add(Pair(phoneData, Quantity(1)))
            updateNumOfItems()
       return true }
    }

    fun addToCartAss(accessoryItem: AccessoriesItem, assData: assdata,context: Context):Boolean {
        val existingItem = cartAss.find { it.first.first == accessoryItem && it.first.second == assData }
        if(existingItem!=null){

            Toast.makeText(
                context,
                "Item already in Cart",
                Toast.LENGTH_LONG
            ).show()
       return false }else
        {Toast.makeText(
            context,
            "Item Added to Cart",
            Toast.LENGTH_LONG
        ).show()
            cartAss.add(Pair(Pair(accessoryItem, assData), Quantity(1)))
            updateNumOfItems()
      return true  }

    }
}
