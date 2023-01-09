package com.example.secondapiproject.controllers;

import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.StudentImages;

public class ImagesApiController {

    public void uploadImage(byte[] imageBytes, ApiProcessCallBack callBack) {
        if (imageBytes != null) {
            StudentImages image = new StudentImages();
            image.imageBytesArray = imageBytes;
            image.uploadImage(callBack);
        } else {
            callBack.onFailure("Select image to upload");
        }
    }

    public void deleteImage(StudentImages image, ApiProcessCallBack callBack) {
        image.deleteImage(callBack);
    }

    public void getImages(ArrayResponseCallBack<StudentImages> callBack) {
        StudentImages.getImages(callBack);
    }

}
