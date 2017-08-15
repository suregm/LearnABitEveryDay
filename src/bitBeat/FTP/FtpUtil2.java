package bitBeat.FTP;

public class FtpUtil2 {
//    /**
//     * downFtpFileForUrl:(通过ftp url 下载ftp文件)
//     *
//     * @param ftpUrl
//     * @return 保存到本地的路径
//     * @throws Exception
//     */
//    public static String downFtpFileForUrl(String ftpUrl)throws Exception {
//
//        String url = "", username = "", password = "", remotePath = "", fileName = "", localPath = "";
//        int port = 21;
//        // 截取FTP地址
//        final String ftpFlag = "ftp://";
//        if (ftpUrl != null && ftpUrl.length() > 0
//                && ftpUrl.toLowerCase().contains(ftpFlag)) {
//            // 首先去掉FTP
//            final String cutedFtp = ftpUrl.substring(ftpUrl.indexOf(ftpFlag)
//                    + ftpFlag.length());
//            // 首先截取ip和端口
//            String ipAndPort = "";
//            if (cutedFtp.indexOf("/") != -1) {
//                ipAndPort = cutedFtp.substring(0, cutedFtp.indexOf("/"));
//            } else {
//                ipAndPort = cutedFtp;
//            }
//            // 开始获取ip和端口
//            if (!"".equals(ipAndPort)) {
//                if (ipAndPort.indexOf(":") != -1) {
//                    url = ipAndPort.substring(0, ipAndPort.indexOf(":"));
//                    String strPort = ipAndPort.substring(
//                            ipAndPort.indexOf(":") + 1, ipAndPort.length());
//                    if (strPort != null)
//                        port = Integer.parseInt(strPort);
//                } else {
//                    // 如果没有端口只获取IP
//                    url = ipAndPort;
//                }
//            }
//
//            // 截取ftp文件路径和文件名
//            String fileNameAndPath = "";
//            if (cutedFtp.indexOf("/") != -1) {
//                fileNameAndPath = cutedFtp.substring(cutedFtp.indexOf("/") + 1,
//                        cutedFtp.length());
//            } else {
//                fileNameAndPath = "";
//            }
//            // 开始获取ftp文件路径和文件名
//            if (!"".equals(ipAndPort)) {
//                if (fileNameAndPath.indexOf("/") != -1) {
//                    remotePath = fileNameAndPath.substring(0,
//                            fileNameAndPath.lastIndexOf("/"));
//                    fileName = fileNameAndPath.substring(
//                            fileNameAndPath.lastIndexOf("/") + 1,
//                            fileNameAndPath.length());
//                } else {
//                    fileName = fileNameAndPath;
//                }
//            }
//            // 获取本地上传路径
//            if (!"".equals(fileName)) {
//                localPath = Vutil.getFullFilePath(Constants.FTP_FILE_FLAG,fileName);
//            }
//
//            // 从配置文件中获取FTP账号密码
//            String userAndPass = "";
//            if (type != null && (type == 2 || type == 3)) {
//                userAndPass = Vutil
//                        .getPropertieValueForLocal("group_ftp_user_password");
//            } else {
//                userAndPass = Vutil
//                        .getPropertieValueForLocal("local_ftp_user_password");
//            }
//            // 开始截取FTP账号密码
//            if (!"".equals(userAndPass)) {
//                if (userAndPass.indexOf(":") != -1) {
//                    username = userAndPass.substring(0,
//                            userAndPass.indexOf(":"));
//                    password = userAndPass.substring(
//                            userAndPass.indexOf(":") + 1, userAndPass.length());
//                } else {
//                    username = userAndPass;
//                    password = username;
//                }
//            }
//            //开始下载文件
//            boolean isSuccess=downFtpFile(url, port, username, password, remotePath, fileName,
//                    localPath);
//            if(isSuccess)
//                return localPath;
//        }
//        return "";
//    }
//
//    /**
//     * Description: 从FTP服务器下载文件
//     *
//     * @Version1.0
//     * @param url
//     *            FTP服务器hostname
//     * @param port
//     *            FTP服务器端口
//     * @param username
//     *            FTP登录账号
//     * @param password
//     *            FTP登录密码
//     *
//     * @param remotePath
//     *            FTP服务器上的相对路径
//     *
//     * @param fileName
//     *            要下载的文件名
//     * @param localPath
//     *            下载后保存到本地的路径
//     * @return
//     */
//    public static boolean downFtpFile(String url, int port, String username,
//                                      String password, String remotePath, String fileName,
//                                      String localPath) {
//        boolean success = false;
//        FTPClient ftp = new FTPClient();
//        try {
//            int reply;
//            ftp.connect(url, port);
//            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//            ftp.login(username, password);// 登录
//            reply = ftp.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftp.disconnect();
//                return success;
//            }
//            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
//            FTPFile[] fs = ftp.listFiles();
//            for (FTPFile ff : fs) {
//                String fname = new String(ff.getName().getBytes("iso-8859-1"),
//                        "gbk");
//                if (fname.equals(fileName)) {
//                    File localFile = new File(localPath);
//                    OutputStream is = new FileOutputStream(localFile);
//                    ftp.retrieveFile(ff.getName(), is);
//                    is.close();
//                    break;
//                }
//            }
//            ftp.logout();
//            success = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (ftp.isConnected()) {
//                try {
//                    ftp.disconnect();
//                } catch (IOException ioe) {
//                }
//            }
//        }
//        return success;
//    }
//
//    public static void main(String[] args) {
//
//        final String ftpUrl = "ftp://192.168.0.111:21/test/aa/测试中文.doc";
//
//        try {
//            downFtpFileForUrl(ftpUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//    问题描述：
//    使用org.apache.commons.net.ftp.FTPClient创建中文目录、上传中文文件名时，目录名及文件名中的中文显示为“??”。
//    原因：
//    FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码。
//    解决方案：
//            1.将中文的目录或文件名转为iso-8859-1编码的字符。参考代码：
//    复制代码 代码如下:
//
//    String name="目录名或文件名";
//    name=new String(name.getBytes("GBK"),"iso-8859-1");// 转换后的目录名或文件名。
//
//2.设置linux环境变量
//    复制代码 代码如下:
//
//    export LC_ALL="zh_CN.GBK"
//    export LANG="zh_CN.GBK"
//
//    实例：
//    复制代码 代码如下:
//
//    public boolean upLoadFile(File file, String path, String fileName) throws IOException {
//        boolean result = false;
//        FTPClient ftpClient = new FTPClient();
//        try {
//            ftpClient.connect(confService.getConfValue(PortalConfContants.FTP_CLIENT_HOST));
//            ftpClient.login(confService.getConfValue(PortalConfContants.FTP_CLIENT_USERNAME), confService
//                    .getConfValue(PortalConfContants.FTP_CLIENT_PASSWORD));
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            // make directory
//            if (path != null && !"".equals(path.trim())) {
//                String[] pathes = path.split("/");
//                for (String onepath : pathes) {
//                    if (onepath == null || "".equals(onepath.trim())) {
//                        continue;
//                    }
//                    onepath=new String(onepath.getBytes("GBK"),"iso-8859-1");
//                    if (!ftpClient.changeWorkingDirectory(onepath)) {
//                        ftpClient.makeDirectory(onepath);
//                        ftpClient.changeWorkingDirectory(onepath);
//                    }
//                }
//            }
//            result = ftpClient.storeFile(new String(fileName.getBytes("GBK"),"iso-8859-1"), new FileInputStream(file));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ftpClient.logout();
//        }
//        return result;
//    }
//
//
//
//
//
//    java.net.URLEncoder.encode 下就Ok了。
}
