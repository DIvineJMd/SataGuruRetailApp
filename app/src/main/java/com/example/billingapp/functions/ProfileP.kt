package com.example.billingapp.functions

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import kotlinx.coroutines.launch

class ProfileP(private val navController: NavHostController) {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyProfile(userData: UserData?) {
        var noti by remember { mutableStateOf(0) }
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
                                        fontSize = 25.sp,
                                        fontFamily = FontFamily(Font(R.font.itim))
                                    )
                                ) { append("Profile") }


                            },
                            modifier = Modifier
                                .statusBarsPadding()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    },

                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color(0xffff6b00)
                    ), navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { noti=noti+1 }) {
                            BadgedBox(
                                badge = { Badge { Text("${noti}") } },
                                modifier = Modifier.offset(x = -7.dp)
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .requiredSize(size = 142.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xFFFFFFFF))
                        .border(
                            border = BorderStroke(5.dp, Color(0xffff6b00)),
                            shape = CircleShape
                        )
                        .padding(8.dp) // Apply padding to the border
                ) {
                    if (userData?.profilePictureUrl != null) {
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
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (userData != null) {
                        userData.email?.let { it1 ->
                            Text(
                                text = it1,
                                color = Color(0xff1a1b2d),
                                lineHeight = 1.6.em,
                                style = TextStyle(
                                    fontSize = 15.sp,

                                    ),

                                )
                        }
                    }
                    if (userData != null) {
                        userData.username?.let {
                            Text(
                                text = it,
                                color = Color(0xff1a1b2d),
                                lineHeight = 1.6.em,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                ),

                                )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = { /* navigate to edit profile */ },
                        ) {

                            Box(modifier = Modifier.fillMaxWidth()) {
                                Icon(
                                    imageVector = Icons.Filled.Edit, contentDescription = null,
                                    modifier = Modifier
                                        .size(25.dp)

                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 50.dp), text = "Edit",
                                    style = TextStyle(fontSize = 20.sp)
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = { /* navigate to edit profile */ },
                        ) {

                            Box(modifier = Modifier.fillMaxWidth()) {
                                Icon(
                                    imageVector = Icons.Filled.BarChart, contentDescription = null,
                                    modifier = Modifier
                                        .size(25.dp)

                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 50.dp),
                                    text = "Statistics",
                                    style = TextStyle(fontSize = 20.sp)
                                )
                            }
                        }
                    }

                }

            }

        }
    }
}
    @Preview
    @Composable
    private fun MyProfilePreview() {
        val user: UserData? = null
        val navController = rememberNavController()
        ProfileP(navController).MyProfile(user)
    }
