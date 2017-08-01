package com.google.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "second activity", Toast.LENGTH_SHORT).show();

        Button button = new Button(this);
        RelativeLayout rl = new RelativeLayout(this);
        rl.addView(button);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        this.addContentView(rl,params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hlx.launch.game.d.bR(MainActivity.this);
            }
        });

    }

}
