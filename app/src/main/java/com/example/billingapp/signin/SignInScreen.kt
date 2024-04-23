package com.example.billingapp.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billingapp.R

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material3.Text(modifier = Modifier.padding(vertical = 80.dp),
                text = "SATGURU",
                style = TextStyle(fontSize = 28.sp, color = Color(0xFFFF6B00), fontWeight = FontWeight.SemiBold)
            )
            androidx.compose.material3.Text(
                text = " TELECOM",
                style = TextStyle(fontSize = 28.sp, color = Color(0xFF515151),
                    fontWeight = FontWeight.SemiBold)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        val portalText = "😊 Let's Get Started!"
        androidx.compose.material3.Text(text = portalText,
                                        fontSize = 25.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.SemiBold
                                            )
        Spacer(modifier = Modifier.height(15.dp))
        androidx.compose.material3.Text(text = "Sign in with Google",
            fontSize = 20.sp,
            color = Color.Gray,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display the error message

        Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.height(28.dp))
        androidx.compose.material3.Button(
            onClick = {
                onSignInClick()
            },
            modifier = Modifier
                .padding(10.dp)
                .height(50.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFFF6B00)
            ),
            shape = RoundedCornerShape(20.dp)
        ) { Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Google",
            modifier = Modifier
                .align(alignment = Alignment.Top)
                .offset(
                    x = (0).dp,
                    y = (0).dp
                )
                .requiredSize(size = 50.dp))
            androidx.compose.material3.Text(
                text = "Login ",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )

        }
    }
}
@Composable
@Preview
fun loginScreen(){
    val viewModel = viewModel<SignInViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignInScreen(state, onSignInClick = {})
}