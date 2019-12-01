/*
package cn.skunk;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class POITest {

    @Test
    public void test1() throws Exception {
        XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File("D:/poi.xlsx")));
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                String value = cell.getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }

    @Test
    public void test2() throws Exception {
        XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File("D:/poi.xlsx")));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i=0;i<=lastRowNum;i++) {
            XSSFRow row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (int j=0;j<lastCellNum;j++) {
                XSSFCell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }
}
*/
