package com.example.billingapp.signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
