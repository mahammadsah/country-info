package com.muhammad.coutry.list.coutryinfo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity
data class CarModel(
    @ColumnInfo("name")
    @SerializedName("name")
    val name:String,
    @ColumnInfo("model")
    @SerializedName("model")
    val model:String,
    @ColumnInfo("color")
    @SerializedName("color")
    val color:String,
    @ColumnInfo("speed")
    @SerializedName("speed")
    val speed:Double) {
}