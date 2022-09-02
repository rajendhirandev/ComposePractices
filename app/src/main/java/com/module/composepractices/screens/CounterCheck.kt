package com.module.composepractices.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.module.composepractices.ui.theme.ComposePracticesTheme


@Composable
fun MyScreenView() {
    MyCounterStateful()
}

/*@Composable
fun MyCounterStateful() {
    var vStart by remember { mutableStateOf(5) }
    var vEnd by remember { mutableStateOf(7) }
    var noOfTimes by remember { mutableStateOf(0) }
    ComposePracticesTheme {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Welcome to the Counter")
            Button(onClick = {
                noOfTimes++
            }) {
                val msg = if (noOfTimes == 0)
                    "Click Me"
                else
                    "I've Clicked by $noOfTimes times"
                Text(text = msg)
            }
            AnimatedVisibility(visible = noOfTimes in vStart..vEnd + 1) {
                Text(
                    text = "I'm Visible between $vStart to $vEnd",
                    modifier = Modifier
                )
                if (noOfTimes == vEnd + 1) {
                    vStart = noOfTimes + 5
                    vEnd = vStart + 2
                }
            }
        }
    }
}*/

@Composable
fun MyCounterStateful() {
    var vStart by remember { mutableStateOf(5) }
    var vEnd by remember { mutableStateOf(7) }
    var noOfTimes by remember { mutableStateOf(0) }
    ComposePracticesTheme {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Welcome to the Counter")
            ButtonCounter(noOfTimes) {
                noOfTimes++
            }
            AnimatedVisibility(visible = noOfTimes in vStart..vEnd + 1) {
                CustomTextAvailable(vStart, vEnd)
                if (noOfTimes == vEnd + 1) {
                    vStart = noOfTimes + 5
                    vEnd = vStart + 2
                }
            }
        }
    }
}

//Stateless Compose
@Composable
fun CustomTextAvailable(sValue: Int, eValue: Int) {
    Text(
        text = "I'm Visible between $sValue to $eValue",
        modifier = Modifier
    )
}

/*//Stateless Compose
@Composable
fun CustomTextAvailable(sValue: Int, eValue: Int) {
    *//*var isSelected by remember {
        mutableStateOf(false)
    }

    val animColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colors.primary
        } else Color.Transparent, animationSpec = tween(6000)
    )

    *//**//* val bgColor = if (isSelected) {
         MaterialTheme.colors.primary
     } else Color.Transparent*//**//*

    Surface(color = animColor) {*//*
    Text(
        text = "I'm Visible between $sValue to $eValue",
        modifier = Modifier
        *//*.clickable {
            isSelected = !isSelected
        }*//*
    )
    //}
}*/

//Stateless compose
@Composable
fun ButtonCounter(count: Int, clickAction: () -> Unit) {
    Column {
        Button(onClick = {
            clickAction()
        }) {
            val msg = if (count == 0)
                "Click Me"
            else
                "I've Clicked by $count times"
            Text(text = msg)
        }
    }
}