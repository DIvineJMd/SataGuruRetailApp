package com.example.billingapp.printing

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.billingapp.workspace.selling.BillItem
import com.example.billingapp.workspace.selling.Catlogpage
import com.example.billingapp.workspace.selling.HD
import com.example.billingapp.workspace.selling.billList2
import com.example.billingapp.workspace.selling.discount
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

var dataH:String=""
class UserInfo  : AppCompatActivity(){

    @SuppressLint("SetJavaScriptEnabled")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun userinfo(navController:NavController) {
        var customerName by remember { mutableStateOf("") }
        var customerMobile by remember { mutableStateOf("") }
        var customerAddress by remember { mutableStateOf("") }
        var customerAddress2 by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    title = {
                        Text(text = "Customer details")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xffff6b00)
                    ),
                    navigationIcon = {
                        IconButton(onClick = {navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
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
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp) // Added padding to the entire Column
            ) {

                // Customer Name
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color(0xfffff0dd))
                        .padding(vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = customerName,
                        onValueChange = { customerName = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFE6B00),
                            cursorColor = Color(0xFFFE6B00),
                            focusedLabelColor = Color(0xFFFE6B00)
                        ),
                        label = { Text("Customer Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }

                // Customer Mobile Number
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color(0xfffff0dd))
                        .padding(vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = customerMobile,
                        onValueChange = { if(customerMobile.length<=10 || it == "\b"){ customerMobile = it }},
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFE6B00),
                            cursorColor = Color(0xFFFE6B00),
                            focusedLabelColor = Color(0xFFFE6B00)
                        ),
                        label = { Text("Customer Mobile Number") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number, // Set the keyboard type to numeric
                            imeAction = ImeAction.Done
                        )

                    )
                    if(customerMobile.length<10 && customerMobile.isNotEmpty()){
                        Text(text = "Mobile number incorrect",
                            color = Color.Red,
                            modifier = Modifier.padding(horizontal = 20.dp))
                    }}

                // Customer Address
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color(0xfffff0dd))
                        .padding(vertical = 8.dp)
                ){
                    OutlinedTextField(
                        value = customerAddress,
                        onValueChange = { customerAddress = it },
                        label = { Text("Address Line 1") },
                        placeholder = {Text("House no, Street address")},
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFE6B00),
                            cursorColor = Color(0xFFFE6B00),
                            focusedLabelColor = Color(0xFFFE6B00)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = Color(0xfffff0dd))
                        .padding(vertical = 8.dp)
                )  {
                    OutlinedTextField(
                        value = customerAddress2,
                        onValueChange = { customerAddress2 = it },
                        label = { Text("Address Line 2") },
                        placeholder = { Text("Area, City, Zip Code") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFE6B00),
                            cursorColor =Color(0xFFFE6B00),
                            focusedLabelColor = Color(0xFFFE6B00)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp)) // Add some spacing

                // Button
                Button(
                    onClick = {
                        if(customerMobile.isNotBlank() && customerName.isNotBlank() && customerAddress.isNotBlank() && customerAddress2.isNotBlank()) {
                            dataH = customerMobile.toLong().let {
                                generateHtmlContent(customerName,
                                    it, customerAddress, customerAddress2, itemsList = billList2,HD.data.html,HD.data.total,HD.data.taxTotal, discount)
                            }.toString()
                            val htmlContent = dataH // Replace with your HTML content
                            val htmlBytes = htmlContent.toByteArray(Charsets.UTF_8)

                            // Generate a unique filename based on timestamp
                            val dateFormat = SimpleDateFormat("yyyy:MM:dd_HH:mm:ss", Locale.getDefault())
                            val timestamp = dateFormat.format(Date())

                            val filename = "$timestamp.html"

                            // Assuming you have a reference to your Firebase Storage instance and a reference to the desired path
                            val storageReference = FirebaseStorage.getInstance().getReference("Invoices/$filename")

                            storageReference.putBytes(htmlBytes)
                                .addOnSuccessListener {
                                    // Handle success, e.g., navigate to "Home"

                                    val myCartData = Catlogpage.CartDataManager.cartData
                                    myCartData.cartPhone.clear()
                                    myCartData.cartAss.clear()
                                    myCartData.numOfItems=0

                                }
                                .addOnFailureListener {}
                          navController.navigate("billbana")}

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.textButtonColors(
                        Color(0xFFFF6B00)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text ="Proceed for Invoice", color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,

                            ))
                }
            }
        }
    }
    fun generateHtmlContent(custname:String,
                            mobilenuumber:Long,
                            custAdress: String,
                            custAdress2: String,
                            itemsList: List<BillItem>,
                            data:String,
                            total: Double,
                            taxTotal: Double,
                            dis: Double
    )
            : String {

        return """
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8" />
        <title>Satguru Telecom Invoice</title>
        <style>
            .section{
                display: flex;
            }
            
            .logo>img {
                height: 6em;
                width: 16em;
                padding-left: 3em;
            }
            
            .taxhead,
            .billaddress,
            .sold,
            .order,
            .invoice,
            .logo {
                width: 50%;
            }
            
            .taxhead,
            .billaddress,
            .invoice{
                text-align: right;
                padding-right: 2em;
            }

            .sold,
            .order {
                padding-left: 2em;
            }
            
            .sold>span,
            .billaddress>span,
            .pan>span,
            .order>span,
            .invoice>span,
            .taxhead>span{
                font-weight:bold;
            }

            th {
                border: 1px solid black;
                background-color:#dddddd;
            }
            
            td {
                border-left: 1px solid black;
                border-right: 1px solid black;
            }
            
            table {
                margin-left: 2em;
                width: 100%;
                margin-right: 2em;
                font-family: arial, sans-serif;
                border-collapse: collapse;
                border: 1px solid black;
                text-align: left;
            }

            .sign {
                height: 3em;
                width: 13em;
            }
            
            .end {
                font-size: 0.75em;
                text-align: center;
                padding: 4em;
            }
        </style>
    </head>


    <body>
        <script>
            
            var today = new Date();
            function numberToWords(number) {
                const units = ['', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine'];
                const teens = ['Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'];
                const tens = ['', 'Ten', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];
                const thousands = ['', 'Thousand', 'Million', 'Billion', 'Trillion'];

                function convertChunk(number) {
                    let words = '';

                    if (number >= 100) {
                        words += units[Math.floor(number / 100)] + ' Hundred ';
                        number %= 100;
                    }

                    if (number > 10 && number < 20) {
                        words += teens[number - 11] + ' ';
                    } else {
                        words += tens[Math.floor(number / 10)] + ' ';
                        number %= 10;

                        words += units[number] + ' ';
                    }

                    return words;
                }
                
                if (number === 0) return 'Zero';

                let wordString = '';
                let chunkCount = 0;

                do {
                    const chunk = number % 1000;
                    if (chunk !== 0) {
                        const chunkWords = convertChunk(chunk) + thousands[chunkCount] + ' ';
                        wordString = chunkWords + wordString;
                    }
                    number = Math.floor(number / 1000);
                    chunkCount++;
                } while (number > 0);
                wordString+="only";

                return wordString.trim();
            }
        
        </script>

        <div>
            <div class="section">
                <div class="logo">
                    <img src="data:image/png;base64,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" alt="" />
                </div>
                <div class="taxhead">
                    <span>Tax Invoice/Bill of Supply/Cash Memo</span><br /> (Original for Recipient)
                    <br /><br /><br />
                    <span>PAN No:</span> AALCA0171E<br />
                    <span>GSTIN:</span> 06AALCA0171E1Z3
                </div>
            </div>
            <br />
            <div class="section">
                <div class="sold">
                    <span>Sold By :</span><br /> Satguru Telecom<br /> N-11 Aruna Nagar, Majnu Ka Tilla,  <br />New Delhi - 110054 <br /> IN
                </div>
                <div class="billaddress">
                    <span>Billing Address :</span> <br /> $custname;</script>  <br /> $custAdress <br /> $custAdress2  <br /> <script>document.write(addressCountry);</script> <br /> Phone: +91-$mobilenuumber
                </div>
            </div>
            <div class="section">
                <div class="order">
                    <br /> <span>Order Number:</span> <script>document.write(orderNumber);</script> <br />
                    <span>Order Date:</span> <script>document.write(formattedDate);</script>
                </div>
                <div class="invoice">
                    <br /> <span>Invoice Number :</span> <script>document.write(invoiceNumber);</script><br />
                    <span>Invoice Date :</span> <script>document.write(formattedDate);</script>
                </div>
            </div>
            <br />
            <div class="section">
                <table >
                    <tr>
                        <th>SL No.</th>
                        <th>Description</th>
                        <th>Unit Price</th>
                        <th>Qty</th>
                        <th>Net Amount</th>
                        <th>Tax Rate</th>
                        <th>Tax Type</th>
                        <th>Tax Amount</th>
                        <th>Total Amount</th>
                    </tr>
                    
                   $data
                    <tr>
                    <th colspan="7" style="text-align: left; font-weight: bold; background-color: white;">Total:</th>
                    <th>₹${String.format("%.2f", taxTotal)}</th>
                    <th>₹${total.roundToInt()}</th>
                </tr>
                <tr>
                    <th colspan="7" style="text-align: left; font-weight: bold; background-color: white;">Discount:</th>
                    <td style="text-align: right; font-weight: bold; background-color: white;"> - </td>
                    <th>₹${String.format("%.2f", discount)}</th>
                </tr>
                <tr>
                    <th colspan="8" style="text-align: left; font-weight: bold; background-color: white;">GRAND TOTAL:</th>
                    <th>₹${(total-dis).roundToInt()}</th>

                </tr>
                <tr>
                    <th colspan="2" style="width: 30%; padding-right: 2em; font-weight: bold; background-color: white;">Amount in Words:</th>
                    <th colspan="8" style="width: 70%; font-weight: bold; background-color: white;"> <script>document.write(numberToWords(${(total-dis).roundToInt()}));</script> </th>
                </tr>
                <tr>
                    <td colspan="9" style="font-weight: bold; text-align: right; ">For Satguru Telecom:<br />
                        <img src="data:image/png;base64,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" alt="" /> <br /> Authorized Signatory
                    </td>
                    </tr>
                </table>
            </div>
            <div class="end">
                Please note that this invoice is not a demand for payment
            </div>
        </div>
    </body>
    </html>

        

    """.trimIndent()
    }


//    @Preview
//    @Composable
//    fun MyScreenPreview() {
//        userinfo()
//    }
}