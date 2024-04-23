package com.example.billingapp.workspace.accessories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.example.billingapp.workspace.data.assdata
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Asslist {

    class Assviewmodel : ViewModel() {
        private val db = FirebaseDatabase.getInstance().reference.child("Inventory/Accessories")
        private val _assItems = mutableStateOf(emptyList<assdata>())
        val assItems: List<assdata> get() = _assItems.value
        var loading by mutableStateOf(true)

        init {
            loadData()
        }

        private fun loadData() {
            val inventoryListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val assdatalist = mutableListOf<assdata>()

                    for (productSnapshot in snapshot.children) {
                        val name = productSnapshot.key.toString()
                        assdatalist.add(assdata(name))
                    }
                    _assItems.value = assdatalist
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
            return assItems
                .map { it.name }
                .filter { it.contains(input, ignoreCase = true) }.take(5)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AssListContent(
        mobileViewModel: Assviewmodel = viewModel(),
        navController: NavHostController
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Accessories")
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
                AssList(viewModel = mobileViewModel, navController = navController)
                FloatingActionButton(
                    onClick = {  navController.navigate("addass") },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(25.dp),
                    containerColor = Color(0xE4FF9141),
                    contentColor = Color(0xFF000000)

                ) {
                    Icon(Icons.Filled.Add, "Small floating action button.")
                }
            }
        }
    }

    @Composable
    fun AssList(viewModel:Assviewmodel, navController: NavHostController) {
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
                items(viewModel.assItems) { item ->
                  AssItemRow(item = item, navController = navController,false)
                }
            }
        }
    }

    @Composable
    fun AssItemRow(item: assdata, navController: NavHostController,cata:Boolean) {
        Card(colors= CardColors(containerColor = Color(0xFFFFBBA6)
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
                        navController.navigate("accessoriesCart/${item.name}")
                    } else {
                        navController.navigate("accessoriesInv/${item.name}")
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
    fun PreviewAssListContent() {
        val viewModel = Assviewmodel()
        val navController = rememberNavController()
      AssListContent(viewModel, navController)
    }

}