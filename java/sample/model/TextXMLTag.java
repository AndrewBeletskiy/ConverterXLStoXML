package sample.model;

import sample.RegExConst;

/**
 * Created by Андрій on 20.11.2016.
 */
public class TextXMLTag implements IXml {
    protected String tagName;
    protected String content;
    protected String regular;
    private int maxContentLength;

    /**
     * Create an empty text typed XML tag
     * @param tagName The name of the tag.
     * @param regular The regular expression that will be test the content.
     */
    public TextXMLTag(String tagName, String regular) {
        maxContentLength = 0;
        this.tagName = tagName;
        this.regular = regular;
    }

    public TextXMLTag(String tagName, String regular, int maxContentLength) {
        this.tagName = tagName;
        this.regular = regular;
        this.maxContentLength = maxContentLength;
    }
    public TextXMLTag(String tagName)
    {
        this.tagName = tagName;
        this.regular = RegExConst.ANY;
    }

    public String toXML() {
        if (tagName == null)
            return "";
            //throw new IXmlException(tagName, "tagName is null. (class TextXMLTag)");
        if (content == null || content.equals(""))
            return "";
            //throw new IXmlException(tagName, "content is null. (class TextXMLTag)");

        return "<"+tagName+">" +content+"</"+tagName+">";
    }

    public boolean setContent(String text) {
        if (maxContentLength != 0 && text.length() > maxContentLength)
            return false;
        if (RegEx.match(text, regular)) {
            content = text;
            return true;
        }
        return false;
    }
}
