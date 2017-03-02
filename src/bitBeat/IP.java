package bitBeat;

import java.net.InetAddress;

/**
 * Created by sure GM on 2017/3/2 23:57.
 */
public class IP {

	InetAddress addr = InetAddress.getLocalHost();
	ip=addr.getHostAddress().toString;//获得本机IP
	address=addr.getHostName()toString;//获得本机名称

}
