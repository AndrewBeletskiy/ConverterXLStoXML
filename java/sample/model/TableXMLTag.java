package sample.model;

/**
 * Created by Андрій on 25.11.2016.
 */
public class TableXMLTag extends TextXMLTag {
    private int num;
    public TableXMLTag(String tagName) {
        super(tagName);
        num = 1;
    }
    public TableXMLTag(String tagName, int num, String regular) {
        super(tagName, regular);
        this.num = num;
    }
    public TableXMLTag(String tagName, int num) {
        super(tagName);
        this.num = num;
    }

    @Override public String toXML() {
        if (tagName == null || content == null || content.equals(""))
            return "";

        return "<" + tagName + " ROWNUM=\"" + num + "\">" + content + "</"+tagName+">";
    }
}
