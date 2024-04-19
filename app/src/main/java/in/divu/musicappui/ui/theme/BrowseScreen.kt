package `in`.divu.musicappui.ui.theme

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import `in`.divu.musicappui.R


val categories = listOf("Hits", "Happy", "TGIF","Yoga", "Workout")


@Composable
fun Browse(){

    LazyVerticalGrid(GridCells.Fixed(2)){
        items(categories){
                cat->
            BrowserItem(category = cat , drawble = R.drawable.apps)
        }

    }



}






