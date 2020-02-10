package com.jhoanes.apps.android.githubissues.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.BODY
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.CREATED_AT
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.HTML_URL
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.STATE
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.TITLE
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.USER

class IssueModel() : BaseModel(), Parcelable {

    @Expose
    @SerializedName(TITLE)
    var title: String? = null

    @Expose
    @SerializedName(STATE)
    var state: String? = null

    @Expose
    @SerializedName(BODY)
    var description: String? = null

    @Expose
    @SerializedName(CREATED_AT)
    var createdAt: String? = null

    @Expose
    @SerializedName(HTML_URL)
    var url: String? = null

    @Expose
    @SerializedName(USER)
    var user: UserModel? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        state = parcel.readString()
        description = parcel.readString()
        createdAt = parcel.readString()
        url = parcel.readString()
        user = parcel.readParcelable(UserModel::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(state)
        parcel.writeString(description)
        parcel.writeString(createdAt)
        parcel.writeString(url)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IssueModel> {
        override fun createFromParcel(parcel: Parcel): IssueModel {
            return IssueModel(parcel)
        }

        override fun newArray(size: Int): Array<IssueModel?> {
            return arrayOfNulls(size)
        }
    }
}