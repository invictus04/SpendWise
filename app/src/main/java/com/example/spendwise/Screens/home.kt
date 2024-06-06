package com.example.spendwise.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationDefaults.windowInsets
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.R
import com.example.spendwise.ui.theme.Inter

@Composable
fun Home(navController: NavController) {
    val ScaffoldState  = rememberScaffoldState()
    Scaffold (
        scaffoldState = ScaffoldState,
        bottomBar = {
            BottomAppBar(
            backgroundColor = Color.White,
                modifier = Modifier
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.home_icon),
                            contentDescription = "Home"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.analytics_icon),
                            contentDescription = "Analytics"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.remainder),
                            contentDescription = "Remainder"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.accounts),
                            contentDescription = "profile"
                        )
                    }
                }

            }
        },
        floatingActionButton = {
            Icon(painter = painterResource(id = R.drawable.add_icon), contentDescription ="Add")
        }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(it)
            ) {
                Image(painter = painterResource(id = R.drawable.photo), contentDescription = "user profile",
                    modifier = Modifier
                        .padding(20.dp)
                        .size(50.dp)
                        .clickable { })
                Column {
                    Text(
                        text = "Hello Name", modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        letterSpacing = 1.5.sp
                    )

                    Text(
                        text = "Good AfterNoon", modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        fontFamily = Inter,
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp,
                        letterSpacing = 1.5.sp
                    )

                }
            }
            Column {
                Text(
                    text = "Total Balance", modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 28.sp,
                    letterSpacing = 2.5.sp
                )
                Text(text = "1500.64" , modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    fontFamily = Inter,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    letterSpacing = 1.5.sp)

            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Box(modifier = Modifier
                    .size(width = 180.dp, height = 100.dp)
                    .clip(
                        RoundedCornerShape(55.dp, 55.dp, 55.dp, 55.dp)
                    )
                    .background(Color.Blue),
                    contentAlignment = Alignment.Center) {
                    Row {
                        Column(Modifier.padding(5.dp)) {
                            Row {
                            Icon(imageVector = Icons.Default.Add, contentDescription ="" ,
                                modifier = Modifier.size(15.dp))
                        Text(text = "Income",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            letterSpacing = 1.5.sp)
                            }
                        Text(text = "45254",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            letterSpacing = 1.5.sp)
                        }

                        Column(Modifier.padding(5.dp)) {
                            Row {
                            Icon(imageVector = Icons.Default.Delete, contentDescription ="" ,
                                modifier = Modifier.size(15.dp))
                            Text(text = "Spendings",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp,
                                letterSpacing = 1.5.sp)

                            }
                            Text(text = "50",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp,
                                letterSpacing = 1.5.sp)
                        }
                    }
                }

            }
            Text(text = "Transaction",modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                textAlign = TextAlign.Left,
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 19.sp,
                letterSpacing = 2.5.sp)
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {

        }




    }
}

@Composable
fun TransactionData() {
    
}

@Preview(showBackground = true)
@Composable
private fun homePreview() {
    Home(navController = rememberNavController())
}