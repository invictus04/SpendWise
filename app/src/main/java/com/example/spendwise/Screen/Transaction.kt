package com.example.spendwise.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.spendwise.Data.model.ExpenseEntity
import com.example.spendwise.R
import com.example.spendwise.Utils
import com.example.spendwise.ui.theme.Inter
import com.example.spendwise.viewModel.AddExpenseViewModel
import com.example.spendwise.viewModel.AddExpenseViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun addTransation(navController: NavController) {

    val viewModel: AddExpenseViewModel =
        AddExpenseViewModelFactory(LocalContext.current).create(AddExpenseViewModel::class.java)
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, list, card, topBar) = createRefs()
            Image(painter = painterResource(id = R.drawable.ic_topbar), contentDescription = null,
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, start = 16.dp, end = 16.dp)
                .constrainAs(nameRow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(
                    text = "Add Expense",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    fontFamily = Inter
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_menu_dots),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_back), contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterStart).clickable {
                        navController.popBackStack()
                    }
                )
            }

            DataBox(modifier = Modifier
                .padding(top = 60.dp)
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, onAddExpenseClick = {
                coroutineScope.launch {
                    if (viewModel.addExpense(it)) {
                        navController.popBackStack()
                    }
                }
            })
        }
    }
}

@Composable
fun DataBox(modifier: Modifier = Modifier, onAddExpenseClick: (model: ExpenseEntity) -> Unit) {
    val name = remember {
        mutableStateOf("")
    }
    val type = remember {
        mutableStateOf("")
    }
    val amount = remember {
        mutableStateOf("")
    }
    val category = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf(0L)
    }

    val dateDialogVisibility = remember {
        mutableStateOf(false)
    }



    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "NAME", fontSize = 16.sp, color = Color.Gray, fontFamily = Inter)
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))

        Text(text = "AMOUNT", fontSize = 16.sp, color = Color.Gray, fontFamily = Inter)
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = amount.value,
            onValueChange = { amount.value = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))
        //date
        Text(text = "DATE", fontSize = 16.sp, color = Color.Gray, fontFamily = Inter)
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(value = if (date.value == 0L) "" else Utils.formatDateToHumanReadableFormat(
            date.value
        ),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { dateDialogVisibility.value = true },
            enabled = false,
            colors =  OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color.Black,
                disabledTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.size(8.dp))
        // dropdown
        Text(text = "CATEGORY", fontSize = 16.sp, color = Color.Gray, fontFamily = Inter)
        Spacer(modifier = Modifier.size(4.dp))
        ExpenseDropDown(
            listOfItems = listOf(
                "Spotify",
                "PayPal",
                "Starbucks",
                "UpWork",
                "YouTube",
                "Other"
            ), onSelectedItems = { category.value = it })
        Spacer(modifier = Modifier.size(8.dp))

        //type
        Text(text = "TYPE", fontSize = 16.sp, color = Color.Gray, fontFamily = Inter)
        Spacer(modifier = Modifier.size(4.dp))
        ExpenseDropDown(
            listOfItems = listOf("Income", "Expense"),
            onSelectedItems = { type.value = it })
        Spacer(modifier = Modifier.size(8.dp))


        Button(
            onClick = {
                val model = ExpenseEntity(
                    null,
                    name.value,
                    amount.value.toDoubleOrNull() ?: 0.0,
                    Utils.formatDateToHumanReadableFormat(date.value),
                    category.value,
                    type.value
                )
                onAddExpenseClick(model)
            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "ADD", fontSize = 14.sp, color = Color.White, fontFamily = Inter)
        }
    }

    if (dateDialogVisibility.value) {
        ExpenseDatePicker(onDateSelected = {
            date.value = it
            dateDialogVisibility.value = false
        }, onDismiss = {
            dateDialogVisibility.value = false
        })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDatePicker(
    onDateSelected: (date: Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis ?: 0L

    DatePickerDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        TextButton(onClick = { onDateSelected(selectedDate) }) {
            Text(text = "Confirm", fontFamily = Inter)
        }
    },
        dismissButton = {
            TextButton(onClick = { onDateSelected(selectedDate) }) {
                Text(text = "Cancel", fontFamily = Inter)
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDropDown(listOfItems: List<String>, onSelectedItems: (item: String) -> Unit) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val selectedItem = remember {
        mutableStateOf<String>(listOfItems[0])
    }

    ExposedDropdownMenuBox(expanded = expanded.value, onExpandedChange = { expanded.value = !expanded.value }) {
        TextField(value = selectedItem.value, onValueChange = {  },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(), readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
            })

        ExposedDropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value=false }) {
            listOfItems.forEach {
                DropdownMenuItem(text = { Text(text = it, fontFamily = Inter) }, onClick = {
                    selectedItem.value = it
                    onSelectedItems(it)
                    expanded.value = false
                })
            }
        }
    }
}

@Preview
@Composable
private fun addTransactionPreview() {
//    addTransation()
}