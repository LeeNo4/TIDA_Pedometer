package com.mobileapp.hhx.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hhx on 2017/4/20.
 */

public class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imgView;

    public ImageLoadTask(ImageView imgView){
        this.imgView = imgView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlString = strings[0];

        try {
            URL url = new URL(urlString);
            InputStream is = url.openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        if(result != null){
            imgView.setImageBitmap(result);
        }
    }
}
