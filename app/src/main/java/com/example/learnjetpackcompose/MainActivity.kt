package com.example.learnjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnjetpackcompose.ui.theme.LearnJetpackComposeTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnJetpackComposeTheme {
                MySootheApp()
            }
        }
    }
}

@Composable
fun MySootheApp() {
    LearnJetpackComposeTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )

    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(modifier) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}


@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(120.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = alignYourBodyData) { item ->
            AlignYourBodyElement(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {

    Surface(shape = MaterialTheme.shapes.small) {
        Row(
            modifier = modifier.size(192.dp, 56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = modifier.width(56.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.body2,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .size(88.dp)
        )
        Spacer(modifier.height(8.dp))
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                56.dp
            ),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
    LearnJetpackComposeTheme {
        AlignYourBodyElement(drawable = R.drawable.ab1_inversions, text = R.string.ab1_inversions)
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    LearnJetpackComposeTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    LearnJetpackComposeTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
    LearnJetpackComposeTheme {
        FavoriteCollectionCard(
            text = R.string.fc2_nature_meditations,
            drawable = R.drawable.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionsGridPreview() {
    LearnJetpackComposeTheme() {
        FavoriteCollectionsGrid()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LearnJetpackComposeTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SootheBottomNavigationPreview(){
    LearnJetpackComposeTheme {
        SootheBottomNavigation()
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)