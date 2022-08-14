package android.project.auction.presentation.postitem

sealed class PostItemEvent {
    data class CategoryChanged(val value: String) : PostItemEvent()
    data class SubCategoryChange(val value: String) : PostItemEvent()
    data class TitleChanged(val value: String) : PostItemEvent()
    data class DescriptionChanges(val value: String) : PostItemEvent()
    data class StartingPriceChanged(val value: String) : PostItemEvent()
    data class MinIncreaseChanged(val value: String) : PostItemEvent()
    data class StartTimeChanged(val value: String) : PostItemEvent()
    data class EndTimeChanged(val value: String) : PostItemEvent()

    object CreateItemClicked : PostItemEvent()
    object OnCategoryItemClicked : PostItemEvent()
}