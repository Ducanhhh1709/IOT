package com.example.voiceassistantapp

import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.voiceassistantapp.ui.theme.VoiceAssistantAppTheme

class MainActivity : ComponentActivity(), VoiceAssistant.VoiceAssistantListener {

    private lateinit var voiceAssistant: VoiceAssistant
    private lateinit var ttsHelper: TextToSpeechHelper

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO_PERMISSION)
        }

        voiceAssistant = VoiceAssistant(this, this)
        ttsHelper = TextToSpeechHelper(this)

        setContent {
            VoiceAssistantAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        var resultText by remember { mutableStateOf("Press the button and speak...") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = resultText, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
            Button(onClick = {
                resultText = "Listening..."
                voiceAssistant.startListening()
            }) {
                Text(text = "Speak Now")
            }
        }
    }

    override fun onResult(result: String) {
        runOnUiThread {
            setContent {
                VoiceAssistantAppTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        MainScreen()
                    }
                }
            }
        }

        ChatGPTAPI.getResponse(result, object : ChatGPTAPI.ChatGPTListener {
            override fun onResponse(response: String) {
                runOnUiThread {
                    setContent {
                        VoiceAssistantAppTheme {
                            Surface(modifier = Modifier.fillMaxSize()) {
                                MainScreen()
                            }
                        }
                    }
                    ttsHelper.speak(response)
                }
            }

            override fun onError(error: String) {
                runOnUiThread {
                    setContent {
                        VoiceAssistantAppTheme {
                            Surface(modifier = Modifier.fillMaxSize()) {
                                MainScreen()
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onError(error: String) {
        runOnUiThread {
            setContent {
                VoiceAssistantAppTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        MainScreen()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        voiceAssistant.destroy()
        ttsHelper.shutdown()
    }
}