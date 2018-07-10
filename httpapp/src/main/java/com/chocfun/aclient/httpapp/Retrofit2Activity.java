package com.chocfun.aclient.httpapp;

import android.widget.EditText;
import android.widget.TextView;

import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseActivity;
import com.chocfun.baselib.util.OneTapUtil;
import com.chocfun.baselib.util.XTextUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofit2Activity extends BaseActivity {

    @BindView(R.id.url_edit_text)
    EditText mUrlET;
    @BindView(R.id.textView)
    TextView mContentTV;

    private Disposable mDisposable;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit2;
    }

    @Override
    public void initData() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(getUrl())
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mDisposable) {
            mDisposable.dispose();
        }
    }

    private String getUrl() {
        String url = mUrlET.getText().toString();
        return XTextUtil.isEmpty(url) ? "https://api.douban.com/" : url;
    }

    @OnClick(R.id.retrofit_get_btn)
    public void get() {
        if (OneTapUtil.checkInexact(R.id.retrofit_get_btn)) return;

        Call<ResponseBody> call = mApiService.getBook(1220562);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Observable.just(getResponse(response))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposable = d;
                            }

                            @Override
                            public void onNext(String s) {
                                LogHelper.i(s);
                                mContentTV.setText(s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogHelper.w(t.getMessage());
            }
        });
    }

    @OnClick(R.id.new_movies_btn)
    public void post() {
        if (OneTapUtil.checkInexact(R.id.new_movies_btn)) return;

        Call<ResponseBody> call = mApiService.new_movies();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Observable.just(getResponse(response))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposable = d;
                            }

                            @Override
                            public void onNext(String s) {
                                LogHelper.i(s);
                                mContentTV.setText(s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogHelper.w(t.getMessage());
            }
        });
    }

    private String getResponse(Response<ResponseBody> response) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(response.code());
        buffer.append(" ");
        buffer.append(response.message());
        buffer.append("\n");

        try {
            buffer.append(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }
}
