# Basic layouts in Compose

In general, to align composables inside a parent container, you set the **alignment** of that parent container. So instead of telling the child to position itself in its parent, you tell the parent how to align its children.

For a `Column`, you decide how its children should be aligned horizontally. The options are:

- Start
- CenterHorizontally
- End

For a `Row`, you set the vertical alignment. The options are similar to those of the `Column`:

- Top
- CenterVertically
- Bottom

For a `Box`, you combine both horizontal and vertical alignment. The options are:

- TopStart
- TopCenter
- TopEnd
- CenterStart
- Center
- CenterEnd
- BottomStart
- BottomCenter
- BottomEnd

All of the container's children will follow this same alignment pattern. You can override the behavior of a single child by adding an [`align`](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/ColumnScope#(androidx.compose.ui.Modifier).align(androidx.compose.ui.Alignment.Horizontal)) modifier to it.

For a `Row`, you can choose the following arrangements:

![](readme%20resources\1.PNG)
[<img src="readme%20resources\1.PNG"/>](readme%20resources\1.PNG)

And for a `Column`:

![](readme%20resources\2.PNG)

In addition to these arrangements, you can also use the `Arrangement.spacedBy()` method to add a fixed space in between each child composable.2

- To maintain the same padding, but still scroll your content within the bounds of your parent list without clipping it, all lists provide a parameter called `contentPadding`.

## Slot-based layouts

Material components make heavy use of *slot APIs*, a pattern Compose introduces to bring in a layer of customization on top of composables. This approach makes components more flexible, as they accept a child element which can configure itself rather than having to expose every configuration parameter of the child. Slots leave an empty space in the UI for the developer to fill as they wish. For example, these are the slots that you can customize in a [`TopAppBar`](https://material.io/components/app-bars-top):

Composables usually take a `content` composable lambda ( `content: @Composable () -> Unit`). Slot APIs expose multiple `content` parameters for specific uses. For example, `TopAppBar` allows you to provide the content for `title`, `navigationIcon`, and `actions`.

For example, [`Scaffold`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Scaffold(androidx.compose.ui.Modifier,androidx.compose.material.ScaffoldState,kotlin.Function0,kotlin.Function0,kotlin.Function1,kotlin.Function0,androidx.compose.material.FabPosition,kotlin.Boolean,kotlin.Function1,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)) allows you to implement a UI with the basic Material Design layout structure. `Scaffold`provides slots for the most common top-level Material components, such as [`TopAppBar`](https://material.io/components/app-bars-top), [`BottomAppBar`](https://material.io/components/app-bars-bottom/), [`FloatingActionButton`](https://material.io/components/buttons-floating-action-button/), and [`Drawer`](https://material.io/components/navigation-drawer/). By using `Scaffold`, it's easy to make sure these components are properly positioned and work together correctly.

```kotlin
@Composable
fun HomeScreen(/*...*/) {
    Scaffold(
        drawerContent = { /*...*/ },
        topBar = { /*...*/ },
        content = { /*...*/ }
    )
}
```

### Scrolling

In general, **you use a Lazy layout when you have many elements in a list or large data sets to load**, so emitting all items at once would come at a performance cost and would slow down your app. When a list has only a limited number of elements, you can instead choose to use a simple `Column` or `Row` and **add the scroll behavior manually**.
