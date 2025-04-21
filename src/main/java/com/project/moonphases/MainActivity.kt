package com.project.moonphases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.moonphases.ui.theme.MoonPhasesTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoonPhasesTheme {
                MainContent()

            }
        }

    }
}

@Composable
fun MyScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}

@Composable
fun MoonPhaseImage(imageResId: Int){
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Moon Phases Image",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)



        )
    }
}

@Composable
fun PhaseDescription(phase: String, modifier: Modifier = Modifier) {
    Text(
        text = phase,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = (25.sp)

    )
}

@Composable
fun NextPhaseButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    )
    {
        Button(onClick = { onClick() }) {
            Text("Next")
        }
    }
}

@Composable
fun PreviousPhaseButton(modifier: Modifier = Modifier,onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
    ) {
        Button(onClick = { onClick() }) {
            Text("Back")
        }
    }
}

@Composable
fun MainContent() {
    val images = listOf(
        R.drawable.new_moon,
        R.drawable.waxing_crescent_moon,
        R.drawable.waxing_first_quarter_moon,
        R.drawable.waxing_gibbous_moon,
        R.drawable.full_moon,
        R.drawable.waning_gibbous_moon,
        R.drawable.waning_last_quarter_moon,
        R.drawable.waning_crescent_moon
    )
    val phases = listOf(
        "New Moon",
        "Waxing Crescent",
        "Waxing First Quarter",
        "Waxing Gibbous",
        "Full Moon",
        "Waning Gibbous",
        "Waning Last Quarter",
        "Waning Crescent"
    )
    var currentIndex by remember { mutableStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MyScreen()

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MoonPhaseImage(imageResId = images[currentIndex])
                Spacer(modifier = Modifier.height(24.dp))
                PhaseDescription(phase = phases[currentIndex])
            }

            NextPhaseButton {
                currentIndex = (currentIndex + 1) % images.size
            }

            PreviousPhaseButton {
                currentIndex = (currentIndex - 1 + images.size) % images.size
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    MoonPhasesTheme {
        MainContent()
    }
}
