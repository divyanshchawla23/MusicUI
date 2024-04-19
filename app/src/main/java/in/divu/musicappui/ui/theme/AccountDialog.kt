package `in`.divu.musicappui.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.primarySurface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import `in`.divu.musicappui.R


@Composable
fun AccountDialog(dialogOpen: MutableState<Boolean>) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen.value = false
            },
            confirmButton = {
                TextButton(onClick = { dialogOpen.value = false }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { dialogOpen.value = false }) {
                    Text("Dismiss")
                }
            },
            title = {
                Text("Add Account")
            },
            text = {
                Column(modifier = Modifier.wrapContentHeight().padding(top = 16.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.padding(top = 16.dp),
                        label = { Text("Email") }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier.weight(1f).padding(top = 16.dp),
                            label = { Text("Password") },
                            visualTransformation = if (passwordVisibility) VisualTransformation.None
                            else PasswordVisualTransformation(),
                        )

                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility },
                            modifier = Modifier.padding(start = 4.dp)
                        ) {
                            if (passwordVisibility) {
                                Icon(
                                    painter = painterResource(id = R.drawable.eye),
                                    contentDescription = "Hide Password",
                                    modifier = Modifier.fillMaxSize().padding(8.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.noeye),
                                    contentDescription = "Hide Password",
                                    modifier = Modifier.fillMaxSize().padding(8.dp)
                                )
                            }
                        }
                    }
                }
            },
            modifier = Modifier.border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp)) // Add border around AlertDialog
        )
    }
}



