package com.module.composepractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.module.composepractices.ui.theme.ComposePracticesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreenView()
        }
    }
}

@Composable
fun MyScreenView() {
    ComposePracticesTheme {
        var vStart by remember {
            mutableStateOf(5)
        }
        var vEnd by remember {
            mutableStateOf(7)
        }

        var noOfTimes by remember {
            mutableStateOf(0)
        }

        Column {
            Text(text = "Welcome to the Counter")
            ButtonCounter(noOfTimes) { count: Int ->
                noOfTimes = count
            }

            if (noOfTimes in vStart..vEnd+1) {
                if(noOfTimes==vEnd+1) {
                    vStart =noOfTimes+5
                    vEnd =vStart+2
                }
                Text(text = "I'm Visible between $vStart to $vEnd")
            }
        }
    }
}

@Composable
fun ButtonCounter(count: Int, clickAction: (Int) -> Unit) {

    Column {
        Button(onClick = {
            clickAction(count + 1)
        }) {
            val msg = if (count == 0)
                "Click Me"
            else
                "I've Clicked by $count times"
            Text(text = msg)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticesTheme {
        MyScreenView()
    }
}


