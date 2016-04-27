package ty.com.caputermarket.fragment;

import android.support.v4.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ty.com.caputermarket.R;

/**
 * Created by Administrator on 2016/3/23.
 */
public class GameFragment extends Fragment
{
    //主要控件
    private ListView gameList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.e("tyty","PanoramaFragment---->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.e("tyty","PanoramaFragment---->onCreateView");
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.game, container, false);
        gameList = (ListView) contentView.findViewById(R.id.game_list);
        mGameListAdapter = new GameListAdapter(inflater);
        gameList.setAdapter(mGameListAdapter);
        return contentView;
    }

    private GameListAdapter mGameListAdapter;
}

class GameListAdapter extends BaseAdapter
{
    private LayoutInflater currentLayoutInflater;

    private App[] items;

    public GameListAdapter(LayoutInflater currentLayoutInflater)
    {
        this.currentLayoutInflater = currentLayoutInflater;
        items = new App[4];
        items[0] = new App("app1","info1","like1");
        items[1] = new App("app2","info2","like2");
        items[2] = new App("app3","info3","like3");
        items[3] = new App("app4","info4","like4");

    }

    @Override
    public int getCount()
    {
        return items.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MyRush tempRush;
        if (convertView == null)
        {
            ViewGroup viewGroupTemp = (ViewGroup) currentLayoutInflater.inflate(R.layout.game_list_item, null);
            ImageView image = (ImageView) viewGroupTemp.findViewById(R.id.game_app_icon);
            TextView name = (TextView) viewGroupTemp.findViewById(R.id.game_app_name);
            TextView info = (TextView) viewGroupTemp.findViewById(R.id.game_app_info);
            TextView like = (TextView) viewGroupTemp.findViewById(R.id.game_app_like);
            Button button = (Button) viewGroupTemp.findViewById(R.id.game_app_down);
            tempRush = new MyRush(image, name, info, like, button);
            convertView = viewGroupTemp;
            convertView.setTag(tempRush);

        } else
        {
            tempRush = (MyRush) convertView.getTag();
        }
        tempRush.image.setImageResource(R.drawable.text);
        tempRush.tv_name.setText(items[position].getName());
        tempRush.tv_info.setText(items[position].getInfo());
        tempRush.tv_like.setText(items[position].getLike());
        return convertView;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public Object getItem(int position)
    {
        return items[position];
    }

    class MyRush
    {
        public ImageView image;
        public TextView tv_name;
        public TextView tv_info;
        public TextView tv_like;
        public Button button;

        MyRush(ImageView image, TextView tv_name, TextView tv_info, TextView tv_like, Button button)
        {
            this.image = image;
            this.tv_name = tv_name;
            this.tv_info = tv_info;
            this.tv_like = tv_like;
            this.button = button;
        }

    }
}

class App
{
    private String name;
    private String info;
    private String like;
public App(String name,String info,String like)
{
    this.name = name;
    this.info = info;
    this.like = like;
}
    public void setName(String name)
    {
        this.name = name;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public void setLike(String like)
    {
        this.like = like;
    }
    public  String getName()
    {
        return  this.name;
    }
    public  String getInfo()
    {
        return  this.info;
    }
    public  String getLike()
    {
        return  this.like;
    }
}
