import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintJob
import android.print.PrintManager
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
//import androidx.compose.material3.icons.Icons
//import androidx.compose.material3.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.billingapp.R
import com.example.billingapp.printing.dataH
import com.example.billingapp.workspace.selling.Catlogpage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceShow(navController: NavController) {
    // Create a WebView object
    val webView = WebView(LocalContext.current)

    // Set up WebViewClient to handle page loading
    webView.webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) = false
    }


    // Compose UI
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Invoice") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffff6b00)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack("Home",inclusive = false)  }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                AndroidView(
                    factory = { webView },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp) // Add padding for spacing

                ) { view ->
                    webView.settings.loadWithOverviewMode = true
                    webView.settings.useWideViewPort = true

                    webView.loadDataWithBaseURL(null, dataH, "text/HTML", "UTF-8", null)
                    // WebView is already loaded, just need to make it visible
                    view.visibility = android.view.View.VISIBLE
                }


                Spacer(modifier = Modifier.height(16.dp)) // Example spacer for additional space
            }
        }
    )
}

private const val TAG = "InvoiceShow"
