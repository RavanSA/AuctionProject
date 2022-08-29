package android.project.auction.domain.model.item

data class Item(
    val description: String,
    val endTime: String,
    val id: String,
    val minIncrease: Int,
    val pictures: String,
    val startTime: String,
    val startingPrice: Int,
    val subCategoryId: String,
    val categoryId: String,
    val title: String,
    val userFullName: String,
    val userId: String,
    val mainItemPicture: String
)
