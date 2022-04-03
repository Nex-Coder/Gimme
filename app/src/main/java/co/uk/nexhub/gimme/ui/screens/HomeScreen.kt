package co.uk.nexhub.gimme.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.uk.nexhub.gimme.BuildConfig
import co.uk.nexhub.gimme.model.NavigatingStandardData
import co.uk.nexhub.gimme.ui.elements.HorizontalDivider
import co.uk.nexhub.gimme.ui.elements.InviteWidget
import co.uk.nexhub.gimme.ui.elements.SpaceWidget
import co.uk.nexhub.gimme.ui.elements.VerticalDivider
import co.uk.nexhub.gimme.ui.screens.destinations.ParamTestScreenDestination
import com.manueldidonna.wavestimeranimation.WavesLoadingIndicator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigateTo

@Destination
@Composable
fun HomeScreen(navController: NavController) {
    val text by rememberSaveable {
        mutableStateOf("Test")
    }

    //val scrollState = rememberScrollState()
    // Create Icon with Download arrow and Radar Icon overlapping. Maybe add temp button/tool tip saying "Receive Files"
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                Modifier
                    .fillMaxWidth(0.75f)
                    .height(50.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.logo_alt_textless), "Logo alternative text-less")

                Spacer(modifier = Modifier.width(6.dp))

                Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.header), "Logo header")

                val version = BuildConfig.VERSION_NAME
                Column(Modifier.fillMaxHeight(), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Bottom) {
                    Text("  v$version", fontSize = 8.sp)
                }
            }
            Row(Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Spacer(Modifier.width(1.dp))

                Button(
                    modifier = Modifier.clip(RoundedCornerShape(50.dp)).width(50.dp).height(50.dp),
                    onClick = { /*TODO*/ }) { // ToDo add border and that custom icon we thought up
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Settings Icon")
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedButton(colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
            shape = RoundedCornerShape(60),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(200.dp),
            onClick = {
                navController.navigateTo(ParamTestScreenDestination(arg = NavigatingStandardData(args = listOf(text)))) {
                    launchSingleTop = true
                }}) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                WavesLoadingIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primaryVariant,
                    progress = 0.5f
                )
                //Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.logo_alt), "Logo alternative")
                Text("Share Files", fontSize = 36.sp, color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        val rbColors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
        val rbBorder = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant)
        val rbShape = RoundedCornerShape(60)
        val rbContentPadding = PaddingValues(0.dp)
        val rbModifier = Modifier
            .width(180.dp)
            .height(90.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OutlinedButton(colors = rbColors,
                border = rbBorder,
                shape = rbShape,
                contentPadding = rbContentPadding,
                modifier = rbModifier,
                onClick = {
                    navController.navigateTo(ParamTestScreenDestination(arg = NavigatingStandardData(args = listOf(text)))) {
                        launchSingleTop = true
                    }}) {
                Text("Advanced Share (Coming in v0.3.0)", fontSize = 16.sp, color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f), textAlign = TextAlign.Center)
            }

            OutlinedButton(colors = rbColors,
                border = rbBorder,
                shape = rbShape,
                contentPadding = rbContentPadding,
                modifier = rbModifier,
                onClick = {
                    navController.navigateTo(ParamTestScreenDestination(arg = NavigatingStandardData(args = listOf(text)))) {
                        launchSingleTop = true
                    }}) {
                Text("Preset Share (Coming in v0.3.0)", fontSize = 16.sp, color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f), textAlign = TextAlign.Center)
            }
        }

        VerticalDivider(15.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val columnModifier = Modifier
                .width(180.dp)// Equal size columns
                .fillMaxHeight()

            Column(columnModifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween) {
                val leftWidgetMod = Modifier
                    .fillMaxWidth(1f)
                    .height(130.dp)
                SpaceWidget(
                    leftWidgetMod)
                InviteWidget(
                    leftWidgetMod)
            }

            HorizontalDivider()

            //Spacer(Modifier.fillMaxWidth())
            Column(modifier = columnModifier,
            ) {
                Box(
                    Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
//                        .background(Color(0xFFED872D))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Alignment Center",
                        style = MaterialTheme.typography.h6,
                        fontSize = 24.sp
                    )
                }
            }
        }
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Credits: Nex-Coder / NexRX")
            Button(
                modifier = Modifier.clip(RoundedCornerShape(100.dp)),
                onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings Icon")
            }
        }
    }
}

/*Button(onClick = {
            navController.navigateTo(ParamTestScreenDestination(arg = NavigatingStandardData(args = listOf(text)))) {
                launchSingleTop = true
            }}) {}*/