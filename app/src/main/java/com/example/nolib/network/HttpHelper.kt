package com.example.nolib.network

import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

class HttpHelper {
    companion object {
        fun sendHttpRequest(urlString: String, method: String, postData: JSONObject?, authorized: String?): String {
            val urlConnection = URL(urlString).openConnection() as HttpURLConnection

            with(urlConnection) {
                requestMethod = method

                authorized?.let {
                    setRequestProperty("Authorization", "Bearer $it")
                }

                if (method == "POST") {
                    setRequestProperty("Content-Type", "application/json")
                    doOutput = true

                    postData?.let {
                        outputStream.bufferedWriter().use { writer ->
                            writer.write(it.toString())
                            writer.flush()
                        }
                    }
                }

                return inputStream.bufferedReader().use {
                    it.readText()
                }
            }
        }
    }
}








