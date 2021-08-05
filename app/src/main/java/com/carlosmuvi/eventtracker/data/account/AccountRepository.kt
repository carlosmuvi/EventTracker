package com.carlosmuvi.eventtracker.data.account

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


interface AccountRepository {
    suspend fun createAccount(email: String, password: String): FirebaseUser
    suspend fun logout()
}

class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    private val mAuth = FirebaseAuth.getInstance()

    override suspend fun createAccount(email: String, password: String): FirebaseUser {
        return suspendCoroutine { cont ->
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        cont.resume(mAuth.currentUser!!)
                    } else {
                        cont.resumeWithException(task.exception!!)
                    }
                }
        }
    }

    override suspend fun logout() {
        mAuth.signOut()
    }
}