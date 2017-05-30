package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.Alert;
import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by Андрій on 29.10.2016.
 */
public class XlsReader {
    private File file;
    private Controller controller;
    private HSSFWorkbook excelBook;
    private HSSFSheet sheet;

    public XlsReader(File file, Controller controller) throws IOException{
        this.file = file;
        this.controller = controller;
        excelBook = new HSSFWorkbook(new FileInputStream(file));
        sheet = excelBook.getSheetAt(0);

    }
    private HSSFCell getCell(int row_index, int column_index) {
        try {
            HSSFRow row = sheet.getRow(row_index);
            return row.getCell(column_index);
        } catch (Exception ex) {
            controller.showError(String.format("HSSFCell ERROR! Row = %d Column = %d %s", row_index, column_index,ex.getMessage()));
        }
        return null;
    }

    public Object read(int row_index, int column_index) {
        return getValue(getCell(row_index, column_index));
    }

    public int readInt(int row_index, int column_index) throws Exception{
        // double d = readDouble(row_index, column_index);
        try {
            double d = Double.parseDouble(readToString(row_index, column_index));
            if (Math.abs(Math.round(d) - d) < 1e-6)
            {
                return (int)Math.round(d);
            } else {
                throw new Exception("В клетке " + row_index +" : "+ column_index + " число не является целым");
            }
        } catch (Exception ex) {
            throw new Exception("В клетке " + row_index +" : "+ column_index + "Неверные данные");
        }

    }
    public String readString(int row_index, int column_index) throws Exception {
        HSSFCell cell = getCell(row_index, column_index);
        if (cell.getCellType() != cell.CELL_TYPE_STRING)
            throw new Exception("В клетке " + row_index +" : "+ column_index + " данные не являются строкой");
        return cell.getStringCellValue();
    }
    public double readDouble(int row_index, int column_index) throws Exception {
        //HSSFCell cell = getCell(row_index, column_index);
        //if (cell.getCellType() != cell.CELL_TYPE_NUMERIC)
        //    throw new Exception("В клетке " + row_index +" : "+ column_index + " данные не являются числом");
        try {
            String value = readToString(row_index, column_index);
            double d = Double.parseDouble(value);
            return d;
        } catch (Exception ex) {
            throw new Exception("Illegal cell value");
        }
    }

    public Object getValue(HSSFCell cell) {
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case HSSFCell.CELL_TYPE_NUMERIC:
                double d =  cell.getNumericCellValue();
                if (Math.abs(Math.round(d) - d) < 1e-8)
                    return Math.round(d);
                else return d;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case HSSFCell.CELL_TYPE_BLANK:
                return "";
        }
        return null;
    }
    public String readToString(int row_index, int column_index) {
        HSSFCell cell = getCell(row_index, column_index);
        if (cell == null)
            return "";
        return getValue(cell).toString();
    }
    public boolean notEmpty(int row, int column) {
        String value = readToString(row, column);
        return !value.matches("^\\s*$");
    }
}
