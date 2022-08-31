package com.module.composepractices.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.module.composepractices.R
import com.module.composepractices.model.Customer
import com.module.composepractices.ui.theme.ComposePracticesTheme
import kotlin.random.Random

@Composable
fun MyCustomers() {
    ComposePracticesTheme {
        MyCustomerListStateful()
    }
}

@Composable
fun MyCustomerListStateful() {
    val ctx = LocalContext.current
    MyCustomerListStateless(modifier = Modifier, customerList = getMyCustomer()) {
        Toast.makeText(ctx, "${it.name} is ${it.desc} - clicked", Toast.LENGTH_SHORT).show()
    }
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

@Composable
fun MyCustomerListStateless(
    modifier: Modifier,
    customerList: List<Customer>,
    clickedItem: (Customer) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(customerList, key = { it.id }) {
            CustomerListItem(
                it,
                modifier = modifier,
                clicked = { clickedItem(it) })
        }
    }
}

@Composable
fun CustomerListItem(customer: Customer, modifier: Modifier, clicked: () -> Unit) {
    val commonModifier = modifier.fillMaxWidth()
    Column(modifier = commonModifier
        .clickable { clicked() }) {
        Text(
            text = customer.name,
            modifier = commonModifier
                .padding(15.dp, 15.dp, 15.dp, 0.dp),
            colorResource(id = R.color.purple_700),
            style = TextStyle(fontSize = 24.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = customer.desc,
            modifier = commonModifier
                .padding(15.dp, 0.dp, 15.dp, 15.dp),
            colorResource(id = R.color.black),
            style = TextStyle(fontSize = 16.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Divider()
    }
}
