package com.wetmarket.staff.Util;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wetmarket.staff.Activity.ImagePickerActivity;


import java.util.List;


public class ImagePickerUtils {
    View.OnClickListener onClickListener;
    private boolean isFreeCrop = false;

    public ImagePickerUtils(Context context, boolean isRemove, View.OnClickListener onClickListener) {
        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            this.onClickListener = onClickListener;
            onProfileImageClick(activity, isRemove);
        }
    }

    public ImagePickerUtils(Context context, boolean isRemove, boolean isFreeCrop, View.OnClickListener onClickListener) {
        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            this.onClickListener = onClickListener;
            this.isFreeCrop = isFreeCrop;
            onProfileImageClick(activity, isRemove);
        }
    }

    public void onProfileImageClick(AppCompatActivity activity, boolean isRemove) {
        Dexter.withActivity(activity)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions(activity, isRemove);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions(AppCompatActivity appCompatActivity, boolean isRemove) {
        ImagePickerActivity.showImagePickerOptions(appCompatActivity, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent(appCompatActivity);
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent(appCompatActivity);
            }

            @Override
            public void onRemoveProfile() {
                //  appCompatActivity.setResult(Activity.RESULT_OK, new Intent().putExtra("remove", "1"));
                if (onClickListener != null) {
                    onClickListener.onClick(null);
                }

            }
        }, isRemove);
    }

    private void launchCameraIntent(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 500);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 500);
        intent.putExtra(ImagePickerActivity.INTENT_FREE_CROP, isFreeCrop);

        appCompatActivity.startActivityForResult(intent, 1310);
    }

    private void launchGalleryIntent(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        intent.putExtra(ImagePickerActivity.INTENT_FREE_CROP, isFreeCrop);
        appCompatActivity.startActivityForResult(intent, 1310);
    }


}
