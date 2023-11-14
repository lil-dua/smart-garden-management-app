package com.advanced.smartgardenapp.ui.login

import androidx.lifecycle.ViewModel
import com.advanced.smartgardenapp.data.model.User
import com.advanced.smartgardenapp.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginViewModel : ViewModel() {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val authenticatorReference = database.reference.child(Constants.KEY_USER)

    fun authenticatorUser(email: String, password: String, callback: (Boolean) -> Unit) {
        authenticatorReference.orderByChild(Constants.KEY_EMAIL).equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var isLoginSuccess = false
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(User::class.java)
                        if (user?.password == password) {
                            isLoginSuccess = true
                            break
                        }
                    }
                    callback(isLoginSuccess)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false)
                }

            })
    }

}