package com.example.proje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class OyunSecim extends Activity {

	ImageButton kartButonu;
	ImageButton kilicButonu;
	ImageButton zarButonu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oyun_secim_ekrani);

		kartButonu = (ImageButton) findViewById(R.id.kartButonu);
		kilicButonu = (ImageButton) findViewById(R.id.kilicButonu);
		zarButonu = (ImageButton) findViewById(R.id.zarButonu);

		kartButonu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent kartIntent = new Intent(OyunSecim.this, KartOyunu.class) ;
				startActivity(kartIntent);

			}
		});

		kilicButonu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent kilicIntent = new Intent(OyunSecim.this, KilicOyunu.class) ;
				startActivity(kilicIntent);

			}
		});

		zarButonu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent zarIntent = new Intent(OyunSecim.this, ZarOyunu.class) ;
				startActivity(zarIntent);

			}
		});

	}

}
