package com.example.starwarsplanets.features.planets.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.starwarsplanets.features.planets.PlanetsViewModel
import com.example.starwarsplanets.navigation.Route
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope

@Composable
fun PlanetsScreen(
    navController: NavHostController,
    viewModel: PlanetsViewModel = hiltViewModel(),
    stateHolder: PlanetsStateHolder = PlanetsStateHolder.rememberStateHolder(viewModel = viewModel)
) {

    val items = viewModel.getAllPlanets.collectAsLazyPagingItems()

    val isRefreshing by remember { derivedStateOf { items.loadState.refresh is LoadState.Loading } }
    val isError by remember { derivedStateOf { items.loadState.refresh is LoadState.Error } }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { items.refresh() },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true,
                backgroundColor = DarkGray,
                contentColor = White
            )
        }
    ) {
        when {
            isError -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Ich hab da ein ganz mieses Gefuehl.")
                }
            }
            !isError -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    items(
                        items = items,
                        key = { planet ->
                            planet.id
                        }
                    ) { planet ->
                        if (planet != null) {
                            Card(
                                shape = MaterialTheme.shapes.small,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clickable {
                                        navController.navigate(Route.Planet.passId(planet.id))
                                    },
                                backgroundColor = MaterialTheme.colors.surface,
                                elevation = 5.dp
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(50.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(planet.name.orEmpty())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

class PlanetsStateHolder(
    private val viewModel: PlanetsViewModel,
    private val scope: CoroutineScope
) {
//    val items
//        @Composable get() = viewModel.getAllPlanets.collectAsLazyPagingItems()
//
//    val isRefreshing
//        @Composable get() = derivedStateOf { items.loadState.refresh is LoadState.Loading }
//
//    val isError by derivedStateOf { items.loadState.refresh is LoadState.Error }
//
//    val swipeRefreshState
//        @Composable get() = rememberSwipeRefreshState(isRefreshing.value)

    companion object {
        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun rememberStateHolder(
            viewModel: PlanetsViewModel,
            scope: CoroutineScope = rememberCoroutineScope(),
        ) = remember {
            PlanetsStateHolder(
                viewModel = viewModel,
                scope = scope
            )
        }
    }
}