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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpActivity extends BaseActivity {

    @BindView(R.id.url_edit_text)
    EditText mUrlET;
    @BindView(R.id.textView)
    TextView mContentTV;

    private OkHttpClient mOkHttpClient;
    private Disposable mDisposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_okhttp;
    }

    @Override
    public void initData() {
        // 创建 OkHttpClient 对象
        mOkHttpClient = new OkHttpClient();
    }

    private String getUrl() {
        String url = mUrlET.getText().toString();
        return XTextUtil.isEmpty(url) ? "http://www.baidu.com" : url;
    }

    @OnClick(R.id.ok_http_get_btn)
    public void okHttpGet() {
        if (OneTapUtil.checkInexact(R.id.ok_http_get_btn)) return;

        LogHelper.d("OKHttp Get :");

        Request.Builder builder = new Request.Builder().url(getUrl());

        Call call = mOkHttpClient.newCall(builder.build());

        // 异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogHelper.w(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
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
        });
    }

    private String getResponse(Response response) {
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

    @OnClick(R.id.ok_http_post_btn)
    public void okHttpPost() {
        if (OneTapUtil.checkInexact(R.id.ok_http_post_btn)) return;

        LogHelper.d("okHttpPost");

        FormBody formBody = new FormBody.Builder()
                .add("name", "name")
                .build();

        Request request = new Request.Builder()
                .url(getUrl())
                .post(formBody)
                .build();

        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogHelper.w(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
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
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mDisposable) {
            mDisposable.dispose();
        }
    }
}
