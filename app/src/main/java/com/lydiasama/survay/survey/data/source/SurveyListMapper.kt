package com.lydiasama.survay.survey.data.source

import android.util.Log
import com.lydiasama.survay.core.ErrorData
import com.lydiasama.survay.core.exception.RemoteException
import com.lydiasama.survay.survey.data.SurveyItem
import io.reactivex.functions.Function
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

class SurveyListMapper : Function<Response<List<SurveyItem>>, List<SurveyItem>> {
	override fun apply(response: Response<List<SurveyItem>>): List<SurveyItem> {
		return if (response.code() == HttpsURLConnection.HTTP_OK) {
			Log.d("SurvayListMapper", "body : ${response.body()
					.toString()}")
			response.body() ?: throw RemoteException(code = response.code(),
					msg = ErrorData.fromResponse(response).message)
		} else {
			throw RemoteException(code = response.code(),
					msg = ErrorData.fromResponse(response).message)
		}
	}
}