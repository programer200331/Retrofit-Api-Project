package com.example.secondapiproject.views;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.R;
import com.example.secondapiproject.controllers.ImagesApiController;
import com.example.secondapiproject.databinding.ActivityUploadImagesBinding;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.StudentImages;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class UploadImagesActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityUploadImagesBinding binding;

    private ActivityResultLauncher<String> permissionPeckImageLauncher;
    private ActivityResultLauncher<Void> cameraResultLauncher;
    private Bitmap imageBitmap;
    private final ImagesApiController apiController = new ImagesApiController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializedViews();
    }

    private void initializedViews() {
        setupResultLaunchers();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.butPickImage.setOnClickListener(this::onClick);
        binding.butUploadImage.setOnClickListener(this::onClick);
    }

    private void lunchPermissionPickImage() {
        permissionPeckImageLauncher.launch(Manifest.permission.CAMERA);
    }

    private void setupResultLaunchers() {

        //Setup Permission Launcher
        permissionPeckImageLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    cameraResultLauncher.launch(null);
                }
            }
        });

        //Setup Camera PickImage Launcher
        cameraResultLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap image) {
                if (image != null) {
                    binding.imvPickedImage.setImageBitmap(image);
                    imageBitmap = image;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.but_pick_image) {
            lunchPermissionPickImage();
        } else {
            performImageUpload();
        }
    }

    private void performImageUpload() {
        if (imageBitmap != null) {
            uploadImage();
        } else {
            Toast.makeText(this, "Please Select Image to Upload", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadImage() {
        apiController.uploadImage(bitmapToBytes(), new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(UploadImagesActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(UploadImagesActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteImage() {
//        apiController.deleteImage();
    }

    private void getAllImages() {
        apiController.getImages(new ArrayResponseCallBack<StudentImages>() {
            @Override
            public void onSuccess(List<StudentImages> list) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private byte[] bitmapToBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}