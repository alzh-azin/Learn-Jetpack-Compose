# Jetpack Compose Theming

A Material Theme comprises [color](https://material.io/design/color/), [typography](https://material.io/design/typography/) and [shape](https://material.io/design/shape/) attributes. Customizing these will be automatically reflected in the components you use to build your app.

##### Color

Primary is your main brand color and secondary is used to provide accents.

 Background and surface colors are used for containers holding components which notionally live on a "surface" in your application.

Material also defines "on" colors—colors to use for content on top of one of the named colors e.g. text in a ‘surface' colored container should be colored ‘on surface'

[<img src="https://github.com/alzh-azin/Learn-Jetpack-Compose/blob/13-compose_theming/resource/1.png"/>](https://github.com/alzh-azin/Learn-Jetpack-Compose/blob/13-compose_theming/resource/1.png)

##### Typography

[<img src="resource\2.PNG"/>](resource\2.PNG)

The Material [type scale generator tool](https://material.io/design/typography/the-type-system.html#type-scale) can help you to build your type scale.

In Compose we can define [`TextStyle`](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/TextStyle) objects to define the information needed to style some text. A sample of its attributes:

```kotlin
data class TextStyle(
    val color: Color = Color.Unset,
    val fontSize: TextUnit = TextUnit.Inherit,
    val fontWeight: FontWeight? = null,
    val fontStyle: FontStyle? = null,
    val fontFamily: FontFamily? = null,
    val letterSpacing: TextUnit = TextUnit.Inherit,
    val background: Color = Color.Unset,
    val textAlign: TextAlign? = null,
    val textDirection: TextDirection? = null,
    val lineHeight: TextUnit = TextUnit.Inherit,
    ...
)
```

##### Shape

[<img src="resource\3.PNG"/>](resource\3.PNG)

Customizing your shape theme will be reflected across numerous components e.g. [Buttons](https://material.io/components/buttons) & [Text Fields](https://material.io/components/text-fields) use the small shape theme, [Cards](https://material.io/components/cards) and [Dialogs](https://material.io/components/dialogs) use medium and [Sheets](https://material.io/components/sheets-bottom) use the large shape theme by default. There is a complete mapping of components to shape themes [here](https://material.io/design/shape/applying-shape-to-ui.html#shape-scheme). The Material [shape customization tool](https://material.io/design/shape/about-shape.html#shape-customization-tool) can help you generate a shape theme.

Compose offers [`RoundedCornerShape`](https://developer.android.com/reference/kotlin/androidx/compose/foundation/shape/RoundedCornerShape) and [`CutCornerShape`](https://developer.android.com/reference/kotlin/androidx/compose/foundation/shape/CutCornerShape) classes which you can use to define your shape theme.



### Define Your Theme

> **Note:** When defining colors, we name them "literally", based on the color value, rather than "semantically" e.g. `Red500` not `primary`. This enables us to define multiple themes e.g. another color might be considered `primary` in dark theme or on a differently styled screen.

### Working With Color

##### Raw Colors

As we saw earlier, compose offers a `Color` class. You can create these locally, hold them in an `object` etc:

```kotlin
Surface(color = Color.LightGray) {
  Text(
    text = "Hard coded colors don't respond to theme changes :(",
    textColor = Color(0xffff00ff)
  )
}
```

> **Note:** Be careful when statically declaring color definitions as they make it harder/impossible to support different themes like light/dark theming. Material guidelines do however call out some situations where the same colors should be used across light/dark themes such as critical [brand colors](https://material.io/design/color/dark-theme.html#ui-application).

`Color` has a number of useful methods on it such as `copy` allowing you to create a new color with different alpha/red/green/blue values.

##### Theme Colors

A more flexible approach is to retrieve colors from your theme:

```kotlin
Surface(color = MaterialTheme.colors.primary)
```

Here we're using the `MaterialTheme` `object` whose `colors` property returns the `Colors` set in the `MaterialTheme` composable. That means we can support different look-and-feels just by supplying different sets of colors to our theme, we don't need to touch application code. For example our `AppBar` uses `primary` color and the screen background is `surface`; changing the theme colors is reflected in these composables:

As each color in our theme are `Color` instances, we can also easily *derive* colors using the `copy` method:

```kotlin
val derivedColor = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
```

Here we're making a copy of the `onSurface` color but with 10% opacity. This approach ensures that colors work under different themes, rather than hard-coding static colors.

##### Surface & Content Color

```kotlin
Surface(
  color: Color = MaterialTheme.colors.surface,
  contentColor: Color = contentColorFor(color),
  ...

TopAppBar(
  backgroundColor: Color = MaterialTheme.colors.primarySurface,
  contentColor: Color = contentColorFor(backgroundColor),
  ...
```

This enables you to not only set the color of a composable, but to provide a default color for the "content" i.e. composables within it. Many composables use this content color by default e.g. `Text` color or `Icon` tint. The [`contentColorFor`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#contentColorFor(androidx.compose.ui.graphics.Color)) method retrieves the appropriate "on" color for any theme colors e.g. if you set a `primary` background, it will return `onPrimary` as the content color. If you set a non-theme background color then you should provide a sensible content color yourself.

```kotlin
Surface(color = MaterialTheme.colors.primary) {
  Text(...) // default text color is 'onPrimary'
}
Surface(color = MaterialTheme.colors.error) {
  Icon(...) // default tint is 'onError'
}
```

You can use the [`LocalContentColor`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#LocalContentColor()) [`CompositionLocal`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/CompositionLocal) to retrieve the color which contrasts with the current background:

```kotlin
BottomNavigationItem(
  unselectedContentColor = LocalContentColor.current ...
```

When setting the color of any elements, prefer using a [`Surface`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Surface(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.foundation.BorderStroke,androidx.compose.ui.unit.Dp,kotlin.Function0)) to do this as it sets an appropriate content color [`CompositionLocal`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/CompositionLocal) value, be wary of direct [`Modifier.background`](https://developer.android.com/reference/kotlin/androidx/compose/foundation/package-summary#background(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Shape)) calls which do **not** set an appropriate content color.

```kotlin
-Row(Modifier.background(MaterialTheme.colors.primary)) {
+Surface(color = MaterialTheme.colors.primary) {
+  Row(
...
```

##### Content Alpha

Jetpack Compose implements this via [`LocalContentAlpha`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#LocalContentAlpha()). You can specify a content alpha for a hierarchy by [providing](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#CompositionLocalProvider(kotlin.Array,kotlin.Function0)) a value for this [`CompositionLocal`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/CompositionLocal). Child composables can use this value, for example [`Text`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Text(kotlin.String,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.font.FontStyle,androidx.compose.ui.text.font.FontWeight,androidx.compose.ui.text.font.FontFamily,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.style.TextDecoration,androidx.compose.ui.text.style.TextAlign,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.style.TextOverflow,kotlin.Boolean,kotlin.Int,kotlin.Function1,androidx.compose.ui.text.TextStyle)) and [`Icon`](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Icon(androidx.compose.ui.graphics.vector.ImageVector,kotlin.String,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color)) by default use the combination of `LocalContentColor` adjusted to use `LocalContentAlpha`. Material specifies some standard alpha values ( [`high`](https://developer.android.com/reference/kotlin/androidx/compose/material/ContentAlpha#high()), [`medium`](https://developer.android.com/reference/kotlin/androidx/compose/material/ContentAlpha#medium()), [`disabled`](https://developer.android.com/reference/kotlin/androidx/compose/material/ContentAlpha#disabled())) which are modelled by the [`ContentAlpha`](https://developer.android.com/reference/kotlin/androidx/compose/material/ContentAlpha) object. Note that `MaterialTheme` defaults `LocalContentAlpha` to `ContentAlpha.high`.

```kotlin
// By default, both Icon & Text use the combination of LocalContentColor &
// LocalContentAlpha. De-emphasize content by setting a different content alpha
CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
    Text(...)
}
CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
    Icon(...)
    Text(...)
}
```

##### Dark Theme

```kotlin
val isLightTheme = MaterialTheme.colors.isLight
```

In material, in dark themes, surfaces with higher elevations receive [elevation overlays](https://material.io/design/color/dark-theme.html#properties) (their background is lightened). This is implemented automatically when using a dark color palette:

```kotlin
Surface(
  elevation = 2.dp,
  color = MaterialTheme.colors.surface, // color will be adjusted for elevation
  ...
```

We can see this automatic behaviour in our app in both the `TopAppBar` and the `Card` components that we are using; they have 4dp and 1dp elevations by default, so their backgrounds are lightened automatically in dark theme to better communicate this elevation

Material design [suggests](https://material.io/design/color/dark-theme.html#custom-application) avoiding large areas of bright colors in dark theme. A common pattern is to color a container `primary` color in light theme and `surface` color in dark themes; many components use this strategy by default e.g. [App Bars](https://material.io/components/app-bars-top) and [Bottom Navigation](https://material.io/components/bottom-navigation). To make this easier to implement, `Colors` offers a `primarySurface` color which provides exactly this behaviour and these components use by default.

Our app currently sets the App Bar to `primary` color, we can follow this guidance by either switching it to `primarySurface` or just removing this parameter as it's the default. In the `AppBar` composable, change the `TopAppBar`'s `backgroundColor` parameter:

```kotlin
@Composable
private fun AppBar() {
  TopAppBar(
    ...
-   backgroundColor = MaterialTheme.colors.primary
+   backgroundColor = MaterialTheme.colors.primarySurface
  )
}
```

### Theme Text Style

Just like with colors, it's best to retrieve `TextStyle`s from the current theme, encouraging you to use a small, consistent set of styles and making them more maintainable. `MaterialTheme.typography` retrieves the `Typography` instance set in your `MaterialTheme` composable, enabling you to use the styles you defined:

```kotlin
Text(
  style = MaterialTheme.typography.subtitle2
)
```

If you need to customize a `TextStyle`, you can either `copy` it and override properties (it's just a `data class`) or the `Text` composable accepts a number of styling parameters which will be overlaid on top of any `TextStyle`:

```kotlin
Text(
  text = "Hello World",
  style = MaterialTheme.typography.body1.copy(
    background = MaterialTheme.colors.secondary
  )
)
```

##### Multiple Styles

If you need to apply multiple styles to some text, then you can use the [`AnnotatedString`](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/AnnotatedString.html) class to apply markup, adding [`SpanStyle`](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/SpanStyle)s to a range of text. You can either add these dynamically or use the DSL syntax to create content:

```kotlin
val text = buildAnnotatedString {
  append("This is some unstyled text\n")
  withStyle(SpanStyle(color = Color.Red)) {
    append("Red text\n")
  }
  withStyle(SpanStyle(fontSize = 24.sp)) {
    append("Large text")
  }
}
```

### Working With Shapes


