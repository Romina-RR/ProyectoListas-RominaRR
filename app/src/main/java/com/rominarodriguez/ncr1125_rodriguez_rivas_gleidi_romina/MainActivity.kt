package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.listas.ListasActivity
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme.NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        onStartClick = {startActivity(Intent(this@MainActivity,
                            ListasActivity::class.java)
                        )}
                    )
                }

            }
        }
    }
}

@Composable
fun MainScreen(
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(
                top = AppTheme.spacing.large,
                bottom = AppTheme.spacing.large,
                start = AppTheme.spacing.medium,
                end = AppTheme.spacing.medium
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.titulo1),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(id = R.string.titulo2),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(AppTheme.spacing.large))

        Image(
            painter = painterResource(id = R.drawable.imgprincipal),
            contentDescription = stringResource(R.string.logo_description),
            modifier = Modifier.size(AppTheme.sizes.logoSizeExtraLarge)
        )

        Spacer(modifier = Modifier.height(AppTheme.spacing.large))

        Button(
            onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.surfaceTint
            )
        ) {
            Text(text = stringResource(R.string.boton_text),
                style = MaterialTheme.typography.titleMedium)
        }
    }

}

