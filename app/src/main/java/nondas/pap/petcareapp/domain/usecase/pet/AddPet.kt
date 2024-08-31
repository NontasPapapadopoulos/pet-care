package nondas.pap.petcareapp.domain.usecase.pet

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import nondas.pap.inventoryapp.domain.SuspendUseCase
import nondas.pap.petcareapp.domain.repository.PetRepository
import nondas.pap.petcareapp.domain.executor.IoDispatcher
import nondas.pap.petcareapp.domain.entity.PetDomainEntity
import nondas.pap.petcareapp.domain.repository.UserRepository
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

class AddPet @Inject constructor(
    private val petRepository: PetRepository,
    private val userRepository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<Unit, AddPet.Params>(dispatcher) {


    override suspend fun invoke(params: Params) {
        try {
            println("Invoke method called with params: $params")

            val user = userRepository.getCurrentUser()
            println("add pet user: $user")

            val age = getAge(params.dob)
            println("Calculated age: $age")

            val pet = PetDomainEntity(
                userId = user.userId ?: "",
                name = params.name,
                dob = params.dob,
                age = age.toString(),
                petId = UUID.randomUUID().toString(),
                kind = params.kind
            )

            println("Pet entity created: $pet")

            petRepository.addPet(pet)
            println("Pet added to repository")

        } catch (e: Exception) {
            println("Error: ${e.message}")
            e.printStackTrace()
        }
    }

    data class Params(
        val name: String,
        val dob: String,
        val kind: String,

    )


    private fun getAge(dob: String): Int {
        val formattedDob = formatDob(dob)
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val birthDate = LocalDate.parse(formattedDob, formatter)
        val currentDate = LocalDate.now()
        return Period.between(birthDate, currentDate).years
    }

    private fun formatDob(dob: String) = dob.substring(0, 2) + "/" + dob.substring(2, 4) + "/" + dob.substring(4, 8)

}