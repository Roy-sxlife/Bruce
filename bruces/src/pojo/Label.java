package pojo;

public class Label {
    private int label_id;
    private String label_name;
    private String lable_content;

    public Label() {

    }

    public int getLabel_id() {
        return label_id;
    }

    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getLable_content() {
        return lable_content;
    }

    public void setLable_content(String lable_content) {
        this.lable_content = lable_content;
    }

    @Override
    public String toString() {
        return "Label{" +
                "label_idp='" + label_id + '\'' +
                ", label_name='" + label_name + '\'' +
                ", lable_content='" + lable_content + '\'' +
                '}';
    }
}
