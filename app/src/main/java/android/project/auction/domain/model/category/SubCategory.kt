package android.project.auction.domain.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubCategory(
    val id: String,
    val name: String
) : Parcelable
