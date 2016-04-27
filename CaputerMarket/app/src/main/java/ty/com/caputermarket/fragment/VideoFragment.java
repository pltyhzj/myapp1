package ty.com.caputermarket.fragment;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import ty.com.caputermarket.MarketReference;
import ty.com.caputermarket.R;
import ty.com.caputermarket.fragmentlistener.MainServer;
import ty.com.ty.com.internet.VideoData;

/**
 * Created by Administrator on 2016/3/23.
 */
@SuppressLint("ValidFragment")
public class VideoFragment extends Fragment
{

    ViewGroup mScrollView;
    MainServer.MyBind iServer;

    ViewGroup mShadow;
    LayoutInflater inflater;

 Handler handler = new Handler()
 {
     @Override
     public void handleMessage(Message msg)
     {

         if (msg.what ==2)
         {
             //12189
            Bitmap bt =(Bitmap)msg.obj;
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             addFilmColumn(bt);
             mShadow.setVisibility(View.GONE);

         }else if(msg.what ==1)
         {
             downLoadImg();
         }
         else {
             Message tempMSG = new Message();
             tempMSG.obj = msg.obj;
             if (iServer!=null&&iServer.finishDownLoadData())
             {
                 tempMSG.what = 1;

             }
             Log.i("tyty","wait server");
             handler.sendMessageDelayed(tempMSG,2000);
         }

     }
 };
/*
    这里不添加空 构造，再次穿件fragment会报错
 */
    public  VideoFragment()
    {

    }
    public VideoFragment(IBinder iServer)
    {
        this.iServer = (MainServer.MyBind)iServer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        this.inflater = inflater;
        // 如果是 false，就是创建正确的子集。 默认是true，系统编译会提示
        //The specified child already has a parent. You must call removeView() on the child's parent first.
        ViewGroup mContentView = (ViewGroup)inflater.inflate(R.layout.video,container,false);
        mScrollView = (ViewGroup)mContentView.findViewById(R.id.video_list);
        mShadow = (ViewGroup)mContentView.findViewById(R.id.shadow);
        Message msg = handler.obtainMessage();
        if(iServer==null)
        {
            msg.what = 0;
        }
        msg.sendToTarget();
        Log.i("tyty","video------>onCreateView");


        return mContentView;
    }

    private  void downLoadImg()
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                InputStream is = null;
                Bitmap bt = null;
                try
                {
                    byte [] datas = new byte[1024];
                    URL uri = new URL(MarketReference.WEB_URL_LINK+"/pic/yewen.jpg");
                    HttpURLConnection conn   = (HttpURLConnection) uri.openConnection();
                    conn.setDoInput(true);
                    //        conn.setUseCaches(true);
                    //        conn.setRequestMethod("GET");

                    conn.connect();
                    is = conn.getInputStream();
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    Rect rect = new Rect();
                    bt= BitmapFactory.decodeStream(is,rect,options);
                    Log.i("tyty","options.height = "+options.outHeight+"   options.width = " + options.outWidth);
                    Message msg = handler.obtainMessage();
                    msg.obj  = bt;
                    msg.what = 2;
                    msg.sendToTarget();
                    Log.i("tyty","img down finish");
                }catch (Exception e)
                {
                    Log.e("tyty",e.toString());
                }
            }
        }).start();




    }
    private void addFilmColumn( Bitmap bt)
{
    VideoData  videoData = iServer.getMarketDate().getmVideoData().get(0);
    ViewGroup itemView = (ViewGroup)inflater.inflate(R.layout.film_chunk,null);
    ImageView tempImageView = (ImageView)itemView.findViewById(R.id.film_photograph);
    TextView filmName = (TextView)itemView.findViewById(R.id.film_name);
    TextView filmInfo = (TextView)itemView.findViewById(R.id.film_info);

    Log.i("tyty",MarketReference.WEB_URL_LINK+"/pic/func.png");

//    InputStream is = null;
//    Bitmap bt = null;
//    try
//    {
//        byte [] datas = new byte[1024];
//        URL uri = new URL("http://image1.webscache.com/baike/haibao/mid/2012-05/203169726-61-2012-05-23-16-14-08.jpg");
//        HttpURLConnection conn   = (HttpURLConnection) uri.openConnection();
//        conn.setDoInput(true);
////        conn.setUseCaches(true);
////        conn.setRequestMethod("GET");
//
//        conn.connect();
//        is = conn.getInputStream();
//        bt= BitmapFactory.decodeStream(is);
//
//
//    }catch (Exception e)
//    {
//
//    }



    //tempImageView.setImageURI(uri); 直接使用uri 不能显示图片
    //tempImageView.setImageBitmap(bt);

    Log.i("tyty","height = "+ bt.getHeight()+"  width" +bt.getWidth());
    tempImageView.setImageBitmap(bt);

    //tempImageView.setImageResource(R.drawable.yewen);

    filmName.setText(videoData.getName());
    filmInfo.setText(videoData.getInfo());
    mScrollView.addView(itemView);

}
}
