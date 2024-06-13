package com.ptn.postotancredo.service.auth

import android.content.SharedPreferences
import com.ptn.postotancredo.service.Dto.UserDataResponse

class GlobalTokenValue {

    companion object{
      var userDataResponse: UserDataResponse? = null

        fun initUserData(userData: UserDataResponse){
            userDataResponse = userData
        }
    }

}
