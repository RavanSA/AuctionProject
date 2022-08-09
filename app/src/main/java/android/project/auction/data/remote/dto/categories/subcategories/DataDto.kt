package android.project.auction.data.remote.dto.categories.subcategories

import android.project.auction.domain.model.category.SubCategories
import android.project.auction.domain.model.category.SubCategory

data class DataDto(
    val id: String,
    val name: String,
    val subCategories: List<SubCategory>
)

fun DataDto.toSubCategories(): SubCategories {
    return SubCategories(
        id = id,
        name = name,
        subCategories = subCategories
    )
}