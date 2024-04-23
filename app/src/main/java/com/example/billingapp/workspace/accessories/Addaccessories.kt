package com.example.billingapp.workspace.accessories


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Addass(navController: NavController, AssviewModel: Ass.AccessoriesViewModel,Editmode:Boolean,com:String,Assname:String) {
    var accessoryCategory by remember { mutableStateOf("") }
    var accessoryName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0) }
    var quantity by remember { mutableStateOf(0) }
    var inputs by remember { mutableStateOf("") }
    var inputq by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var companySuggestions by remember { mutableStateOf(emptyList<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = if (Editmode) "Edit Accessories" else "Add Accessories")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xffff6b00)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if(Editmode){
                OutlinedTextField(
                    value = com,
                    onValueChange = {
                    },
                    label = { Text("Category") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFE6B00),
                        cursorColor = Color(0xFFFE6B00),
                        focusedLabelColor = Color(0xFFFE6B00)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                )
                OutlinedTextField(
                    value = Assname,
                    onValueChange = {
                    },
                    label = { Text("Name") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFE6B00),
                        cursorColor = Color(0xFFFE6B00),
                        focusedLabelColor = Color(0xFFFE6B00)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                )
            }else{
                OutlinedTextField(
                    value = accessoryCategory,
                    onValueChange = {
                        accessoryCategory = it
                        isDropdownExpanded = true
                        companySuggestions = AssviewModel.getSuggestions(it)
                    },
                    label = { Text("Category") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFE6B00),
                        cursorColor = Color(0xFFFE6B00),
                        focusedLabelColor = Color(0xFFFE6B00)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                )

                if (isDropdownExpanded) {
                    LazyColumn {
                        items(companySuggestions) { suggestion ->
                            Text(
                                text = suggestion,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFFFFB985))
                                    .fillMaxWidth()
                                    .clickable {
                                        accessoryCategory = suggestion
                                        isDropdownExpanded = false
                                    }
                                    .padding(8.dp)
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = accessoryName,
                    onValueChange = {
                        accessoryName = it
                    },
                    label = { Text("Name") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFE6B00),
                        cursorColor = Color(0xFFFE6B00),
                        focusedLabelColor = Color(0xFFFE6B00)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                )
            }

            OutlinedTextField(
                value = inputs,
                onValueChange = {
                    inputs = it
                    price = inputs.toIntOrNull() ?: 0
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFE6B00),
                    cursorColor = Color(0xFFFE6B00),
                    focusedLabelColor = Color(0xFFFE6B00)
                ),
                label = { Text("Price") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number, // Set the keyboard type to numeric
                    imeAction = ImeAction.Done
                ),
            )

            OutlinedTextField(
                value = inputq,
                onValueChange = {
                    inputq = it
                    quantity = inputq.toIntOrNull() ?: 0
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFE6B00),
                    cursorColor = Color(0xFFFE6B00),
                    focusedLabelColor = Color(0xFFFE6B00)
                ),
                label = { Text("Quantity") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number, // Set the keyboard type to numeric
                    imeAction = ImeAction.Done
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if(Editmode){
                        accessoryCategory=com
                        accessoryName=Assname
                    }
                    if (accessoryCategory.isNotBlank() && accessoryName.isNotBlank() && price >= 0 && quantity > 0) {
                        // Add the accessory data to the Firebase Realtime Database
                        val accessoryData = mapOf(
                            "price" to price,
                            "quantity" to quantity
                            // Add other details based on your database structure
                        )
                        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
                        try {
                            database.child("Inventory/Accessories/$accessoryCategory/$accessoryName").setValue(accessoryData)
                        } catch (e: Exception) {
                            database.child("Inventory/Accessories/$accessoryCategory").setValue(accessoryCategory)
                            database.child("Inventory/Accessories/$accessoryCategory/$accessoryName").setValue(accessoryData)
                        }

                        navController.popBackStack("ass",inclusive = false)
                    } else {
                        print("Error in sending data")
                    }
                },
                colors = ButtonColors(
                    contentColor = Color(0xFFFE6B00),
                    containerColor = Color(0xFFFE6B00),
                    disabledContainerColor = Color(0xFFEEC8AC),
                    disabledContentColor = Color(0xFFCCA284)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = if (Editmode) "Save" else "Add Accessories"
                , color = Color.White)
            }
        }
    }
}
