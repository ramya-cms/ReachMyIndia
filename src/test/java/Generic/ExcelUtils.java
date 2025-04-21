package Generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils implements Auto_Constant{

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    // Method to get data from Excel
    public static Object[][] getTableArray(String filePath, String sheetName) throws Exception {
        try (FileInputStream ExcelFile = new FileInputStream(filePath)) {
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);

            int totalRows = ExcelWSheet.getLastRowNum();
            int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

            String[][] tabArray = new String[totalRows][totalCols];

            for (int i = 1; i <= totalRows; i++) { // Start from row 1 to skip headers
                for (int j = 0; j < totalCols; j++) {
                    tabArray[i - 1][j] = getCellData(i, j);
                }
            }
            return tabArray;
        }
    }

    public static String getCellData(int RowNum, int ColNum) {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            if (Cell == null) return ""; // Handle blank cells

            switch (Cell.getCellType()) {
                case STRING:
                    return Cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(Cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(Cell.getBooleanCellValue());
                case BLANK:
                    return ""; // Return empty string for blank cells
                default:
                    return "UNSUPPORTED_CELL_TYPE";
            }
        } catch (Exception e) {
            System.err.println("Error reading cell at Row: " + RowNum + ", Col: " + ColNum + ". " + e.getMessage());
            return "";
        }
    }
}