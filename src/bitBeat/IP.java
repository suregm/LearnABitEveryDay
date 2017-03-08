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
	public String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

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


}
