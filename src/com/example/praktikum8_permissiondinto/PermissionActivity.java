package com.example.praktikum8_permissiondinto;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PermissionActivity extends Activity {
	
	ListView listView;
	ArrayList<String>arrayList;
	ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        
        listView = (ListView) findViewById(R.id.listView1);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        
    	if(ContextCompat.checkSelfPermissions(this,Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
    		ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
    	}else{
    		readContacts();
    	}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResult){
    	super.onRequestPermissionsResult(requesCode, permission, grantResult);
    	readContacts();
    }

    @SuppressLint("Range")
    private void readContacts() {
    	ContentResolver contentResolver=getContentResolver();
    	Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    	if (cursor.moveToFirst()){
    		do{
    			arrayList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
    		}while(cursor.moveToNext());
    		arrayAdapter.notifyDataSetChanged();
    	}
		// TODO Auto-generated method stub
		
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.permission, menu);
        return true;
    }
    
}
