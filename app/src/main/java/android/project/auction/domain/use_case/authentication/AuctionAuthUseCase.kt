package android.project.auction.domain.use_case.authentication

import android.project.auction.domain.use_case.authentication.auth.Authentication
import android.project.auction.domain.use_case.authentication.auth.GetUserId
import android.project.auction.domain.use_case.authentication.logout.Logout
import android.project.auction.domain.use_case.authentication.sign_in.SignIn
import android.project.auction.domain.use_case.authentication.sign_up.SignUp

data class AuctionAuthUseCase(
    val signIn: SignIn,
    val signUp: SignUp,
    val authentication: Authentication,
    val logout: Logout,
    val userId: GetUserId
)
