package com.hgh.ttoklip_manger.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hgh.ttoklip_manger.presentation.theme.AppTheme
import com.hgh.ttoklip_manger.presentation.ui.write.WriteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: TtoklipViewModel by viewModels()

    private var createResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        viewModel.setEvent(TtoklipContract.TtoklipEvent.FinishedWriteActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            AppTheme {
                TtoklipScreen(
                    { intentToCreatePlan() }
                )
            }
        }
    }
    private fun intentToCreatePlan() {
        val intent = Intent(this, WriteActivity::class.java)
        createResultLauncher.launch(intent)
    }
}
