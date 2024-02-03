package com.example.catapp.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CatBreedDataModel(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("origin")
    var origin: String? = "",
    @SerializedName("life_span")
    var lifeSpan: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(origin)
        parcel.writeString(lifeSpan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CatBreedDataModel> {
        override fun createFromParcel(parcel: Parcel): CatBreedDataModel {
            return CatBreedDataModel(parcel)
        }

        override fun newArray(size: Int): Array<CatBreedDataModel?> {
            return arrayOfNulls(size)
        }
    }
}