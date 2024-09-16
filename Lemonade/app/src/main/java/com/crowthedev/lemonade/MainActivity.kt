package com.crowthedev.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.crowthedev.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonImageAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionId),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun LemonadeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var currentStep by remember { mutableIntStateOf(1) }

        when (currentStep) {
            1 -> {
                LemonImageAndImage(
                    textLabelResourceId = R.string.first_step,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionId = R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep = 2
                    }
                )
            }
            2 -> {
                LemonImageAndImage(
                    textLabelResourceId = R.string.second_step,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionId = R.string.lemon_squeeze_content_description,
                    onImageClick = {
                        currentStep = 3
                    }
                )

            }
            3 -> {
                LemonImageAndImage(
                    textLabelResourceId = R.string.third_step,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.glass_drink_content_description,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }
            else -> {
                LemonImageAndImage(
                    textLabelResourceId = R.string.fourth_step,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.glass_restart_content_description,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}