package com.carlosmuvi.eventtracker.data.person

import android.util.Log
import com.carlosmuvi.eventtracker.data.account.AccountRepository
import com.carlosmuvi.eventtracker.data.account.UserManager
import com.carlosmuvi.eventtracker.data.person.PersonMapper.toPerson
import com.carlosmuvi.eventtracker.data.person.PersonMapper.toFirestorePerson
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface PersonRepository {
    suspend fun save(event: Person)
    suspend fun getAll(): List<Person>
}

class PersonRepositoryImpl @Inject constructor(
    userManager: UserManager,
    firestore: FirebaseFirestore
) : PersonRepository {

    private val personsCollection = firestore
        .collection("users")
        .document(userManager.currentUser!!.uid)
        .collection("persons")

    override suspend fun save(event: Person) {
        return suspendCoroutine { cont ->
            personsCollection.add(event.toFirestorePerson())
                .addOnSuccessListener { documentReference ->
                    Log.d("TEST", "DocumentSnapshot written with ID: ${documentReference.id}")
                    cont.resume(Unit)
                }
                .addOnFailureListener { e ->
                    Log.w("TEST", "Error adding document", e)
                    cont.resumeWithException(e)
                }
        }
    }

    override suspend fun getAll(): List<Person> {
        return suspendCoroutine { cont ->
            personsCollection.get()
                .addOnSuccessListener {
                    cont.resume(it.mapNotNull { document -> document.toPerson() })
                }
                .addOnFailureListener { cont.resumeWithException(it) }
        }
    }
}


