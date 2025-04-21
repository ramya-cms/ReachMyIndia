package Generic;

import org.testng.annotations.DataProvider;

public class TVS_DataProvider extends ExcelUtils {

    @DataProvider(name = "excelData")
    public static Object[][] getExcelData() throws Exception {
        // Specify the Excel file path and sheet name
        String filePath = Path; // Ensure 'Path' is correctly initialized in your utility class
        String sheetName = "Sheet4";

        try {
            // Fetch data from Excel using utility method
            return ExcelUtils.getTableArray(filePath, sheetName);
        } catch (Exception e) {
            System.err.println("Error reading data from Excel file: " + filePath + ", Sheet: " + sheetName);
            e.printStackTrace();
            throw new Exception("Failed to fetch data from Excel file. Please check the file path or sheet name.");
        }
    }
}
