package Bean;

public class User {
    private String ACCNUM;          //个人公积金账号
    private String UNITACCNUM;      //单位公积金账号
    private String NAME;            //个人姓名
    private String TYPE;            //证件类型
    private String NUM;             //证件号码
    private String OPENDATE;        //开户日期
    private String BALANCE;         //公积金余额
    private String PERACCSTATE;     //个人账户状态
    private String BASENUMBER;      //缴存基数
    private String UNITPROP;        //单位比例
    private String INDIPROP;        //个人比例
    private String LASTPAYDATE;     //最后汇缴月
    private String UNITMONPAYSUM;   //单位月应缴额
    private String PERMONPAYSUM;    //个人月应缴额
    private String YPAYAMT;         //本年汇补缴额
    private String YDRAWAMT;        //年提取额
    private String YINTERESTBAL;    //年度结息
    private String INSTCODE;        //公积金中心机构代码
    private String OP;              //柜员
    private String REMARK;          //备注

    public User(){}

    public User(String ACCNUM, String UNITACCNUM,String NAME,String TYPE, String NUM, String OPENDATE, String BALANCE, String PERACCSTATE, String BASENUMBER, String UNITPROP, String INDIPROP, String LASTPAYDATE, String UNITMONPAYSUM, String PERMONPAYSUM, String YPAYAMT, String YDRAWAMT, String YINTERESTBAL, String INSTCODE, String OP, String REMARK) {
        this.ACCNUM = ACCNUM;
        this.UNITACCNUM = UNITACCNUM;
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.NUM = NUM;
        this.OPENDATE = OPENDATE;
        this.BALANCE = BALANCE;
        this.PERACCSTATE = PERACCSTATE;
        this.BASENUMBER = BASENUMBER;
        this.UNITPROP = UNITPROP;
        this.INDIPROP = INDIPROP;
        this.LASTPAYDATE = LASTPAYDATE;
        this.UNITMONPAYSUM = UNITMONPAYSUM;
        this.PERMONPAYSUM = PERMONPAYSUM;
        this.YPAYAMT = YPAYAMT;
        this.YDRAWAMT = YDRAWAMT;
        this.YINTERESTBAL = YINTERESTBAL;
        this.INSTCODE = INSTCODE;
        this.OP = OP;
        this.REMARK = REMARK;
    }

    public String getACCNUM() {
        return ACCNUM;
    }

    public void setACCNUM(String ACCNUM) {
        this.ACCNUM = ACCNUM;
    }

    public String getUNITACCNUM() {
        return UNITACCNUM;
    }

    public void setUNITACCNUM(String UNITACCNUM) {
        this.UNITACCNUM = UNITACCNUM;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getNUM() {
        return NUM;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    public String getOPENDATE() {
        return OPENDATE;
    }

    public void setOPENDATE(String OPENDATE) {
        this.OPENDATE = OPENDATE;
    }

    public String getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
    }

    public String getPERACCSTATE() {
        return PERACCSTATE;
    }

    public void setPERACCSTATE(String PERACCSTATE) {
        this.PERACCSTATE = PERACCSTATE;
    }

    public String getBASENUMBER() {
        return BASENUMBER;
    }

    public void setBASENUMBER(String BASENUMBER) {
        this.BASENUMBER = BASENUMBER;
    }

    public String getUNITPROP() {
        return UNITPROP;
    }

    public void setUNITPROP(String UNITPROP) {
        this.UNITPROP = UNITPROP;
    }

    public String getINDIPROP() {
        return INDIPROP;
    }

    public void setINDIPROP(String INDIPROP) {
        this.INDIPROP = INDIPROP;
    }

    public String getLASTPAYDATE() {
        return LASTPAYDATE;
    }

    public void setLASTPAYDATE(String LASTPAYDATE) {
        this.LASTPAYDATE = LASTPAYDATE;
    }

    public String getUNITMONPAYSUM() {
        return UNITMONPAYSUM;
    }

    public void setUNITMONPAYSUM(String UNITMONPAYSUM) {
        this.UNITMONPAYSUM = UNITMONPAYSUM;
    }

    public String getPERMONPAYSUM() {
        return PERMONPAYSUM;
    }

    public void setPERMONPAYSUM(String PERMONPAYSUM) {
        this.PERMONPAYSUM = PERMONPAYSUM;
    }

    public String getYPAYAMT() {
        return YPAYAMT;
    }

    public void setYPAYAMT(String YPAYAMT) {
        this.YPAYAMT = YPAYAMT;
    }

    public String getYDRAWAMT() {
        return YDRAWAMT;
    }

    public void setYDRAWAMT(String YDRAWAMT) {
        this.YDRAWAMT = YDRAWAMT;
    }

    public String getYINTERESTBAL() {
        return YINTERESTBAL;
    }

    public void setYINTERESTBAL(String YINTERESTBAL) {
        this.YINTERESTBAL = YINTERESTBAL;
    }

    public String getINSTCODE() {
        return INSTCODE;
    }

    public void setINSTCODE(String INSTCODE) {
        this.INSTCODE = INSTCODE;
    }

    public String getOP() {
        return OP;
    }

    public void setOP(String OP) {
        this.OP = OP;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }


    @Override
    public String toString() {
        return "User{" +
                "ACCNUM='" + ACCNUM + '\'' +
                ", UNITACCNUM='" + UNITACCNUM + '\'' +
                ", NAME='" + NAME + '\'' +
                ", TYPE='" + TYPE + '\'' +
                ", NUM='" + NUM + '\'' +
                ", OPENDATE='" + OPENDATE + '\'' +
                ", BALANCE='" + BALANCE + '\'' +
                ", PERACCSTATE='" + PERACCSTATE + '\'' +
                ", BASENUMBER='" + BASENUMBER + '\'' +
                ", UNITPROP='" + UNITPROP + '\'' +
                ", INDIPROP='" + INDIPROP + '\'' +
                ", LASTPAYDATE='" + LASTPAYDATE + '\'' +
                ", UNITMONPAYSUM='" + UNITMONPAYSUM + '\'' +
                ", PERMONPAYSUM='" + PERMONPAYSUM + '\'' +
                ", YPAYAMT='" + YPAYAMT + '\'' +
                ", YDRAWAMT='" + YDRAWAMT + '\'' +
                ", YINTERESTBAL='" + YINTERESTBAL + '\'' +
                ", INSTCODE='" + INSTCODE + '\'' +
                ", OP='" + OP + '\'' +
                ", REMARK='" + REMARK + '\'' +
                '}';
    }
}
