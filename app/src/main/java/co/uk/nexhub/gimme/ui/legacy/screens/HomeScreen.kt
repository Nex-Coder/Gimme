package co.uk.nexhub.gimme.ui.legacy.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.uk.nexhub.gimme.BuildConfig
import co.uk.nexhub.gimme.ui.elements.VerticalDivider
import com.manueldidonna.wavestimeranimation.WavesLoadingIndicator
import com.ramcosta.composedestinations.annotation.Destination

//@Destination
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
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(id = co.uk.nexhub.gimme.R.drawable.logo_alt_textless),
                    "Logo alternative text-less"
                )

                Spacer(modifier = Modifier.width(6.dp))

                Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.header), "Logo header")

                val version = BuildConfig.VERSION_NAME
                Column(
                    Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text("  v$version", fontSize = 8.sp)
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
            onClick = { }) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                WavesLoadingIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primaryVariant,
                    progress = 0.5f
                )
                //Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.logo_alt), "Logo alternative")
                Text(
                    "Share Files",
                    fontSize = 36.sp,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
                )
            }
        }

        VerticalDivider(15.dp)

    }
}

/*Button(onClick = {
            navController.navigateTo(ParamTestScreenDestination(arg = NavigatingStandardData(args = listOf(text)))) {
                launchSingleTop = true
            }}) {}*/