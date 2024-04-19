package `in`.divu.musicappui.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun library (){
    LazyColumn(){
        items(libraries){
            lib ->
            libItem(lib = lib)
        }
    }
}




@Composable
fun libItem(lib : lib ){

    Column {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Row {
                Image(painter = painterResource(id = lib.icon), contentDescription = "")
                Text(text =lib.name )
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")



        }
        Divider(color = Color.LightGray)
    }

}