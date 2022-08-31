package com.module.composepractices.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.module.composepractices.ui.theme.ComposePracticesTheme


@Composable
fun MyInputScreen() {
    ComposePracticesTheme {
        MyInputContentState(modifier = Modifier)
    }
}

// Stateful Compose gonna Hoist the MyInputContentState
@Composable
fun MyInputContentState(modifier: Modifier) {
    var name by remember {
        mutableStateOf("")
    }
    //name = "Ragavan"
    MyInputContent(name = name, modifier = modifier) {
        name = it
    }
}

// Stateless Compose -- Hoisting by MyInputContentState
@Composable
fun MyInputContent(name: String, modifier: Modifier, onValueChange: (String) -> Unit) {
    Column(modifier = modifier.padding(10.dp)) {

        Text(text = name)
        OutlinedTextField(value = name, onValueChange = onValueChange, label = {
            Text(text = "Enter your data here")
        })
    }
}


// Stateful Compose
/*@Composable
fun MyInputContent(modifier: Modifier) {
    Column(modifier = modifier.padding(10.dp)) {
        var name by remember {
            mutableStateOf("")
        }

        Text(text = name)
        OutlinedTextField(value = name, onValueChange = { name = it }, label = {
            Text(text = "Enter your data here")
        })
    }
}*/
