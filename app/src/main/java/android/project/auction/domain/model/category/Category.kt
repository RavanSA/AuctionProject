package android.project.auction.domain.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Category(
    val id: String,
    val name: String
)

@Parcelize
data class SubAndCategory(
    val subCategoryID: String,
    val categoryID: String,
    val categoryName: String,
    val subCategoryName: String
) : Parcelable