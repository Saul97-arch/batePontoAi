package android.bignerdranch.bateopontoai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
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
                var entry1 by remember { mutableStateOf(TextFieldValue("")) }
                var entry2 by remember { mutableStateOf(TextFieldValue("")) }
                var entry3 by remember { mutableStateOf(TextFieldValue("")) }
                var entry4 by remember { mutableStateOf(TextFieldValue("")) }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(red = 78, green = 13, blue = 58))
                        .wrapContentSize(Alignment.Center)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .background(Color(red = 92, green = 19, blue = 73))
                                .clip(
                                    RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp)
                                )
                        ) {
                            Column(modifier = Modifier.padding(32.dp)) {
                                OutlinedTextField(
                                    label = { Text(text = "Entrada 1") },
                                    value = entry1,
                                    onValueChange = { newValue -> entry1 = newValue })
                                Spacer(modifier = Modifier.height(24.dp))
                                OutlinedTextField(
                                    label = { Text(text = "Entrada 2") },
                                    value = entry2,
                                    onValueChange = { newValue -> entry2 = newValue })
                                Spacer(modifier = Modifier.height(24.dp))
                                OutlinedTextField(
                                    label = { Text(text = "Entrada 3") },
                                    value = entry3,
                                    onValueChange = { newValue -> entry3 = newValue })
                                Spacer(modifier = Modifier.height(24.dp))
                                OutlinedTextField(
                                    label = { Text(text = "Entrada 4") },
                                    value = entry4,
                                    onValueChange = { newValue -> entry4 = newValue })
                            }
                        }

                        Column {
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(text = "Alarme definido", color = Color.White)
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(text = "11:59", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    MaterialTheme {
        // Add padding around our message
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = msg.author)
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }
    }
}
