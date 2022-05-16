package fm.last.example.base

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.dp

object Dimens {
    val paddingExtraSmall = 8.dp
    val paddingSmall = 12.dp
    val paddingMedium = 14.dp
    val paddingLarge = 16.dp

    val imageMedium = 36.dp
    val imageLarge = 56.dp
    val imageExtraLarge = 200.dp
    val imageExtraExtraLarge = 300.dp

}


val MaterialTheme.dimens: Dimens
    get() = Dimens
