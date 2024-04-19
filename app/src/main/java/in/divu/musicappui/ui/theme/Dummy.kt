package `in`.divu.musicappui.ui.theme

import androidx.annotation.DrawableRes
import `in`.divu.musicappui.R

data class lib(@DrawableRes val icon : Int , val name : String)


val libraries = listOf<lib>(
    lib (R.drawable.playlist,"Playlist"),
    lib (R.drawable.mic,"Artists"),
    lib (R.drawable.album,"Album"),
    lib (R.drawable.genre,"Genre"),
    lib (R.drawable.music,"Songs")

)