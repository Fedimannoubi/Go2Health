package com.example.bites;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //this page is just for TESTING
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    FirebaseStorage storage;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1 = findViewById(R.id.imagetest);
        image2 = findViewById(R.id.imagetest2);
        //image3 = findViewById(R.id.imagetest3);

        ImageLoader(image1,"images/ads/add1.jpg");
        ImageLoader(image2,"images/ads/add2.jpg");
       // ImageLoader(image3,"images/ads/add2.jpg");

    }

    private void ImageLoader(final ImageView image, String url){

        storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        // Get reference to the file
        StorageReference forestRef = storageRef.child(url);

        forestRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                // Metadata now contains the metadata for 'images/forest.jpg'

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
                Toast.makeText(MainActivity.this, "no such file found", Toast.LENGTH_SHORT).show();
            }
        });

        forestRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(image);
            }
        });
    }


}

