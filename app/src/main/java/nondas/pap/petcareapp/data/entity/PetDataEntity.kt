package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pet")
data class PetDataEntity(
    @PrimaryKey(autoGenerate = true)
    val petId: Int,
    val name: String,
    val kind: String,
    val age: Int,
    val userId: Int

    )
