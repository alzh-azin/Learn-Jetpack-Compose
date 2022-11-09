# State in Jetpack Compose

### Events in Compose

We talked about state as any value that changes over time, for example, the last messages received in a chat app. But what causes the state to update? In Android apps, state is updated in response to events.

Events are inputs generated from outside or inside an application, such as:

- The user interacting with the UI by, for example, pressing a button.
- Other factors, such as sensors sending a new value, or network responses.

**While the state of the app offers a description of what to display in the UI, events are the mechanism through which the state changes, resulting in changes to the UI.**

> **Key idea:** State *is*. Events *happen*.

Events notify a part of a program that something has happened. In all Android apps, there's a core UI update loop that goes like this:

[<img src="resources\1.PNG"/>](resources\1.PNG)

- Event - An event is generated by the user or another part of the program.
- Update State - An event handler changes the state that is used by the UI.
- Display State - The UI is updated to display the new state.

### Memory in Composable Function

Compose apps transform data into UI by calling composable functions. We refer to ***the Composition*** as the description of the UI built by Compose when it executes composables. If a state change happens, Compose re-executes the affected composable functions with the new state, creating an updated UI—this is called ***recomposition***. Compose also looks at what data an individual composable needs, so that it only recomposes components whose data has changed and skips those that are not affected.

> **The Composition:** a description of the UI built by Jetpack Compose when it executes composables.
> 
> **Initial composition:** creation of a Composition by running composables the first time.
> 
> **Recomposition:** re-running composables to update the Composition when data changes.

**Compose has a special state tracking system in place that schedules recompositions for any composables that read a particular state**.

This lets Compose be granular and just recompose those composable functions that need to change, not the whole UI.

Use Compose's [`State`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State) and [`MutableState`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) types to make state observable by Compose.

Compose keeps track of each composable that reads State `value` properties and triggers a recomposition when its `value` changes.

> You can think of using **`remember`** as a mechanism to store a single object in the Composition, in the same way a private val property does in an object.

### State Driven UI

Compose is a declarative UI framework. Instead of removing UI components or changing their visibility when state changes, we describe how the UI *is* under specific conditions of state. As a result of a recomposition being called and UI updated, composables might end up entering or leaving the Composition.

[<img src="resources\2.PNG"/>](resources\2.PNG)

### Remember in Composition

[`remember`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#remember(kotlin.Function0)) stores objects in the Composition, and forgets the object if the source location where `remember` is called is not invoked again during a recomposition.

### Restore State in Compose

While `remember` helps you retain state across recompositions, it's **not retained across configuration changes**. For this, you must use [`rememberSaveable`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/package-summary#rememberSaveable(kotlin.Array,androidx.compose.runtime.saveable.Saver,kotlin.String,kotlin.Function0)) instead of `remember`

`rememberSaveable` automatically saves any value that can be saved in a [`Bundle`](https://developer.android.com/reference/android/os/Bundle). For other values, you can pass in a custom saver object. For more information on [Restoring state in Compose](https://developer.android.com/jetpack/compose/state#restore-ui-state), check out the documentation.

> Use **`rememberSaveable`** to restore your UI state after an Activity or process is recreated. Besides retaining state across recompositions, **`rememberSaveable`** also retains state across Activity and process recreation.

Consider whether to use `remember` or `rememberSaveable` depending on your app's state and UX needs.
