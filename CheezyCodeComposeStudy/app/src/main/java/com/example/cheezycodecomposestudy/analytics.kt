package com.example.cheezycodecomposestudy

data class User(val name: String)

//just like hoooks

//@Composable
//fun rememberFirebaseAnalytics(user: User): FirebaseAnalytics {
//    val analytics: FirebaseAnalytics = remember {
//        FirebaseAnalytics()
//    }
//
//    // On every successful composition, update FirebaseAnalytics with
//    // the userType from the current User, ensuring that future analytics
//    // events have this metadata attached
//    SideEffect {
//        analytics.setUserProperty("userType", user.userType)
//    }
//    return analytics
//}