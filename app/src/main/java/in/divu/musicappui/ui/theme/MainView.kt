package `in`.divu.musicappui.ui.theme

import androidx.annotation.RestrictTo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import `in`.divu.musicappui.MainViewModel
import `in`.divu.musicappui.R
import `in`.divu.musicappui.Screen
import `in`.divu.musicappui.screensInBottom
import `in`.divu.musicappui.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch






@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(){


    val scaffoldState :ScaffoldState = rememberScaffoldState()
    val scope:CoroutineScope = rememberCoroutineScope()
    val viewModel :MainViewModel= viewModel()
    val controller :NavController= rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentroute = navBackStackEntry?.destination?.route
    val isSheetFullScreen by remember{ mutableStateOf(false) }
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    val dialogOpen = remember{ mutableStateOf(false) }



    val currentScreen = remember{
        viewModel.currentScreen.value
    }

    val title = remember { mutableStateOf(currentScreen.title) }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )


    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp



    val bottomBar: @Composable () -> Unit  = {

        if (currentScreen is Screen.DrawerScreen || currentScreen==Screen.BottomScreen.Home){
            BottomNavigation(modifier = Modifier.wrapContentSize()) {
                screensInBottom.forEach(){
                    item ->
                    val isSelected = currentroute==item.broute

                    val tint = if(isSelected) Color.White else Color.Black
                    BottomNavigationItem(selected = currentroute==item.broute ,
                        onClick = { controller.navigate(item.broute)
                            title.value= item.btitle

                                  },
                        icon = {

                            Icon(tint = tint,
                                painter = painterResource(id = item.Bicon)
                             , contentDescription ="" )
                        },

                        label = { Text(text = item.btitle, color =tint )},
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black

                    )

                }


            }
        }


    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
            MoreBottomSheet(modifier = modifier)
        }) {
        Scaffold(
            bottomBar =bottomBar ,



            topBar = {
                TopAppBar(title = { Text(title.value) },
                    actions ={
                             IconButton(
                                 onClick = { scope.launch{
                                     if(modalSheetState.isVisible)
                                         modalSheetState.hide()
                                     else
                                         modalSheetState.show()

                                    }
                                 }
                             ) {
                                 Icon(imageVector = Icons.Default.MoreVert, contentDescription =null )
                             }
                    },
                    navigationIcon = { IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()

                        }

                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle,
                            contentDescription = "")
                    }})

            }
            , scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screensInDrawer){
                            item ->
                        drawerItem(selected = currentroute == item.droute, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if(item.droute == "addaccount"){
                                dialogOpen.value= true
                            }else{
                                controller.navigate(item.droute)
                                title.value = item.dtitle
                            }
                        }
                    }
                }
            }

        ) {
            Navigation(
                navController = controller,
                viewModel = viewModel,
                pd = it
            )

            AccountDialog(dialogOpen = dialogOpen)

        }

    }










}



@Composable
fun MoreBottomSheet(modifier: Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.primarySurface)
    ){
        Column (modifier=Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween){
            Row (modifier=Modifier.padding( 16.dp)){
                Icon(
                    painterResource(id = R.drawable.set),
                    contentDescription = "",
                    modifier=Modifier.padding(end = 8.dp)
                        .clickable {  })
                Text("Settings", fontSize = 20.sp, color = Color.White)
            }
            Row (modifier=Modifier.padding( 16.dp)){
                Icon(painterResource(id = R.drawable.share),
                    contentDescription = "",
                    modifier=Modifier.padding(end = 8.dp)
                        .clickable {  })
                Text("Share", fontSize = 20.sp, color = Color.White)
            }
            Row (modifier=Modifier.padding( 16.dp)){
                Icon(painterResource(id = R.drawable.help),
                    contentDescription = "",
                    modifier=Modifier.padding(end = 8.dp)
                        .clickable {  })
                Text("Help", fontSize = 20.sp, color = Color.White)
            }

        }
    }

}



@Composable
fun drawerItem(
    selected : Boolean,
    item :Screen.DrawerScreen,
    onDrawerItemClicked : ()-> Unit
){
    var background = if (selected) Color.Gray else Color.Transparent
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(background)
            .clickable { onDrawerItemClicked() }
    ) {
        Icon(painter = painterResource(id = item.icon), contentDescription ="" ,
            Modifier.padding(end = 8.dp))
        
        Text(text = item.dtitle,
            style = MaterialTheme.typography.h5 )

    }
}



@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd:PaddingValues){

    NavHost(navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route, modifier = Modifier.padding(pd) ){

        composable(Screen.DrawerScreen.Account.route){
            accountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
            SubscriptionView()
        }

        composable(Screen.BottomScreen.Home.broute){
            Home()
        }
        composable(Screen.BottomScreen.Browse.broute){
            Browse()
        }
        composable(Screen.BottomScreen.Library.broute){
            library ()
        }
    }

}




