package com.bagas.asynctest;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MyAsycnTask extends AsyncTask<Void, Integer, String> {
    private TextView textView;
    private ProgressBar progressBar;

    public MyAsycnTask(TextView textView, ProgressBar progressBar) {
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("Task dimulai....");
        progressBar.setProgress(0);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int progress = 0;
        while (progress < 100) {
            try {
                Thread.sleep(500); // Simulasi Tugas Berat
                progress += random.nextInt(20);
                publishProgress(progress);
            } catch (InterruptedException e) {
                return "Error: Task Terhenti!";
            }
        }
        return "Tugas Selesai!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        textView.setText("Progress: " + values[0] + "%");
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        textView.setText(result);
    }
}
