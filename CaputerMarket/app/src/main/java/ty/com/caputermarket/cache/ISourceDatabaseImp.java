package ty.com.caputermarket.cache;

/**
 * Created by Administrator on 2016/4/23.
 */
public interface ISourceDatabaseImp
{
    /*
    增、删、改/查
     */
    public void addSource(Object object);
    public void deleteSource(Object object);
    //public void addSource(Object object);
    public  Object findByURl(String url);


}
