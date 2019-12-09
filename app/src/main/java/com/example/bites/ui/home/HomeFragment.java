package com.example.bites.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bites.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FirebaseStorage storage;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private TextView text1;
    private TextView text2;
    private TextView text3;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //set up the home page titile
                textView.setText("New stuff WAITING YOU!!!");
            }
        });

        //set up ads at the firs page
        //fill the home
        text1 = root.findViewById(R.id.add1Text);
        image1 = root.findViewById(R.id.imagetest);

        text2 = root.findViewById(R.id.add2Text);
        image2 = root.findViewById(R.id.imagetest2);

        text3 = root.findViewById(R.id.add3Text);
        image3 = root.findViewById(R.id.imagetest3);

        //Toast.makeText(this, ""+image1, Toast.LENGTH_SHORT).show();

        text1.setText("The New Fight! Lets GO!");
        ImageLoader(image1,"images/ads/add1.jpg");

        text2.setText("Join NOW! 50% OFF FOR ANNUAL MEMBERSHIP");
        ImageLoader(image2,"images/ads/add2.jpg");

        text3.setText("Get in SHAPE TODAY!");
        ImageLoader(image3,"images/ads/add3.jpg");



        return root;
    }

    //method to set an imageView withe image uri from the data store
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