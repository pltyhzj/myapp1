package ty.com.caputermarket;




import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.View;

import android.widget.Button;

import ty.com.caputermarket.cache.SourceCacheManager;
import ty.com.caputermarket.fragment.GameFragment;
import ty.com.caputermarket.fragment.LocalFragment;
import ty.com.caputermarket.fragment.PanoramaFragment;
import ty.com.caputermarket.fragment.VideoFragment;
import ty.com.caputermarket.fragmentlistener.MyFragmentListener;
import ty.com.ty.com.internet.MarketDate;

public class MainActivity extends FragmentActivity implements View.OnClickListener,MyFragmentListener
{
    //subclass fragment
    FragmentManager fm;
    Fragment video;
    Fragment panorama;
    Fragment game;
    Fragment local;
    int currentFragmentIndex;
    Fragment [] fragments;
    //title button
    Button titleVideoBT;
    Button titlePanoramaBT;
    Button titleGameBT;
    Button titleLocalBT;
    IBinder iService;

    private MarketDate marketDate;


private ServiceConnection serviceConnection = new ServiceConnection()
{
    @Override
    public void onServiceConnected(ComponentName name, IBinder service)
    {
        Log.i("tyty","onServiceConnected");
        iService  =service;
        initFragment();
        initTitleButton();
        setDefaultFragment();
        //marketDate =  myBind.getMarketDate();

    }

    @Override
    public void onServiceDisconnected(ComponentName name)
    {
        iService = null;
    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        Log.i("tyty","setOnClickListener");
        //开启获取网络数据（video,game,panorama ）列
        Intent intent = new Intent("ty.com.caputermarket.fragmentlistener.MainServer");
        this.bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        // 开启fragment
        fm = this.getSupportFragmentManager();
//        SourceCacheManager sourceCacheManager = new SourceCacheManager(this);
//        //sourceCacheManager.addSource(new Object());
//        sourceCacheManager.deleteSource("yewen");
//        sourceCacheManager.findByURl("yewen");
        Log.i("tyty","activity ----> onCreate");
    }

    @Override
    public void finish()
    {

        this.unbindService(serviceConnection);
        super.finish();
    }

    private  void initTitleButton()
    {
        titleVideoBT  = (Button)findViewById(R.id.title_video);
        titlePanoramaBT  = (Button)findViewById(R.id.title_panaroama);
        titleGameBT  = (Button)findViewById(R.id.title_game);
        titleLocalBT  = (Button)findViewById(R.id.title_local);
        titleVideoBT.setOnClickListener(this);
        titlePanoramaBT.setOnClickListener(this);
        titleGameBT.setOnClickListener(this);
        titleLocalBT.setOnClickListener(this);

    }
private  void initFragment()
{
    fragments = new Fragment[4];
    fragments[0] =video= new VideoFragment(iService);
    fragments[1] =panorama = new PanoramaFragment();
    fragments[2] = game = new GameFragment();
    fragments[3] =local = new LocalFragment();


}
    private void setDefaultFragment()
    {

        currentFragmentIndex = 0;
        FragmentTransaction ft = fm.beginTransaction();
//

         ft.replace(R.id.id_content, video);
        //ft.add(R.id.id_content,fragments[0],"v");
        //        ft.add(R.id.id_content,fragments[1],"p");
        //        ft.add(R.id.id_content,fragments[2],"g");
        //        ft.add(R.id.id_content,fragments[3],"l");
       // ft.addToBackStack("name");
        ft.commit();
    }
    /*
        移除替换
     */
    private  void replaceCurrentFragment(int f)
    {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.id_content, fragments[f]);


       // ft.addToBackStack("name");
        ft.commit();
    }

    private  void changeCurrentFragment(int formf)
    {
        FragmentTransaction ft = fm.beginTransaction();
        if(fragments[formf].isAdded())
        {
            ft.hide(fragments[currentFragmentIndex]).show(fragments[formf]).commit();

        }
        else
        {
            ft.hide(fragments[currentFragmentIndex]).add(R.id.id_content,fragments[formf]).commit();
        }
        currentFragmentIndex = formf;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
           case R.id.title_video:
               changeCurrentFragment(0);
            break;
            case R.id.title_panaroama:
                changeCurrentFragment(1);
                break;
            case R.id.title_game:
                changeCurrentFragment(2);
                break;
            case R.id.title_local:
                changeCurrentFragment(3);
                break;

            default:
                break;
        }
    }

    @Override
    public void onLeftFlip(int shiftX, boolean touch)
    {
        if(touch)
        {
            if(currentFragmentIndex!=0)
            {
                currentFragmentIndex--;
            }
            //replaceCurrentFragment(fragments[currentFragmentIndex]);
        }
        else
        {

        }
    }

    @Override
    public void onRightFlip(int shiftX, boolean touch)
    {
        if(touch)
        {
            if(currentFragmentIndex!=3)
            {
                currentFragmentIndex++;
            }
           // replaceCurrentFragment(fragments[currentFragmentIndex]);
        }
        else
        {

        }
    }
}
