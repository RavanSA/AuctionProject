package android.project.auction.domain.use_case

import android.project.auction.domain.use_case.auth.Authentication
import android.project.auction.domain.use_case.logout.Logout
import android.project.auction.domain.use_case.sign_in.SignIn
import android.project.auction.domain.use_case.sign_up.SignUp

data class AuctionAuthUseCase(
    val signIn: SignIn,
    val signUp: SignUp,
    val authentication: Authentication,
    val logout: Logout
 )
