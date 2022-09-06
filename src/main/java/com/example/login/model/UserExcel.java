package com.example.login.model;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.login.dto.ListUser;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class UserExcel {
private XSSFWorkbook workbook;
private XSSFSheet sheet;
private List<User> listUser;

public UserExcel(List<User> list2) {
	this.listUser = list2;
	workbook =new XSSFWorkbook();
}
private void writeHeaderLine() {
	sheet = workbook.createSheet("Users");
	
	Row row = sheet.createRow(0);
	
	CellStyle style = workbook.createCellStyle();
	XSSFFont font =workbook.createFont();
	font.setBold(true);
	font.setFontHeight(16);
	style.setFont(font);
	//duplication 
	createCell(row,0,"id",style);
	createCell(row,1,"username",style);	
	createCell(row,2,"email",style);	
	createCell(row,3,"role",style);	
}
private void createCell(Row row , int columnCount, Object value, CellStyle style) {
	sheet.autoSizeColumn(columnCount);
	Cell cell = row.createCell(columnCount);
	if(value instanceof Integer) {
		cell.setCellValue((Integer) value);
	}else if (value instanceof Boolean) {
		cell.setCellValue((Boolean) value);
	}else {
		cell.setCellValue((String) value);
	}
	cell.setCellStyle(style);
}

private void writeDataLines() {
	int rowCount = 1;
	CellStyle style = workbook.createCellStyle();
	XSSFFont font = workbook.createFont();
	font.setFontHeight(14);
	style.setFont(font);
	
	for(User user : listUser) {
		Row row = sheet.createRow(rowCount++);
		int columnCount =0;
		createCell(row, columnCount++, user.getId(),style);
		createCell(row, columnCount++, user.getUsername(),style);
		createCell(row, columnCount++, user.getEmail(),style);
		createCell(row, columnCount++, user.getRole(),style);
	}
}

public void export(HttpServletResponse response) throws IOException {
	writeDataLines();
	writeHeaderLine();
	
	ServletOutputStream outputStream = response.getOutputStream();
	workbook.write(outputStream);
	((Closeable) workbook).close();
	outputStream.close();
	
}
}
