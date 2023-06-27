package Bean;

import com.alibaba.excel.annotation.ExcelProperty;

public class CompanyForExcel {
    @ExcelProperty("用户")
    private String UNITACCNAME;	    //单位名称
    @ExcelProperty("地址")
    private String UNITADDR;	    //单位地址
    @ExcelProperty("组织机构代码")
    private String ORGCODE;	        //组织机构代码
    @ExcelProperty("联系电话")
    private String UNITPHONE;	    //联系电话
    @ExcelProperty("单位联系人")
    private String UNITLINKMAN;	    //单位联系人

    public CompanyForExcel() {
    }

    public CompanyForExcel(String UNITACCNAME, String UNITADDR, String ORGCODE, String UNITPHONE, String UNITLINKMAN) {
        this.UNITACCNAME = UNITACCNAME;
        this.UNITADDR = UNITADDR;
        this.ORGCODE = ORGCODE;
        this.UNITPHONE = UNITPHONE;
        this.UNITLINKMAN = UNITLINKMAN;
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

    @Override
    public String toString() {
        return "CompanyForExcel{" +
                "UNITACCNAME='" + UNITACCNAME + '\'' +
                ", UNITADDR='" + UNITADDR + '\'' +
                ", ORGCODE='" + ORGCODE + '\'' +
                ", UNITPHONE='" + UNITPHONE + '\'' +
                ", UNITLINKMAN='" + UNITLINKMAN + '\'' +
                '}';
    }
}
