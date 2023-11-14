package com.advanced.smartgardenapp.ui.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.advanced.smartgardenapp.data.model.User
import com.advanced.smartgardenapp.utils.Constants
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val registerReference = database.reference.child(Constants.KEY_USER)
    fun registerUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val userId = registerReference.push().key
            registerReference.child(userId ?: "").setValue(user)
        }
    }

}