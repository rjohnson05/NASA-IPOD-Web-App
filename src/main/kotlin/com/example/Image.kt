package com.example

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

/**
 * Stores the data for each Image of the Day that is fetched from the NASA API. This data is then displayed in
 * the HTML templates. Each image stores the URL source of the featured image or video, the date this image was
 * featured, a title and explanation of the image, and copyright information for the media if applicable.
 */
@Entity
data class Image (
    @Id
    @GeneratedValue
    val id: Int = 0,
    val copyright: String? = null,
    val date: Date? = null,
    val explanation: String? = null,
    val url: String? = null,
    val title: String? = null
)