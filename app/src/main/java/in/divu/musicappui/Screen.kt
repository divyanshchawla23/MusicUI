package `in`.divu.musicappui

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes

sealed class Screen (val title :String , val route : String){


    sealed class BottomScreen(val btitle :String , val broute : String, @DrawableRes val Bicon : Int)
        :Screen(btitle,broute){

            object Home : BottomScreen("Home" , "home", R.drawable.music )

        object Browse : BottomScreen("Browse" , "browse", R.drawable.apps )

            object Library : BottomScreen("Library" , "library", R.drawable.ic_subscribe )


        }

    sealed class DrawerScreen(val dtitle :String , val droute : String, @DrawableRes val icon : Int)
        :Screen(dtitle,droute){
            object Account : DrawerScreen(
                "Account","account",R.drawable.ic_account
            )
        object Subscription : DrawerScreen(
            "Subscription","subscribe",R.drawable.ic_subscribe
        )
        object AddAccount : DrawerScreen(
            "AddAccount","addaccount",R.drawable.ic_addaccount
        )
        }

}



val screensInBottom= listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library

)


val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount

)