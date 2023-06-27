package Bean;

public class SelectValue {
    private String typename;
    private String value1;
    private String value2;
    private String pageNumber;
    private String allPageNumber;
    private String eachpage = "10";//每一页的数据条数

    public SelectValue(String typename, String value1, String value2, String pageNumber, String allPageNumber) {
        this.typename = typename;
        this.value1 = value1;
        this.value2 = value2;
        this.pageNumber = pageNumber;
        this.allPageNumber = allPageNumber;
    }

    public SelectValue() {

    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getAllPageNumber() {
        return allPageNumber;
    }

    public void setAllPageNumber(String allPageNumber) {
        this.allPageNumber = allPageNumber;
    }

    public String getEachpage() {
        return eachpage;
    }

    public void setEachpage(String eachpage) {
        this.eachpage = eachpage;
    }

    @Override
    public String toString() {
        return "SelectValue{" +
                "typename='" + typename + '\'' +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", allPageNumber='" + allPageNumber + '\'' +
                '}';
    }

}
