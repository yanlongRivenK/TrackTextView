package com.yanlongrivenk.tracktextview;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yanlongrivenk.yltextview.TextViewYL;

public class MainActivity extends AppCompatActivity {

    protected TextViewYL mTtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTtv = ((TextViewYL) findViewById(R.id.ttv));
        findViewById(R.id.bt_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTtv.setDirectionn(TextViewYL.Directionn.DIRECTION_LEFT);
                mTtv.startAnimator(2000);
            }
        });

        findViewById(R.id.bt_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTtv.setDirectionn(TextViewYL.Directionn.DIRECTION_RIGHT);
                mTtv.startAnimator(1000);
            }
        });

        findViewById(R.id.objectAnimator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mTtv, "percent", 0, 1);
                animator.setDuration(10000).start();
            }
        });



    }
}
