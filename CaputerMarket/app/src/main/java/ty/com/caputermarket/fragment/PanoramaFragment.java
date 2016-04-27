package ty.com.caputermarket.fragment;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ty.com.caputermarket.R;

/**
 * Created by Administrator on 2016/3/23.
 */
public class PanoramaFragment extends Fragment
{
    ViewGroup mScrollView;
    ViewGroup mShadow;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.e("tyty","PanoramaFragment---->onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("tyty","PanoramaFragment---->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        Log.i("tyty","PanoramaFragment---->onCreateView");
        // 如果是 false，就是创建正确的子集。 默认是true，系统编译会提示
        //The specified child already has a parent. You must call removeView() on the child's parent first.
        ViewGroup mContentView = (ViewGroup)inflater.inflate(R.layout.video,container,false);
        mScrollView = (ViewGroup)mContentView.findViewById(R.id.video_list);
        mShadow = (ViewGroup)mContentView.findViewById(R.id.shadow);
        mShadow.setVisibility(View.GONE);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);
        addFilmColumn(inflater);

        return mContentView;
    }

    private void addFilmColumn(LayoutInflater inflater)
    {
        ViewGroup itemView = (ViewGroup)inflater.inflate(R.layout.film_chunk,null);
        ImageView tempImageView = (ImageView)itemView.findViewById(R.id.film_photograph);
        TextView filmName = (TextView)itemView.findViewById(R.id.film_name);
        TextView filmInfo = (TextView)itemView.findViewById(R.id.film_info);
        tempImageView.setImageResource(R.drawable.func);
        filmName.setText("func");
        filmInfo.setText("have a good day");
        mScrollView.addView(itemView);
    }
}
