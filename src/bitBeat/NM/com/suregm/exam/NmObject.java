package bitBeat.NM.com.suregm.exam;

class NmObject {

    private String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    private String objId;

    NmObject() {
        this.objId = getObjId();
    }

    NmObject(String objId) {
        this.objId = getObjId();
    }


}
