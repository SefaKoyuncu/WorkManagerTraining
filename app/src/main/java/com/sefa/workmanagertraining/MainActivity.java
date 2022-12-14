package com.sefa.workmanagertraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.util.Log;

import com.sefa.workmanagertraining.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonYap.setOnClickListener(view -> {

            WorkRequest istek=new PeriodicWorkRequest
                    .Builder(MyWorkerBildirim.class,15, TimeUnit.MINUTES)
                    .setInitialDelay(10,TimeUnit.SECONDS)
                    .build();

            WorkManager.getInstance(this).enqueue(istek);

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(istek.getId())
                    .observe(this,workInfo -> {
                        String durum=workInfo.getState().name();
                        Log.e("Arkaplan işlem durumu",durum);
                    });

        });






    }
}