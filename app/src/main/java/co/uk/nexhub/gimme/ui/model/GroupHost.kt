package co.uk.nexhub.gimme.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class GroupHost<T>(unselectedValue: T) {
    internal val selectedState: MutableState<T> = mutableStateOf(unselectedValue)

    fun getSelectedValue(): T {
        return selectedState.value
    }

    fun createValue(value: T): GroupValue<T> {
        return GroupValue(this, value)
    }
}

class GroupValue<T>(private val host: GroupHost<T>, private val value: T) {
    fun setHostValue() {
        host.selectedState.value = value
    }

    @Composable
    fun isSelected(): Boolean {
        return host.selectedState.value == value
    }
}
