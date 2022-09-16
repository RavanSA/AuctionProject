package android.project.auction.domain.use_case.updateuserinfo

import android.net.Uri
import android.project.auction.data.remote.dto.userinfo.UpdateUserInfoRequest
import android.project.auction.domain.repository.AuctionRepository
import android.project.auction.domain.repository.FirebaseStorageRepository
import retrofit2.Response
import javax.inject.Inject

class UpdateUserInfo @Inject constructor(
    private val repository: AuctionRepository,
    private val cloudRepository: FirebaseStorageRepository
) {

    suspend operator fun invoke(userInfoRequest: UpdateUserInfoRequest): Response<Unit> {
        return repository.updateUserInfo(userInfoRequest)
    }

    suspend fun uploadProfilePicture(uri: Uri) {
        return cloudRepository.updateProfileImage(uri)
    }

}