package com.example.billingapp.workspace

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class Loan {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun loan() {

        Scaffold(topBar = {
            TopAppBar(

                title = {

                    Text(text = "Loans")
                },


                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xffff6b00)
                ), navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
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
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                loanlist()
            }
        }


    }
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun loanlist() {
        val transactionList = List(10) { index ->
//            Transaction(
////                mobileNumber = "987654321$index",
////                name = "User $index",
////                fromDate = "2023-01-01",
////                toDate = "2023-01-15",
////                amount = (index + 1) * 1000.0,
////                status = if (index % 2 == 0) "Approved" else "Pending"
//            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                itemsIndexed(transactionList) { index, transaction ->
                    androidx.compose.material.ListItem(
                        text = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {

                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Handle item click */ }
                    )
                }
            }
        }
    }

    @Preview
    @Composable

    fun PreviewLoan(){
        loan()
    }
}
