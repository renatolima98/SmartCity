package ipvc.estg.myapplication.api

import retrofit2.http.*
import retrofit2.Call


interface EndPoints {

   // @GET("api/users")
   // fun getUsers(): Call<List<User>>

    @FormUrlEncoded
    @POST("api/users")
    fun login(@Field("username") username: String?,@Field("password") password: String?): Call<OutputPost>
}