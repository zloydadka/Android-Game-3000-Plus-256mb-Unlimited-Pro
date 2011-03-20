package com.superdeveloperz.supergame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class GameActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String text = getIntent().getExtras().getString("text");
        Log.e("GameActivity.text", text);
        
        setContentView(R.layout.main);
        GameView findViewById = (GameView)findViewById(R.id.game);
        
        findViewById.setPornoText(text);
    }
}
