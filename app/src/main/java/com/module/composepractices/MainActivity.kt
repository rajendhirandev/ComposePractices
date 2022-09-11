package com.module.composepractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.module.composepractices.screens.MyCustomers
import com.module.composepractices.screens.MyInputScreen
import com.module.composepractices.screens.MyScreenView
import com.module.composepractices.screens.TaskListScreen
import com.module.composepractices.ui.theme.ComposePracticesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticesTheme {
        TaskListScreen()
    }
}


