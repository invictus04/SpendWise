package com.example.spendwise.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.BottomNavigation
import com.example.spendwise.Data.model.ExpenseEntity
import com.example.spendwise.NavScreen
import com.example.spendwise.R
import com.example.spendwise.Utils
import com.example.spendwise.ui.theme.Inter
import com.example.spendwise.ui.theme.Zinc
import com.example.spendwise.viewModel.HomeViewModel
import com.example.spendwise.viewModel.HomeViewModelFactory
import com.example.spendwise.viewModel.StatsViewModel
import com.example.spendwise.viewModel.StatsViewModelFactory
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.Zoom
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.LineCartesianLayerModel
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer


@Composable
fun StatsScreen(navController: NavController, items: List<BottomNavigation>, viewModel: StatsViewModel) {

    val state = viewModel.entries.collectAsState(initial = emptyList())

    val isExpenseSelected = remember { mutableStateOf(true) }

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val entries = viewModel.getEntriesForChart(state.value)
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val(chgBtn, chart, Spending) = createRefs()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(chgBtn) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(onClick = {isExpenseSelected.value = true },
                        modifier = Modifier.padding(26.dp),
                        enabled = !isExpenseSelected.value
                    ) {
                        Text(text = "Expense",
                            fontFamily = Inter,
                            color = Color.Red)
                    }
                    OutlinedButton(onClick = { isExpenseSelected.value = false },
                        modifier = Modifier.padding(26.dp),
                        enabled = isExpenseSelected.value
                    ) {
                        Text(text = "Income",
                            fontFamily = Inter,
                            color = Zinc)
                    }
                }
            LineChart(entries = entries,
                Modifier
                    .constrainAs(chart) {
                        top.linkTo(chgBtn.bottom)
                        start.linkTo(parent.start)
                        end.linkTo((parent.end))
                    }
                    .fillMaxWidth())
                val HviewModel: HomeViewModel = HomeViewModelFactory(LocalContext.current).create(HomeViewModel::class.java)
                val Spendstate = HviewModel.spending.collectAsState(initial = emptyList())
                val incomeState = HviewModel.income.collectAsState(initial = emptyList())
            TopSpending(modifier = Modifier.constrainAs(Spending){
                top.linkTo(chart.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, list = if (isExpenseSelected.value) Spendstate.value else incomeState.value , HviewModel, navController)
            }
        }
}

@Composable
fun TopSpending(
    modifier: Modifier,
    list: List<ExpenseEntity>,
    viewModel: HomeViewModel,
    navController: NavController
) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Top Spending",
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
                        .clickable { navController.navigate(NavScreen.All.route) },
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
fun LineChart(entries: List<LineCartesianLayerModel.Entry>, modifier: Modifier) {
    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(entries) {
        modelProducer.runTransaction {
            if (entries.isNotEmpty()){
            val xvalues = entries.map {
                it.x
            }
            val yvalues = entries.map { it.y }
            lineSeries { series( xvalues, yvalues) }
            }
        }
    }
    CartesianChartHost(
        chart = rememberCartesianChart(
        rememberLineCartesianLayer(
            LineCartesianLayer.LineProvider.series(
                LineCartesianLayer.rememberLine(
                    remember {
                        LineCartesianLayer.LineFill.single(fill(Zinc))
                    }
                )
            )
        ),
            startAxis = VerticalAxis.rememberStart(
                guideline = null
            ),
            bottomAxis = HorizontalAxis.rememberBottom(
                guideline = null,
                valueFormatter = CartesianValueFormatter { _, x, _ ->
                    Utils.formatDateForChart(x.toLong())
                },
            )
    ),
        modelProducer = modelProducer,
        modifier = modifier,
        scrollState = rememberVicoScrollState(),
        zoomState = rememberVicoZoomState(
            initialZoom = remember {
                Zoom.min(Zoom.static(), Zoom.Content)
            }
        )

    )
}

@Preview(showBackground = true)
@Composable
private fun preview() {
//    StatsScreen(rememberNavController())
}


