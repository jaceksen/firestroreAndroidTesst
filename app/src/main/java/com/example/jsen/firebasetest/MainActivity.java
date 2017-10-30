package com.example.jsen.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String ZGLOSZENIE_KLUCZ = "opis_zgloszenia";
    public static final String DATA_KLUCZ = "data_wpisu";
    public static final String STATUS_KLUCZ = "status";
//    private DocumentReference mDocRef= FirebaseFirestore.getInstance().document("usterki/usterka");
    private CollectionReference mColRef = FirebaseFirestore.getInstance().collection("usterki");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveZgloszenie(View view) {
        EditText tekstZgloszenia = (EditText) findViewById(R.id.etZgloszenie);
        String tekstZgString = tekstZgloszenia.getText().toString();
        Date dataWpisu = new Date();
        String statusZgloszenia = "zgłoszone";

        if(tekstZgString.isEmpty()){ return; }
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(ZGLOSZENIE_KLUCZ,tekstZgString);
        dataToSave.put(DATA_KLUCZ,dataWpisu);
        dataToSave.put(STATUS_KLUCZ,statusZgloszenia);
        //mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
        mColRef.add(dataToSave).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("Zgloszenia","Dodano zgloszenie");
            }
        });
    }
}
