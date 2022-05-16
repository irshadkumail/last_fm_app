package fm.last.example.presentation

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.skydoves.landscapist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint
import fm.last.example.R
import fm.last.example.base.*
import fm.last.example.domain.Album

@AndroidEntryPoint
class AlbumDetailActivity : BaseActivity() {

    companion object {

        const val ALBUM_ID = "album_id"

        fun start(context: Context, id: String) {
            context.startActivity(Intent(context, AlbumDetailActivity::class.java).apply {
                putExtra(ALBUM_ID, id)
            })
        }
    }

    private val viewModel by viewModels<AlbumDetailViewModel>()

    @Composable
    override fun getScreenView() {
        val id = intent.getStringExtra(ALBUM_ID) ?: ""
        viewModel.init(id)

        AlbumDetailScreen(
            onBackPressed = {
                onBackPressed()
            },
            albumInfo = viewModel.albumInfo
        )
    }
}

@Composable
fun AlbumDetailScreen(onBackPressed: () -> Unit, albumInfo: State<Album>) {
    ScreenView(
        modifier = Modifier,
        title = albumInfo.value.title,
        onBackClicked = onBackPressed
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.paddingSmall)
                .verticalScroll(rememberScrollState()),
        ) {
            CoilImage(
                imageModel = albumInfo.value.imageUrl,
                modifier = Modifier
                    .size(
                        height = MaterialTheme.dimens.imageExtraExtraLarge,
                        width = MaterialTheme.dimens.imageExtraLarge
                    )
                    .align(CenterHorizontally),
                contentScale = ContentScale.Crop,
                placeHolder = ImageBitmap.imageResource(R.drawable.music_album),
                error = ImageBitmap.imageResource(R.drawable.music_album)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.paddingSmall))
            Text(text = albumInfo.value.artist, style = heading_2a.copy(blueGrey700))
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.paddingSmall))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Total listeners: ${albumInfo.value.listeners}",
                    style = heading_3b.copy(blueGrey500)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Playcount: ${albumInfo.value.playcount}",
                    style = heading_3b.copy(blueGrey500)
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.paddingSmall))
            Text(text = albumInfo.value.content, style = heading_3b.copy(blueGrey500))

        }
    }
}

