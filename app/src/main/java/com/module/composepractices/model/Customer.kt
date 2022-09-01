package com.module.composepractices.model

import kotlin.random.Random

data class Customer(val id: Int, val name: String, val desc: String = "") {
    var isSelected: Boolean = false
}

fun getMyCustomer() = MutableList(60) {
    Customer(it, "Employee ${it + 1}", getMyTagLine()[Random.nextInt(getMyTagLine().size)])
}

fun getMyTagLine() =
    listOf(
        "Engineer Manager",
        "Engineering Director",
        "Sr. Director",
        "Sr. Developer",
        "Lead",
        "Developer",
        "Architect"
    )
