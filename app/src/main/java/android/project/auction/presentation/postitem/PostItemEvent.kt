package android.project.auction.presentation.postitem

sealed class PostItemEvent {
    object OnCategoryItemClicked : PostItemEvent()
}