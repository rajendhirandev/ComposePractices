package com.module.composepractices.screens

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.module.composepractices.model.Task
import com.module.composepractices.ui.theme.ComposePracticesTheme


@Composable
fun TaskListScreen() {
    Tasks()
}

@Composable
private fun Tasks() {
    val tasks = remember {
        Task.getTaskItems().toMutableStateList()
    }

    val assignedTask by remember {
        derivedStateOf {
            tasks.filter { it.assignedTo != "NA" }
        }
    }

    val unAssignedTask by remember {
        derivedStateOf {
            tasks.filter { it.assignedTo == "NA" }
        }
    }

    val addNewTask: (Task) -> Unit = {
        tasks.add(it)
    }

    val removeTask: (String) -> Unit = { id ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tasks.removeIf { it.taskId == id }
        }
    }

    ComposePracticesTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            AddTask(addNewTask)
            TaskListItem(assignedTask, unAssignedTask, removeTask)
        }
    }
}

@Composable
fun AddTask(addTask: (Task) -> Unit) {
    val ctx = LocalContext.current

    var taskName by rememberSaveable {
        mutableStateOf("")
    }
    var taskAssigned by rememberSaveable {
        mutableStateOf("NA")
    }

    AddTask(
        taskName = taskName,
        taskAssigned = taskAssigned, { tName ->
            taskName = tName
        }, { tAssigned ->
            taskAssigned = tAssigned
        }
    ) {
        var msg = "Task added successfully"
        if (taskName.isNotEmpty()) {
            addTask(Task(taskName = taskName, assignedTo = taskAssigned))
            taskAssigned = "NA"
            taskName = ""
        } else {
            msg = "Task name is mandatory!"
        }
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun AddTask(
    taskName: String,
    taskAssigned: String,
    taskNameChange: (String) -> Unit,
    taskAssignedChange: (String) -> Unit,
    addTaskAction: () -> Unit
) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Add new Task",
            modifier = modifier,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )
        OutlinedTextField(value = taskName, onValueChange = taskNameChange, label = {
            Text(text = "Task Name")
        }, modifier = modifier, maxLines = 1, singleLine = true)
        OutlinedTextField(value = taskAssigned, onValueChange = taskAssignedChange, label = {
            Text(text = "Assigned")
        }, modifier = modifier, maxLines = 1, singleLine = true)
        Button(onClick = addTaskAction, modifier = modifier) {
            Text(
                text = "Add Task", modifier = modifier, textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
    println("Inside Add Task method")
}

@Composable
fun TaskListItem(
    assignedTask: List<Task>,
    unAssignedTask: List<Task>,
    removeTask: (String) -> Unit
) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    LazyColumn(
        modifier = modifier
    ) {
        item(key = "assigned") {
            TaskTitle("Assigned")
            Divider(modifier = modifier, color = Color.DarkGray, thickness = 2.dp)
            println("Inside Assigned Task header")
        }
        items(assignedTask, key = { it.taskId }) {
            TaskItem(
                taskName = it.taskName,
                assignedTo = it.assignedTo,
                modifier = Modifier
            ) { removeTask(it.taskId) }
            Divider(modifier = modifier, color = Color.LightGray, thickness = 1.dp)
            println("Inside Assigned Task Item ${it.taskName}")
        }
        item(key = "unassigned") {
            TaskTitle("UnAssigned")
            Divider(modifier = modifier, color = Color.DarkGray, thickness = 2.dp)
            println("Inside UnAssigned Task header")
        }
        items(unAssignedTask, key = { it.taskId }) {
            TaskItem(
                taskName = it.taskName,
                assignedTo = if (it.assignedTo == "NA") it.assignedTo else "To: ${it.assignedTo}",
                modifier = Modifier
            ) { removeTask(it.taskId) }
            Divider(modifier = modifier, color = Color.LightGray, thickness = 1.dp)
            println("Inside UnAssigned Task Item ${it.taskName}")
        }
    }
}

@Composable
fun TaskTitle(groupTitle: String) {
    Text(
        text = groupTitle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        style = TextStyle(color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.Bold),
    )
    println("Inside Task Title $groupTitle")
}

@Composable
fun TaskItem(taskName: String, assignedTo: String, modifier: Modifier, removeTask: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(
                text = taskName, modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = assignedTo, modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 14.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            println("Inside TaskItems $taskName, $assignedTo")
        }
        IconButton(onClick = removeTask) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
    println("Inside TaskItems method done")
}

@Preview
@Composable
private fun TaskPreview() {
    TaskListScreen()
}
