import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

import common.*;
import mail.*;
import webui.*;
import webui.http.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
	    Scanner sc=new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
	    while(sc.hasNext())
	    {
	    	String line=sc.nextLine();
	    	if(line.isEmpty())
	    		continue;
	    	String[] strs=line.split("=");
	    	switch(strs[0])
		    {
			    case "WEBROOT":
			    	Shared.WEBROOT=strs[1];
			    	break;
			    case "WEBADDRESS":
				    Shared.WEBADDRESS=strs[1];
				    break;
			    case "MAILROOT":
				    Shared.MAILROOT=strs[1];
				    break;
			    case "DBDRIVER":
				    Shared.DBDRIVER=strs[1];
				    break;
			    case "DBURL":
				    Shared.DBURL=strs[1];
				    break;
			    case "DBUSERNAME":
			        Shared.DBUSERNAME=strs[1];
			        break;
			    case "DBPASSWORD":
				    Shared.DBPASSWORD=strs[1];
				    break;
			    case "AUTOOPENBROWSER":
			    	Shared.AUTOOPENBROWSER=Boolean.parseBoolean(strs[1]);
		    }
	    }
	    Server server=new Server();
	    HTTPContainer container=new HTTPContainer();
	    container.env(new HashMap<>(){
		    {
			    put("webroot",Shared.WEBROOT);
			    put("exe",new HashMap<String,String>(){
				    {
				    	put("/accountadmin","AccountAdmin");
				    	put("/mailadmin","MailAdmin");
				    	put("/download","Download");
				    }
			    });
		    }
	    });
        server.add(Shared.WEBADDRESS,container);
	    if(Shared.AUTOOPENBROWSER&&Desktop.isDesktopSupported())
	    {
		    try
		    {
			    URI uri=URI.create("http://"+Shared.WEBADDRESS+"/index.html");
			    Desktop dp=Desktop.getDesktop();
			    if (dp.isSupported(Desktop.Action.BROWSE))
				    dp.browse(uri);
		    }catch(Exception e){e.printStackTrace();}
	    }
    }
}
