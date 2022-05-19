package com.mhdthasneemp.check_24.data.products.remote.dto


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Product(
    @SerializedName("available")
    var available: Boolean?,
    @SerializedName("color")
    var color: String?,
    @SerializedName("colorCode")
    var colorCode: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("imageURL")
    var imageURL: String?,
    @SerializedName("longDescription")
    var longDescription: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: Price?,
    @SerializedName("rating")
    var rating: Double?,
    @SerializedName("releaseDate")
    var releaseDate: Int?,
    @SerializedName("type")
    var type: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        available = parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        color = parcel.readString(),
        colorCode = parcel.readString(),
        description = parcel.readString(),
        id = parcel.readValue(Int::class.java.classLoader) as? Int,
        imageURL = parcel.readString(),
        longDescription = parcel.readString(),
        name = parcel.readString(),
        price = parcel.readParcelable(Price::class.java.classLoader),
        rating = parcel.readValue(Double::class.java.classLoader) as? Double,
        releaseDate = parcel.readValue(Int::class.java.classLoader) as? Int,
        type = parcel.readString()
    ) {
    }


    fun getFormattedDate(): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
            Date(
                releaseDate?.toLong() ?: 0L
            )
        )
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(available)
        parcel.writeString(color)
        parcel.writeString(colorCode)
        parcel.writeString(description)
        parcel.writeValue(id)
        parcel.writeString(imageURL)
        parcel.writeString(longDescription)
        parcel.writeString(name)
        parcel.writeParcelable(price, flags)
        parcel.writeValue(rating)
        parcel.writeValue(releaseDate)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}