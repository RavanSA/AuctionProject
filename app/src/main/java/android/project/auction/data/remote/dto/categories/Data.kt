package android.project.auction.data.remote.dto.categories

import android.project.auction.domain.model.category.Category

data class Data(
    val id: String,
    val name: String,
    val description: String,
    val categoryImage: String
)

fun Data.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        description = description,
        categoryImage = categoryImage
    )
}