package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Member;
import model.Porder;
import model.Porder_Detail;

public class CreateExecl {
	
	static HSSFSheet sheet = null;
	HSSFWorkbook execlbook = new HSSFWorkbook();
	
	XSSFWorkbook execlBook = new XSSFWorkbook();
	
	public void createExecl(String execlname, String sheetname, String[] titlename )
	{
		try {
			FileOutputStream out = new FileOutputStream(execlname);
			sheet = execlbook.createSheet(sheetname);
			HSSFRow row = sheet.createRow((short)0);

			for(int i = 0;i<titlename.length;i++)
			{
				row.createCell((short)i).setCellValue(titlename[i]);
			}
			
			execlbook.write(out);
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void insertPorderDetail(String execlname, String sheetname, List<Porder_Detail> detail)
	{
		try {
			execlbook = new HSSFWorkbook(new FileInputStream (execlname));
			HSSFSheet sheet = execlbook.getSheet(sheetname);
			int count = sheet.getPhysicalNumberOfRows();
			
			for (Porder_Detail de : detail) {
				HSSFRow row = sheet.createRow((short)count++);
	            row.createCell(0).setCellValue(de.getOrder_no());
	            row.createCell(1).setCellValue(de.getProduct_id());
	            row.createCell(2).setCellValue(de.getProduct_name());
	            row.createCell(3).setCellValue(de.getPrice());
	            row.createCell(4).setCellValue(de.getQuantity());
	            row.createCell(5).setCellValue(de.getMember());
	            row.createCell(6).setCellValue(de.getEmployee());
	        }

	        
	        FileOutputStream out;	//新增輸出檔案流
	        out = new FileOutputStream(execlname);
	        execlbook.write(out);	//把對應的execl工作簿存碟
	        out.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void insertMember(String execlname, String sheetname, List<Member> member)
	{
		try {
			execlbook = new HSSFWorkbook(new FileInputStream (execlname));
			HSSFSheet sheet = execlbook.getSheet(sheetname);
			int count = sheet.getPhysicalNumberOfRows();
			
			
			for(Member m : member)
			{
				HSSFRow row = sheet.createRow((short)count++);
				row.createCell((short) 0).setCellValue(m.getName());  
				row.createCell((short) 1).setCellValue(m.getUsername());        // 在索引0的位置建立單元格（左上端）
		        row.createCell((short) 2).setCellValue(m.getPassword());
		        row.createCell((short) 3).setCellValue(m.getEmail());
		        row.createCell((short) 4).setCellValue(m.getPhone());
		        row.createCell((short) 5).setCellValue(m.getAddress());
			}
			
	        
	        FileOutputStream out;	//新增輸出檔案流
	        out = new FileOutputStream(execlname);
	        execlbook.write(out);	//把對應的execl工作簿存碟
	        out.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//新的
	
	/** 銷售報表（含圖表） */
	public void createSalesReport(String execlname, List<Porder_Detail> details, List<Porder> orders) {
	    try (XSSFWorkbook book = new XSSFWorkbook()) {
	        XSSFSheet sheet = book.createSheet("銷售報表");

	        CellStyle headerStyle = createHeaderStyle(book);

	        String[] titles = {"月份", "銷售總額"};
	        Row header = sheet.createRow(0);
	        for (int i = 0; i < titles.length; i++) {
	            Cell cell = header.createCell(i);
	            cell.setCellValue(titles[i]);
	            cell.setCellStyle(headerStyle);
	        }

	        // 先建立 order_no → created_time 對照表
	        Map<String, LocalDateTime> orderTimeMap = new TreeMap<>();
	        for (Porder o : orders) {
	        	LocalDateTime time = null;
	        	if (o.getCreate_time() != null) {
	        	    time = o.getCreate_time().toLocalDateTime(); // Timestamp -> LocalDateTime
	        	}
	        	orderTimeMap.put(o.getOrder_no(), time);
	        }

	        // 統計每月銷售額
	        Map<YearMonth, Double> salesByMonth = new TreeMap<>();
	        for (Porder_Detail d : details) {
	            LocalDateTime created = orderTimeMap.get(d.getOrder_no());
	            if (created != null) {
	                YearMonth ym = YearMonth.from(created);
	                salesByMonth.merge(ym, d.getPrice() * (double)d.getQuantity(), Double::sum);
	            }
	        }

	        int rowIndex = 1;
	        for (Map.Entry<YearMonth, Double> e : salesByMonth.entrySet()) {
	            Row row = sheet.createRow(rowIndex++);
	            row.createCell(0).setCellValue(e.getKey().toString());
	            row.createCell(1).setCellValue(e.getValue());
	        }

	        autoSizeColumns(sheet, titles.length);

	        // 插入圖表
	        XSSFDrawing drawing = sheet.createDrawingPatriarch();
	        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 3, 1, 10, 20);

	        XSSFChart chart = drawing.createChart(anchor);
	        chart.setTitleText("每月銷售統計");
	        chart.setTitleOverlay(false);

	        XDDFChartLegend legend = chart.getOrAddLegend();
	        legend.setPosition(LegendPosition.BOTTOM);

	        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
	        bottomAxis.setTitle("月份");
	        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
	        leftAxis.setTitle("銷售總額");

	        int lastRow = sheet.getLastRowNum();

	        XDDFDataSource<String> months = XDDFDataSourcesFactory.fromStringCellRange(
	            sheet, new CellRangeAddress(1, lastRow, 0, 0));
	        XDDFNumericalDataSource<Double> totals = XDDFDataSourcesFactory.fromNumericCellRange(
	            sheet, new CellRangeAddress(1, lastRow, 1, 1));

	        XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
	        XDDFChartData.Series series = data.addSeries(months, totals);
	        series.setTitle("銷售額", null);
	        chart.plot(data);

	        try (FileOutputStream out = new FileOutputStream(execlname)) {
	            book.write(out);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/** 訂單報表（含訂單明細） */
	public void createOrderReport(String execlname, List<Porder> orders, List<Porder_Detail> details) {
	    try (XSSFWorkbook book = new XSSFWorkbook()) {
	        XSSFSheet sheet = book.createSheet("訂單報表");

	        CellStyle headerStyle = createHeaderStyle(book);

	        String[] titles = {"訂單編號", "會員", "員工", "商品名稱", "數量", "單價", "小計", "建立時間"};
	        Row header = sheet.createRow(0);
	        for (int i = 0; i < titles.length; i++) {
	            Cell cell = header.createCell(i);
	            cell.setCellValue(titles[i]);
	            cell.setCellStyle(headerStyle);
	        }

	        // 建立 order_no → Porder 的對照表，方便查找
	        Map<String, Porder> orderMap = new HashMap<>();
	        for (Porder o : orders) {
	            orderMap.put(o.getOrder_no(), o);
	        }

	        int rowIndex = 1;
	        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        // 輸出每一筆明細
	        for (Porder_Detail d : details) {
	            Porder o = orderMap.get(d.getOrder_no());
	            if (o == null) continue; // 如果 detail 找不到對應的 order 就跳過

	            Row row = sheet.createRow(rowIndex++);
	            row.createCell(0).setCellValue(o.getOrder_no());
	            row.createCell(1).setCellValue(d.getMember());
	            row.createCell(2).setCellValue(d.getEmployee());
	            row.createCell(3).setCellValue(d.getProduct_name());
	            row.createCell(4).setCellValue(d.getQuantity());
	            row.createCell(5).setCellValue(d.getPrice());
	            row.createCell(6).setCellValue(d.getPrice() * d.getQuantity());

	            LocalDateTime time = null;
	            if (o.getCreate_time() != null) {
	                time = o.getCreate_time().toLocalDateTime();
	            }
	            row.createCell(7).setCellValue(time != null ? fmt.format(time) : "");
	        }

	        autoSizeColumns(sheet, titles.length);

	        try (FileOutputStream out = new FileOutputStream(execlname)) {
	            book.write(out);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

    

    // ====== 共用方法 ======
    private CellStyle createHeaderStyle(XSSFWorkbook book) {
        CellStyle style = book.createCellStyle();
        Font font = book.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private void autoSizeColumns(Sheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
	
	
	
}
