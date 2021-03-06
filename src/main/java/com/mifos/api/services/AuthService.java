/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.api.services;

import com.mifos.api.model.APIEndPoint;
import com.mifos.api.model.FcmToken;
import com.mifos.api.model.UpdatePasswordPayload;
import com.mifos.api.model.UpdatePasswordResponse;
import com.mifos.objects.user.LoginData;
import com.mifos.objects.user.User;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author fomenkoo
 */
public interface AuthService {

    @POST(APIEndPoint.AUTHENTICATION)
    Observable<User> authenticate(@Body LoginData loginData);

    @PUT("users/{userId}")
    Observable<String> setFcmToken(@Path("userId") int userId, @Body FcmToken fcmToken);

    @PUT("users/{userId}")
    Observable<UpdatePasswordResponse> updatePassword(@Path("userId") long userId, @Body UpdatePasswordPayload updatePasswordPayload);

}
