package com.example.routes

import com.example.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import java.io.File

/**
 * Serves to establish the endpoints for the NASA Image of the Day web application. Depending upon which type
 * of request is received, a different HTML page is served.
 *
 * @author Ryan Johnson
 */
fun Route.nasaRouting() {
    route("/") {
        /**
         * Serves the static home page.
         */
        get {
            call.respondFile(File("src/main/resources/templates"), "index.html")
        }
        /**
         * Serves the Image of the Day pages. This page displays all information surrounding a featured image.
         * Given a specific date, the image featured from that date are displayed with all of its accompanying
         * information, such as the title, explanation, and copyright info. This information is fetched from a
         * NASA's APOD API and stored as an Image object.
         */
        get("/{date}") {
            val client = HttpClient(CIO)
            val responseData: String = client.get("https://api.nasa.gov/planetary/apod") {
                url {
                    parameters.append("date", call.parameters["date"]!!)
                    parameters.append("api_key", "KDJnfjk5d8u9dw7SKtYiHLUTubNENtxMWNbnZD3f")
                }
            }.body()
            client.close()
            println("Response Data: $responseData")

            val imageType = object : TypeToken<Image>() {}.type
            val image = Gson().fromJson<Image>(responseData, imageType)

            call.respond(ThymeleafContent("image", mapOf("image" to image)))
        }
    }
}
