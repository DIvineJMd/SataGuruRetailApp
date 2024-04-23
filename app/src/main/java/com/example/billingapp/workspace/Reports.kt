package com.example.billingapp.workspace

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.billingapp.R

class Reports {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun report(){
        Scaffold(  topBar = { MyTopAppBar(modifier =Modifier) }
        ) { paddingValues ->
            // rest of the app's UI
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .offset(
                            x = 0.dp,
                            y = 50.dp
                        )
                        .requiredWidth(width = 365.dp)
                        .requiredHeight(height = 199.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 29.dp
                            )
                            .requiredWidth(width = 365.dp)
                            .requiredHeight(height = 170.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = Color(0xfffff0dd)))
                    Text(
                        text = "Quick Report",
                        color = Color(0xff2e2e2e),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 14.dp,
                                y = 0.dp
                            )
                            .wrapContentHeight(align = Alignment.CenterVertically))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 255.dp,
                                y = 51.dp
                            )
                            .requiredWidth(width = 100.dp)
                            .requiredHeight(height = 130.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 100.dp)
                                .requiredHeight(height = 130.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White)
                                .border(
                                    border = BorderStroke(1.dp, Color(0xffa5a5a5)),
                                    shape = RoundedCornerShape(10.dp)
                                ).clickable {  }

                        )
                        Text(
                            text = "1",
                            color = Color(0xff3f3f3f),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 46.dp,
                                    y = 89.dp
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Text(
                            text = "Active Staff",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 18.dp,
                                    y = 60.dp
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 35.dp,
                                    y = 16.dp
                                )
                                .requiredSize(size = 30.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 40.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffff8c00)))
                            Image(
                                painter = painterResource(id = R.drawable.frame),
                                contentDescription = "Frame",
                                modifier = Modifier
                                    .fillMaxSize()

                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 9.dp,
                                y = 51.dp
                            )
                            .requiredWidth(width = 100.dp)
                            .requiredHeight(height = 130.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 100.dp)
                                .requiredHeight(height = 130.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White)
                                .border(
                                    border = BorderStroke(1.dp, Color(0xffa5a5a5)),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {  })
                        Text(
                            text = "Bills Made",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 20.dp,
                                    y = 60.dp
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Text(
                            text = "254",
                            color = Color(0xff3f3f3f),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 38.dp,
                                    y = 89.dp
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 28.dp,
                                    y = 10.dp
                                )
                                .requiredSize(size = 40.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xffff8c00)))
                        Image(
                            painter = painterResource(id = R.drawable.bill1),
                            contentDescription = "bill_made",
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(
                                    x = (0.0).dp,
                                    y = (-35.81480407714844).dp
                                )
                                .requiredWidth(width = 25.dp)
                                .requiredHeight(height = 30.dp))
                    }
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 132.dp,
                                y = 51.dp
                            )
                            .requiredWidth(width = 100.dp)
                            .requiredHeight(height = 130.dp).clickable {  }
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 100.dp)
                                .requiredHeight(height = 130.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White)
                                .border(
                                    border = BorderStroke(1.dp, Color(0xffa5a5a5)),
                                    shape = RoundedCornerShape(10.dp)
                                )

                        )
                        Text(
                            text = "5640",
                            color = Color(0xff3f3f3f),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 33.dp,
                                    y = 89.dp
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Text(
                            text = "Sales",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 35.dp,
                                    y = 60.dp
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 35.dp,
                                    y = 16.dp
                                )
                                .requiredSize(size = 30.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 40.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xffff8c00)))
                            Image(
                                painter = painterResource(id = R.drawable.rupee),//sales
                                contentDescription = "Frame",
                                colorFilter = ColorFilter.tint(Color(0xff4d4d4d)),
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 4.dp,
                                        y = 4.dp
                                    )
                                    .requiredSize(size = 24.dp))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 269.dp,
                                y = 0.dp
                            )
                            .requiredWidth(width = 86.dp)
                            .requiredHeight(height = 24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 86.dp)
                                .requiredHeight(height = 24.dp)
                                .clip(shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                                .background(color = Color(0xfffff0dd)))
                        //yaha
                        Text(modifier = Modifier
                            .padding(3.dp)
                            .offset(x = 6.dp, y = 0.dp), text = "Today")
                        dropdown()
                    }
                }
            }
        }
    }

    @Composable
    fun dropdown() {
        val listItems = arrayOf("This Week", "This Month")
        val disabledItem = 2
        val contextForToast = LocalContext.current.applicationContext

        // State of the selected item and the menu
        var selectedItem by remember { mutableStateOf(listItems[0]) }
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .requiredWidth(width = 86.dp)
                .requiredHeight(height = 24.dp),
            contentAlignment = Alignment.BottomEnd
        ) {

            // 3 vertical dots icon
            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Open Options"
                )
            }

            // Drop down menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                // Adding items
                listItems.forEachIndexed { itemIndex, itemValue ->
                    DropdownMenuItem(
                        onClick = {
                            // Update the selected item
                            selectedItem = itemValue
                            expanded = false

                            // Perform actions or updates based on the selected item
                            when (itemValue) {
                                "This Week" -> {
                                    // Handle the "This Week" selection
                                    // Update your UI accordingly
                                }
                                "This Month" -> {
                                    // Handle the "This Month" selection
                                    // Update your UI accordingly
                                }
                            }

                            Toast.makeText(contextForToast, itemValue, Toast.LENGTH_SHORT)
                                .show()
                        },
                        enabled = (itemIndex != disabledItem)
                    ) {
                        Text(text = itemValue)
                    }
                }
            }

            // Conditionally render components based on the selected item
            when (selectedItem) {
                "This Week" -> {
                    // Render components for "This Week" selection
                }
                "This Month" -> {
                    // Render components for "This Month" selection
                }
            }
        }


    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTopAppBar(modifier: Modifier) {
        TopAppBar(

            title = {

                Text(text = "Report")
                            },


            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xffff6b00)
            )
            ,navigationIcon={IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Localized description"
                )
            }}
        )
    }
    @Preview
    @Composable
    fun ReportPreview(){
        report()
    }
}