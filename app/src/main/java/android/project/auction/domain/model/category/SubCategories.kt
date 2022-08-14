package android.project.auction.domain.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SubCategories(
    val id: String,
    val name: String,
    val subCategories: @RawValue List<SubCategory>
) : Parcelable
