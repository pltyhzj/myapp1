package ty.com.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ty.com.caputermarket.MarketReference;
import ty.com.ty.com.internet.GameData;
import ty.com.ty.com.internet.MarketDate;
import ty.com.ty.com.internet.MarketDateListener;
import ty.com.ty.com.internet.PanoramaData;
import ty.com.ty.com.internet.VideoData;

/**
 * Created by Administrator on 2016/3/31.
 */
public class Util
{
    public static boolean DEBUG = true;

    public static MarketDate parseMarketData(MarketDateListener marketDateListener)
    {
        ArrayList<VideoData> videoDatas = new ArrayList<VideoData>();
        ArrayList<PanoramaData> panoramaDatas = new ArrayList<PanoramaData>();
        ArrayList<GameData> gameDatas = new ArrayList<GameData>();

        MarketDate marketDate = new MarketDate(videoDatas, panoramaDatas, gameDatas);
        byte[] datas = new byte[1024];
        ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
        try
        {
            URL noteURL = new URL(MarketReference.NOTE_RUL);
            //incompatible 不相容的
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) noteURL.openConnection();
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setUseCaches(true);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.connect();
            InputStream is = mHttpURLConnection.getInputStream();
            //牲口！！！！！！！！！！！！！
            int len;

            while ((len = is.read(datas)) > 0)
            {
                Log.i("tyty", "http len = " + len);
                mByteArrayOutputStream.write(datas, 0, len);
                //http://tianyaopl.3vhost.net/note/note.xml
            }
            String comtent = new String(mByteArrayOutputStream.toByteArray(), "GBK");
            // Log.i("tyty","down :" + comtent);
            //  JSONObject jsonObject = new JSONObject(comtent);

            JSONArray jsonArray = new JSONArray(comtent);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonArray1 = jsonArray.getJSONObject(i);
                if (i == 0)
                {
                    String title = jsonArray1.getString(MarketReference.REFERENCE_STRING_TITLE);
                    String link = jsonArray1.getString(MarketReference.REFERENCE_STRING_LINK);
                    String info = jsonArray1.getString(MarketReference.REFERENCE_STRING_INFO);
                    String like = jsonArray1.getString(MarketReference.REFERENCE_STRING__LIKE);
                    if(DEBUG)
                    {
                        Log.i("tyty", title + " : " + link + " : " + info + " : " + like + " : ");
                    }

                    VideoData tempVideoData = new VideoData(title, info, like, link);
                    videoDatas.add(tempVideoData);
                }
                if (i == 1)
                {
                    String title = jsonArray1.getString(MarketReference.REFERENCE_STRING_TITLE);
                    String link = jsonArray1.getString(MarketReference.REFERENCE_STRING_LINK);
                    String info = jsonArray1.getString(MarketReference.REFERENCE_STRING_INFO);
                    String like = jsonArray1.getString(MarketReference.REFERENCE_STRING__LIKE);
                    PanoramaData tempPanoramaData = new PanoramaData(title, info, like, link);
                    panoramaDatas.add(tempPanoramaData);
                    if(DEBUG)
                    {
                        Log.i("tyty", title + " : " + link + " : " + info + " : " + like + " : ");
                    }

                }
            }

            ;
        } catch (Exception e)
        {
            Log.e("tyty", "http erro :" + e.toString());

        }
        marketDateListener.onFinishDownLoadData();
        return marketDate;
    }

}
