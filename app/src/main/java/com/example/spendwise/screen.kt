package com.example.spendwise

sealed class screen(val route: String) {
    object gettingStarted: screen("gettingStartedScreen")
    object signUp: screen("signUpScreen")
    object login: screen("loginScreen")
    object otpVerification: screen("otpVerficationScreen")
    object home: screen("homeScreen")
    object profile: screen("profile")
    object stats: screen("stats")

}
