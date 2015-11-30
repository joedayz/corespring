package accounts.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import accounts.ReportManager;

@Controller
public class ReportJasperController {

	@Autowired
	private ReportManager reportManager;
	
	@RequestMapping("/accountReport.pdf")
	public void generatePDFJasperReport(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException{
		generateJasperReport("pdf", request, response);
	}
	
	@RequestMapping("/accountReport.xls")
	public void generateXLSJasperReport(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException{
		generateJasperReport("xls", request, response);
	}
	
	@RequestMapping("/accountReport.xlsx")
	public void generateXLSXJasperReport(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException{
		generateJasperReport("xlsx", request, response);
	}
	
	public void generateJasperReport(String reportType, HttpServletRequest request, 
			HttpServletResponse response) throws JRException, IOException{
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		InputStream imageStream = request.getSession().getServletContext().getResourceAsStream("/report/coffee.jpg");
		parameters.put("REPORT_IMAGE", imageStream);
		parameters.put("REPORT_TITLE", "Accounts");
		
		List<Map<String, Object>> accounts = reportManager.getAllAccounts();
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(accounts);
		
		InputStream reportStream = request.getSession().getServletContext().getResourceAsStream("/report/AccountReport.jasper");
		OutputStream outStream = response.getOutputStream();
		if(reportType.equalsIgnoreCase("pdf")){
			
			byte[] byteStream = JasperRunManager.runReportToPdf(reportStream, parameters, ds);
			response.setHeader("Content-Disposition","attachment; filename=Accounts.pdf");
			response.setContentType("application/pdf");
			response.setContentLength(byteStream.length);
			outStream.write(byteStream, 0, byteStream.length);
		}
		else if(reportType.equalsIgnoreCase("xlsx")){
		
			JasperPrint jasperPrint = JasperFillManager.fillReport (reportStream, parameters, ds);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition","attachment; filename=\""+ "Accounts.xlsx" + "\"");
			JRXlsxExporter exporterXls = new JRXlsxExporter();
			exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
			exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
			exporterXls.exportReport();
		}
		else{
			
			JasperPrint jasperPrint = JasperFillManager.fillReport (reportStream, parameters, ds);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment; filename=\""+ "Accounts.xls" + "\"");
			JRXlsExporter exporterXls = new JRXlsExporter ();
			exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
			exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
			exporterXls.exportReport();
		}
		
		outStream.flush();
		outStream.close();
	}
}