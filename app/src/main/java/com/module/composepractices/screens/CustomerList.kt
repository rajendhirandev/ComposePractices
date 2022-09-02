package com.module.composepractices.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.module.composepractices.R
import com.module.composepractices.model.Customer
import com.module.composepractices.model.getMyCustomer
import com.module.composepractices.ui.theme.ComposePracticesTheme

@Composable
fun MyCustomers() {
    ComposePracticesTheme {
        Log.d("TestCompose", "MyCustomerListStateful")
        val customerList = remember {
            getMyCustomer().toMutableStateList()
        }
        MyCustomerList(
            modifier = Modifier,
            customerList = customerList
        ) { customerList.remove(it) }
    }
}

@Composable
fun MyCustomerList(
    modifier: Modifier,
    customerList: List<Customer>,
    removeCustomer: (Customer) -> Unit
) {
    val ctx = LocalContext.current
    Log.d("TestCompose", "MyCustomerListStateless")
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(customerList, key = { it.id }) {
            Log.d("TestCompose", "MyCustomerListStateless-Items")
            CustomerListItem(
                it,
                modifier = modifier, {
                    val customer = customerList.count { it.isSelected }
                    if (customer > 0) {
                        Toast.makeText(ctx, "Total $customer Employee(s) Selected ", Toast.LENGTH_SHORT)
                            .show()
                    }
                }, { removeCustomer(it) })
        }
    }
}

//Stateful Compose
@Composable
fun CustomerListItem(
    customer: Customer,
    modifier: Modifier,
    onCountUpdates: () -> Unit,
    onRemove: () -> Unit
) {
    val ctx = LocalContext.current

    Log.d("TestCompose", "CustomerListItem-Stateful")
    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }
    //val onCheckChange: (Boolean) -> Unit = { newCheck -> isChecked = newCheck }
    CustomerListItem(customer = customer, modifier = modifier, {
        Toast.makeText(ctx, "${customer.name} is ${customer.desc} clicked", Toast.LENGTH_SHORT)
            .show()
    }, isChecked, {
        customer.isSelected = it
        isChecked = it
        if (it) {
            onCountUpdates()
        }
    }, { onRemove() })
}

// Stateless Compose
@Composable
fun CustomerListItem(
    customer: Customer,
    modifier: Modifier,
    clickedItem: () -> Unit,
    isChecked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    onRemove: () -> Unit
) {
    Log.d("TestCompose", "CustomerListItem-Stateless")
    val commonModifier = modifier.fillMaxWidth()
    Surface(modifier = commonModifier) {
        Log.d("TestCompose", "CustomerListItem-Stateless-Surface")
        Row(
            modifier = commonModifier.clickable { clickedItem() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("TestCompose", "CustomerListItem-Stateless-Row")
            Column(
                modifier = commonModifier
                    .weight(1f)
            ) {
                Log.d("TestCompose", "CustomerListItem-Stateless-Column")
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
            }
            Log.d("TestCompose", "CustomerListItem-Stateless-Checkbox")
            Checkbox(checked = isChecked, onCheckedChange = onCheckChange)

            IconButton(onClick = { onRemove() }) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
        Divider()
    }
}

@Preview
@Composable
fun CustomerPreview() {
    MyCustomers()
}
