package com.example.billingapp.workspace

import android.annotation.SuppressLint
import androidx.compose.foundation.checkScrollableContainerConstraints
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.billingapp.R

class Inventory(private val navController: NavHostController) {

    @SuppressLint(
        "UnusedMaterialScaffoldPaddingParameter", "NotConstructor",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Inventory(modifier: Modifier = Modifier) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Inventory",
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.itim)),
                            color = Color.White,
                            modifier = Modifier
                                .statusBarsPadding()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color(0xffff6b00)
                    ),
                    navigationIcon = {
                        IconButton(onClick = {navController.popBackStack("Home",inclusive = false) }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {navController.popBackStack("Home",inclusive = false) }) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .offset(y = 50.dp)


            ) {
                Property1Group17(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                Column(modifier= Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 25.dp)
                    .offset(y=-5.dp)
                    .verticalScroll(state = rememberScrollState())) {
                    CardItem(
                        icon = R.drawable.mobile,
                        label = "Mobile",
                        onClick = {navController.navigate("mobilelist") }
                    )

                    CardItem(
                        icon = R.drawable.pana,
                        label = "Repair",
                        onClick = { /* Handle click for Repair category */ }
                    )

                    CardItem(
                        icon = R.drawable.ass,
                        label = "Accessories",
                        onClick = {  navController.navigate("ass")}
                    )
                    CardItem(
                        icon = R.drawable.music,
                        label = "Sound",
                        onClick = { /* Handle click for Accessories category */ }
                    )
                }
            }
        }
    }
}
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Property1Group17(modifier: Modifier) {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(

            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text("Available Items") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            text = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            singleLine = true,
            modifier = modifier.fillMaxWidth()
            ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color(0xffff6b00),
                unfocusedLabelColor = Color.Gray,
                focusedBorderColor = Color(0xffff6b00),
                unfocusedBorderColor = Color.Gray,
            )

        )
    }


    @Composable
    private fun CardItem(icon: Int, label: String, onClick: () -> Unit) {
        Card(
            colors = CardColors(
                containerColor = Color(0xFFFCDEB9),
                contentColor = Color(0xFF2E2E2E),
                disabledContainerColor = Color(0xFF6BBCC7),
                disabledContentColor = Color(0xFF6BBCC7)
            ),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .padding(5.dp)
                .fillMaxWidth()
//                .size(100.dp)

//                .height(50.dp) // Set a fixed height for all cards
                .clickable(onClick = onClick)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                verticalAlignment  = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(1.dp))
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Your Icon",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = " $label ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }


@Composable
@Preview
private fun InventoryPreview() {
    val navController = rememberNavController()
    Inventory(navController = navController).Inventory(Modifier)
}
