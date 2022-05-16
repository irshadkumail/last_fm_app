package fm.last.example.presentation

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint
import fm.last.example.R
import fm.last.example.base.*
import fm.last.example.domain.AlbumListItem

@AndroidEntryPoint
class AlbumListActivity : BaseActivity() {
    @Composable
    override fun getScreenView() {
        AlbumListScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AlbumListScreen() {
    val viewModel = hiltViewModel<AlbumListViewModel>()

    fun LazyListState.isScrolledToEnd() =
        layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

    val scrollState = rememberLazyListState()

    val endOfListReached by remember {
        derivedStateOf { scrollState.isScrolledToEnd() }
    }

    LaunchedEffect(key1 = endOfListReached) {
        if (endOfListReached)
            viewModel.loadMore()
    }


    SearchScreenView(
        modifier = Modifier.background(blueGrey50),
        onSearchEntered = viewModel::onSearchQueryEntered
    ) {
        when {
            viewModel.searchText.value.isEmpty() ->
                StartSearchView()
            viewModel.isLoading.value ->
                CircularProgressIndicator()
            viewModel.albums.isEmpty() -> {
                EmptySearchResults()
            }
            else -> LazyColumn(
                state = scrollState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.albums) {
                    AlbumItemView(
                        modifier = Modifier.animateItemPlacement(),
                        album = it
                    )
                }
            }

        }

    }
}

@Composable
private fun AlbumItemView(
    modifier: Modifier,
    album: AlbumListItem
) {
    val context = LocalContext.current
    Card(
        backgroundColor = white1000,
        shape = RoundedCornerShape(5),
        elevation = 2.dp,
        modifier = modifier
            .padding(MaterialTheme.dimens.paddingExtraSmall)
            .clickable {
                if (album.id.isNullOrEmpty()) {
                    Toast
                        .makeText(context, "No data found for selected Album", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    AlbumDetailActivity.start(context = context, album.id)
                }
            }
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.dimens.paddingExtraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoilImage(
                imageModel = album.image,
                modifier = Modifier.size(MaterialTheme.dimens.imageMedium),
                contentScale = ContentScale.Crop,
                placeHolder = ImageBitmap.imageResource(R.drawable.music_album),
                error = ImageBitmap.imageResource(R.drawable.music_album)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.paddingMedium))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = album.name, style = heading_1b)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = album.artistName, style = heading_3b)
            }
        }
    }
}

@Composable
private fun EmptySearchResults() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_list),
            contentDescription = "Empty List"
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.paddingMedium))
        Text(text = "No results for the search", style = heading_1b)

    }
}


@Composable
private fun StartSearchView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.search_icon),
            contentDescription = "Empty List"
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.paddingMedium))
        Text(text = "Enter Query to start searching", style = heading_1b)

    }
}