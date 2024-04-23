package com.example.billingapp.workspace.selling

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.billingapp.R
import com.example.billingapp.workspace.accessories.Asslist
import com.example.billingapp.workspace.data.Cartdata
import com.example.billingapp.workspace.mobile.MobileItemRow
import com.example.billingapp.workspace.mobile.MobilelistViewModel

class Catlogpage : AppCompatActivity(){
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()

    }
    object CartDataManager {
        private val _cartData = mutableStateOf(Cartdata())
        var cartData: Cartdata
            get() = _cartData.value
            set(value) {
                _cartData.value = value
            }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Catalog(navController: NavHostController, mViewModel: MobilelistViewModel,assview: Asslist.Assviewmodel) {
        val myCartData = CartDataManager.cartData

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Catalog",
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
                        IconButton(onClick = { navController.popBackStack("Home",inclusive = false) }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("billing") }) {
                            BadgedBox(
                                badge = { Badge { Text("${myCartData.numOfItems}") } },
                                modifier = Modifier
                                    .offset(x = -7.dp)
                                    .size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingCart,
                                    contentDescription = "Cart",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }
                )
            }
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 60.dp)
            ) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .offset(y = 5.dp)) {

                    Icon(
                        painter = painterResource(id = R.drawable.mobile),
                        contentDescription = "Arrow",
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "Mobile",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 5.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                }
                LazyRow {
                    items(mViewModel.inventoryItems) { item ->
                       MobileItemRow(item =item , navController =navController,true )}
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .offset(y = 5.dp)) {

                    Icon(
                        painter = painterResource(id = R.drawable.ass),
                        contentDescription = "Arrow",
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "Accessories",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 5.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
                LazyRow {
                    items(assview.assItems) { item ->
                        Asslist().AssItemRow(item = item, navController = navController,true)}
                }
            }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    @Composable
//    @Preview
//    fun PreCatlog() {
//        val navController = rememberNavController()
//        val mobileListViewModel = MobilelistViewModel() // Create an instance of MobilelistViewModel
//        val inventoryViewModel = InventoryViewModel() // Create an instance of InventoryViewModel
//
//        Catalog(navController = navController, mViewModel = mobileListViewModel,)
//    }
    }
