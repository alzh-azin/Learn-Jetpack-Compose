# Write your first Compose app

- To preserve state across recompositions, *remember* the mutable state using `remember`.
  
- `remember` is used to ***guard*** against recomposition, so the state is not reset.
  
- Note that if you call the same composable from different parts of the screen you will create different UI elements, each with its own version of the state. **You can think of internal state as a private variable in a class.**
  
- The composable function will automatically be "subscribed" to the state. If the state changes, composables that read these fields will be recomposed to display the updates.
  

### State Hoisting

In Composable functions, state that is read or modified by multiple functions should live in a common ancestor—this process is called **state hoisting**. To *hoist* means to *lift* or *elevate*.

Making state hoistable avoids duplicating state and introducing bugs, helps reuse composables, and makes composables substantially easier to test. Contrarily, state that doesn't need to be controlled by a composable's parent should not be hoisted. The **source of truth** belongs to whoever creates and controls that state.

In Compose **you don't hide UI elements**. Instead, you simply don't add them to the composition, so they're not added to the UI tree that Compose generates.

### Creating a performant lazy list

To display a scrollable column we use a `LazyColumn`. `LazyColumn` renders only the visible items on screen, allowing performance gains when rendering a big list.

### Persisting State

Our app has a problem: if you run the app on a device, click on the buttons and then you rotate, the onboarding screen is shown again. The `remember` function works **only as long as the composable is kept in the Composition**. When you rotate, the whole activity is restarted so all state is lost. This also happens with any configuration change and on process death.

Instead of using `remember` you can use `rememberSaveable`. This will save each state surviving configuration changes (such as rotations) and process death.

### Styling and theming you app

`MaterialTheme` is a composable function that reflects the styling principles from the [Material design specification](https://m3.material.io/). That styling information cascades down to the components that are inside its `content`, which may read the information to style themselves. In your UI, you are already using `BasicsCodelabTheme` as follows:

In general it's much better to keep your colors, shapes and font styles inside a `MaterialTheme`. For example, dark mode would be hard to implement if you hard-code colors and it would require a lot of error-prone work to fix.

However sometimes you need to deviate slightly from the selection of colors and font styles. In those situations it's better to base your color or style on an existing one.

For this, you can modify a predefined style by using the `copy` function. Make the number extra bold:

```kotlin
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
```
