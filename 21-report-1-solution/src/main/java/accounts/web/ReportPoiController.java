package accounts.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import accounts.Account;
import accounts.AccountManager;

@Controller
public class ReportPoiController {

	public static final short FONT_COLOR = new HSSFColor.BLACK().getIndex();
	public static final short FONT_COLOR_TITLE = new HSSFColor.WHITE().getIndex();
	public static final short BACKGROUND_COLOR = new HSSFColor.GREEN().getIndex();
	public static final String FONT_TYPE = HSSFFont.FONT_ARIAL;
	
	@Autowired
	private AccountManager accountManager;
	 
	@RequestMapping("/accountPoiReport.xls")
	public void generateExcelPoiReport(HttpServletResponse response) throws IOException {
		
		Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("Accounts");
	    
	    int rowNum = 1;
	    Row row = sheet.createRow(rowNum++);
	    
	    Font font = wb.createFont();
	    font.setFontHeightInPoints((short)24);
	    font.setFontName(FONT_TYPE);
	    font.setColor(FONT_COLOR_TITLE);

	    /***  Header *****/
	    CellStyle style = wb.createCellStyle();
	    style.setFont(font);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style.setFillForegroundColor(BACKGROUND_COLOR);	     

	    Cell cell = row.createCell((short) 1);
	    cell.setCellValue("Account Report per Beneficiary");
	    cell.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(
	            1, //first row (0-based)
	            2, //last row  (0-based)
	            1, //first column (0-based)
	            16  //last column  (0-based)
	    ));

	    /***  Body *****/
	    font = wb.createFont();
	    font.setFontHeightInPoints((short)12);
	    font.setFontName(FONT_TYPE);
	    font.setColor(FONT_COLOR);
	    
	    style = wb.createCellStyle();
	    style.setFont(font);
	    
	    rowNum =  rowNum + 3;
	 	List<Account> accounts = accountManager.getAllAccounts();
	 	for (Account account : accounts) {
	 		
	 		row = sheet.createRow(rowNum++);
	 		
	 		cell = row.createCell((short) 1);
	 		cell.setCellStyle(style);
	 		cell.setCellValue(account.getName());
	 		
	 		cell = row.createCell((short) 2);
	 		cell.setCellValue(account.getNumber());
	 		cell.setCellStyle(style);
	 		
	 		cell = row.createCell((short) 3);
	 		cell.setCellValue(account.getEntityId());
	 		cell.setCellStyle(style);
	 	}

	 	sheet.autoSizeColumn(1);
	 	sheet.autoSizeColumn(2);
	 	sheet.autoSizeColumn(3);
	 	
	 	response.setContentType("application/vnd.ms-excel");
	 	response.setHeader("Content-Disposition","attachment; filename=\""+ "Accounts.xls" + "\"");

	 	OutputStream out = response.getOutputStream();			  		
	 	wb.write(out);				  		
	 	out.close();
	}

}

