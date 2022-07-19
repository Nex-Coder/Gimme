package co.uk.nexhub.gimme.ui.elements.parents

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

const val startWeight = 0.3f
const val endWeight = 0.07f

@Composable
fun DefaultScreenWrapper(scrollState: ScrollState = rememberScrollState(),
                         content: @Composable ColumnScope.() -> Unit) {
    val weights: FloatArray = calculateGradientWeights()//scrollState.value, scrollState.maxValue)
    Box(
        Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(
                0f to MaterialTheme.colors.primary.copy(0.3f),
                0.2f to MaterialTheme.colors.primary.copy(0.07f),
                0.5f to MaterialTheme.colors.background.copy(0f),
                0.8f to MaterialTheme.colors.primary.copy(0.07f),
                1f to MaterialTheme.colors.primary.copy(0.3f),
            ))
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(scrollState),
            content = { content(); Spacer(Modifier.height(70.dp)) }
        )
    }
}


fun calculateGradientWeights(/*current: Int, end: Int*/): FloatArray {
    /* TODO
    * 1. optimise upper & lower *multipliers* (made you can piggy back off the calculations of just one and do the math to invert it)
    * 2. add some sort of logic to consider low max scrollableState (;ages with (almost) no scrolling) so pages can have both graidents or
    * 	  somewhere inbetween depending on boolean flag.
    * 3. optimise code for animation logic. use compose/android libraries to accelerate this task.
    * 4. Document and standardise how this will be passed to DefaultScreenWrapper
    */
    val current = 999; val end = 999;

    val gStart = 0.3f; val gEnd = 0.07f;

    val weights = FloatArray(5)

    val upperbase = Math.max(0.0, 1 - (Math.min((current.toDouble() / end), 0.3334) * 3)) // to test half is current = 166.5
    val lowerbase = ((Math.max(0.6666666666666666, current.toDouble() / end)) - 0.6666666666666666) * 3 // to test half is current = 832.5

    // start
    weights[0] = (gStart * upperbase).toFloat() // remove to keep double type accuracy
    weights[1] = (gEnd * upperbase).toFloat()

    // middle
    weights[2] = 1f // whatever middle alpha should be

    // end
    weights[3] = (gEnd * lowerbase).toFloat() // remove to keep double type accuracy
    weights[4] = (gStart * lowerbase).toFloat()

    return weights
    /*println(0)
    println("---")
    println(upperbase)
    println(lowerbase)
    println("---")
    println(weights[0])
    println(weights[1])
    println(weights[2])
    println(weights[3])
    println(weights[4])*/
}