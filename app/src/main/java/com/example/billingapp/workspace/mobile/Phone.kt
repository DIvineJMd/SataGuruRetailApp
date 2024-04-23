package com.example.billingapp.workspace.mobile

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.billingapp.workspace.data.InventoryPhone
import com.google.firebase.database.*
import java.time.temporal.TemporalQueries.offset
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.compose.rememberNavController
import com.example.billingapp.workspace.data.Cartdata
import com.example.billingapp.workspace.data.phonedata
import com.example.billingapp.workspace.selling.Catlogpage
import kotlinx.coroutines.delay

class InventoryViewModel(name: String) : ViewModel() {
    constructor() : this("")
    private val database = FirebaseDatabase.getInstance().reference.child("Inventory/Inventory/Phones/$name")
    private val _inventoryItems = mutableStateOf(emptyList<InventoryPhone>())
    val inventoryItems: List<InventoryPhone> get() = _inventoryItems.value
    var loading by mutableStateOf(true)

    init {
        loadData()
    }

    private fun loadData() {
        val inventoryListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<InventoryPhone>()

                for (productSnapshot in snapshot.children) {
                    val name = productSnapshot.key.toString()
                    val price=productSnapshot.child("price").getValue<Float>() ?: 0
                    val units = productSnapshot.child("units").getValue<Long?>() ?: 0
                    val specsSnapshot = productSnapshot.child("specs")
                    val specs = if (specsSnapshot.exists()) {
                        mapOf(
                            "color" to specsSnapshot.child("color").value.toString(),
                            "storage" to specsSnapshot.child("storage").value.toString()
                        )
                    } else {
                        null
                    }

                    items.add(InventoryPhone(name,price.toFloat(), units.toInt(), specs))
                }
                loading = false
                _inventoryItems.value = items


            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                loading = false
            }
        }

        database.addValueEventListener(inventoryListener)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneInventory(phoneName: String, controller:NavController,cata:Boolean,context:Context) {
    val inventoryViewModel: InventoryViewModel = viewModel(factory = ViewModelFactory(phoneName))
    val myCart by remember { mutableStateOf(Catlogpage.CartDataManager.cartData) }
    var isContextMenuVisible by remember { mutableStateOf(false) }

    var localcart by remember { mutableStateOf(0) }
    localcart=myCart.numOfItems
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if(cata){Text(text = "Phone Catalog")}else{ Text(text = "Phone Inventory") }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xffff6b00)
                ),
                navigationIcon = {
                    IconButton(onClick = { controller.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {IconButton(onClick = {  controller.navigate("billing")  }) {
                    if (cata) {
                        // Cart icon for Accessories Inventory

                        BadgedBox(
                            badge = { Badge { Text("$localcart") } },
                            modifier = Modifier.offset(x = -7.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "Cart",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    } else {
                        // Home icon for Accessories Catalog
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }}
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (inventoryViewModel.loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                )
            } else if (inventoryViewModel.inventoryItems.isEmpty()) {
                // Empty state
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No items available.")
                }
            } else {
                // Display the list
                LazyColumn {
                    items(inventoryViewModel.inventoryItems) { item ->
                        Card(
                            colors = CardColors(
                                containerColor = Color(0xFFFFBBA6),
                                contentColor = Color(0xFF2E2E2E),
                                disabledContainerColor = Color(0xFF6BBCC7),
                                disabledContentColor = Color(0xFF6BBCC7)
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 12.dp,
                                focusedElevation = 10.dp,
                                hoveredElevation = 10.dp,
                                draggedElevation = 10.dp,
                                disabledElevation = 4.dp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(7.5.dp)
                                .clickable { }

                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = " ${item.name}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                    if(cata){
                                        IconButton(onClick = {
                                            val naamcom=phonedata(phoneName)
                                           if( myCart.addToCartPhone((Pair(naamcom,item)),context)) {
                                               localcart++
                                           }
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.AddShoppingCart,
                                                contentDescription = "Cartadd",
                                                modifier = Modifier.size(35.dp)
                                            )
                                        }
                                    }else{
                                        Row(){
                                            if(cata.not()){
                                                IconButton(onClick = {
                                                    isContextMenuVisible = true
                                                }, modifier = Modifier.offset(y = -10.dp)) {
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        contentDescription = "Delete",
                                                        modifier = Modifier.size(30.dp)
                                                    )
                                                }
                                            }

                                            if (isContextMenuVisible) {
                                                AlertDialog(
                                                    onDismissRequest = {
                                                        isContextMenuVisible = false
                                                    },
                                                    title = {
                                                        Text("Confirm Delete")
                                                    },
                                                    text = {
                                                        Text(text="Are you sure you want to delete ${item.name}?",
                                                            fontWeight =  FontWeight.SemiBold
                                                        )
                                                    },
                                                    containerColor= Color(0xFFF8E9DE),
                                                    confirmButton = {
                                                        Button(
                                                            onClick = {
                                                                val database= FirebaseDatabase.getInstance().reference.child("Inventory/Inventory/Phones/${phoneName}/${item.name}")
                                                                database.removeValue()

                                                                isContextMenuVisible = false
                                                            }
                                                        ) {
                                                            Text("Delete")
                                                        }
                                                    },
                                                    dismissButton = {
                                                        Button(
                                                            onClick = {
                                                                isContextMenuVisible = false
                                                            }
                                                        ) {
                                                            Text("Cancel")
                                                        }
                                                    }
                                                )
                                            }

                                            IconButton(onClick = {
                                                controller.navigate("editm/$phoneName/${item.name}")
                                            }, modifier = Modifier.offset(y = -15.dp)) {
                                                Icon(
                                                    imageVector = Icons.Default.Edit,
                                                    contentDescription = "Edit",
                                                    modifier = Modifier.size(30.dp)
                                                )
                                            }
                                        }
                                    }

                                }


                                Row(  modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = -5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween){
                                    item.specs?.let {
                                        Text(
                                            text = "${item.units} units •   ${it["color"]} •  ${it["storage"]} •  ${item.price} ",
                                            fontSize = 16.sp
                                        )


                                    }

                                }

                            }

                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.Gray),
                            thickness = 3.dp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }
}

class ViewModelFactory(private val phoneName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            return InventoryViewModel(phoneName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

