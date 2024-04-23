package com.example.billingapp.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.billingapp.R
import com.example.billingapp.signin.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

class Home2(private val navController: NavHostController) {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun home(drawerState: DrawerState, drawerScope: CoroutineScope) {
        val backPressHandled by remember { mutableStateOf(false) }
        val activity = (LocalContext.current as? Activity)
        BackHandler(enabled = !backPressHandled, onBack = { activity?.finish()})
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier,

                    title = {

                        Text(
                            textAlign = TextAlign.Center,
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.itim))
                                    )
                                ) { append("SATGURU") }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xFF424242),
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily(Font(R.font.itim))
                                    )
                                ) { append(" ") }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xff424242),
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.itim))
                                    )
                                ) { append("TELECOM") }
                            },
                            modifier = Modifier
                                .statusBarsPadding()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    },

                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color(0xffff6b00)
                    ), navigationIcon = {
                        IconButton(onClick = {  drawerScope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            BadgedBox(
                                badge = { Badge { Text("8") } },
                                modifier = Modifier.offset(x = -8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.notification),
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }
                )
            }
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(state = rememberScrollState())
      ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 30.dp)
                    .padding(15.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xfffff0dd))){


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                    .fillMaxHeight()
                        .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
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
                            .clickable { navController.navigate("inv") }
                    ) {
                        Text(
                            text = "Inventory",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            fontFamily = FontFamily(Font(R.font.itim)),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.management1),
                            contentDescription = "management 1",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 25.dp,
                                    y = 36.dp
                                )
                                .requiredSize(size = 51.dp)
                        )
                    }
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
                            .clickable { navController.navigate("billdikha") }
                    ) {
                        Text(
                            text = "Bill",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            fontFamily = FontFamily(Font(R.font.itim)),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.bill),
                            contentDescription = "bill 1",
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(
                                    x = (-0.2784423828125).dp,
                                    y = (-6.0932464599609375).dp
                                )
                                .requiredSize(size = 51.dp)
                        )
                    }
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
//                            .clickable { navController.navigate("inv") }
                    ) {
                        Text(
                            text = "Report",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            fontFamily = FontFamily(Font(R.font.itim)),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.businessreport1),
                            contentDescription = "business-report 1",
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(
                                    x = 0.5.dp,
                                    y = (-3.3148040771484375).dp
                                )
                                .requiredSize(size = 51.dp)
                        )
                    }

                }
//                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                    .fillMaxHeight()
                        .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
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
//                            .clickable { navController.navigate("inv") }
                    ) {
                        Text(
                            text = "Loan",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            fontFamily = FontFamily(Font(R.font.itim)),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(5.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.management1),
                            contentDescription = "management 1",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 25.dp,
                                    y = 36.dp
                                )
                                .requiredSize(size = 51.dp)
                        )
                    }
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
//                            .clickable { navController.navigate("inv") }
                    ) {
                        Text(
                            text = "Credits",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            fontFamily = FontFamily(Font(R.font.itim)),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.credit),
                            contentDescription = "management 1",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 25.dp,
                                    y = 36.dp
                                )
                                .requiredSize(size = 51.dp)
                        )
                    }
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
                            .clickable { navController.navigate("catlog") }
                    ) {
                        Text(
                            text = "Sell Product",
                            color = Color(0xffa3695c),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            fontFamily = FontFamily(Font(R.font.itim)),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(5.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.management1),
                            contentDescription = "management 1",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 25.dp,
                                    y = 36.dp
                                )
                                .requiredSize(size = 51.dp)
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(50.dp))

                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .requiredHeight(height = 43.dp)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(color = Color(0xfffcfcfc))
                        .border(
                            border = BorderStroke(2.dp, Color(0xff009638)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .align(Alignment.CenterHorizontally)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 43.dp)
                            .fillMaxSize()
                    ) { val launcher = rememberLauncherForActivityResult(
                        ActivityResultContracts.StartActivityForResult()) { }
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 360.dp)
                                .requiredHeight(height = 43.dp)
                                .align(alignment = Alignment.BottomCenter)
                                .offset(
                                    x = 0.dp,
                                    y = 0.dp
                                ).clickable {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/919540143877?text=Hello%21%20I%20am%20facing%20some%20issues%20in%20Satguru%20Telecom%20App"))
                                    launcher.launch(intent) }

                        )
                        Text(
                            text = "Facing issue? Contact Us",
                            color = Color(0xff009638),
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.BottomCenter)
                                .offset(
                                    x = 4.dp,
                                    y = (-14).dp
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically)
                                )
                        Image(
                            painter = painterResource(id = R.drawable.whatsapp),
                            contentDescription = "whatsapp 1",
                            modifier = Modifier
                                .align(alignment = Alignment.BottomCenter)
                                .offset(
                                    x = (-86).dp,
                                    y = (-11).dp
                                )
                                .requiredSize(size = 20.dp))
                    }
                }

            }
        }

    }
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun NavDra(modifier: Modifier, userData: UserData?,
                   onSignOut: () -> Unit) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                val drawerState =
                    rememberDrawerState(initialValue = DrawerValue.Closed)
                val drawerScope = rememberCoroutineScope()

                // Use the provided drawerState and drawerScope
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet( drawerShape = RectangleShape,
                            modifier = Modifier.width(300.dp)) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xD7F0DDE3))
                                .padding(vertical = 30.dp, horizontal = 10.dp)){
//                                Text(text ="Drawer title" )
                                Box(
                                    modifier = Modifier
                                        .requiredSize(size = 70.dp)
                                        .clip(shape = RoundedCornerShape(48.dp))

                                        .background(color = Color(0xFFFFFFFF))
                                ) {
                                    if(userData?.profilePictureUrl != null) {
                                        AsyncImage(
                                            model = userData.profilePictureUrl,
                                            contentDescription = "Profile picture",
                                            modifier = Modifier
                                                .size(150.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp, horizontal = 15.dp)
                                        .clip(shape = RoundedCornerShape(3.dp))
                                        .clickable {navController.navigate("Profile") }
                                ) {
                                    if (userData != null) {
                                        userData.username?.let {
                                            Text(
                                                text = it,
                                                color = Color(0xff1a1b2d),
                                                lineHeight = 1.6.em,
                                                style = TextStyle(
                                                    fontSize = 15.sp),
                                                modifier = Modifier
                                                    .fillMaxWidth())
                                        }
                                    }
                                    if (userData != null) {
                                        userData.email?.let {
                                            Text(
                                                text = it,
                                                color = Color(0xff535763),
                                                lineHeight = 1.em,
                                                style = TextStyle(
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Medium),
                                                modifier = Modifier
                                                    .fillMaxWidth())
                                        }
                                    }
                                }
                            }
                            HorizontalDivider()
                            NavigationDrawerItem(
                                label = { Text(text = "Employee")},
                                selected = false,
                                onClick = {  },
                                icon = { Icon(Icons.Filled.Person, contentDescription = "") }
                            )
                            HorizontalDivider()
                            NavigationDrawerItem(
                                label = { Text(text = "Edit profile")},
                                selected = false,
                                onClick = {  },
                                icon = { Icon(Icons.Filled.Create, contentDescription = "") }
                            )
                            HorizontalDivider()
                            NavigationDrawerItem(
                                label = { Text(text = "About")},
                                selected = false,
                                onClick = {  },
                                icon = { Icon(Icons.Filled.Settings, contentDescription = "") }
                            )
                            HorizontalDivider()
                            NavigationDrawerItem(
                                label = { Text(text = "Logout") },
                                selected = false,
                                onClick = onSignOut,
                                icon = { Icon(Icons.Filled.ExitToApp, contentDescription = "") }
                            )


                        }
//                    NavigationTypeFullScreenThemeLight(Modifier,userData,onSignOut)

                    }
                ) {
                    // Your main screen content
                    home(drawerState, drawerScope)
                }
            }
        }


}

@Preview(widthDp = 390, heightDp = 1008)
@Composable
fun home2Preview(){
    val navController = rememberNavController()
    val user: UserData? = null
    Home2(navController).NavDra(Modifier, userData =user ) {
        
    }
}