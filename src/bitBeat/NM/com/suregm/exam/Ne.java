package bitBeat.NM.com.suregm.exam;

public class Ne extends NmObject {

    private String objId;

    public Ne() {
        super();
    }

    public Ne(String objId) {
        this.objId = objId;
    }

    public static void main(String[] args) {
        Ne ne = new Ne("NE233");
    }
}
