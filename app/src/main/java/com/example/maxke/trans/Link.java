package com.example.maxke.trans;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by maxke on 25.05.2017.
 */

/*import retrofit.Call;
import retrofit.http.FormUrlEncoded;
*/

public interface Link {
    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Call<Object> translate (@FieldMap Map<String, String> map);
}
