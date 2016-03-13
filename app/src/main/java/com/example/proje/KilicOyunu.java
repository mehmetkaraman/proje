package com.example.proje;

import java.util.ArrayList;
import java.util.Random;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class KilicOyunu extends Activity {

	Button kartDegistir;
	String uri, uri2;
	int imageResource, imageResource2;
	Drawable res, res2;
	int cikacakSayi;
	int index;
	int control;
	int butonVisibleControl;
	Random r;
	Kart secilenKart;
	private String array_spinner[];
	String secilenKartIsmi = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kilic_oyunu);

		kartDegistir = (Button) findViewById(R.id.kartDegistir);

		final ImageView imageView = (ImageView) findViewById(R.id.bilgisayarKarti);
		final ImageView imageView2 = (ImageView) findViewById(R.id.oyuncuKarti);

		secilenKart = new Kart();
		secilenKart.setIsim("karo_as");

		index = 52;
		r = new Random();
		control = 1;
		array_spinner = new String[52];
		final ArrayList<Kart> kartList = desteOlustur();

		butonVisibleControl = 0;

		
		Spinner s = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
		s.setAdapter(adapter);
		
		
		s.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				secilenKartIsmi = parent.getItemAtPosition(pos).toString();
				Toast.makeText(KilicOyunu.this,
						secilenKartIsmi + " seçildi", Toast.LENGTH_SHORT)
						.show();
			}
 
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
 
			}
		});

		kartDegistir.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (index < 1)
					return;

				if (butonVisibleControl == 0) {
					kartDegistir.setVisibility(View.INVISIBLE);
					butonVisibleControl = 1;

				}

				if (control == 1) {
					cikacakSayi = r.nextInt(index);
					System.out.println("içerik  " + cikacakSayi);

					uri = "@drawable/" + kartList.get(cikacakSayi).getIsim();
					imageResource = getResources().getIdentifier(uri, null, getPackageName());

					// res = getResources().getDrawable(imageResource);
					// imageView.setImageDrawable(res);
					imageView.setImageResource(imageResource);
					if (kartList.get(cikacakSayi).getIsim().equals(secilenKartIsmi)) {

						Toast.makeText(getApplicationContext(), "Bilgisayar kazandý", Toast.LENGTH_LONG).show();
						return;
					}
					kartList.remove(cikacakSayi);
					index--;
					control = 0;
				} else {

					cikacakSayi = r.nextInt(index);
					System.out.println("içerik  " + cikacakSayi);
					uri2 = "@drawable/" + kartList.get(cikacakSayi).getIsim();
					imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
					// res2 = getResources().getDrawable(imageResource2);
					// imageView2.setImageDrawable(res2);
					imageView2.setImageResource(imageResource2);
					if (kartList.get(cikacakSayi).getIsim().equals(secilenKartIsmi)) {

						Toast.makeText(getApplicationContext(), "Sen kazandýn", Toast.LENGTH_LONG).show();
						return;
					}
					kartList.remove(cikacakSayi);
					index--;
					control = 1;
				}

				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {

						kartDegistir.callOnClick();

					}

				}, 200);

			}
		});

	}

	public ArrayList<Kart> desteOlustur() {

		String kartTipi[] = { "karo", "maca", "kupa", "sinek" };
		int kartSayisi[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		String kartIsmi[] = { "iki", "uc", "dort", "bes", "alti", "yedi", "sekiz", "dokuz", "on", "vale", "kiz",
				"papaz", "as" };
		int i, j;

		int k = 0;
		ArrayList<Kart> kartListesi = new ArrayList<Kart>();
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 13; j++) {
				Kart kart = new Kart();
				kart.setIsim(kartTipi[i] + "_" + kartIsmi[j]);
				kart.setPuan(kartSayisi[j]);
				
				System.out.println(kart.getIsim() + "  ve " + kart.getPuan());
				kartListesi.add(kart);
				
				array_spinner[k] = kart.getIsim();
				k++;
			}
		}

		return kartListesi;

	}

}
