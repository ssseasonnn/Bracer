package zlc.season.bracerapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import zlc.season.bracer.params

class ParamsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {
    private val byteParams by stateHandle.params<Byte>()


}