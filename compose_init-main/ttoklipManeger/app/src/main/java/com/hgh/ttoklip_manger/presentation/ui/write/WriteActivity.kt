package com.hgh.ttoklip_manger.presentation.ui.write

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hgh.ttoklip_manger.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            AppTheme {
                WriteScreen(
                    onClose = { closeActivity() }
                )
            }
        }
    }

    private fun closeActivity() {
        finish()
    }
}
