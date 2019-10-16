package lookat.example.tushar.allstatemobile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HerosModel(
    @SerializedName("name")
    @Expose
    val name :String,
    @SerializedName("realname")
    @Expose
    val realname :String,
    @SerializedName("team")
    @Expose
    val team :String,
    @SerializedName("firstappearance")
    @Expose
    val firstappearance :String,
    @SerializedName("createdby")
    @Expose
    val createdby :String,
    @SerializedName("publisher")
    @Expose
    val publisher :String,
    @SerializedName("imageurl")
    @Expose
    val imageurl :String,
    @SerializedName("bio")
    @Expose
    val bio:String){}