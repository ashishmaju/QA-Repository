package com.ndtv.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public XSSFWorkbook workbook;

	public ArrayList<String> getData(String TestCase) {

		ArrayList<String> al = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/ndtv/resources/TestData.xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int numOfSheets = workbook.getNumberOfSheets(); 						// num of sheet present in workbook
			Sheet sheet;
			for (int i = 0; i < numOfSheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase("APIData")) {
					sheet = workbook.getSheetAt(i); 									// get sheet instance
					Iterator<Row> row = sheet.iterator(); 							// get all the row present in the sheet
					int column = 0; 											// Consider first column number of cell is 0
					while (row.hasNext()) {
						Row firstRow = row.next(); 								// Find the first row
						Iterator<Cell> cell = firstRow.cellIterator(); 			// loop for all number of cell in the particular

						int k = 0;
						while (cell.hasNext()) {
							Cell value = cell.next();
							if (value.getStringCellValue().equalsIgnoreCase("TestCase")) 	// Conditioning for the Data2																// existence cell value
							{
								column = k;
							}
							k++;
						}
						while (row.hasNext()) { 											// Once column is got iterate for the that row and it's cell value
							Row r = row.next();
							if (r.getCell(column).getStringCellValue().equalsIgnoreCase(TestCase)) { 	// condition																		// row found
								Iterator<Cell> c2 = r.cellIterator(); 									// started iterating cell
								while (c2.hasNext()) { 													// checking cell for value
									Cell c = c2.next(); 
									if (c.getCellTypeEnum().equals(CellType.STRING)) 					// checking if cell have string																	// value
									{
										al.add(c.getStringCellValue()); 								// Storing cell value to arraylist<String>
									} else {
										al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
									}
								}
							}

						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return al;
	}
}


