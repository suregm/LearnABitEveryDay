package bitBeat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sure GM on 2017/3/2 23:57.
 */
public class IP {

	public IP() throws SocketException {
	}

	public static void main(String[] args) {
		easyIP();

		InetAddress google = null;
		try {
			google = InetAddress.getByName("www.google.com");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("google : " + google);

		System.out.println(getLocalIPByCMD());
		System.out.println("");

		System.out.println(getLocalIPByJava());
		System.out.println("");

		getSystemProperties();
	}

	/**
	 * 最常用方法，但是不推荐，如果开vpn或者添加多网卡后获得的是小网ip或是第一个ip
	 *
	 * @return
	 */
	private static String easyIP() {
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String localIP = ia.getHostAddress().toString();    //获得本机IP
		String localName = ia.getHostName().toString();     //获得本机名称
		System.out.println("HostIP: " + localIP);
		System.out.println("HostName: " + localName);
		System.out.println("");
		return localIP;
	}

	/**
	 * 获取本机的IP
	 * @return Ip地址
	 */
	public static String getLocalHostIP() {
		String ip;
		try {
			/**返回本地主机。*/
			InetAddress addr = InetAddress.getLocalHost();
			/**返回 IP 地址字符串（以文本表现形式）*/
			ip = addr.getHostAddress();
		} catch(Exception ex) {
			ip = "";
		}

		return ip;
	}

	/**
	 * 或者主机名：
	 * @return
	 */
	public static String getLocalHostName() {
		String hostName;
		try {
			/**返回本地主机。*/
			InetAddress addr = InetAddress.getLocalHost();
			/**获取此 IP 地址的主机名。*/
			hostName = addr.getHostName();
		}catch(Exception ex){
			hostName = "";
		}

		return hostName;
	}

	/**
	 * 获得本地所有的IP地址
	 * @return
	 */
	public static String[] getAllLocalHostIP() {

		String[] ret = null;
		try {
			/**获得主机名*/
			String hostName = getLocalHostName();
			if(hostName.length()>0) {
				/**在给定主机名的情况下，根据系统上配置的名称服务返回其 IP 地址所组成的数组。*/
				InetAddress[] addrs = InetAddress.getAllByName(hostName);
				if(addrs.length>0) {
					ret = new String[addrs.length];
					for(int i=0 ; i< addrs.length ; i++) {
						/**.getHostAddress()   返回 IP 地址字符串（以文本表现形式）。*/
						ret[i] = addrs[i].getHostAddress();
					}
				}
			}

		}catch(Exception ex) {
			ret = null;
		}

		return ret;
	}


	/**
	 * 命令行findstr方法，获得多组ip，需要再次筛选
	 *
	 * @return
	 */
	// 方法一，使用CMD命令：
	public static String getLocalIPByCMD() {
		StringBuilder sb = new StringBuilder();
		String command = "cmd.exe /c ipconfig | findstr IPv4";
		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.substring(line.lastIndexOf(":") + 2, line.length());
				sb.append(line + " ");
			}
			br.close();
			p.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "getLocalIPByCMD： " + sb.toString();
	}

	/**
	 * 通过NetworkInterface获取网络属性及判断条件inetAddress.isSiteLocalAddress()进行筛选，强烈推荐
	 *
	 * @return
	 */
	// 方法二，使用Java方法： https://zhidao.baidu.com/question/241423332.html?fr=iks&word=java+%B1%BE%BB%FAip&ie=gbk
	public static String getLocalIPByJava() {
		StringBuilder sb = new StringBuilder();
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface intf = (NetworkInterface) en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						sb.append(inetAddress.getHostAddress().toString() + " ");
					}
				}
			}
		} catch (SocketException e) {
		}
		return "getLocalIPByJava： " + sb.toString();
	}

	public static void getSystemProperties() {
		Properties properties = System.getProperties();
		Set<String> set = properties.stringPropertyNames(); //获取java虚拟机和系统的信息。
		for (String name : set) {
			System.out.println(name + ":" + properties.getProperty(name));
		}
	}

	// 在 Windows 和 Linux下正确执行的代码如下：
	public static void getLocalIPForWindowsAndLinux() {
		Enumeration allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		InetAddress ip = null;
		while(allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			System.out.println(netInterface.getName());
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					System.out.println("本机的IP = " + ip.getHostAddress());
				}
			}
		}
	}

	// 获取linux系统本地ip
	/**
	 * Get host IP address
	 *
	 * @return IP Address
	 */
	private InetAddress getAddress() {
		try {
			for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
				NetworkInterface networkInterface = interfaces.nextElement();
				if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
					continue;
				}
				Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
				if (addresses.hasMoreElements()) {
					return addresses.nextElement();
				}
			}
		} catch (Exception e) {
			Logger.getLogger("").log(Level.INFO, "Error when getting host ip address: <{}>.");
		}
		return null;
	}


	// 在JSP里，获取客户端的IP地址的方法是：request.getRemoteAddr（），这种方法在大部分情况下都是有效的。但是在通过了Apache，Squid等反向代理软件就不能获取到客户端的真实IP地址了。
	// 经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。当我们访问http://www.javapeixun.com.cn /index.jsp/ 时，其实并不是我们浏览器真正访问到了服务器上的index.jsp文件，而是先由代理服务器去访问http://192.168.1.110：2046/index.jsp ，代理服务器再将访问到的结果返回给我们的浏览器，因为是代理服务器去访问index.jsp的，所以index.jsp中通过request.getRemoteAddr（）的方法获取的IP实际上是代理服务器的地址，并不是客户端的IP地址。
	// 获取远程服务器的ip地址
	public String getRemortIP(javax.servlet.http.HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	// request.getRemoteAddr()这种方法在大部分情况下获得的IP都是有效的。但是在客户通过向代理软件就不能获取到客户端的真实IP地址了。经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。所以想要获得客户端的真正IP就要先判断一下request.getHeader();
	// squid.conf 的配制文件　forwarded_for 项默认是为on，如果 forwarded_for 设成了 off 　则：X-Forwarded-For： unknown
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}



	// 一般通过反向代理服务器的客户端请求，代理服务器都会将客户端源 IP 地址附加在原始的 HTTP 请求头上，非标准协议的代理源 IP 地址请求头有 X-Forwarded-For、X-Real-Ip 等，可以直接依据优先级从这些 HTTP 头获取数据，如果实现在获取不到的话，再从 HttpServletRequest 的 getRemoteAddr() 方法中获取。
	/**
	 * <p>
	 * Web 服务器反向代理中用于存放客户端原始 IP 地址的 Http header 名字，
	 * 若新增其他的需要增加或者修改其中的值。
	 * </p>
	 */
	private static final String[] PROXY_REMOTE_IP_ADDRESS = { "X-Forwarded-For", "X-Real-IP" };
	/**
	 * <p>
	 * 获取请求的客户端的 IP 地址。若应用服务器前端配有反向代理的 Web 服务器，
	 * 需要在 Web 服务器中将客户端原始请求的 IP 地址加入到 HTTP header 中。
	 * 详见 {@link #PROXY_REMOTE_IP_ADDRESS}
	 * </p>
	 */
	public static String getRemoteIp( HttpServletRequest request ) {
		for ( int i = 0 ; i < PROXY_REMOTE_IP_ADDRESS.length ; i++ ) {
			String ip = request.getHeader( PROXY_REMOTE_IP_ADDRESS[i] );
			if ( ip != null && ip.trim().length > 0 ) {
				return getRemoteIpFromForward( ip.trim() );
			}
		}
		return request.getRemoteHost();
	}

	/**
	 * <p>
	 * 从 HTTP Header 中截取客户端连接 IP 地址。如果经过多次反向代理，
	 * 在请求头中获得的是以“,&lt;SP&gt;”分隔 IP 地址链，第一段为客户端 IP 地址。
	 * </p>
	 *
	 * @param xforwardIp 从 HTTP 请求头中获取转发过来的 IP 地址链
	 * @return 客户端源 IP 地址
	 */
	private static String getRemoteIpFromForward( String xforwardIp ) {
		int commaOffset = xforwardIp.indexOf( ',' );
		if ( commaOffset < 0 ) {
			return xforwardIp;
		}
		return xforwardIp.substring( 0 , commaOffset );
	}





/*
	需要注意的是X-Forwarded-For和X-Real-IP都不是http的正式协议头，而是squid等反向代理软件最早引入的，之所以resin能拿到，是因为NGINX里一般缺省都会这么配置转发的http请求：
	location / {
		proxy_pass       http://yourdomain.com;
		proxy_set_header   Host             $host;
		proxy_set_header   X-Real-IP        $remote_addr;
		proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
	}，从X-Forwarded-For的定义来看，ips[0]才是原始客户端ip，如果这个都不是，那拿第二个就更不靠谱了，我们平时检验的时候，可能是直接在内网挂代理去访问的，跟外面网友访问经过的网络路径不一样，后面不停添加的是经过的每一层代理ip才对,下面举例说明;
	request.getRemoteAddr() 192.168.239.196
		request.getHeader("X-Forwarded-For") 58.63.227.162, 192.168.237.178, 192.168.238.218
		request.getHeader("X-Real-IP") 192.168.238.218
	所以访问的流程应该是这样，客户端58.63.227.162发出请求，经过192.168.237.178, 192.168.238.218两层转发，到了192.168.239.196这台NGINX上，NGINX就把X-Real-IP头设成了自己看到的remote_addr，也就是直接发给到他的192.168.238.218，这时候resin收到这个包，对resin来说直接发给他的remote_addr就是NGINX的ip，也就是192.168.239.196，那么resin里面的request.getRemoteAddr()就是192.168.239.196，那么在resin里拿最原始的ip逻辑（也就是拿能够知道的最外层的ip）应该是这样：
	如果XFF不为空，拿XFF的左边第一个
	如果XFF为空，拿XRI
	如果XRI为空，只能拿request.getRemoteAddr()，也就是只能拿到最直接发给他的机器ip了，
*/

	// 第一种代码：
	/**
	 * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
	 * @param request
	 * @return ip
	 */
	public static String getLocalIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");

		String ip = null;
		if (realIp == null) {
			if (forwarded == null) {
				ip = remoteAddr;
			} else {
				ip = remoteAddr + "/" + forwarded.split(",")[0];
			}
		} else {
			if (realIp.equals(forwarded)) {
				ip = realIp;
			} else {
				if(forwarded != null){
					forwarded = forwarded.split(",")[0];
				}
				ip = realIp + "/" + forwarded;
			}
		}
		return ip;
	}

	// 第二种代码:
	public static String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");

		String ip = null;
		if (realIp == null) {
			if (forwarded == null) {
				ip = remoteAddr;
			} else {
				ip = remoteAddr + "/" + forwarded;
			}
		} else {
			if (realIp.equals(forwarded)) {
				ip = realIp;
			} else {
				ip = realIp + "/" + forwarded.replaceAll(", " + realIp, "");
			}
		}
		return ip;
	}

	// 第三种代码:
	public static String getIp2(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		return request.getRemoteAddr();
	}
	// 第三种是最合适的，最清晰理解的
}
