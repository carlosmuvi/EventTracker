package com.carlosmuvi.eventtracker.data.account

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserManager @Inject constructor() {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get() = mAuth.currentUser

}