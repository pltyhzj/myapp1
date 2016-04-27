package ty.com.ty.com.internet;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ObjectData
{
    private  String name;
    private  String info;
    private  String like;
    private  String path;

    public ObjectData(String name, String info, String like, String path)
    {
        this.name = name;
        this.info = info;
        this.like = like;
        this.path = path;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getLike()
    {
        return like;
    }

    public void setLike(String like)
    {
        this.like = like;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
}
