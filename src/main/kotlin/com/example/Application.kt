package com.example

import com.example.routes.nasaRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver


/**
 * Starts the NASA Image of the Day web application. This program allows a user to select a date from the
 * calendar and view the featured NASA's "image of the day" for the selected date. Dates cannot be selected
 * from before June 16, 1995 (the start date of the Image of the Day program) or after the current date (where
 * the images have not yet been selected by NASA). The user may continue to view as many Image of the Day pages
 * as they desire.
 *
 * @Ryan Johnson
 */
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", watchPaths = listOf("resources"),
        module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureTemplating()
    configureRouting()
}

/**
 * Configures Thymeleaf for serving the HTML pages of the application.
 */
fun Application.configureTemplating() {
    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }
}

/**
 * Configures the routing endpoints for the application, as well as recognizing the static .css files.
 */
fun Application.configureRouting() {
    routing {
        nasaRouting()
        staticResources("/", "static") {
            default("index.html")
            preCompressed(CompressedFileType.GZIP)
        }
    }
}
