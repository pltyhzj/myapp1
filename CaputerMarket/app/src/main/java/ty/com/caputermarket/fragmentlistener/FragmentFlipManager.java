package ty.com.caputermarket.fragmentlistener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/3/26.
 */
public class FragmentFlipManager implements GestureDetector.OnGestureListener
{
    MyFragmentListener myFragmentListener;

    GestureDetector mGestureDetector ;
    public  FragmentFlipManager(Context c)
    {
        mGestureDetector = new GestureDetector(c,this);
    }
    public  void registerMyFragmentListener(MyFragmentListener listener)
    {
    this.myFragmentListener = listener;
    }

    @Override
    public boolean onDown(MotionEvent e)
    {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e)
    {

    }
}
