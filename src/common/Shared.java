package common;

import java.util.concurrent.*;
public class Shared
{
	public static String WEBROOT,WEBADDRESS,MAILROOT,DBDRIVER, DBURL, DBUSERNAME, DBPASSWORD;
	public static boolean AUTOOPENBROWSER;
	public static final ExecutorService THREAD_POOL=Executors.newCachedThreadPool();
}
