package com.example.proje;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Anasayfa extends Fragment {
	Button girisButonu;
	Button gmailButonu;
	Button fotoCekButonu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_main, container, false);
		
		 gmailButonu = (Button) view.findViewById(R.id.gmailButonu);
		 girisButonu = (Button) view.findViewById(R.id.girisButonu);
		 fotoCekButonu = (Button) view.findViewById(R.id.fotoButon);
		 girisButonu.setOnClickListener(new OnClickListener() {
		
		 public void onClick(View v) {
		
		 Intent intent2 = new Intent(getActivity(), OyunSecim.class);
		 startActivity(intent2);
		 
		
		 }
		
		 });
		 gmailButonu.setOnClickListener(new OnClickListener() {
		
		 public void onClick(View v) {
		 Intent intent3 = new Intent(getActivity(), Deneme.class);
		 startActivity(intent3);
		
		 }
		 });
//		 fotoCekButonu.setOnClickListener(new OnClickListener() {
//		
//		 public void onClick(View v) {
//		
//		 dispatchTakePictureIntent();
//		
//		 }
//		 });
//		

		return view;

	}

}
