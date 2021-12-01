package br.com.picpayandroid.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "usermodel")
@Parcelize
data class UserModel(
    @PrimaryKey @SerializedName("id") val id: Int?,
    @ColumnInfo(name = "img") @SerializedName("img") val img: String?,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String?,
    @ColumnInfo(name = "username") @SerializedName("username") val username: String?
) : Parcelable