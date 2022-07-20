package com.velvet.tarot.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.velvet.tarot.R

@Composable
fun SearchBar(
    state: SearchBarState
) {
    Box(state.modifier) {
        OutlinedTextField(
            value = state.searchText,
            onValueChange = state.onChangedSearchText,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.error,
                unfocusedBorderColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.error
            ),
            label = {
                Text(
                    text = stringResource(R.string.search),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.appTypography.caption
                )
            }
        )
    }
}

//TODO mutlipreview when 1.2.0 compose will be released


@Preview(name = "dark theme", group = "themes", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun searchBarPreviewDarkTheme() {
    Surface(color = MaterialTheme.colors.surface) {
        SearchBar(state = SearchBarState(searchText = "Test text"))
    }
}

@Preview(name = "light theme", group = "themes", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun searchBarPreviewLight() {
    Surface(color = MaterialTheme.colors.surface) {
        SearchBar(state = SearchBarState(searchText = "Test text"))
    }
}