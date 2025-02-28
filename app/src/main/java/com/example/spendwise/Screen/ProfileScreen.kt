package com.example.spendwise.Screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.R
import com.example.spendwise.ui.theme.Inter
import com.example.spendwise.ui.theme.dark
import com.example.spendwise.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(navController: NavController, profileViewModel: ProfileViewModel) {

    val user = profileViewModel.user
    println(user)
        Surface(modifier = Modifier
            .fillMaxSize()) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (topBar, profile, id, section) = createRefs()
                Image(painter = painterResource(id = R.drawable.ic_topbar), contentDescription = null,
                    modifier = Modifier.constrainAs(topBar){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .clickable {
                                navController.navigate("home")
                            }
                            .padding(16.dp))
                    Text(text = "Profile",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = Inter,
                        modifier = Modifier.align(Alignment.Center))
                    Image(painter = painterResource(id = R.drawable.ic_bell), contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { }
                            .padding(16.dp))
                }

                Image(painter = painterResource(id = R.drawable.photo), contentDescription = null,
                    modifier = Modifier
                        .constrainAs(profile) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 220.dp)
                        .size(100.dp))
                user?.let {
                    Text(
                        text = "@${it.id}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Inter,
                        color = dark,
                        modifier = Modifier.constrainAs(id){
                            top.linkTo(profile.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }.padding(top=8.dp)
                    )
                    Box(modifier = Modifier.constrainAs(section) {
                        top.linkTo(id.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.padding(top = 16.dp))
                    {

                        Column {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly){
                                Text(text = "Name :", fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                                Text(text = it.name,  fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly){
                                Text(text = "Age :", fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                                Text(text = it.age.toString(), fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)

                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly){
                                Text(text = "DOB :", fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                                Text(text = it.dob,  fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly){
                                Text(text = "Gender :", fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                                Text(text = it.gender,  fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly){
                                Text(text = "Phone Number :", fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                                Text(text = it.phoneNumber,  fontSize = 25.sp, fontFamily = Inter, fontWeight = FontWeight.Light)
                            }
                        }

                    }

                } ?: Text("No user Data is Available")


            }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
//    ProfileScreen(rememberNavController())
}