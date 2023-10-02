package nondas.pap.petcareapp.presentation.medicine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(

): ViewModel() {


    var state by mutableStateOf(MedicineState())



    fun onEvent(event: MedicineEvent) {
        when(event) {

            is MedicineEvent.TypeSelected -> TODO()

            is MedicineEvent.FrequencySelected -> TODO()

            is MedicineEvent.CommentsEntered -> TODO()



            is MedicineEvent.AddMedicine -> TODO()

            is MedicineEvent.DeleteButtonClicked -> TODO()
            is MedicineEvent.EditButtonClicked -> TODO()
            is MedicineEvent.EditMedicine -> TODO()


        }
    }

}