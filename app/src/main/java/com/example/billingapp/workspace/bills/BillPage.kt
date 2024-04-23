package com.example.billingapp.workspace.bills

import android.annotation.SuppressLint
import android.content.Context
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import androidx.browser.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.storage.FirebaseStorage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowBill(navController: NavController) {
    var fileNames by remember { mutableStateOf(emptyList<String>()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bills",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(com.example.billingapp.R.font.itim)),
                        color = Color.White,
                        modifier = Modifier
                            .statusBarsPadding()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffff6b00)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.fallback_menu_item_copy_link)
                        )
                    }
                }
            )
        },
        content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y=70.dp)
            ) {
                if (errorMessage != null) {
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyColumn {
                        items(fileNames) { fileName ->
                            Card (colors= CardColors(containerColor = Color(0xFFFFBBA6)
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
                                        val storageReference = FirebaseStorage.getInstance().getReference("Invoices/$fileName")
                                        // Download the file as a byte array
                                        storageReference.getBytes(1000000)
                                            .addOnSuccessListener { bytes ->
                                                // Convert the byte array to a string using UTF-8 encoding
                                                val htmlString = String(bytes, Charsets.UTF_8)
                                                printHtmlContent(context, htmlString)
                                                // Now you have the HTML content as a string (htmlString)
                                            }
                                            .addOnFailureListener {
                                                // Handle failure
                                            }

                                    }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(5.dp)
                                ) {
                                    Text(
                                        text = fileName,
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )

    DisposableEffect(context) {
        val storage = FirebaseStorage.getInstance()
        val listRef = storage.reference.child("/Invoices")

        listRef.listAll()
            .addOnSuccessListener { result ->
                fileNames = result.items.map { it.name }
            }
            .addOnFailureListener { exception ->
                Log.e("ShowBill", "Error getting file list", exception)
                errorMessage = "Failed to retrieve file list"
            }

        onDispose {
            // Clean up any resources if needed
        }
    }
}
private fun printHtmlContent(context: Context, htmlContent: String) {
    val webView = WebView(context)
    webView.loadDataWithBaseURL(null, htmlContent, "text/HTML", "UTF-8", null)

    // Create a print job
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as? PrintManager
    val jobName = "${context.getString(com.example.billingapp.R.string.app_name)} Document"
    val printAdapter = webView.createPrintDocumentAdapter(jobName)

    printManager?.print(
        jobName,
        printAdapter,
        PrintAttributes.Builder().build()
    )?.also { printJob ->
        // Save the job object for later status checking
        printJobs += printJob
    }

    // Remove the WebView from the view hierarchy
    (webView.parent as? ViewGroup)?.removeView(webView)
}

//@Preview
//@Composable
//fun PreviewBills() {
//    ShowBill()
//}
private var printJobs: List<PrintJob> = emptyList()