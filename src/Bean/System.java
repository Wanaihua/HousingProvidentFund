package Bean;

public class System {
    private int id;
    private String SEQNAME;     //键值信息
    private String SEQ;         //当前序号
    private String MAXSEQ;      //最大序号
    private String DESC;        //描述
    private String FREEUSE1;    //备用1

    public System(String SEQNAME, String SEQ, String MAXSEQ, String DESC, String FREEUSE1) {
        this.SEQNAME = SEQNAME;
        this.SEQ = SEQ;
        this.MAXSEQ = MAXSEQ;
        this.DESC = DESC;
        this.FREEUSE1 = FREEUSE1;
    }

    public System() {
        super();
    }

    public String getSEQNAME() {
        return SEQNAME;
    }

    public void setSEQNAME(String SEQNAME) {
        this.SEQNAME = SEQNAME;
    }

    public String getSEQ() {
        return SEQ;
    }

    public void setSEQ(String SEQ) {
        this.SEQ = SEQ;
    }

    public String getMAXSEQ() {
        return MAXSEQ;
    }

    public void setMAXSEQ(String MAXSEQ) {
        this.MAXSEQ = MAXSEQ;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    public String getFREEUSE1() {
        return FREEUSE1;
    }

    public void setFREEUSE1(String FREEUSE1) {
        this.FREEUSE1 = FREEUSE1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "SEQNAME='" + SEQNAME + '\'' +
                ", SEQ='" + SEQ + '\'' +
                ", MAXSEQ='" + MAXSEQ + '\'' +
                ", DESC='" + DESC + '\'' +
                ", FREEUSE1='" + FREEUSE1 + '\'' +
                '}';
    }
}
