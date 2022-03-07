package com.geteat.geteat

import com.geteat.geteat.models.PushNotification
import com.geteat.geteat.utils.Constants.CONTENT_TYPE
import com.geteat.geteat.utils.Constants.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotificationAPI(
        @Body notification: PushNotification
    ): Response<ResponseBody>

}