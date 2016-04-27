package ty.com.ty.com.internet;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/1.
 */
public class MarketDate
{
    ArrayList<VideoData> mVideoData;
    ArrayList<PanoramaData> mPanormaData;
    ArrayList<GameData> mGameData;

    public ArrayList<VideoData> getmVideoData()
    {
        return mVideoData;
    }

    public void setmVideoData(ArrayList<VideoData> mVideoData)
    {
        this.mVideoData = mVideoData;
    }

    public ArrayList<PanoramaData> getmPanormaData()
    {
        return mPanormaData;
    }

    public void setmPanormaData(ArrayList<PanoramaData> mPanormaData)
    {
        this.mPanormaData = mPanormaData;
    }

    public ArrayList<GameData> getmGameData()
    {
        return mGameData;
    }

    public void setmGameData(ArrayList<GameData> mGameData)
    {
        this.mGameData = mGameData;
    }

    public MarketDate(ArrayList<VideoData> mVideoData, ArrayList<PanoramaData> mPanormaData, ArrayList<GameData> mGameData)
    {

        this.mVideoData = mVideoData;
        this.mPanormaData = mPanormaData;
        this.mGameData = mGameData;
    }
}
