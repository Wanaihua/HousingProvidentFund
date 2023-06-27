package Bean;

public class Company {
    private String UNITACCNUM;	    //单位公积金账号
    private String UNITACCNAME;	    //单位名称
    private String UNITADDR;	    //单位地址
    private String ORGCODE;	        //组织机构代码
    private String UNITCHAR;	    //单位类别
    private String UNITKIND;	    //企业类型
    private String SALARYDATE;	    //发薪日期
    private String UNITPHONE;	    //联系电话
    private String UNITLINKMAN;	    //单位联系人
    private String UNITAGENTPAPNO;	//经办人身份证号码
    private String ACCSTATE;	    //账户状态
    private String BALANCE;	        //公积金余额
    private String BASENUMBER;	    //缴存基数
    private String UNITPROP;	    //单位比例
    private String PERPROP;	        //个人比例
    private String UNITPAYSUM;	    //单位月应缴额
    private String PERPAYSUM;	    //个人月应缴额
    private String PERSNUM;	        //单位人数
    private String LASTPAYDATE;	    //最后汇缴月
    private String INSTCODE;	    //公积金中心机构代码
    private String OP;	            //柜员
    private String CREATDATE;	    //建立日期
    private String REMARK;	        //备注

    public Company() {
        super();
    }

    public Company(String unitaccnum, String unitaccname, String unitaddr, String orgcode, String unitchar, String unitkind, String salarydate, String unitphone, String unitlinkman, String unitagentpapno, String accstate, String balance, String basenumber, String unitprop, String perprop, String unitpaysum, String perpaysum, String persnum, String lastpaydate, String instcode, String op, String creatdate, String remark) {
        UNITACCNUM = unitaccnum;
        UNITACCNAME = unitaccname;
        UNITADDR = unitaddr;
        ORGCODE = orgcode;
        UNITCHAR = unitchar;
        UNITKIND = unitkind;
        SALARYDATE = salarydate;
        UNITPHONE = unitphone;
        UNITLINKMAN = unitlinkman;
        UNITAGENTPAPNO = unitagentpapno;
        ACCSTATE = accstate;
        BALANCE = balance;
        BASENUMBER = basenumber;
        UNITPROP = unitprop;
        PERPROP = perprop;
        UNITPAYSUM = unitpaysum;
        PERPAYSUM = perpaysum;
        PERSNUM = persnum;
        LASTPAYDATE = lastpaydate;
        INSTCODE = instcode;
        OP = op;
        CREATDATE = creatdate;
        REMARK = remark;
    }
    public String getUNITACCNUM() {
        return UNITACCNUM;
    }

    public void setUNITACCNUM(String UNITACCNUM) {
        this.UNITACCNUM = UNITACCNUM;
    }

    public String getUNITACCNAME() {
        return UNITACCNAME;
    }

    public void setUNITACCNAME(String UNITACCNAME) {
        this.UNITACCNAME = UNITACCNAME;
    }

    public String getUNITADDR() {
        return UNITADDR;
    }

    public void setUNITADDR(String UNITADDR) {
        this.UNITADDR = UNITADDR;
    }

    public String getORGCODE() {
        return ORGCODE;
    }

    public void setORGCODE(String ORGCODE) {
        this.ORGCODE = ORGCODE;
    }

    public String getUNITCHAR() {
        return UNITCHAR;
    }

    public void setUNITCHAR(String UNITCHAR) {
        this.UNITCHAR = UNITCHAR;
    }

    public String getUNITKIND() {
        return UNITKIND;
    }

    public void setUNITKIND(String UNITKIND) {
        this.UNITKIND = UNITKIND;
    }

    public String getSALARYDATE() {
        return SALARYDATE;
    }

    public void setSALARYDATE(String SALARYDATE) {
        this.SALARYDATE = SALARYDATE;
    }

    public String getUNITPHONE() {
        return UNITPHONE;
    }

    public void setUNITPHONE(String UNITPHONE) {
        this.UNITPHONE = UNITPHONE;
    }

    public String getUNITLINKMAN() {
        return UNITLINKMAN;
    }

    public void setUNITLINKMAN(String UNITLINKMAN) {
        this.UNITLINKMAN = UNITLINKMAN;
    }

    public String getUNITAGENTPAPNO() {
        return UNITAGENTPAPNO;
    }

    public void setUNITAGENTPAPNO(String UNITAGENTPAPNO) {
        this.UNITAGENTPAPNO = UNITAGENTPAPNO;
    }

    public String getACCSTATE() {
        return ACCSTATE;
    }

    public void setACCSTATE(String ACCSTATE) {
        this.ACCSTATE = ACCSTATE;
    }

    public String getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
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

    public String getPERPROP() {
        return PERPROP;
    }

    public void setPERPROP(String PERPROP) {
        this.PERPROP = PERPROP;
    }

    public String getUNITPAYSUM() {
        return UNITPAYSUM;
    }

    public void setUNITPAYSUM(String UNITPAYSUM) {
        this.UNITPAYSUM = UNITPAYSUM;
    }

    public String getPERPAYSUM() {
        return PERPAYSUM;
    }

    public void setPERPAYSUM(String PERPAYSUM) {
        this.PERPAYSUM = PERPAYSUM;
    }

    public String getPERSNUM() {
        return PERSNUM;
    }

    public void setPERSNUM(String PERSNUM) {
        this.PERSNUM = PERSNUM;
    }

    public String getLASTPAYDATE() {
        return LASTPAYDATE;
    }

    public void setLASTPAYDATE(String LASTPAYDATE) {
        this.LASTPAYDATE = LASTPAYDATE;
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

    public String getCREATDATE() {
        return CREATDATE;
    }

    public void setCREATDATE(String CREATDATE) {
        this.CREATDATE = CREATDATE;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }


    @Override
    public String toString() {
        return "Company{" +
                "UNITACCNUM='" + UNITACCNUM + '\'' +
                ", UNITACCNAME='" + UNITACCNAME + '\'' +
                ", UNITADDR='" + UNITADDR + '\'' +
                ", ORGCODE='" + ORGCODE + '\'' +
                ", UNITCHAR='" + UNITCHAR + '\'' +
                ", UNITKIND='" + UNITKIND + '\'' +
                ", SALARYDATE='" + SALARYDATE + '\'' +
                ", UNITPHONE='" + UNITPHONE + '\'' +
                ", UNITLINKMAN='" + UNITLINKMAN + '\'' +
                ", UNITAGENTPAPNO='" + UNITAGENTPAPNO + '\'' +
                ", ACCSTATE='" + ACCSTATE + '\'' +
                ", BALANCE='" + BALANCE + '\'' +
                ", BASENUMBER='" + BASENUMBER + '\'' +
                ", UNITPROP='" + UNITPROP + '\'' +
                ", PERPROP='" + PERPROP + '\'' +
                ", UNITPAYSUM='" + UNITPAYSUM + '\'' +
                ", PERPAYSUM='" + PERPAYSUM + '\'' +
                ", PERSNUM='" + PERSNUM + '\'' +
                ", LASTPAYDATE='" + LASTPAYDATE + '\'' +
                ", INSTCODE='" + INSTCODE + '\'' +
                ", OP='" + OP + '\'' +
                ", CREATDATE='" + CREATDATE + '\'' +
                ", REMARK='" + REMARK + '\'' +
                '}';
    }
}
