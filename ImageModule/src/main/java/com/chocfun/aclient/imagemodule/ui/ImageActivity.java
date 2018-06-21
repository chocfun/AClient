package com.chocfun.aclient.imagemodule.ui;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.chocfun.aclient.imagemodule.R;
import com.chocfun.aclient.imagemodule.R2;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.BindArray;
import butterknife.BindView;

public class ImageActivity extends BaseActivity {

    @BindView(R2.id.src_spinner)
    Spinner mSrcSpinner;
    @BindView(R2.id.type_spinner)
    Spinner mTypeSpinner;
    @BindView(R2.id.size_seekbar)
    SeekBar mSizeSeekBar;

    @BindArray(R2.array.img_src_list)
    String[] mImgs;

    private String mCurrentImg;

    @Override
    public int getLayoutId() {
        return R.layout.img_activity_image;
    }

    @Override
    public void initData() {
        mSrcSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < mImgs.length) {
                    mCurrentImg = mImgs[position];
                }
            }
        });

        mTypeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
