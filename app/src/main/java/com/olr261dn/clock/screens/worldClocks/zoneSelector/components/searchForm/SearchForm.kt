package com.olr261dn.clock.screens.worldClocks.zoneSelector.components.searchForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.olr261dn.clock.components.textField.CreateSearchField

@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    hint: String = "Search",
    onSearch: (String) -> Unit = {})
{
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty() }

    Column {
        CreateSearchField(
            modifier = modifier,
            valueState = searchQueryState.value,
            labelId = hint,
            enabled = true,
            onValueChange = {
                searchQueryState.value = it ?: ""
                onSearch(searchQueryState.value)
            },
            onAction = KeyboardActions{
                if (!valid) return@KeyboardActions
                onSearchAction(
                    searchQueryState = searchQueryState,
                    keyboardController = keyboardController){ onSearch(it) }
            }
        )
    }
}


private fun onSearchAction(
    searchQueryState: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    onSearch: (String) -> Unit)
{
    onSearch(searchQueryState.value.trim())
    searchQueryState.value = ""
    keyboardController?.hide()
}

