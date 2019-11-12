package com.khatwa.zilalalrahmaapp.NewsList;

import android.util.Log;

import com.khatwa.zilalalrahmaapp.Model.NewsItem;
import com.khatwa.zilalalrahmaapp.Model.NewsListResponse;
import com.khatwa.zilalalrahmaapp.Network.ApiClient;
import com.khatwa.zilalalrahmaapp.Network.ApiInterface;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListModel implements NewsListContract.Model {
    private static final String TAG = "NewsListModel";

    @Override
    public void getNewsList(final OnFinishedListener onFinishedListener, int pageNo) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<NewsListResponse> call = apiService.getNewsList(pageNo);
        call.enqueue(new Callback<NewsListResponse>() {
            @Override
            public void onResponse(Call<NewsListResponse> call, Response<NewsListResponse> response) {
                if (response.isSuccessful()) {
                    List<NewsItem> news = response.body().getResults();

                    onFinishedListener.onFinished(news);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        onFinishedListener.onFailure(jObjError.getJSONObject("error").getString("message"));
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<NewsListResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }
}
