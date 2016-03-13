package com.example.proje;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ZarOyunu extends Activity {

	ImageView oyuncuZar1;
	ImageView oyuncuZar2;
	ImageView bilgisayarZar1;
	ImageView bilgisayarZar2;
	Button zarAtButonu;
	String zarIsimleri[];
	Random r;
	String oyuncuzar1Ismi, oyuncuZar2Ismi, bilgisayar1ZarIsmi, bilgisayar2ZarIsmi;
	int imageResource;
	int zarSuresi;
	int zarBir, zarIki, zarUc, ZarDort ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zar_oyunu);

		oyuncuZar1 = (ImageView) findViewById(R.id.zarOyuncuBir);
		oyuncuZar2 = (ImageView) findViewById(R.id.ZarOyuncuIki);
		bilgisayarZar1 = (ImageView) findViewById(R.id.zarBilgisayarBir);
		bilgisayarZar2 = (ImageView) findViewById(R.id.zarBilgisayarIki);
		zarAtButonu = (Button) findViewById(R.id.zarAtButonu);

		r = new Random();
		zarIsimleri = new String[6];
		zarSuresi = 10;

		zarIsimleri[0] = "zar_bir";
		zarIsimleri[1] = "zar_iki";
		zarIsimleri[2] = "zar_uc";
		zarIsimleri[3] = "zar_dort";
		zarIsimleri[4] = "zar_bes";
		zarIsimleri[5] = "zar_alti";

		zarAtButonu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				zarBir = r.nextInt(6) ;
				oyuncuzar1Ismi = "@drawable/" + zarIsimleri[zarBir];
				imageResource = getResources().getIdentifier(oyuncuzar1Ismi, null, getPackageName());
				oyuncuZar1.setImageResource(imageResource);
				
				zarIki = r.nextInt(6) ;
				oyuncuZar2Ismi = "@drawable/" + zarIsimleri[zarIki];
				imageResource = getResources().getIdentifier(oyuncuZar2Ismi, null, getPackageName());
				oyuncuZar2.setImageResource(imageResource);

				zarUc = r.nextInt(6) ;
				bilgisayar1ZarIsmi = "@drawable/" + zarIsimleri[zarUc];
				imageResource = getResources().getIdentifier(bilgisayar1ZarIsmi, null, getPackageName());
				bilgisayarZar1.setImageResource(imageResource);

				ZarDort = r.nextInt(6) ;
				bilgisayar2ZarIsmi = "@drawable/" + zarIsimleri[ZarDort];
				imageResource = getResources().getIdentifier(bilgisayar2ZarIsmi, null, getPackageName());
				bilgisayarZar2.setImageResource(imageResource);

				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {

						if (zarSuresi > 0)
							zarAtButonu.callOnClick();
						else{
							kazananHesapla() ;
							zarSuresi = 10 ;
						}
						zarSuresi--;

					}

					private void kazananHesapla() {
						
						if((zarBir + zarIki) == (zarUc + ZarDort)){
							Toast.makeText(ZarOyunu.this,
									" Beaberlik", Toast.LENGTH_SHORT)
									.show();
						}
						else if((zarBir + zarIki) > (zarUc + ZarDort)){
							Toast.makeText(ZarOyunu.this,
									" Oyuncu Kazandý", Toast.LENGTH_SHORT)
									.show();
						}
						else{
							Toast.makeText(ZarOyunu.this,
									" Bilgisayar Kazandý", Toast.LENGTH_SHORT)
									.show();
							
						}
					}

				}, 200);

			}
		});

	}

}
