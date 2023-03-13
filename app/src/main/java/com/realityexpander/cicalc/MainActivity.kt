package com.realityexpander.cicalc

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.play.core.review.ReviewManagerFactory
import com.realityexpander.cicalc.config.ApplicationConfig
import com.realityexpander.cicalc.presentation.CalculatorScreen
import com.realityexpander.cicalc.ui.theme.CICalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CICalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculatorScreen(
                        onShowFeedbackDialog = ::showFeedbackDialog
                    )

                    if(BuildConfig.DEBUG) {
                        Column {
                            Text("DEBUG MODE", color = Color.White)
                            Text(text = ApplicationConfig.BASE_URL, color = Color.White)
                        }
                    }
                }
            }
        }
    }

    private fun showFeedbackDialog() {
        val reviewManager = ReviewManagerFactory.create(this)
        reviewManager.requestReviewFlow().addOnCompleteListener { request ->
            if (request.isSuccessful) {
                val reviewInfo = request.result
                reviewManager.launchReviewFlow(this, reviewInfo)
            }
        }
    }
}


@Preview(showBackground = true, group = "light")
@Composable
fun DefaultPreview() {
    CICalcTheme {
        CalculatorScreen()
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, group = "dark"
)
@Composable
fun DarkPreview() {
    CICalcTheme {
        CalculatorScreen()
    }
}