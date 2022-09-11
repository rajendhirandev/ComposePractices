package com.module.composepractices.model

import kotlin.random.Random

data class Task(
    val taskId: String = Random.nextInt().toString(),
    val taskName: String,
    val assignedTo: String = "NA"
) {

    companion object {
        fun getTaskItems() = mutableListOf(
            Task(taskName = "Update the Tablet Record", assignedTo = "Ragavan"),
            Task(taskName = "Update Data Store Procedures")/*,
            Task("Backup the Stage DB", "Arivu"),
            Task("Perform SonarQube check on Stage Code"),
            Task("Close QA Approved IRs")*/
        )
    }
}