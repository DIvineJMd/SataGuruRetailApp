package com.example.billingapp.workspace.mobile

import android.support.v4.os.IResultReceiver2.Default
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.billingapp.workspace.data.phonedata
import com.google.firebase.database.*

class MobilelistViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance().reference.child("Inventory/Inventory/Phones")
    private val _inventoryItems = mutableStateOf(emptyList<phonedata>())
    val inventoryItems: List<phonedata> get() = _inventoryItems.value
    var loading by mutableStateOf(true)

    init {
        loadData()
    }

    private fun loadData() {
        val inventoryListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val mobiledatalist = mutableListOf<phonedata>()

                for (productSnapshot in snapshot.children) {
                    val name = productSnapshot.key.toString()
                    mobiledatalist.add(phonedata(name))
                }
                _inventoryItems.value = mobiledatalist
                loading = false
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                loading = false
            }
        }

        db.addValueEventListener(inventoryListener)
    }
    fun getSuggestions(input: String): List<String> {
        return inventoryItems
            .map { it.name }
            .filter { it.contains(input, ignoreCase = true) }.take(5)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileListContent(
    mobileViewModel: MobilelistViewModel = viewModel(),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Mobiles")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xffff6b00)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MobileList(viewModel = mobileViewModel, navController = navController)
            FloatingActionButton(
                onClick = {  navController.navigate("addm") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(25.dp),
                containerColor =Color(0xE4FF9141),
                contentColor = Color(0xFF000000)

            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }
    }
}

@Composable
fun MobileList(viewModel: MobilelistViewModel, navController: NavHostController) {
    if (viewModel.loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(viewModel.inventoryItems) { item ->
                MobileItemRow(item = item, navController = navController,false)
            }
        }
    }
}

@Composable
fun MobileItemRow(item: phonedata, navController: NavHostController,cata:Boolean) {
    Card(colors=CardColors(containerColor = Color(0xFFFFBBA6)
                            , contentColor = Color(0xFF2E2E2E)
                            , disabledContainerColor = Color(0xFF6BBCC7)
                                ,disabledContentColor=Color(0xFF6BBCC7)),

    elevation =  CardDefaults.cardElevation(defaultElevation = 5.dp,
            pressedElevation = 12.dp,
            focusedElevation = 10.dp,
            hoveredElevation = 10.dp,
            draggedElevation = 10.dp,
            disabledElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.5.dp)
            .clickable {
                if (cata) {
                    navController.navigate("phoneCart/${item.name}")
                } else {
                    navController.navigate("phoneInv/${item.name}")
                }
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)

        ) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMobileListContent() {
    val viewModel = MobilelistViewModel()
    val navController = rememberNavController()
    MobileListContent(viewModel, navController)
}
