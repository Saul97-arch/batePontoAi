package android.bignerdranch.bateopontoai.presentation

import android.bignerdranch.bateopontoai.R
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                backgroundColor = Color(247, 54, 62),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_add_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(red = 78, green = 13, blue = 58))
                .wrapContentSize(Alignment.Center)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(red = 248, green = 244, blue = 247, alpha = 255))
                ) {
                    Column(modifier = Modifier.padding(32.dp)) {

                        TimeButton(
                            context = context,
                            timeState = viewModel.entry1,
                            text = "Entrada 1",
                            viewModel
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        TimeButton(
                            context = context,
                            timeState = viewModel.out1,
                            text = "Saída 2",
                            viewModel
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        TimeButton(
                            context = context,
                            timeState = viewModel.entry2,
                            text = "Entrada 2",
                            viewModel
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        TimeButton(
                            context = context,
                            timeState = viewModel.out2,
                            text = "Saída 2",
                            viewModel
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Alarme definido", color = Color.White)
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "11:59", color = Color.White)
                }
            }
        }
    }
}



