package com.example.proje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class KartOyunu extends Activity {

	ArrayList<Kart> kartList;
	Random r;
	ImageView imagivewOyuncuKart1, imagivewOyuncuKart2, imagivewOyuncuKart3, imagivewOyuncuKart4;
	ImageView imagivewBilgisayarKart1, imagivewBilgisayarKart2, imagivewBilgisayarKart3, imagivewBilgisayarKart4;
	Button baslatButonu, kartButonu, tamamButonu;
	int index, cikacakSayi;
	String uri;
	int imageResource;
	int devamSayisi;
	int oyuncuPuan, bilgisayarPuani;
	Map<Integer, ImageView> userMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kart_oyunu);

		imagivewBilgisayarKart1 = (ImageView) findViewById(R.id.kartOyunuBilgisayar1);
		imagivewBilgisayarKart2 = (ImageView) findViewById(R.id.kartOyunuBilgisayar2);
		imagivewBilgisayarKart3 = (ImageView) findViewById(R.id.kartOyunuBilgisayar3);
		imagivewBilgisayarKart4 = (ImageView) findViewById(R.id.kartOyunuBilgisayar4);
		imagivewOyuncuKart1 = (ImageView) findViewById(R.id.kartOyunuOyuncu1);
		imagivewOyuncuKart2 = (ImageView) findViewById(R.id.kartOyunuOyuncu2);
		imagivewOyuncuKart3 = (ImageView) findViewById(R.id.kartOyunuOyuncu3);
		imagivewOyuncuKart4 = (ImageView) findViewById(R.id.kartOyunuOyuncu4);
		baslatButonu = (Button) findViewById(R.id.kartOyunuBaslatButonu);
		kartButonu = (Button) findViewById(R.id.kartOyunuKartButonu);
		tamamButonu = (Button) findViewById(R.id.kartOyunuTamamButonu);

		tamamButonu.setVisibility(View.INVISIBLE);
		kartButonu.setVisibility(View.INVISIBLE);

		kartList = desteOlustur();
		r = new Random();
		userMap = new HashMap<Integer, ImageView>();
		index = 52;
		devamSayisi = 1;

		baslatButonu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				baslatButonu.setVisibility(View.INVISIBLE);
				tamamButonu.setVisibility(View.VISIBLE);
				kartButonu.setVisibility(View.VISIBLE);
				kartDagit(imagivewOyuncuKart1, imagivewBilgisayarKart1);

			}
		});
		kartButonu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (devamSayisi == 1)
					kartDagit(imagivewOyuncuKart2, imagivewBilgisayarKart2);
				else if (devamSayisi == 2)
					kartDagit(imagivewOyuncuKart3, imagivewBilgisayarKart3);
				else if (devamSayisi == 3) {
					kartDagit(imagivewOyuncuKart4, imagivewBilgisayarKart4);
					tamamButonu.callOnClick();
				}

				devamSayisi++;
			}
		});
		tamamButonu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				kartButonu.setVisibility(View.INVISIBLE);
				tamamButonu.setVisibility(View.INVISIBLE);

				if (oyuncuPuan == bilgisayarPuani) {
					Toast.makeText(getApplicationContext(), "Beraberesiniz", Toast.LENGTH_LONG).show();
				} else if (oyuncuPuan > bilgisayarPuani)
					Toast.makeText(getApplicationContext(), "Senin Kazandýn ", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getApplicationContext(), "Bilgisayar Kazandý ", Toast.LENGTH_LONG).show();
				
				for (Map.Entry<Integer, ImageView> pairs : userMap.entrySet()) {
					pairs.getValue().setImageResource(pairs.getKey());
				}
			}
		});

	}

	public void kartDagit(ImageView oyuncuKarti, ImageView bilgisayarKarti) {

		cikacakSayi = r.nextInt(index);
		uri = "@drawable/" + kartList.get(cikacakSayi).getIsim();
		imageResource = getResources().getIdentifier(uri, null, getPackageName());
		oyuncuKarti.setImageResource(imageResource);
		oyuncuPuan += kartList.get(cikacakSayi).getPuan();
		kartList.remove(cikacakSayi);
		index--;

		cikacakSayi = r.nextInt(index);
		uri = "@drawable/" + kartList.get(cikacakSayi).getIsim();
		imageResource = getResources().getIdentifier(uri, null, getPackageName());
		userMap.put(imageResource, bilgisayarKarti);

		// bilgisayarKarti.setImageResource(imageResource);
		bilgisayarPuani += kartList.get(cikacakSayi).getPuan();
		kartList.remove(cikacakSayi);
		index--;

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

				// array_spinner[k] = kart.getIsim();
				k++;
			}
		}

		return kartListesi;

	}

}
