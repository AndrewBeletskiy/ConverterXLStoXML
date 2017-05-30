package sample.model;

import sample.RegExConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрій on 29.10.2016.
 */
public class DeclarBody implements IXml{
    public TextXMLTag H03;
    public TextXMLTag R03G10S;
    public TextXMLTag HORIG1;
    public TextXMLTag HTYPR;
    public TextXMLTag HFILL;
    public TextXMLTag HNUM;
    public TextXMLTag HNUM1;
    public TextXMLTag HNAMESEL;
    public TextXMLTag HNAMEBUY;
    public TextXMLTag HKSEL;
    public TextXMLTag HNUM2;
    public TextXMLTag HKBUY;
    public TextXMLTag HFBUY;
    public TextXMLTag R04G11;
    public TextXMLTag R03G11;
    public TextXMLTag R03G7;
    public TextXMLTag R03G109;
    public TextXMLTag R01G7;
    public TextXMLTag R01G109;
    public TextXMLTag R01G9;
    public TextXMLTag R01G8;
    public TextXMLTag R01G10;
    public TextXMLTag R02G11;
    public TextXMLTag HBOS;
    public TextXMLTag HKBOS;
    public TextXMLTag R003G10S;
    public ArrayList<IXml> table;

    public DeclarBody() {
        table = new ArrayList<IXml>();

        H03 = new TextXMLTag("H03", RegExConst.DGchk);
        HBOS = new TextXMLTag("HBOS", RegExConst.DGHBOS);
        HFBUY = new TextXMLTag("HFBUY", RegExConst.DGI4nom);
        HFILL = new TextXMLTag("HFILL", RegExConst.DGDate);
        HKBOS = new TextXMLTag("HKBOS", RegExConst.DGLong);
        HKBUY = new TextXMLTag("HKBUY", RegExConst.DGHNPDV);
        HKSEL = new TextXMLTag("HKSEL", RegExConst.DGHNPDV);
        HNAMEBUY = new TextXMLTag("HNAMEBUY", RegExConst.DGHNAME);
        HNAMESEL = new TextXMLTag("HNAMESEL", RegExConst.DGHNAME);
        HNUM = new TextXMLTag("HNUM", RegExConst.DGI7nom);
        HNUM1 = new TextXMLTag("HNUM1", RegExConst.DGspecNom);
        HNUM2 = new TextXMLTag("HNUM2", RegExConst.DGI4nom);
        HORIG1 = new TextXMLTag("HORIG1", RegExConst.DGchk);
        HTYPR = new TextXMLTag("HTYPR", RegExConst.DGPNtypr);
        R003G10S = new TextXMLTag("R003G10S", RegExConst.ANY);
        R01G10 = new TextXMLTag("R01G10", RegExConst.DGdecimal2_P);
        R01G109 = new TextXMLTag("R01G109", RegExConst.DGdecimal2_P);
        R01G7 = new TextXMLTag("R01G7", RegExConst.DGdecimal2_P);
        R01G8 = new TextXMLTag("R01G8", RegExConst.DGdecimal2_P);
        R01G9 = new TextXMLTag("R01G9", RegExConst.DGdecimal2_P);
        R02G11 = new TextXMLTag("R02G11", RegExConst.DGdecimal2_P);
        R03G109 = new TextXMLTag("R03G109", RegExConst.DGdecimal2_P);
        R03G10S = new TextXMLTag("R03G10S", RegExConst.ANY);
        R03G11 = new TextXMLTag("R03G11", RegExConst.DGdecimal2_P);
        R03G7 = new TextXMLTag("R03G7", RegExConst.DGdecimal2_P);
        R04G11 = new TextXMLTag("R04G11", RegExConst.DGdecimal2_P);
    }

    public String toXML() {
        return "<DECLARBODY>\n\t\t" + getContent() + "</DECLARBODY>";
    }
    public String getContent()
    {
        StringBuilder res = new StringBuilder();
        res.append(H03.toXML()).append("\n\t\t")
                .append(R03G10S.toXML()).append("\n\t\t")
                .append(HORIG1.toXML()).append("\n\t\t")
                .append(HTYPR.toXML()).append("\n\t\t")
                .append(HFILL.toXML()).append("\n\t\t")
                .append(HNUM.toXML()).append("\n\t\t")
                .append(HNUM1.toXML()).append("\n\t\t")
                .append(HNAMESEL.toXML()).append("\n\t\t")
                .append(HNAMEBUY.toXML()).append("\n\t\t")
                .append(HKSEL.toXML()).append("\n\t\t")
                .append(HNUM2.toXML()).append("\n\t\t")
                .append(HKBUY.toXML()).append("\n\t\t")
                .append(HFBUY.toXML()).append("\n\t\t")
                .append(R04G11.toXML()).append("\n\t\t")
                .append(R03G11.toXML()).append("\n\t\t")
                .append(R03G7.toXML()).append("\n\t\t")
                .append(R03G109.toXML()).append("\n\t\t")
                .append(R01G7.toXML()).append("\n\t\t")
                .append(R01G109.toXML()).append("\n\t\t")
                .append(R01G9.toXML()).append("\n\t\t")
                .append(R01G8.toXML()).append("\n\t\t")
                .append(R01G10.toXML()).append("\n\t\t")
                .append(R02G11.toXML()).append("\n\t\t")
                .append(getTableXML()).append("\n\t\t")
                .append(HBOS.toXML()).append("\n\t\t")
                .append(HKBOS.toXML()).append("\n\t\t")
                .append(R003G10S.toXML()).append("\n\t");

        String resValue = res.toString();
        while (resValue.indexOf("\n\t\t\n\t\t")>=0) {
            resValue = resValue.replace("\n\t\t\n\t\t", "\n\t\t");
        }
        //return res.toString();
        return resValue;
    }
    private String getTableXML() {
        int n = table.size();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++){
            res.append(table.get(i).toXML()).append("\n\t\t");
        }
        return res.toString();
    }
}
