package android.project.auction.domain.model.category


data class SubCategories(
    val id: String,
    val name: String,
    val subCategories: List<SubCategory>
)
