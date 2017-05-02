package com.gws.android.tips.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.anye.greendao.gen.TipDao;
import com.gws.android.tips.App;
import com.gws.android.tips.R;
import com.gws.android.tips.base.BaseActivity;
import com.gws.android.tips.bean.Tip;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;


public class AddActivity extends BaseActivity {

    @Bind(R.id.title)
    EditText mTitle;
    @Bind(R.id.content)
    EditText mContent;
    Tip mTip;
    TipDao mTipDao;
    @Override
    protected void initInject() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTipDao = App.getInstance().getDaoSession().getTipDao();
        String id = getIntent().getExtras().getString("ID");
        List<Tip> list = mTipDao.queryRaw("id",id);
        if(list.isEmpty()){
            mTip = new Tip();
        }else {
            mTip = list.get(0);
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add;
    }

    @Override
    protected void initEventAndData() {
        mTitle.setText(mTip.getTitle());
        mContent.setText(mTip.getContent());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.add_quest);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTip.setTitle(mTitle.getText().toString().trim());
                mTip.setContent(mContent.getText().toString().trim());
                if(TextUtils.isEmpty(mTip.getId())){
                    mTip.newId();
                    mTipDao.insert(mTip);
                }else {
                    mTipDao.update(mTip);
                }
            }
        });

        Observable<CharSequence> titleOb= RxTextView.textChanges(mTitle);
        Observable<CharSequence>  contentOb= RxTextView.textChanges(mContent);
        Observable.combineLatest(titleOb, contentOb, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence text1, CharSequence text2) {
                return !TextUtils.isEmpty(text1)  &&  !TextUtils.isEmpty(text2);
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean complete) {
                fab.setClickable(complete);
                fab.setBackgroundResource(complete ? R.color.colorAccent : R.color.colorPrimary);
            }
        });



    }



    @Override
    public void useNightMode(boolean isNight) {

    }


}
