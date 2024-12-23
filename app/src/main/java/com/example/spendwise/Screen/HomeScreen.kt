package com.example.spendwise.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.Data.model.ExpenseEntity
import com.example.spendwise.R
import com.example.spendwise.ui.theme.Inter
import com.example.spendwise.ui.theme.Zinc
import com.example.spendwise.viewModel.HomeViewModel
import com.example.spendwise.viewModel.HomeViewModelFactory

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel =
        HomeViewModelFactory(LocalContext.current).create(HomeViewModel::class.java)
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController) },
        floatingActionButton = {
            Image(painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null, modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        navController.navigate("/add")
                    })
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true

    ) { unit ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(unit)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (nameRow, list, card, topBar, add) = createRefs()
                Image(painter = painterResource(id = R.drawable.ic_topbar),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(topBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp, start = 16.dp, end = 16.dp)
                        .constrainAs(nameRow) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                    Column {
                        Text(
                            text = "Good Afternoon",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontFamily = Inter
                        )
                        Text(
                            text = "Rajvardhan Singh",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontFamily = Inter
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_bell),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable {
                                Toast
                                    .makeText(context, "The button is clicked", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    )
                }
                val state = viewModel.expenses.collectAsState(initial = emptyList())
                val expenses = viewModel.getTotalExpense(state.value)
                val income = viewModel.getTotalIncome(state.value)
                val balance = viewModel.getBalance(state.value)

                CardItem(modifier = Modifier.constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, balance, income, expenses)

                TransactionList(modifier = Modifier.constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }, list = state.value, viewModel, navController)

//            Image(painter = painterResource(id = R.drawable.add_icon),
//                contentDescription = null,
//                modifier = Modifier
//                    .constrainAs(add) {
//                        bottom.linkTo(parent.bottom)
//                        end.linkTo(parent.end)
//                    }
//                    .size(48.dp)
//                    .clip(CircleShape)
//                    .clickable {
//                        navController.navigate("/add")
//                    })

            }
        }
    }
}

@Composable
fun CardItem(modifier: Modifier, balance: String, income: String, expenses: String) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Zinc)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                Text(
                    text = "Total Balance",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = Inter
                )
                Text(
                    text = balance,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = Inter
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_menu_dots),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            CardRowItem(
                modifier = Modifier.align(Alignment.CenterStart),
                title = "Income",
                amount = income,
                image = R.drawable.ic_arrowdown
            )
            CardRowItem(
                modifier = Modifier.align(Alignment.CenterEnd),
                title = "Expense",
                amount = expenses,
                image = R.drawable.ic_arrowup
            )
        }
    }
}

@Composable
fun CardRowItem(modifier: Modifier, title: String, amount: String, image: Int) {
    Column(modifier = modifier) {
        Row {
            Image(painter = painterResource(id = image), contentDescription = null)
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = Inter,
                fontWeight = FontWeight.Light
            )
        }

        Text(
            text = amount,
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = Inter,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    }
}

@Composable
fun TransactionList(
    modifier: Modifier,
    list: List<ExpenseEntity>,
    viewModel: HomeViewModel,
    navController: NavController
) {
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Transaction History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Inter
                )
                Text(
                    text = "See All",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .align(
                            Alignment.CenterEnd
                        )
                        .clickable { navController.navigate("/all") },
                    fontFamily = Inter
                )
            }
        }
        items(list) { item ->
            val icon = viewModel.getItemIcon(item)
            TransactionItem(
                title = item.title,
                amount = item.amount.toString(),
                icon = icon,
                date = item.date,
                color = if (item.type == "Income") Color.Green else Color.Red
            )
        }
    }
}

@Composable
fun TransactionItem(title: String, amount: String, icon: Int, date: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Inter
                )
                Text(text = date, fontSize = 12.sp, fontFamily = Inter)
            }
        }
        Text(
            text = amount,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterEnd),
            color = color,
            fontFamily = Inter
        )
    }

}


@Composable
fun allExpensesScreen(navController: NavController) {
    val viewModel: HomeViewModel =
        HomeViewModelFactory(LocalContext.current).create(HomeViewModel::class.java)
    val state = viewModel.expenses.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController = rememberNavController()) },
        topBar = { TopAppBar(name = "All Expenses", onBackClick = { navController.popBackStack() }, icon = Icons.Default.Info)}
    ) { innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                // Create references for the TopAppBar, LazyColumn, and BottomBar
                val (lazyColumn) = createRefs()
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(lazyColumn) {
                            top.linkTo(parent.top)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                            bottom.linkTo(parent.bottom)
                        }
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    items(state.value) { item ->
                        val icon = viewModel.getItemIcon(item)
                        TransactionItem(
                            title = item.title,
                            amount = item.amount.toString(),
                            icon = icon,
                            date = item.date,
                            color = if (item.type == "Income") Color.Green else Color.Red
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun BottomBar(navController: NavController) {
    BottomAppBar(
        backgroundColor = Color.White,
        cutoutShape = CircleShape
    ) {
        BottomNavigationItem(selected = true, onClick = { }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = null
            )
        })
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("/stats") },
            icon = {
                Icon(
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.ic_analysis)
                )
            },
            modifier = Modifier.padding(end = 8.dp)
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("/profile") },
            icon = {
                Icon(
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.ic_user)
                )
            })
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                contentDescription = null,
                painter = painterResource(id = R.drawable.ic_user)
            )
        })
    }
}


@Composable
fun TopAppBar(name: String, onBackClick: () -> Unit, icon: ImageVector) {
    TopAppBar(title = {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = name,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontFamily = Inter
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Download"
                )
            }
        })
}

@Preview
@Composable
private fun HomeScreenPreview() {
//    HomeScreen(rememberNavController())
//    BottomBar()
}