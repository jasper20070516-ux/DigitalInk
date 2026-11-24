package tw.edu.pu.csim.tcyang.myapplicationdigitalink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import tw.edu.pu.csim.tcyang.myapplicationdigitalink.ui.theme.MyApplicationDigitalInkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationDigitalInkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Doodle(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Doodle(modifier: Modifier = Modifier) {
    var recognizedText by remember {
        mutableStateOf("請在畫布區手寫，再按「開始辨識」按鈕")
    }

    // 建立一個路徑物件
    var path by remember {
        mutableStateOf(
            Path().apply {
                moveTo(50f, 100f)   // 移動到起始點 (50f, 100f)
                lineTo(200f, 500f)  // 畫一條線到 (200f, 500f)
                lineTo(450f, 400f)  // 畫一條線到 (450f, 400f)
                close()             // 閉合路徑
            }
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(300.dp)
                .border(2.dp, Color.Gray)
        ) {
            // 繪製黑色路徑，透明度1 (不透明)，線條粗細10
            drawPath(path, Color.Black, 1f, Stroke(10f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // 開始辨識
            Button(onClick = {
            }) {
                Text("開始辨識")
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 清除畫布
            Button(onClick = {
                path = Path()
            }) {
                Text("清除")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(recognizedText)
    }
}