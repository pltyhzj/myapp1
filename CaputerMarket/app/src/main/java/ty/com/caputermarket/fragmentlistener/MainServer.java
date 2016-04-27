package ty.com.caputermarket.fragmentlistener;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import ty.com.ty.com.internet.MarketDate;
import ty.com.ty.com.internet.MarketDateListener;
import ty.com.util.Util;

/**
 * Created by Administrator on 2016/4/1.
 */
public class MainServer extends Service implements MarketDateListener
{
    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate()
    {

        super.onCreate();
    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
    }

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     * <p/>
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                marketDate = Util.parseMarketData(MainServer.this);
            }
        });
        thread.start();


        Log.i("tyty","onBind");
        return new MyBind();
    }
    private MarketDate marketDate;
    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    @Override
    public void onFinishDownLoadData()
    {
        hasData = true;
    }

    public class MyBind extends  Binder
    {
        public MarketDate getMarketDate()
        {
            return  marketDate;
        }
        public  boolean finishDownLoadData(){
            return hasData;
        }

    }

    private boolean hasData = false;
}
