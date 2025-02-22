package com.example.spendwise.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.BottomNavigation
import com.example.spendwise.NavScreen
import com.example.spendwise.R
import com.example.spendwise.ui.theme.Inter
import com.example.spendwise.ui.theme.Zinc
import com.example.spendwise.ui.theme.dark
import com.example.spendwise.ui.theme.light

@Composable
fun GettingStarted(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.back), contentDescription = null,
            modifier = Modifier.fillMaxHeight(),
            alignment = Alignment.TopCenter)
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val(img, startTxt, btn) = createRefs()
            Box(modifier = Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 64.dp)){
                Image(painter = painterResource(id = R.drawable.model), contentDescription = null,
                    modifier = Modifier.size(550.dp),
                )
            }
            Text(text = "Spend Smarter \n Save More",
                fontSize = 38.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Zinc,
                lineHeight = 32.sp,
                textAlign = TextAlign.Center ,
                modifier = Modifier.constrainAs(startTxt){
                    top.linkTo(img.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                })

            Button(onClick = { navController.navigate(BottomNavigation.Home.route)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = light,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .constrainAs(btn) {
                        top.linkTo(startTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(0.8f)
                    ) {
                Text(text = "Getting Started", fontFamily = Inter,
                    fontSize = 16.sp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun GettingStartedPreview() {
    GettingStarted(rememberNavController())
}