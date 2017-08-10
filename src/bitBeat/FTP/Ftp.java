package bitBeat.FTP;

/**
 * ftp连接常量
 * // 引用三个jar包  commons-net-1.4.1.jar   jakarta-oro-2.0.8.jar  log4j-1.2.17.jar
 *
 */
public class Ftp {

    private String ipAddr;//ip地址

    private Integer port;//端口号

    private String userName;//用户名

    private String pwd;//密码

    private String path;//aaa路径

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
