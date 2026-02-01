package com.example.confessit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.confessit.viewModel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel) {

    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") }
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") }
        )

        Row{
            Button(onClick = {
                viewModel.login(email.value, password.value)
            }) {
                Text("Login")
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Button(onClick =  {
                viewModel.signUp(email.value, password.value)
            }) {
                Text("Sign Up")
            }
        }

    }
}