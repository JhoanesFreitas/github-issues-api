package com.jhoanes.apps.android.githubissues.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jhoanes.apps.android.githubissues.constants.Constants

class UserModel() : BaseModel(), Parcelable {

    @Expose
    @SerializedName(Constants.AVATAR)
    var avatar: String? = null

    constructor(parcel: Parcel) : this() {
        avatar = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}