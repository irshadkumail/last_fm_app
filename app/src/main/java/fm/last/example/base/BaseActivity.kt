package fm.last.example.base

import android.icu.text.CaseMap
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colors = MaterialTheme.colors.copy(
                    primary = primaryColor,
                    secondary = primaryDark,
                    background = white1000,
                    surface = white1000,
                )
            ) {
                getScreenView()
            }
        }
    }

    @Composable
    abstract fun getScreenView()
}

@Composable
fun ScreenView(
    modifier: Modifier,
    title: String,
    onBackClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(text = title, style = heading_1b.copy(white1000))
                }
            )
        },
        content = { content() }
    )

}

@Composable
fun SearchScreenView(
    modifier: Modifier,
    onSearchEntered: (query: String) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { SearchToolbar(onSearchEntered) },
        content = { content() }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchToolbar(onSearchEntered: (query: String) -> Unit) {

    val searchText = remember { mutableStateOf("") }

    TopAppBar(
        backgroundColor = blueGrey50
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            value = searchText.value,
            onValueChange = {
                searchText.value = it
            },
            label = {
                Text(text = "Search", color = blueGrey700)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = {
                onSearchEntered(searchText.value)
            }),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = blueGrey700
                )
            },
            trailingIcon = {
                if (searchText.value.isNotEmpty())
                    IconButton(
                        onClick = {
                            searchText.value = ""
                        }, content = {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close",
                                tint = blueGrey700
                            )
                        }
                    )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = blueGrey700,
                focusedIndicatorColor = blueGrey700,
                unfocusedIndicatorColor = blueGrey700,
                cursorColor = blueGrey700
            )
        )
    }
}

