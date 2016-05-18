package com.louisgeek.louisasynctaskdemo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by louisgeek on 2016/5/18.
 */

/**
 * Params 启动任务执行的输入参数，比如HTTP请求的URL。
 Progress 后台任务执行的百分比。
 Result 后台执行任务最终返回的结果，比如String。
 */
public class MyAsyncTask extends AsyncTask<String,Integer,String> {
    private static final String TAG="MyAsyncTask";

    private TextView id_tv;
    private ProgressBar id_pb;

    public MyAsyncTask(TextView id_tv, ProgressBar id_pb) {
        this.id_tv = id_tv;
        this.id_pb = id_pb;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        id_tv.setText("开始");
    }

    /*这里的params参数对应AsyncTask中的第一个参数
    * 这里的返回值对应AsyncTask的第三个参数
     该方法不运行在UI线程中,主要用于异步操作,通过调用publishProgress()方法
    触发onProgressUpdate对UI进行操作
    */
    @Override
    protected String doInBackground(String... params) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress((i + 1) * 10);//更新进度
        }
        return params[0];
    }

    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        id_tv.setText("结束" + s);
    }

    //publishProgress 过来的参数
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int value = values[0];
        id_pb.setProgress(value);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG, "onCancelled() called");
    }

}
