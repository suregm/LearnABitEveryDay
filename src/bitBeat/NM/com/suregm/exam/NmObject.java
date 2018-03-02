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

    }

    NmObject(String objId) {
        this.objId = getObjId();



    }

    public String toString() {
        return objId;
    }

}
