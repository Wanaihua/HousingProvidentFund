package Bean;

import com.alibaba.excel.annotation.ExcelProperty;

public class UserForExcel {
    @ExcelProperty("姓名")
    private String NAME;

    @ExcelProperty("证件类型")
    private String TYPE;

    @ExcelProperty("证件号码")
    private String NUM;

    @ExcelProperty("缴存基数")
    private String BASENUMBER;

    public UserForExcel() {
    }

    public UserForExcel(String NAME, String TYPE, String NUM, String BASENUMBER) {
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.NUM = NUM;
        this.BASENUMBER = BASENUMBER;
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

    public String getBASENUMBER() {
        return BASENUMBER;
    }

    public void setBASENUMBER(String BASENUMBER) {
        this.BASENUMBER = BASENUMBER;
    }

    @Override
    public String toString() {
        return "UserForExcel{" +
                "NAME='" + NAME + '\'' +
                ", TYPE='" + TYPE + '\'' +
                ", NUM='" + NUM + '\'' +
                ", BASENUMBER='" + BASENUMBER + '\'' +
                '}';
    }
}
