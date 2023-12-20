package com.improve.myphotoalbum;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyImagesRepository {

    private MyImagesDao myImagesDao;
    private LiveData<List<MyImages>> imagesList;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MyImagesRepository (Application application){

        MyImagesDatabase database = MyImagesDatabase.getInstance(application);
        myImagesDao = database.myImagesDao();
        imagesList = myImagesDao.getAllImages();

    }

    public void insert(MyImages myImages){

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                myImagesDao.insert(myImages);
            }
        });


    }

    public void delete(MyImages myImages){

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                myImagesDao.delete(myImages);
            }
        });


    }

    public void update(MyImages myImages){

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                myImagesDao.update(myImages);
            }
        });


    }

    public LiveData<List<MyImages>> getImagesList() {
        return imagesList;
    }



}
