package nondas.pap.petcareapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "pet",
    foreignKeys = [ForeignKey(
        entity = UserDataEntity::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["userId"])]
)
data class PetDataEntity(
    @PrimaryKey
    val petId: String,
    val name: String,
    val kind: String,
    val age: String,
    val userId: String
)
