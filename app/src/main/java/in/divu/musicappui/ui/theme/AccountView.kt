package `in`.divu.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import `in`.divu.musicappui.R

@Composable
fun accountView(){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Row(){
                Icon(imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp))

                Column {
                    Text("Divyansh Chawla")
                    Text("@divyanshchawla")
                }
            }
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "")
                
            }
        }

        Row(modifier = Modifier.padding(top = 16.dp)){
            Icon(painter = painterResource(id = R.drawable.music),
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("My Music")

        }

        Divider()
    }


}

