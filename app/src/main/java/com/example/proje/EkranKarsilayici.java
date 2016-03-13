package com.example.proje;

import java.util.ArrayList;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class EkranKarsilayici extends Activity {

	Button girisButonu;
	Button gmailButonu;
	Button fotoCekButonu;
	static final int REQUEST_IMAGE_CAPTURE = 1;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	static int mesaj_sayisi = 5;// menüde gösterilecek olan mesaj sayýsý
	static int bildirim_sayisi = 7;// menüde gösterilecek olan bildirim sayýsý
	private ActionBar actionBar;

	// navigasyon menü baþlýk
	private CharSequence mDrawerTitle;

	private CharSequence mTitle;

	// slide menu items
	private String[] menuList;
	private TypedArray menuIconList;
	private ArrayList<NavDrawerItem> menuItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anasayfa);

//		gmailButonu = (Button) findViewById(R.id.gmailButonu);
//		girisButonu = (Button) findViewById(R.id.girisButonu);
//		fotoCekButonu = (Button) findViewById(R.id.fotoButon);
//		girisButonu.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//
//				Intent intent2 = new Intent(EkranKarsilayici.this, OyunSecim.class);
//				startActivity(intent2);
//			}
//
//		});
//		gmailButonu.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				Intent intent3 = new Intent(EkranKarsilayici.this, Deneme.class);
//				startActivity(intent3);
//
//			}
//		});
//		fotoCekButonu.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//
//				dispatchTakePictureIntent();
//
//			}
//		});
//
//		/// ***//////
		mTitle = mDrawerTitle = getTitle();// uygulama adý

		// slider menüitemlerini yüklüyoruz.
		menuList = getResources().getStringArray(R.array.menuList);

		// slider menü iconlarýný arrayden çekiyoruz
		menuIconList = getResources().obtainTypedArray(R.array.menuIconList);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		menuItems = new ArrayList<NavDrawerItem>();

		// arraylerden aldýðýmýz deðerleri NavDrawerItem e ekliyoruz
		// Anasayfa
		menuItems.add(new NavDrawerItem(menuList[0], menuIconList.getResourceId(0, -1)));
		// Profil
		menuItems.add(new NavDrawerItem(menuList[1], menuIconList.getResourceId(1, -1)));
		// Mesajlar
		menuItems.add(new NavDrawerItem(menuList[2], menuIconList.getResourceId(2, -1), true, "" + mesaj_sayisi));
		// Bildirimler
		menuItems.add(new NavDrawerItem(menuList[3], menuIconList.getResourceId(3, -1), true, "" + bildirim_sayisi));
		// Ayarlar
		menuItems.add(new NavDrawerItem(menuList[4], menuIconList.getResourceId(4, -1)));
		// çýkýþ
		menuItems.add(new NavDrawerItem(menuList[5], menuIconList.getResourceId(5, -1)));

		// menuList[x] menü adý
		// menuIconList.getResourceId(4, -1) //menü iconu
		// true counter yani mesaj bildirim saysýnýn gösterip gösterilmeyeceði
		// mesaj sayisi veya bildirim sayýsý

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());// menu
																			// Liste
																			// Click
																			// listener
																			// ekliyoruz

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(), menuItems);// adapter
																				// oluþturuyoruz

		mDrawerList.setAdapter(adapter); // adapteri set ediyoruz

		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name,
				R.string.app_name) {// menü aýlýp kapandýðýnda action bar title
									// deðiþimi
			public void onDrawerClosed(View view) { // acýkken uygulama adý
													// gözükecek
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {// kapalkýyken menü adý
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			displayView(0); // ilk açýlýþta 0.menü seçile gelecek
		}

		/// ***///////

	}

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	/// **//////


	private class SlideMenuClickListener implements
			ListView.OnItemClickListener { //menü elemanlarýna týklanýnca
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			displayView(position); //diplayView methodu çaðýrýlýyor
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void displayView(int position) { // gelen positiona göre ilgili
												// fragmentý çaðýrýyor

		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new Anasayfa();
			break;
		case 1:
			fragment = new Profil();
			break;
		case 2:
			fragment = new Mesaj();
			break;
		case 3:
			fragment = new Bildirimler();
			break;
		case 4:

			fragment = new Ayarlar();
			break;
		case 5:
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EkranKarsilayici.this);
			alertDialogBuilder.setTitle(this.getTitle());
			alertDialogBuilder.setMessage("Çýkýþ Yap?");
			alertDialogBuilder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(startMain);
				}

			});

			alertDialogBuilder.setNegativeButton("Hayýr", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}

			});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			break;

		default:
			break;
		}

		if (fragment != null) {// fragment boþ deðilse
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(menuList[position]);
			mDrawerLayout.closeDrawer(mDrawerList);

		} else {
			// error in creating fragment
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {// search iconu ile ilgili
													// ayar.

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);
		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub

		return false;
	}

	public void dataChanges(int i) {// Mesaj sayýsý veya bildirim sayýsý
									// deðiþtiðinde çaðýrýlýyor
		int sayi = 0;

		// Gelen i deðerine göre sayi belirleniyor
		// i 2 ise mesaj 3 ise bildirim
		if (i == 2) {
			sayi = mesaj_sayisi;
		} else if (i == 3) {
			sayi = bildirim_sayisi;

		}
		menuItems.set(i, new NavDrawerItem(menuList[i], menuIconList.getResourceId(i, -1), true, "" + sayi));// menüdeki
																												// deðer
																												// deðiþtiriliyor
		adapter.notifyDataSetChanged();// adapter basþtan oluþturuluor.Yeni
										// deðerlere göre
	}

	/// **///////

}
