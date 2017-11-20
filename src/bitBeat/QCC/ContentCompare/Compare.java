package bitBeat.QCC.ContentCompare;

import java.util.ArrayList;
import java.util.List;

public class Compare {

    // 每一行的多列的单元格的值放入一个record
    static List<RecordPerf> recordPerfs = new ArrayList<RecordPerf>();
    static List<RecordPerf> recordPerfs2 = new ArrayList<RecordPerf>();
    public static void main(String[] agrs) {

        String ne = "";
        String board = "";
        String port = "";
        String path = "";
        String time = "";
        String key = ne + "_" + board + "_" + port + "_" + path + "_" + time;

        RecordPerf recordPerf = new RecordPerf(key, ne, board, port, path, time);
        recordPerfs.add(recordPerf);


        String ne2 = "";
        String board2 = "";
        String port2 = "";
        String path2 = "";
        String time2 = "";
        String key2 = ne2 + "_" + board2 + "_" + port2 + "_" + path2 + "_" + time2;

        RecordPerf recordPerf2 = new RecordPerf(key2, ne2, board2, port2, path2, time2);
        recordPerfs2.add(recordPerf2);
    }


}

class RecordPerf {

    private String key;
    private String ne;
    private String board;
    private String port;
    private String path;
    private String time;

    public RecordPerf()
    {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public RecordPerf(String key, String ne, String board, String port, String path, String time) {
        this.key = key;
        this.ne = ne;
        this.board = board;
        this.port = port;
        this.path = path;
        this.time = time;
    }

}