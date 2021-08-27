package com.skanderjabouzi.rvezytest.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

class Breed : Serializable {
    @SerializedName("breeds")
    @Expose
    var breeds: List<BreedItem>? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null
}