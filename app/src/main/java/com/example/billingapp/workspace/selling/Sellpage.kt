package com.example.billingapp.workspace.selling

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.billingapp.R
import com.example.billingapp.workspace.data.AccessoriesItem
import com.example.billingapp.workspace.data.InventoryPhone
import com.example.billingapp.workspace.data.Quantity
import com.example.billingapp.workspace.data.assdata
import com.example.billingapp.workspace.data.phonedata
import kotlin.math.roundToInt

data class BillItem(
    val type: ItemType,
    val phoneData: Pair<Pair<phonedata, InventoryPhone>, Quantity>? = null,
    val accessoryData: Pair<Pair<AccessoriesItem, assdata>, Quantity>? = null,
)
data class Htmldata(
    var html: String,
    var total: Double,
    var taxTotal: Double
)
enum class ItemType {
    PHONE,
    ACCESSORY
}
object HD {
    private var instance: Htmldata? = null

    var data: Htmldata
        get() {
            if (instance == null) {
                instance = Htmldata("", 0.0, 0.0)
            }
            return instance!!
        }
        set(value) {
            instance = value
        }
}
val myCartData = Catlogpage.CartDataManager.cartData

var discount =0.0
val itemsList: List<BillItem> = (myCartData.cartPhone.map {
    BillItem(
        type = ItemType.PHONE,
        phoneData = Pair(Pair(it.first.first, it.first.second), it.second),
    )
} + myCartData.cartAss.map {
    BillItem(
        type = ItemType.ACCESSORY,
        accessoryData = Pair(Pair(it.first.first, it.first.second), it.second),
    )
}).toList()
var billList2 = itemsList.toMutableList()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellPage(controller: NavController) {
    val itemsList: List<BillItem> = (myCartData.cartPhone.map {
        BillItem(
            type = ItemType.PHONE,
            phoneData = Pair(Pair(it.first.first, it.first.second), it.second),
        )
    } + myCartData.cartAss.map {
        BillItem(
            type = ItemType.ACCESSORY,
            accessoryData = Pair(Pair(it.first.first, it.first.second), it.second),
        )
    }).toList()
    val billList = itemsList.toMutableList()
    var total: Float by remember { mutableStateOf(0.0f) }



    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "Sell Products")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffff6b00)
                ),
                navigationIcon = {
                    IconButton(onClick = { controller.popBackStack("catlog",inclusive = false)}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { controller.popBackStack("Home",inclusive = false) }) {
                        Icon(
                            imageVector =  Icons.Default.Home,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column {
                Property1Group17(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xfffff0dd))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .requiredHeight(height = 260.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // bill list
                    val updatedTotal =
                        billList.sumByDouble { BillListItem(bill = it,{updateProce-> total=updateProce}).toDouble() }.toFloat()
                    total = updatedTotal
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xfffff0dd))
            ) {
                LaunchedEffect( total){}
                var discount by remember { mutableStateOf(0.0) }
                var dis by remember{ mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .requiredHeight(120.dp) // Increased height to accommodate the input field and avoid overlapping
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total",
                            modifier = Modifier
                                .weight(1f)
                                .offset(x = 25.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .weight(1f)
                                .offset(x = 15.dp)
                        )
                        Text(
                            text = "₹ ${"%.2f".format(total)}",
                            modifier = Modifier.weight(1f),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
//        Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(100.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextField(
                            value = dis,
                            onValueChange = {
                                dis=it
                                discount = dis.toDoubleOrNull() ?: 0.0
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = Color(0xFFFE6B00),
                                focusedBorderColor = Color(0xFFFE6B00),
                                unfocusedBorderColor = Color.Gray,
                                focusedLabelColor = Color(0xFFFE6B00),
                            ),
                            label = { Text("Discount %") },
                            modifier = Modifier
//                    .size(150.dp)
                                .offset(y = (-10).dp) // Adjusted offset for better visibility
                                .padding(horizontal = 20.dp)
                            ,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),

                            )
                    }
                    Spacer(modifier = Modifier.height(35.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Bill",
                            modifier = Modifier
                                .weight(1f)
                                .offset(x = 25.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "",
                            modifier = Modifier
                                .weight(1f)
                        )
                        Text(
                            text = "₹ ${"%.2f".format(total - (total*(discount/100)))}",
                            modifier = Modifier.weight(1f),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
                Button(
                    onClick = {
                        val result = htmldatamaker(itemsList, dis = discount.toInt())
                        HD.data.html=result.first
                        HD.data.taxTotal=result.third
                        HD.data.total=result.second

                        if(billList.size>0){
                            billList2=billList
                            controller.navigate("userinfo") }
                    },
                    colors = ButtonColors(
                        contentColor = Color(0xFFFE6B00),
                        containerColor = Color(0xFFFE6B00),
                        disabledContainerColor = Color(0xFFEEC8AC),
                        disabledContentColor = Color(0xFFCCA284)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(50.dp)
                ) {
                    Text(text ="Proceed", color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,

                            ))
                }

            }
        }

    }
}

fun htmldatamaker(itemsList: List<BillItem>, dis: Int): Triple<String, Double, Double>{
    var i = 1
    var datahtml = ""
    var total=0.0
    var taxtotal=0.0
    itemsList.forEach {
        datahtml += "<tr>"
        datahtml += "<td>$i</td>"
        when (it.type.name) {
            "PHONE" -> {
                // Add data for Phone item
                val phoneData = it.phoneData!!
                val unitPrice = String.format("%.2f", phoneData.first.second.price / 1.18).toDouble()
                val netAmount = unitPrice * phoneData.second.quantity
                val taxRate = 18
                val gst = String.format("%.2f", netAmount * (taxRate / 100.0)).toDouble()

                datahtml += """
        <td>${phoneData.first.second.name}</td>
        <td>${unitPrice}</td>
        <td>${phoneData.second.quantity}</td>
        <td>${netAmount}</td>
        <td>${taxRate}%</td>
        <td>GST</td>
        <td>${gst}</td>
        <td>${netAmount + gst}</td>
    """

                total += netAmount+ gst
                taxtotal += gst
            }
            "ACCESSORY" -> {
                // Add data for Accessory item
                val accessoryData = it.accessoryData!!
                val unitPrice = String.format("%.2f", accessoryData.first.first.price / 1.18).toDouble()
                val netAmount = unitPrice * accessoryData.second.quantity
                val taxRate = 18
                val gst = String.format("%.2f", netAmount * (taxRate / 100.0)).toDouble()

                datahtml += """
                <td>${accessoryData.first.second.name}</td>
                <td>${unitPrice}</td>
                <td>${accessoryData.second.quantity}</td>
                <td>${netAmount}</td>
                <td>${taxRate}%</td>
                <td>GST</td>
                <td>${gst}</td>
                <td>${netAmount + gst}</td>
    """

                total += netAmount + gst
                taxtotal += gst
            }

            else -> "Unknown"
        }
        datahtml += "</tr>"
        i++
    }

    return  Triple(datahtml, total, taxtotal)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BillListItem(bill: BillItem,onQuantityChanged: (Float) -> Unit): Float {
    var price: Float = 0.0f
    var qun by remember {
        mutableStateOf(0)
    }
    qun= (bill.phoneData?.second?.quantity ?: bill.accessoryData?.second?.quantity)!!

    androidx.compose.material.ListItem(
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .requiredHeight(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val item: String = when {
                        bill.phoneData?.first?.second?.name != null -> "Phone"
                        bill.accessoryData?.first?.first?.name != null -> "ass"
                        else -> "Unknown"
                    }

                    Text(
                        text = when (item) {
                            "Phone" -> {
                                // Add data for Phone item
                                "${bill.phoneData?.first?.second?.name}"
                            }
                            "ass" -> {
                                // Add data for Accessory item
                                "${bill.accessoryData?.first?.first?.name}"
                            }
                            else -> "Unknown"
                        },
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {

                                        when (item) {
                                            "Phone" -> {
                                                qun++
                                                bill.phoneData?.second?.quantity =
                                                    bill.phoneData?.second?.quantity!! + 1

                                            }
                                            "ass" -> {
                                                qun++
                                                bill.accessoryData?.second?.quantity =
                                                    bill.accessoryData?.second?.quantity!! + 1

                                            }
                                            else -> {
                                            }
                                        }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddCircleOutline,
                                    contentDescription = "add"
                                )
                            }

                            Text(
                                text = "${qun}",
                                //
                            )

                            IconButton(
                                onClick = {

                                        when (item) {
                                            "Phone" -> {
                                                if ((bill.phoneData?.second?.quantity ?: 0) > 1) {
                                                    bill.phoneData?.second?.quantity =
                                                        (bill.phoneData?.second?.quantity ?: 0) - 1
                                                }
                                                qun--
                                                qun= (bill.phoneData?.second?.quantity ?: bill.accessoryData?.second?.quantity)!!

                                            }
                                            "ass" -> {

                                                if ((bill.accessoryData?.second?.quantity ?: 0) > 1) {
                                                    bill.accessoryData?.second?.quantity =
                                                        (bill.accessoryData?.second?.quantity ?: 0) - 1
                                                }
                                                qun--
                                                qun= (bill.phoneData?.second?.quantity ?: bill.accessoryData?.second?.quantity)!!

                                            }
                                            else -> {
                                            }
                                        }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.minus),
                                    contentDescription = "sub",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }

                    price = (if (item == "Phone") bill.phoneData?.second?.quantity
                    else bill.accessoryData?.second?.quantity)?.let { quantity ->
                        quantity * ((bill.phoneData?.first?.second?.price
                            ?: bill.accessoryData?.first?.first?.price) ?: 0.0f)
                    } ?: 0.0f
                    LaunchedEffect(qun) {

                        onQuantityChanged(price)
                    }
                    Text(
                        text = "$price",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )

    return price
}




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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xffff6b00),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color(0xffff6b00),
            unfocusedLabelColor = Color.Gray,
        )
    )
}