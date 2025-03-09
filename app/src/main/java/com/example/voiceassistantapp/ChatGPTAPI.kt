package com.example.voiceassistantapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object ChatGPTAPI {

    interface ChatGPTListener {
        fun onResponse(response: String)
        fun onError(error: String)
    }

    fun getResponse(userInput: String, listener: ChatGPTListener) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://api.openai.com/v1/chat/completions")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Authorization", "Bearer YOUR_OPENAI_API_KEY")
                conn.setRequestProperty("Content-Type", "application/json")

                // Xây dựng JSON request
                val jsonBody = JSONObject().apply {
                    put("model", "gpt-4")
                    val messages = JSONArray().apply {
                        put(JSONObject().apply {
                            put("role", "user")
                            put("content", userInput)
                        })
                    }
                    put("messages", messages)
                }

                // Gửi request
                conn.outputStream.use { os ->
                    os.write(jsonBody.toString().toByteArray())
                    os.flush()
                }

                // Đọc phản hồi
                val response = conn.inputStream.bufferedReader().use { it.readText() }

                // Xử lý phản hồi trong Main thread
                withContext(Dispatchers.Main) {
                    try {
                        val jsonResponse = JSONObject(response)
                        val choices = jsonResponse.getJSONArray("choices")
                        if (choices.length() > 0) {
                            val firstChoice = choices.getJSONObject(0)
                            val message = firstChoice.getJSONObject("message")
                            val content = message.getString("content")
                            listener.onResponse(content)
                        } else {
                            listener.onError("Không có phản hồi")
                        }
                    } catch (e: Exception) {
                        listener.onError("Lỗi phân tích dữ liệu: ${e.message}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    listener.onError("Lỗi: ${e.message}")
                }
            }
        }
    }
}