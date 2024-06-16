package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void setExcelFile(String path, String sheetName) throws IOException {
        FileInputStream excelFile = new FileInputStream(path);
        workbook = new XSSFWorkbook(excelFile);
        sheet = workbook.getSheet(sheetName);
    }

    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return "";
        }
    }

    public static List<String> getRowData(int rowNum) {
        List<String> rowData = new ArrayList<>();
        Row row = sheet.getRow(rowNum);
        for (Cell cell : row) {
            if (cell.getCellType() == CellType.STRING) {
                rowData.add(cell.getStringCellValue());
            } else if (cell.getCellType() == CellType.NUMERIC) {
                rowData.add(String.valueOf((int) cell.getNumericCellValue()));
            }
        }
        return rowData;
    }

    public static Object[][] getTableArray(String filePath, String sheetName) throws Exception {
        String[][] tabArray = null;
        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);

            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();

            tabArray = new String[totalRows - 1][totalCols];
            for (int i = 1; i < totalRows; i++) {
                for (int j = 0; j < totalCols; j++) {
                    tabArray[i - 1][j] = getCellData(i, j);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return tabArray;
    }
}
