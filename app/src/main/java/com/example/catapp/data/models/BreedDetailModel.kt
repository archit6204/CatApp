package com.example.catapp.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BreedDetailModel(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("url")
    var url: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BreedDetailModel> {
        override fun createFromParcel(parcel: Parcel): BreedDetailModel {
            return BreedDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<BreedDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}