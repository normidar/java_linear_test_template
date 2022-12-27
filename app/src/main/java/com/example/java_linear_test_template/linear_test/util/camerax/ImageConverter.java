package com.example.java_linear_test_template.linear_test.util.camerax;


import static androidx.core.math.MathUtils.clamp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;

import androidx.camera.core.ImageProxy;

import java.nio.ByteBuffer;

public class ImageConverter {

    public static int[] rgbFromYuv420888(ImageProxy image) {
        if (image.getFormat() != ImageFormat.YUV_420_888) {
            throw new IllegalArgumentException("Invalid image format");
        }

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int[] rgbArray = new int[imageWidth * imageHeight];
        ByteBuffer yBuffer = image.getPlanes()[0].getBuffer();
        yBuffer.position(0);

        ByteBuffer uBuffer = image.getPlanes()[1].getBuffer();
        uBuffer.position(0);
        ByteBuffer vBuffer = image.getPlanes()[2].getBuffer();
        vBuffer.position(0);

        int yRowStride = image.getPlanes()[0].getRowStride();
        int yPixelStride = image.getPlanes()[0].getPixelStride();
        int uvRowStride = image.getPlanes()[1].getRowStride();
        int uvPixelStride = image.getPlanes()[1].getPixelStride();

        int r, g, b;
        int yValue, uValue, vValue;

        for (int y = 0; y < imageHeight; ++y) {
            for (int x = 0; x < imageWidth; ++x) {
                int yIndex = (y * yRowStride) + (x * yPixelStride);
                yValue = (yBuffer.get(yIndex) & 0xff);

                int uvx = x / 2;
                int uvy = y / 2;
                int uvIndex = (uvy * uvRowStride) +  (uvx * uvPixelStride);

                uValue = (uBuffer.get(uvIndex) & 0xff) - 128;
                vValue = (vBuffer.get(uvIndex) & 0xff) - 128;

                r = (int) (yValue + 1.370705f * vValue);
                g = (int) (yValue - (0.698001f * vValue) - (0.337633f * uValue));
                b = (int) (yValue + 1.732446f * uValue);
                r = clamp(r, 0, 255);
                g = clamp(g, 0, 255);
                b = clamp(b, 0, 255);

                int rgbIndex = y * imageWidth + x;
                rgbArray[rgbIndex]
                        = (0) | (r & 255) << 16 | (g & 255) << 8 | (b & 255);
            }
        }

        return rgbArray;
    }

    public static int[] arrayFromRGB(ImageProxy imageProxy) {

        if (imageProxy.getFormat() != PixelFormat.RGBA_8888) {
            throw new IllegalArgumentException();
        }
        ImageProxy.PlaneProxy rgbaPlane = imageProxy.getPlanes()[0];
        ByteBuffer rgbaBuffer = rgbaPlane.getBuffer();
        int width = imageProxy.getWidth();
        int height = imageProxy.getHeight();
        int[] rgbArray = new int[width * height];
        int rowStride = rgbaPlane.getRowStride();
        int pixelStride = rgbaPlane.getPixelStride();
        int padding = (rowStride / pixelStride - width) / 2;
        rgbaBuffer.rewind();

        try {
            for (int y = 0; y < height; ++y) {
                int rgbaIndex = y * rowStride + padding * 4;
                rgbaBuffer.position(rgbaIndex);
                for (int x = 0; x < width; ++x) {
                    int r = rgbaBuffer.get();
                    int g = rgbaBuffer.get();
                    int b = rgbaBuffer.get();
                    int a = rgbaBuffer.get();
                    rgbArray[y * width + x] = (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return rgbArray;
    }

    public static Bitmap bitmapFromARGBArray(int[] array, int width, int height, boolean makeOpaque) {
        if (makeOpaque) {
            array = array.clone();
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    array[y*width+x] |= (0xFF << 24);
                }
            }
        }
        return Bitmap.createBitmap(array, width, height, Bitmap.Config.ARGB_8888);
    }

    @SuppressLint("RestrictedApi")
    public static Bitmap imageProxyToBitmap(ImageProxy imageProxy) {
        int width = imageProxy.getWidth();
        int height = imageProxy.getHeight();
        int[] frame = null;
        try {
            if (imageProxy.getFormat() == ImageFormat.YUV_420_888) {
                frame = ImageConverter.rgbFromYuv420888(imageProxy);
            } else {
                frame = ImageConverter.arrayFromRGB(imageProxy);
            }
            if (frame != null) {
                return ImageConverter.bitmapFromARGBArray(frame, width, height, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
