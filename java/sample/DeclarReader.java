package sample;

import sample.model.ConstantXML;
import sample.model.Declar;
import sample.model.TableXMLTag;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Андрій on 22.11.2016.
 */
public class DeclarReader {
    XlsReader reader;
    Declar declar;
    Controller controller;
    public ArrayList<Exception> Errors;

    int tableHeight = 0;

    public DeclarReader(Declar declar, XlsReader reader, Controller controller) {
        this.reader = reader;
        this.declar = declar;
        this.controller = controller;
        Errors = new ArrayList<Exception>();
    }

    private void setNewTableHeight(int newTableHeight) {
        if (newTableHeight > tableHeight) {
            tableHeight = newTableHeight;
        }
    }
    public void read() {
        readHead();
        readBody();
    }
    public void readHead() {
        // DECLAR HEAD
        readTIN();
        readC_DOC_TYPE();
        readC_DOC_CNT();
        readC_REG();
        readC_RAJ();
        readPERIOD_MONTH();
        readPERIOD_TYPE();
        readPERIOD_YEAR();
        readC_STI_ORIG();
        readC_DOC_STAN();
        readD_FILL();
    }
    public void readBody() {
        readH03();
        readR03G10S();
        readHORIG1();
        readHTYPR();
        readHFILL();
        readHNUM();
        readHNUM1();
        readHNAMESEL();
        readHKSEL();
        readHNUM2();
        readHNAMEBUY();
        readHKBUY();
        readHFBUY();
        readR04G11();
        readR03G11();
        readR03G7();
        readR03G109();
        readR01G7();
        readR01G109();
        readR01G9();
        readR01G8();
        readR01G10();
        readR02G11();
        readTable();
        readHBOS();
        readHKBOS();
        readR003G10S();
    }
    private void readTIN() {
        String in = controller.getInFileName().substring(4,14);
        String in2 = "";
        for (int col = XlsCoord.TIN_START.column;
             col <= XlsCoord.TIN_END.column;
             col++) {
            in2 += reader.readToString(XlsCoord.TIN_START.row, col);
        }
        if (in.indexOf(in2)>=0) {
            if (!declar.declarhead.TIN.setContent(in.substring(in.indexOf(in2))))
            {
                addError("Неправильный формат кода ЕДРПОУ в названии файла: " + in);
            }
        } else {
            addError("TIN в названии файла не совпадает с указанным в документе. \"" + in + "\" не включает в себя с \"" + in2 + "\"");
        }
    }
    private void readC_DOC_TYPE() {
        String in = controller.getInFileName().substring(23,25);
        try {
            int type = Integer.parseInt(in);
            if (!declar.declarhead.C_DOC_TYPE.setContent(String.valueOf(type))) {
                addError("Неверный формат С_DOC_TYPE в названии файла: " + in);
            }
        } catch (Exception ex) {
            addError("Неверный формат С_DOC_TYPE в названии файла: " + in);
        }



    }
    private void readC_DOC_CNT() {
        String in = controller.getInFileName().substring(25,32);
        try {
            int cnt = Integer.parseInt(in);
            if (!declar.declarhead.C_DOC_CNT.setContent(String.valueOf(cnt))) {
                addError("Неверный формат С_DOC_CNT в названии файла: " + in);
            }
        } catch (Exception ex) {
            addError("Неверный формат С_DOC_CNT в названии файла: " + in);
        }
    }
    private void readC_REG() {
        String in = controller.getInFileName().substring(0,2);
        try {
            int reg = Integer.parseInt(in);
            if (!declar.declarhead.C_REG.setContent(String.valueOf(reg))) {
                addError("Неверный формат C_REG в названии файла: " + in);
            }
        } catch (Exception ex) {
            addError("Неверный формат C_REG в названии файла: " + in);
        }
    }
    private void readC_RAJ() {
        String in = controller.getInFileName().substring(2,4);
        try {
            int raj = Integer.parseInt(in);
            if (!declar.declarhead.C_RAJ.setContent(String.valueOf(raj))) {
                addError("Неверный формат C_RAJ в названии файла: " + in);
            }
        } catch (Exception ex) {
            addError("Неверный формат C_RAJ в названии файла: " + in);
        }
    }
    private void readPERIOD_MONTH() {
        String in = controller.getInFileName().substring(33,35);
        if (!declar.declarhead.PERIOD_MONTH.setContent(in)) {
            addError("Неверный формат PERIOD_MONTH в названии файла: " + in);
        }
    }
    private void readPERIOD_TYPE() {
        String in = controller.getInFileName().substring(33,34);
        if (!declar.declarhead.PERIOD_TYPE.setContent(in)) {
            addError("Неверный формат PERIOD_TYPE в названии файла: " + in);
        }
    }
    private void readPERIOD_YEAR() {
        String in = controller.getInFileName().substring(35,39);
        if (!declar.declarhead.PERIOD_YEAR.setContent(in)) {
            addError("Неверный формат PERIOD_YEAR в названии файла: " + in);
        }
    }
    private void readC_STI_ORIG() {
        String in = controller.getInFileName().substring(39,43);
        if (!declar.declarhead.C_STI_ORIG.setContent(in)) {
            addError("Неверный формат C_STI_ORIG в названии файла: " + in);
        }
    }
    private void readC_DOC_STAN() {
        String in = controller.getInFileName().substring(22,23);
        if (!declar.declarhead.C_DOC_STAN.setContent(in)) {
            addError("Неверный формат C_DOC_STAN в названии файла: " + in);
        }
    }
    private void readD_FILL() {
        DateFormat format = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        String dateString =format.format(date);
        if (!declar.declarhead.D_FILL.setContent(dateString)) {
            addError("Дата задана неверно(правильный формат ddmmyyyy): "
                    + dateString);
        }
    }

    private void readH03() {
        if (reader.notEmpty(XlsCoord.H03.row, XlsCoord.H03.column)) {
            String in = reader.readToString(XlsCoord.H03.row, XlsCoord.H03.column);
            if (in.equalsIgnoreCase("X")) {
                declar.declarbody.H03.setContent("1");
            }
        }
    }
    private void readR03G10S() {
        if (reader.notEmpty(XlsCoord.R03G10S.row, XlsCoord.R03G10S.column)){
            String value = reader.readToString(XlsCoord.R03G10S.row, XlsCoord.R03G10S.column);
            if (!declar.declarbody.R03G10S.setContent(value)) {
                addError("Неправильный формат R03G10S:" + value);
            }
        }
    }
    private void readHORIG1() {
        if (reader.notEmpty(XlsCoord.HORIG1.row, XlsCoord.HORIG1.column)) {
            String in = reader.readToString(XlsCoord.HORIG1.row, XlsCoord.HORIG1.column);
            if (in.equalsIgnoreCase("X")
                    || in.equalsIgnoreCase("Х")
                    || in.equalsIgnoreCase("Х")) {
                declar.declarbody.HORIG1.setContent("1");
            } else {
                addError("Неверный формат HORIG1: \""+in+"\"");
            }
        }
    }
    private void readHTYPR() {
        StringBuilder strBuilder = new StringBuilder();
        int i = XlsCoord.HTYPR.row;

        for (int j = XlsCoord.HTYPR.column; j < XlsCoord.HTYPR.column + XlsCoord.HTYPR_Columns; j++) {
            if (reader.notEmpty(i,j)) {
                try {
                    int n = reader.readInt(i, j);
                    strBuilder.append(n);
                } catch(Exception ex) {
                    addError("Неверный формат числа HTYPR: " + reader.readToString(i,j));
                    return;
                }
            }
        }
        String value = strBuilder.toString();

        if (value.equals(""))
            return;

        if (!declar.declarbody.HTYPR.setContent(strBuilder.toString())) {
            addError("Неверный формат числа HTYPR: " + strBuilder.toString());
        }
    }
    private void readHFILL() {
        StringBuilder HFILLValue = new StringBuilder();
        int row = XlsCoord.HFILL_START.row;
        for (int j = XlsCoord.HFILL_START.column; j <= XlsCoord.HFILL_END.column; j++) {
            if (reader.notEmpty(row,j)) {
                int num = 0;
                try {
                    num = reader.readInt(row, j);
                } catch(Exception ex)
                {
                    addError("Неправильное значение ячейки: \"" + reader.readToString(row, j)+"\"");
                    return;
                }
                HFILLValue.append(num);
            }
        }
        String value = HFILLValue.toString();
        if (!declar.declarbody.HFILL.setContent(value)) {
            addError("Неверный формат данных HFILL: \"" + value+"\"");
        }
    }
    private void readHNUM() {
        StringBuilder strBuilder = new StringBuilder();
        int i = XlsCoord.HNUM.row;

        for (int j = XlsCoord.HNUM.column; j < XlsCoord.HNUM.column + XlsCoord.HNUM_Columns; j++) {
            if (reader.notEmpty(i,j)) {
                try {
                    int n = reader.readInt(i, j);
                    strBuilder.append(n);
                } catch(Exception ex) {
                    addError("Неверный формат числа HNUM: \""+reader.readToString(i,j)+"\"");
                    return;
                }
            }
        }
        String value = strBuilder.toString();

        if (!declar.declarbody.HNUM.setContent(strBuilder.toString())) {
            addError("Неверный формат HNUM: \""+strBuilder.toString()+"\"");
        }
    }
    private void readHNUM1() {
        int i = XlsCoord.HNUM1.row;
        int j = XlsCoord.HNUM1.column;
        String value = "";
        if (reader.notEmpty(i,j)) {
            try {
                int n = reader.readInt(i, j);
                value = String.valueOf(n);
            } catch(Exception ex) {
                addError("Неверный формат числа HNUM1: \""+reader.readToString(i,j)+"\"");
                return;
            }
        }
        if (!value.equals("") && !declar.declarbody.HNUM1.setContent(value)) {
            addError("Неверный формат HNUM1: \""+value+"\"");
        }
    }

    private void readHNAMESEL() {
        int i = 0;
        int j = 0;
        try {
            i = XlsCoord.HNAMESEL.row;
            j = XlsCoord.HNAMESEL.column;
            String value = reader.readString(i,j);
            if (!declar.declarbody.HNAMESEL.setContent(value)) {
                addError("Неправильное наполнение ячейки с именем продавца: \""+value+"\"");
            }
        } catch(Exception ex) {
            addError("Неправильное наполнение ячейки с именем продавца: \""+reader.readToString(i,j)+"\"");
        }
    }
    private void readHKSEL() {
        StringBuilder strBuilder = new StringBuilder();
        int i = XlsCoord.HKSEL.row;

        for (int j = XlsCoord.HKSEL.column; j < XlsCoord.HKSEL.column + XlsCoord.HKSEL_Columns; j++) {
            if (reader.notEmpty(i,j)) {
                try {
                    int n = reader.readInt(i, j);
                    strBuilder.append(n);
                } catch(Exception ex) {
                    addError("Неверный формат числа HKSEL: \""+reader.readToString(i,j)+"\"");
                    return;
                }
            }
        }
        String value = strBuilder.toString();

        if (!declar.declarbody.HKSEL.setContent(value)) {
            addError("Неверный формат HKSEL: \""+value+"\"");
        }
    }
    private void readHNUM2() {
        StringBuilder strBuilder = new StringBuilder();
        int i = XlsCoord.HNUM2.row;

        for (int j = XlsCoord.HNUM2.column; j < XlsCoord.HNUM2.column + XlsCoord.HNUM2_Columns; j++) {
            if (reader.notEmpty(i,j)) {
                try {
                    int n = reader.readInt(i, j);
                    strBuilder.append(n);
                } catch(Exception ex) {
                    addError("Неверный формат числа HNUM2: \""+reader.readToString(i,j)+"\"");
                    return;
                }
            }
        }
        String value = strBuilder.toString();
        if (value.equals(""))
            return;
        if (!declar.declarbody.HNUM2.setContent(value)) {
            addError("Неверный формат HNUM2: \""+value+"\"");
        }
    }
    private void readHNAMEBUY() {
        int i = 0;
        int j = 0;
        try {
            i = XlsCoord.HNAMEBUY.row;
            j = XlsCoord.HNAMEBUY.column;
            String value = reader.readString(i,j);
            if (!declar.declarbody.HNAMEBUY.setContent(value)) {
                addError("Неправильное наполнение ячейки с именем покупателя: \""+value+"\"");
            }
        } catch(Exception ex) {
            addError("Неправильное наполнение ячейки с именем покупателя: \""+reader.readToString(i,j)+"\"");
        }
    }
    private void readHKBUY() {
        StringBuilder strBuilder = new StringBuilder();
        int i = XlsCoord.HKBUY.row;

        for (int j = XlsCoord.HKBUY.column; j < XlsCoord.HKBUY.column + XlsCoord.HKBUY_Columns; j++) {
            if (reader.notEmpty(i,j)) {
                try {
                    int n = reader.readInt(i, j);
                    strBuilder.append(n);
                } catch(Exception ex) {
                    addError("Неверный формат числа HKBUY: \""+reader.readToString(i,j)+"\"");
                    return;
                }
            }
        }
        String value = strBuilder.toString();

        if (!declar.declarbody.HKBUY.setContent(value)) {
            addError("Неверный формат HKBUY: \""+value+"\"");
        }
    }
    private void readHFBUY() {
        StringBuilder strBuilder = new StringBuilder();
        int i = XlsCoord.HFBUY.row;

        for (int j = XlsCoord.HFBUY.column; j < XlsCoord.HFBUY.column + XlsCoord.HFBUY_Columns; j++) {
            if (reader.notEmpty(i,j)) {
                try {
                    int n = reader.readInt(i, j);
                    strBuilder.append(n);
                } catch(Exception ex) {
                    addError("Неверный формат числа HFBUY: \""+reader.readToString(i,j)+"\"");
                    return;
                }
            }
        }
        String value = strBuilder.toString();
        if (value.equals(""))
            return;
        if (!declar.declarbody.HFBUY.setContent(value)) {
            addError("Неверный формат HFBUY: \""+value+"\"");
        }
    }
    private void readR04G11() {
        double d = 0;
        int i = XlsCoord.R04G11.row;
        int j = XlsCoord.R04G11.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R04G11.setContent(value))
                addError("Неверно заполнено R04G11: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R04G11: \"" + value + "\"");
        }
    }
    private void readR03G11() {
        double d = 0;
        int i = XlsCoord.R03G11.row;
        int j = XlsCoord.R03G11.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R03G11.setContent(value))
                addError("Неверно заполнено R03G11: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R03G11: \"" + value + "\"");
        }
    }
    private void readR03G7() {
        double d = 0;
        int i = XlsCoord.R03G7.row;
        int j = XlsCoord.R03G7.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R03G7.setContent(value))
                addError("Неверно заполнено R03G7: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R03G7: \"" + value + "\"");
        }
    }
    private void readR03G109() {
        double d = 0;
        int i = XlsCoord.R03G109.row;
        int j = XlsCoord.R03G109.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R03G109.setContent(value))
                addError("Неверно заполнено R03G109: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R03G109: \"" + value + "\"");
        }
    }
    private void readR01G7() {
        double d = 0;
        int i = XlsCoord.R01G7.row;
        int j = XlsCoord.R01G7.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R01G7.setContent(value))
                addError("Неверно заполнено R01G7: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R01G7: \"" + value + "\"");
        }
    }
    private void readR01G109() {
        double d = 0;
        int i = XlsCoord.R01G109.row;
        int j = XlsCoord.R01G109.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R01G109.setContent(value))
                addError("Неверно заполнено R01G109: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R01G109: \"" + value + "\"");
        }
    }
    private void readR01G9() {
        double d = 0;
        int i = XlsCoord.R01G9.row;
        int j = XlsCoord.R01G9.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R01G9.setContent(value))
                addError("Неверно заполнено R01G9: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R01G9: \"" + value + "\"");
        }
    }
    private void readR01G8() {
        double d = 0;
        int i = XlsCoord.R01G8.row;
        int j = XlsCoord.R01G8.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R01G8.setContent(value))
                addError("Неверно заполнено R01G8: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R01G8: \"" + value + "\"");
        }
    }
    private void readR01G10() {
        double d = 0;
        int i = XlsCoord.R01G10.row;
        int j = XlsCoord.R01G10.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R01G10.setContent(value))
                addError("Неверно заполнено R01G10: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R01G10: \"" + value + "\"");
        }
    }
    private void readR02G11() {
        double d = 0;
        int i = XlsCoord.R02G11.row;
        int j = XlsCoord.R02G11.column;
        String value;
        try {
            if (!reader.notEmpty(i,j))
                return;
            d = reader.readDouble(i,j);
            value = String.format("%.2f", d).replace(',','.');
            if (!declar.declarbody.R02G11.setContent(value))
                addError("Неверно заполнено R02G11: \"" + value + "\"");
        } catch (Exception ex) {
            value = reader.readToString(i,j);
            addError("Неверно заполнено R02G11: \"" + value + "\"");
        }
    }

    private void readTable() {
        readRXXXXG3S();
        readRXXXXG4();
        readRXXXXG4S();
        readRXXXXG105_2S();
        readRXXXXG5();
        readRXXXXG6();
        readRXXXXG008();
        readRXXXXG009();
        readRXXXXG010();
    }

    private void readHBOS() {
        int i = 0;
        int j = 0;
        try {
            i = XlsCoord.HBOS.row + tableHeight-1;
            j = XlsCoord.HBOS.column;
            String value = reader.readString(i,j);
            if (!declar.declarbody.HBOS.setContent(value)) {
                addError("Неправильное наполнение ячейки с именем законного представителя(HBOS): \""+value+"\"");
            }
        } catch(Exception ex) {
            addError("Неправильное наполнение ячейки с именем законного представителя(HBOS): \""+reader.readToString(i,j)+"\"");
        }
    }
    private void readHKBOS() {
        StringBuilder strBuilder = new StringBuilder();
        int i = XlsCoord.HKBOS.row + tableHeight - 1;

        for (int j = XlsCoord.HKBOS.column; j < XlsCoord.HKBOS.column + XlsCoord.HKBOS_Columns; j++) {
            if (reader.notEmpty(i,j)) {
                strBuilder.append(reader.readToString(i,j));
            }
        }
        String value = strBuilder.toString();

        if (!declar.declarbody.HKBOS.setContent(strBuilder.toString())) {
            addError("Неверный формат HKBOS: \""+strBuilder.toString()+"\"");
        }
    }
    private void readR003G10S() {
        int i = 0;
        int j = 0;
        try {
            if (!reader.notEmpty(i,j))
                return;
            i = XlsCoord.R003G10S.row + tableHeight - 1;
            j = XlsCoord.R003G10S.column;
            String value = reader.readString(i,j);

            if (!declar.declarbody.R003G10S.setContent(value)) {
                addError("Неправильное наполнение ячейки R003G10S: \""+value+"\"");
            }
        } catch(Exception ex) {
            addError("Неправильное наполнение ячейки R003G10S: \""+reader.readToString(i,j)+"\"");
        }
    }


    public void readRXXXXG3S() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = reader.readToString(i,j+XlsCoord.RXXXXG3SColumn);
            if (!value.equalsIgnoreCase("")) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG3S", num);
                if (!newTag.setContent(value))
                {
                    addError("Неверный формат тега RXXXXG3S на строке №"+String.valueOf(num)+": \""+value+"\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }
            i++;
        } while (!isExit);

    }
    public void readRXXXXG4S() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            setNewTableHeight(num);
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = reader.readToString(i,XlsCoord.RXXXXG4SColumn);
            if (!value.equalsIgnoreCase("")) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG4S",num);
                if (!newTag.setContent(value))
                {
                    addError("Неверный формат данных RXXXXG4S в строке №"+String.valueOf(num)+": \"" + value+"\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }
            i++;
        } while (!isExit);
    }
    private void readRXXXXG4() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = reader.readToString(i,XlsCoord.RXXXXG4Column);
            if (!value.equalsIgnoreCase("")) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG4", num, RegExConst.DGUKTZED);
                if (!newTag.setContent(value)) {
                    addError("Неверный формат данных RXXXXG4 в строке №"+String.valueOf(num)+": \"" + value + "\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }

            i++;
        } while (!isExit);
    }
    private void readRXXXXG105_2S() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = reader.readToString(i,XlsCoord.RXXXXG105_2SColumn);
            if (!value.equalsIgnoreCase("")) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG105_2S", num,RegExConst.DGI4lz);
                if (!newTag.setContent(value)) {
                    addError("Неверный формат данных RXXXXG105_2S в строке №"+String.valueOf(num)+": \"" + value + "\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }

            i++;
        } while (!isExit);
    }
    private void readRXXXXG5() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
                break;
            }
            String value = "";
            if (reader.notEmpty(i,XlsCoord.RXXXXG5Column)) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG5", num,RegExConst.DGdecimal6_R);
                double d = 0.0;
                try {
                    d = reader.readDouble(i, XlsCoord.RXXXXG5Column);
                } catch(Exception ex) {
                    addError("Неверный формат данных RXXXXG5 в строке №"+String.valueOf(num)+": \"" + reader.readToString(i,XlsCoord.RXXXXG5Column) + "\"");
                    return;
                }
                value = String.format("%.6f", d).replace(',','.');
                if (!newTag.setContent(value)) {
                    addError("Неверный формат данных RXXXXG5 в строке №"+String.valueOf(num)+": \"" + value + "\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }

            i++;
        } while (!isExit);
    }
    private void readRXXXXG6() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = "";
            if (reader.notEmpty(i,XlsCoord.RXXXXG6Column)) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG6", num,RegExConst.DGdecimal12_R);
                double d = 0.0;
                try {
                    d = reader.readDouble(i, XlsCoord.RXXXXG6Column);
                } catch (Exception ex ){
                    addError("Неверный формат данных RXXXXG6 в строке №"+String.valueOf(num)+": \"" + reader.readToString(i,XlsCoord.RXXXXG6Column) + "\"");
                    return;
                }
                value = String.format("%.12f", d).replace(',','.');
                if (!newTag.setContent(value)) {
                    addError("Неверный формат данных RXXXXG6 в строке №"+String.valueOf(num)+": \"" + value + "\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }

            i++;
        } while (!isExit);
    }
    private void readRXXXXG008() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = reader.readToString(i,XlsCoord.RXXXXG008Column);
            if (!value.equalsIgnoreCase("")) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG008", num, RegExConst.DGI3nom);
                if (!newTag.setContent(value)) {
                    addError("Неверный формат данных RXXXXG008 в строке №"+String.valueOf(num)+": \"" + value + "\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }

            i++;
        } while (!isExit);
    }
    private void readRXXXXG009() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = reader.readToString(i,XlsCoord.RXXXXG009Column);
            if (!value.equalsIgnoreCase("")) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG009", num, RegExConst.DGCodPilg);
                if (!newTag.setContent(value)) {
                    addError("Неверный формат данных RXXXXG009 в строке №"+String.valueOf(num)+": \"" + value + "\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }

            i++;
        } while (!isExit);
    }
    private void readRXXXXG010() {
        int i = XlsCoord.TABLE_LEFT_START.row;
        int j = XlsCoord.TableFirstColumn;
        boolean isExit = false;
        do {
            int num = 0;
            try {
                num = reader.readInt(i,j);
                setNewTableHeight(num);
            } catch (Exception ex) {
                isExit = true;
            }
            String value = "";
            if (reader.notEmpty(i,XlsCoord.RXXXXG010Column)) {
                TableXMLTag newTag = new TableXMLTag("RXXXXG010", num,RegExConst.DGdecimal2_P);
                double d = 0.0;
                try {
                    d = reader.readDouble(i,XlsCoord.RXXXXG010Column);
                } catch (Exception ex) {
                    addError("Неверный формат данных RXXXXG010 в строке №"+String.valueOf(num)+": \"" + reader.readToString(i,XlsCoord.RXXXXG010Column) + "\"");
                    return;
                }
                value = String.format("%.2f", d).replace(',','.');
                if (!newTag.setContent(value)) {
                    addError("Неверный формат данных RXXXXG010 в строке №"+String.valueOf(num)+": \"" + value + "\"");
                    return;
                }
                declar.declarbody.table.add(newTag);
            }

            i++;
        } while (!isExit);
    }
    public void addError(String str) {
        Errors.add(new Exception(str));
    }
}

interface XlsCoord {
    Coord TIN_START = new Coord(13,1);
    Coord TIN_END = new Coord(13, 13);

    Coord H03 = new Coord(0,33);
    Coord R03G10S = new Coord(1,33);
    Coord HORIG1 = new Coord(2, 33);

    Coord HTYPR = new Coord(3,33);
    int HTYPR_Columns = 3;

    Coord HFILL_START = new Coord(6,28);
    Coord HFILL_END = new Coord(6,42);

    Coord HNUM = new Coord(6,48);
    int HNUM_Columns = 13;

    Coord HNUM1 = new Coord(6,63);

    Coord HNAMESEL = new Coord(10,1);

    Coord HKSEL = new Coord(13,1);
    int HKSEL_Columns = 23;

    Coord HNUM2 = new Coord(13,27);
    int HNUM2_Columns = 7;

    Coord HNAMEBUY = new Coord(10,38);

    Coord HKBUY = new Coord(13,38);
    int HKBUY_Columns = 23;

    Coord HFBUY = new Coord(13,64);
    int HFBUY_Columns = 7;

    Coord R04G11 = new Coord(17,61);
    Coord R03G11 = new Coord(18,61);
    Coord R03G7 = new Coord(19,61);
    Coord R03G109 = new Coord(20,61);
    Coord R01G7 = new Coord(21,61);
    Coord R01G109 = new Coord(22,61);
    Coord R01G9 = new Coord(23,61);
    Coord R01G8 = new Coord(24,61);
    Coord R01G10 = new Coord(25,61);
    Coord R02G11 = new Coord(26,61);
    Coord HBOS = new Coord(38,27);
    Coord HKBOS = new Coord(38,49);
    int HKBOS_Columns = 19;
    Coord R003G10S = new Coord(44,3);

    Coord TABLE_LEFT_START = new Coord(34,0);
    Coord TABLE_RIGHT_START = new Coord(34,60);



    int RXXXXG3SColumn = 2;
    int RXXXXG4Column = 22;
    int RXXXXG4SColumn = 30;
    int RXXXXG105_2SColumn = 34;
    int RXXXXG5Column = 37;
    int RXXXXG6Column = 42;
    int RXXXXG008Column = 48;
    int RXXXXG009Column = 53;
    int RXXXXG010Column = 60;
    int TableFirstColumn = 0;
}
class Coord implements ICoord{
    public int row;
    public int column;

    public Coord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
interface ICoord {
    int getRow();
    int getColumn();
}