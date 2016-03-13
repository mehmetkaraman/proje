package com.example.proje;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements AnimationListener {
	
	ImageView imageview;
	Animation animasyon;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ekran_karsilayici);
		
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.music);
		mp.start();
		
		animasyonBaslat();
		animasyonIcinThreadBaslat();
		
		
	}

	private void animasyonIcinThreadBaslat() {
		Thread t1 = new Thread(){
			
			public void run(){
				try{
					sleep(3000) ;
					Intent intent = new Intent(MainActivity.this,EkranKarsilayici.class);
					startActivity(intent);
					
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					finish();
				}
			}
		};
		t1.start();
	}

	private void animasyonBaslat() {
		imageview = (ImageView) findViewById(R.id.imageview1);
	
		animasyon = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.acilis_animasyonu);
		
		animasyon.setAnimationListener(this);
		imageview.startAnimation(animasyon);
	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if (animation == animasyon) {

		}
		
	}

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}
}
