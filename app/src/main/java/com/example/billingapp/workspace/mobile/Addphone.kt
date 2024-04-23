package com.example.billingapp.workspace.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class AddEditphone {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddEditPhoneContent(viewModel: MobilelistViewModel, navController: NavController,Editmode:Boolean,com:String,ph:String) {


        var phoneName by remember { mutableStateOf("") }
        var units by remember { mutableStateOf(0) }
        var unit by remember { mutableStateOf("") }
        var color by remember { mutableStateOf("") }
        var storage by remember { mutableStateOf("") }
        var company by remember { mutableStateOf("") }
        var isDropdownExpanded by remember { mutableStateOf(false) }
        var companySuggestions by remember { mutableStateOf(emptyList<String>()) }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = if (Editmode) "Edit Phone" else "Add Phone")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color(0xffff6b00)
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack()}) {
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
                        label = { Text("Company") },
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
                        value = ph,
                        onValueChange = {},
                        label = { Text("Phone Name") },
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
                else {
                    OutlinedTextField(
                        value = company,
                        onValueChange = {
                            company = it
                            isDropdownExpanded = true
                            companySuggestions = viewModel.getSuggestions(it)
                        },
                        label = { Text("Company") },
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
                                            company = suggestion
                                            isDropdownExpanded = false
                                        }
                                        .padding(8.dp)
                                )
                            }
                        }
                    }


                OutlinedTextField(
                    value = phoneName,
                    onValueChange = { phoneName = it
                        isDropdownExpanded = false},
                    label = { Text("Phone Name") },
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
                    value = unit,
                    onValueChange = {
                        unit=it
                        isDropdownExpanded = false
                        units = unit.toIntOrNull() ?: 0
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFE6B00),
                        cursorColor = Color(0xFFFE6B00),
                        focusedLabelColor = Color(0xFFFE6B00)
                    ),
                    label = { Text("Units") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, // Set the keyboard type to numeric
                        imeAction = ImeAction.Done
                    ),
                )

                OutlinedTextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Color") },
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
                    value = storage,
                    onValueChange = { storage = it },
                    label = { Text("Storage") },
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

                Spacer(modifier = Modifier.height(16.dp))
                if(Editmode){
                    company=com
                    phoneName=ph
                }
                Button(
                    onClick = {
                        if (phoneName.isNotBlank() && units>0 && color.isNotBlank() && storage.isNotBlank()) {
                            // Add the phone data to the Firebase Realtime Database
                            val phoneData = mapOf(
                                "units" to units,
                                "specs" to mapOf(
                                    "color" to color,
                                    "storage" to storage
                                )
                            )
                            val database: DatabaseReference = FirebaseDatabase.getInstance().reference
                            try {
                                database.child("Inventory/Inventory/Phones/${company}/${phoneName}").setValue(phoneData)
                            } catch (e: Exception) {
                                database.child("Inventory/Inventory/Phones${company}").setValue(company)
                                database.child("Inventory/Inventory/Phones/${company}/${phoneName}").setValue(phoneData)
                            }

                            navController.popBackStack("mobilelist",inclusive = false)
                        } else {
                            // Show an error message or handle empty fields
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
                    Text(text = if (Editmode) "Edit Phone" else "Add Phone", color = Color.White)
                }
            }
        }

    }
}



