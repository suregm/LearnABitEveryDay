package bitBeat.FTP;

public class FtpUtil {

    private static Logger logger=Logger.getLogger(FtpUtil.class);

    private static FTPClient ftp;

    windows的ftp服务器，是用gbk来处理的，Linux的是utf-8，原来只需要在访问ftp前设置一下系统编码就OK了

[java] view plain copy
    String(makedir.toString().getBytes("GBK"), "iso-8859-1")


    /**
     * 获取FTP连接
     * @param f
     * @return
     * @throws Exception
     */
    public static boolean connectFtp(Ftp f) throws Exception{
        ftp=new FTPClient();
        boolean flag=false;
        int reply;
        if (f.getPort()==null) {
            ftp.connect(f.getIpAddr(),21);
        }else{
            ftp.connect(f.getIpAddr(),f.getPort());
        }
        ftp.login(f.getUserName(), f.getPwd());
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return flag;
        }
        ftp.changeWorkingDirectory(f.getPath());
        flag = true;
        return flag;
    }

    /**
     * 连接到服务器
     *
     * @return true 连接服务器成功，false 连接服务器失败
     */
    public boolean connectServer() {
        boolean flag = true;
        if (ftpClient == null) {
            int reply;
            try {
                if(setArg(configFile)){
                    ftpClient = new FTPClient();
                    ftpClient.setControlEncoding("GBK");
                    //ftpClient.configure(getFtpConfig());
                    ftpClient.connect(ip,port);
                    ftpClient.login(userName, password);
                    reply = ftpClient.getReplyCode();
                    ftpClient.setDataTimeout(120000);

                    if (!FTPReply.isPositiveCompletion(reply)) {
                        ftpClient.disconnect();
                        logger.debug("FTP 服务拒绝连接！");
                        flag = false;
                    }
                    i++;
                }else{
                    flag = false;
                }
            } catch (SocketException e) {
                flag = false;
                e.printStackTrace();
                logger.debug("登录ftp服务器 " + ip + " 失败,连接超时！");
            } catch (IOException e) {
                flag = false;
                e.printStackTrace();
                logger.debug("登录ftp服务器 " + ip + " 失败，FTP服务器无法打开！");
            }
        }
        return flag;
    }

    /**
     * 关闭FTP连接
     */
    public static void closeFtp(){
        if (ftp!=null && ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp上传文件
     * @param f
     * @throws Exception
     */
    public static void upload(File f) throws Exception{
        if (f.isDirectory()) {
            ftp.makeDirectory(f.getName());
            ftp.changeWorkingDirectory(f.getName());
            String[] files=f.list();
            for(String fstr : files){
                File file1=new File(f.getPath()+"/"+fstr);
                if (file1.isDirectory()) {
                    upload(file1);
                    ftp.changeToParentDirectory();
                }else{
                    File file2=new File(f.getPath()+"/"+fstr);
                    FileInputStream input=new FileInputStream(file2);
                    ftp.storeFile(file2.getName(),input);
                    input.close();
                }
            }
        }else{
            File file2=new File(f.getPath());
            FileInputStream input=new FileInputStream(file2);
            ftp.storeFile(file2.getName(),input);
            input.close();
        }
    }

    /**
     * 上传文件
     *
     * @param remoteFile
     *            远程文件路径,支持多级目录嵌套
     * @param localFile
     *            本地文件名称，绝对路径
     *
     */
    public boolean uploadFile(String remoteFile, File localFile)
            throws IOException {
        boolean flag = false;
        InputStream in = new FileInputStream(localFile);
        String remote = new String(remoteFile.getBytes("GBK"),"iso-8859-1");
        if(ftpClient.storeFile(remote, in)){
            flag = true;
            logger.debug(localFile.getAbsolutePath()+"上传文件成功！");
        }else{
            logger.debug(localFile.getAbsolutePath()+"上传文件失败！");
        }
        in.close();
        return flag;
    }

    /**
     * 下载连接配置
     * @param f
     * @param localBaseDir 本地目录
     * @param remoteBaseDir 远程目录
     * @throws Exception
     */
    public static void startDown(Ftp f,String localBaseDir,String remoteBaseDir ) throws Exception{
        if (FtpUtil.connectFtp(f)) {

            try {
                FTPFile[] files = null;
                boolean changedir = ftp.changeWorkingDirectory(remoteBaseDir);
                if (changedir) {
                    ftp.setControlEncoding("iso-8859-1");//注意编码格式
                    FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
                    conf.setServerLanguageCode("zh");//中文
                    files = ftp.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        try{

                            downloadFile(files[i], localBaseDir, remoteBaseDir);
                        }catch(Exception e){
                            logger.error(e);
                            logger.error("<"+files[i].getName()+"下载失败");
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                logger.error("下载过程中出现异常");
            }
        }else{
            logger.error("连接失败!");
        }

    }


    /**
     *
     * 下载FTP文件
     * 当你需要下载FTP文件的时候，调用此方法
     * 根据--获取的文件名，本地地址，远程地址--进行下载
     * @param ftpFile
     * @param relativeLocalPath
     * @param relativeRemotePath
     * @throws Exception
     */
    private  static void downloadFile(FTPFile ftpFile, String relativeLocalPath,String relativeRemotePath) throws Exception {



        if (ftpFile.isFile()) {

            String filename=new String(ftpFile.getName().getBytes("iso-8859-1"), "utf-8");//涉及到中文文件
            System.out.println(filename);

            if (ftpFile.getName().indexOf("?") == -1) {
                OutputStream outputStream = null;
                try {
                    File locaFile= new File(relativeLocalPath+ filename);
                    //若文件已存在则返回
                    if(locaFile.exists()){
                        System.out.println("提示：目标文件已存在！！！！");
                        return;
                    }else{
                        outputStream = new FileOutputStream(relativeLocalPath+ filename);

                        ftp.retrieveFile(ftpFile.getName(), outputStream);
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (Exception e) {
                    logger.error(e);
                } finally {
                    try {
                        if (outputStream != null){
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        logger.error("输出文件流异常");
                    }
                }
            }
        } else {
            String newlocalRelatePath = relativeLocalPath + ftpFile.getName();
            String newRemote = new String(relativeRemotePath+ ftpFile.getName().toString());
            File fl = new File(newlocalRelatePath);
            if (!fl.exists()) {
                fl.mkdirs();
            }
            try {
                newlocalRelatePath = newlocalRelatePath + '/';
                newRemote = newRemote + "/";
                String currentWorkDir = ftpFile.getName().toString();
                boolean changedir = ftp.changeWorkingDirectory(currentWorkDir);
                if (changedir) {
                    FTPFile[] files = null;
                    files = ftp.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        downloadFile(files[i], newlocalRelatePath, newRemote);
                    }
                }
                if (changedir){
                    ftp.changeToParentDirectory();
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }


    public static void main(String[] args) throws Exception{
        Ftp f=new Ftp();
        //ip地址
        f.setIpAddr("36.110.85.10");
        //ftp用户名
        f.setUserName("p_center");
        //ftp密码
        f.setPwd("ea65db2fa7f45672");
        //端口号
        f.setPort(21);
        //
        FtpUtil.connectFtp(f);
        //File file = new File("F:/test/com/test/Testng.java");
        //FtpUtil.upload(file);//
        String local = "f:/";
        String remote ="/大连华讯/邮币卡";
        String remoteUrl = new String(remote.getBytes("utf-8"), "iso-8859-1");//涉及到中文问题 根据系统实际编码改变
        FtpUtil.startDown(f, local,  remoteUrl);
        System.out.println("ok");
    }

    /**
     * 上传单个文件，并重命名
     *
     * @param localFile--本地文件路径
     * @param localRootFile--本地文件父文件夹路径
     * @param distFolder--新的文件名,可以命名为空""
     * @return true 上传成功，false 上传失败
     * @throws IOException
     */
    public boolean uploadFile(String local, String remote) throws IOException {
        boolean flag = true;
        String remoteFileName = remote;
        if (remote.contains("/")) {
            remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
            // 创建服务器远程目录结构，创建失败直接返回
            if (!CreateDirecroty(remote)) {
                return false;
            }
        }
        FTPFile[] files = ftpClient.listFiles(new String(remoteFileName));
        File f = new File(local);
        if(!uploadFile(remoteFileName, f)){
            flag = false;
        }
        return flag;
    }

    /**
     * 上传文件夹内的所有文件
     *
     *
     * @param filename
     *       本地文件夹绝对路径
     * @param uploadpath
     *       上传到FTP的路径,形式为/或/dir1/dir2/../
     * @return true 上传成功，false 上传失败
     * @throws IOException
     */
    public List uploadManyFile(String filename, String uploadpath) {
        boolean flag = true;
        List l = new ArrayList();
        StringBuffer strBuf = new StringBuffer();
        int n = 0; //上传失败的文件个数
        int m = 0; //上传成功的文件个数
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            ftpClient.changeWorkingDirectory("/");
            File file = new File(filename);
            File fileList[] = file.listFiles();
            for (File upfile : fileList) {
                if (upfile.isDirectory()) {
                    uploadManyFile(upfile.getAbsoluteFile().toString(),uploadpath);
                } else {
                    String local = upfile.getCanonicalPath().replaceAll("\\\\","/");
                    String remote = uploadpath.replaceAll("\\\\","/") + local.substring(local.indexOf("/") + 1);
                    flag = uploadFile(local, remote);
                    ftpClient.changeWorkingDirectory("/");
                }
                if (!flag) {
                    n++;
                    strBuf.append(upfile.getName() + ",");
                    logger.debug("文件［" + upfile.getName() + "］上传失败");
                } else{
                    m++;
                }
            }
            l.add(0, n);
            l.add(1, m);
            l.add(2, strBuf.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.debug("本地文件上传失败！找不到上传文件！", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("本地文件上传失败！", e);
        }
        return l;
    }

    /**
     * 下载文件
     *
     * @param remoteFileName             --服务器上的文件名
     * @param localFileName--本地文件名
     * @return true 下载成功，false 下载失败
     */
    public boolean loadFile(String remoteFileName, String localFileName) {
        boolean flag = true;
        // 下载文件
        BufferedOutputStream buffOut = null;
        try {
            buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
            flag = ftpClient.retrieveFile(remoteFileName, buffOut);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("本地文件下载失败！", e);
        } finally {
            try {
                if (buffOut != null)
                    buffOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 删除一个文件
     */
    public boolean deleteFile(String filename) {
        boolean flag = true;
        try {
            flag = ftpClient.deleteFile(filename);
            if (flag) {
                logger.debug("删除文件"+filename+"成功！");
            } else {
                logger.debug("删除文件"+filename+"成功！");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除目录
     */
    public void deleteDirectory(String pathname) {
        try {
            File file = new File(pathname);
            if (file.isDirectory()) {
                File file2[] = file.listFiles();
            } else {
                deleteFile(pathname);
            }
            ftpClient.removeDirectory(pathname);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 删除空目录
     */
    public void deleteEmptyDirectory(String pathname) {
        try {
            ftpClient.removeDirectory(pathname);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 列出服务器上文件和目录
     *
     * @param regStr --匹配的正则表达式
     */
    public void listRemoteFiles(String regStr) {
        try {
            String files[] = ftpClient.listNames(regStr);
            if (files == null || files.length == 0)
                logger.debug("没有任何文件!");
            else {
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出Ftp服务器上的所有文件和目录
     */
    public void listRemoteAllFiles() {
        try {
            String[] names = ftpClient.listNames();
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void closeConnect() {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置传输文件的类型[文本文件或者二进制文件]
     *
     * @param fileType--BINARY_FILE_TYPE、ASCII_FILE_TYPE
     *
     */
    public void setFileType(int fileType) {
        try {
            ftpClient.setFileType(fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置参数
     *
     * @param configFile --参数的配置文件
     */
    private boolean setArg(String configFile) {
        boolean flag = true;
        property = new Properties();
        BufferedInputStream inBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(getClass().getResource("/").getPath() + configFile));
            property.load(inBuff);
            userName = property.getProperty("username");
            password = property.getProperty("password");
            ip = property.getProperty("ip");
            port = Integer.parseInt(property.getProperty("port"));
        } catch (FileNotFoundException e1) {
            flag = false;
            logger.debug("配置文件 " + configFile + " 不存在！");
        } catch (IOException e) {
            flag = false;
            logger.debug("配置文件 " + configFile + " 无法读取！");
        }
        return flag;
    }



    /**
     * 进入到服务器的某个目录下
     *
     * @param directory
     */
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                logger.debug("进入文件夹"+ directory + " 成功！");

            } else {
                logger.debug("进入文件夹"+ directory + " 失败！");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    /**
     * 返回到上一层目录
     */
    public void changeToParentDirectory() {
        try {
            ftpClient.changeToParentDirectory();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 重命名文件
     *
     * @param oldFileName --原文件名
     * @param newFileName --新文件名
     */
    public void renameFile(String oldFileName, String newFileName) {
        try {
            ftpClient.rename(oldFileName, newFileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 设置FTP客服端的配置--一般可以不设置
     *
     * @return ftpConfig
     */
    private FTPClientConfig getFtpConfig() {
        FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
        return ftpConfig;
    }

    /**
     * 转码[ISO-8859-1 -> GBK] 不同的平台需要不同的转码
     *
     * @param obj
     * @return ""
     */
    private String iso8859togbk(Object obj) {
        try {
            if (obj == null)
                return "";
            else
                return new String(obj.toString().getBytes("iso-8859-1"), "GBK");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 在服务器上创建一个文件夹
     *
     * @param dir 文件夹名称，不能含有特殊字符，如 \ 、/ 、: 、* 、?、 "、 <、>...
     */
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                logger.debug("创建文件夹"+ dir + " 成功！");

            } else {
                logger.debug("创建文件夹"+ dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 检查路径是否存在，存在返回true，否则false
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
       /* for (FTPFile ftpFile : ftpFileArr) {
            if (ftpFile.isDirectory()
                    && ftpFile.getName().equalsIgnoreCase(path)) {
                flag = true;
                break;
            }
        } */
        if(ftpFileArr.length > 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 递归创建远程服务器目录
     *
     * @param remote
     *            远程服务器文件绝对路径
     *
     * @return 目录创建是否成功
     * @throws IOException
     */
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/")&& !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"),"iso-8859-1");
                if (changeWorkingDirectory(subDirectory)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        logger.debug("创建目录["+subDirectory+"]失败");
                        System.out.println("创建目录["+subDirectory+"]失败");
                        success = false;
                        return success;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    public static void main(String[] args) {
        FTPClientTest ftpClient = new FTPClientTest();
        if(ftpClient.connectServer()){
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);// 设置传输二进制文件
            ftpClient.uploadManyFile("H:\\d", "/d/");
            ftpClient.closeConnect();// 关闭连接
        }
    }























    private static final Logger logger = Logger.getLogger(FTPUtil.class);
    private static String encoding = System.getProperty("file.encoding");

    FTPClient client;

    /** ftp服务器地址 */
    private String host;
    /** ftp 端口号 默认21 */
    private int port = 21;
    /** ftp服务器用户名 */
    private String username;
    /** ftp服务器密码 */
    private String password;
    /** ftp远程目录 */
    private String remoteDir;
    /** 本地存储目录 */
    private String localDir;
    /** 文件路径通配符 默认列出所有*/
    private String regEx = "*";
    /** 指定要下载的文件名 */
    private String downloadFileName;

    /**
     * 设置连接属性
     *
     * @param host
     * @param username
     * @param password
     * @return
     */
    public FTPUtil setConfig(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        return this;
    }

    /**
     * 设置连接属性
     *
     * @param host
     * @param port
     * @param username
     * @param password
     */
    public FTPUtil setConfig(String host, int port, String username,String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        return this;
    }

    /**
     * 连接FTP服务器
     */
    private FTPUtil connectServer() {
        client = new FTPClient();
        //设置超时时间
        client.setConnectTimeout(30000);
        try {
            // 1、连接服务器
            if(!client.isConnected()){
                // 如果采用默认端口，可以使用client.connect(host)的方式直接连接FTP服务器
                client.connect(host, port);
                // 登录
                client.login(username, password);
                // 获取ftp登录应答码
                int reply = client.getReplyCode();
                // 验证是否登陆成功
                if (!FTPReply.isPositiveCompletion(reply)) {
                    logger.info("未连接到FTP，用户名或密码错误。");
                    client.disconnect();
                    throw new RuntimeException("未连接到FTP，用户名或密码错误。");
                } else {
                    logger.info("FTP连接成功。IP:"+host +"PORT:" +port);
                }
                // 2、设置连接属性
                client.setControlEncoding(encoding);
                // 设置以二进制方式传输
                client.setFileType(FTPClient.BINARY_FILE_TYPE);
                client.enterLocalPassiveMode();
            }
        } catch (SocketException e) {
            try {
                client.disconnect();
            } catch (IOException e1) {
            }
            logger.error("连接FTP服务器失败" + e.getMessage());
            throw new RuntimeException("连接FTP服务器失败" + e.getMessage());
        } catch (IOException e) {
        }
        return this;
    }


    /**
     * 下载文件
     */
    public List<File> download(){

        List<File> files = null;

        this.connectServer();
        InputStream is = null;
        File downloadFile = null;
        try {
            // 1、设置远程FTP目录
            client.changeWorkingDirectory(remoteDir);
            logger.info("切换至工作目录【" + remoteDir + "】");
            // 2、读取远程文件
            FTPFile[] ftpFiles = client.listFiles(regEx);
            if(ftpFiles.length==0) {
                logger.warn("文件数为0，没有可下载的文件！");
                return null;
            }
            logger.info("准备下载" + ftpFiles.length + "个文件");
            // 3、保存文件到本地
            for (FTPFile file : ftpFiles) {
                //如果有指定下载的文件
                if(StringUtils.isNotBlank(downloadFileName) && !file.getName().equals(downloadFileName)){
                    continue;
                }
                if(files == null) files = new ArrayList<File>();
                is = client.retrieveFileStream(file.getName());
                if(is==null) throw new RuntimeException("下载失败，检查文件是否存在");
                downloadFile = new File(localDir + file.getName());
                FileOutputStream fos = FileUtils.openOutputStream(downloadFile);
                IOUtils.copy(is, fos);
                client.completePendingCommand();
                IOUtils.closeQuietly(is);
                IOUtils.closeQuietly(fos);

                /*
                //另外一种方式，供参考
                OutputStream is = new FileOutputStream(localFile);
                ftpClient.retrieveFile(ff.getName(), is);
                is.close();
                */

                files.add(downloadFile);
            }
            logger.info("文件下载成功,下载文件路径：" + localDir);
            return files;
        } catch (IOException e) {
            logger.error("下载文件失败" + e.getMessage());
            throw new RuntimeException("下载文件失败" + e.getMessage());
        }
    }

    /**
     * 下载文件
     * @param localDir
     * @param remoteDir
     */
    public List<File> download(String remoteDir,String localDir){
        this.remoteDir = remoteDir;
        this.localDir = localDir;
        return this.download();
    }
    /**
     * 下载文件
     * @param remoteDir
     * @param regEx 文件通配符
     * @param localDir
     * @return
     */
    public List<File> download(String remoteDir,String regEx,String localDir){
        this.remoteDir = remoteDir;
        this.localDir = localDir;
        this.regEx = regEx;
        return this.download();
    }

    /**
     * 下载文件
     * @param downloadFileName 指定要下载的文件名称
     * @return
     */
    public List<File> download(String downloadFileName){
        this.downloadFileName = downloadFileName;
        return this.download();
    }

    /**
     * 上传文件
     * @param files
     */
    public void upload(List<File> files){

        OutputStream os = null;
        try {
            // 2、取本地文件
            if(files == null || files.size()==0) {
                logger.warn("文件数为0，没有找到可上传的文件");
                return;
            }
            logger.info("准备上传" + files.size() + "个文件");
            // 3、上传到FTP服务器
            for(File file : files){
                this.connectServer();
                // 1、设置远程FTP目录
                client.changeWorkingDirectory(remoteDir);
                logger.info("切换至工作目录【" + remoteDir + "】");
                os = client.storeFileStream(file.getName());
                if(os== null) throw new RuntimeException("上传失败，请检查是否有上传权限");
                IOUtils.copy(new FileInputStream(file), os);
                IOUtils.closeQuietly(os);
            }
            logger.info("文件上传成功,上传文件路径：" + remoteDir);
        } catch (IOException e) {
            logger.error("上传文件失败" + e.getMessage());
            throw new RuntimeException("上传文件失败" + e.getMessage());
        }
    }

    public OutputStream getOutputStream(String fileName){
        OutputStream os = null;
        this.connectServer();
        // 1、设置远程FTP目录
        try {
            client.changeWorkingDirectory(remoteDir);
            logger.info("切换至工作目录【" + remoteDir + "】");
            os = client.storeFileStream(fileName);
            if(os== null) throw new RuntimeException("服务器上创建文件对象失败");
            return os;
        } catch (IOException e) {
            logger.error("服务器上创建文件对象失败" + e.getMessage());
            throw new RuntimeException("服务器上创建文件对象失败" + e.getMessage());
        }
    }
    /**
     * 上传文件
     * @param files 上传的文件
     * @param remoteDir
     */
    public void upload(List<File> files,String remoteDir){
        this.remoteDir = remoteDir;
        this.upload(files);
    }

    /**
     * 上传文件
     * @param file
     */
    public void upload(File file){
        List<File> files = new ArrayList<File>();
        files.add(file);
        upload(files);
    }

    /**
     * 判断文件在FTP上是否存在
     * @param fileName
     * @return
     */
    public boolean isFileExist(String fileName) {

        boolean result = false;
        this.connectServer();
        try {
            // 1、设置远程FTP目录
            client.changeWorkingDirectory(remoteDir);
            logger.info("切换至工作目录【" + remoteDir + "】");
            // 2、读取远程文件
            FTPFile[] ftpFiles = client.listFiles(regEx);
            if(ftpFiles.length==0) {
                logger.warn("文件数为0，没有可下载的文件！");
                return result;
            }
            // 3、检查文件是否存在
            for (FTPFile file : ftpFiles) {
                if(file.getName().equals(fileName)){
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("检查文件是否存在失败" + e.getMessage());
            throw new RuntimeException("检查文件是否存在失败" + e.getMessage());
        }

        return result;
    }

    /**
     * 关闭连接
     */
    public void closeConnect() {
        try {
            client.disconnect();
            logger.info(" 关闭FTP连接!!! ");
        } catch (IOException e) {
            logger.warn(" 关闭FTP连接失败!!! ",e);
        }
    }
    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getLocalPath() {
        return localDir;
    }

    public void setLocalPath(String localPath) {
        this.localDir = localPath;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    @Override
    public String toString() {
        return "FTPUtil [host=" + host + ", port=" + port + ", username="
                + username + ", password=" + password + "]";
    }

}
