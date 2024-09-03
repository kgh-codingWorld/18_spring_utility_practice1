package com.application.utility.practice1.chapter05_export;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.utility.practice1.data.BrandDTO;
import com.application.utility.practice1.data.ProductDTO;
import com.application.utility.practice1.chapter04_scheduler.SchedulerDAO;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/export")
public class ExportController {
	
	@Autowired
	private SchedulerDAO schedulerDAO;

	@GetMapping("/main")
	public String main() {
		return "export/main";
	}
	
	@GetMapping("/ex")
	public void exportEx(HttpServletResponse response) {
  
		try {
			 
			// Excel 파일명
			String fileName = "엑셀파일다운로드예시";
			
			// Excel Sheet
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("와신기하다");
			
			// Excel 헤더
			String[] header = {"안녕", "하세요", "엑셀", "만들기", "처음해봐요"};
			Row row = sheet.createRow(0);                 // 1row를 생성한다.
			for (int i = 0; i < header.length; i++) {
			    Cell cell = row.createCell(i);			  // 1cell을 생성한다.
			    cell.setCellValue(header[i]);			  // 데이터를 대입한다.
			}
	
			// Excel 본문
			for (int i = 1; i < 10; i++) {
				
				row = sheet.createRow(i); 
				
				Cell cell = row.createCell(0);
				cell.setCellValue("컬럼1 데이터");
				
				cell = row.createCell(1);
				cell.setCellValue("컬럼2 데이터");
				
				cell = row.createCell(2);
				cell.setCellValue("컬럼3 데이터");
				
				cell = row.createCell(3);
				cell.setCellValue("컬럼4 데이터");
				
				cell = row.createCell(4);
				cell.setCellValue("컬럼5 데이터");
				
			}

			// 엑셀 파일 생성 및 다운로드
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();
	
		} catch(IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	@GetMapping("/productExport")
	public void productExport(HttpServletResponse response) {
  
		try {
			 
			// Excel 파일명
			String fileName = "productList";
			
			// Excel Sheet
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("상품리스트");
			
			// Excel 헤더
			String[] header = {"PRODUCT_ID", "PRODUCT_NM", "PRICE", "DELIVERY_PRICE", "ENROLL_DT", "BRAND_ID"};
			Row row = sheet.createRow(0);                 // 1row를 생성한다.
			for (int i = 0; i < header.length; i++) {
			    Cell cell = row.createCell(i);			  // 1cell을 생성한다.
			    cell.setCellValue(header[i]);			  // 데이터를 대입한다.
			}
	
			// Excel 본문
			List<ProductDTO> productList = schedulerDAO.getProductList(); // DB에서 데이터 조회
			
			int i = 1; // row index
			for (ProductDTO productDTO : productList) {
				row = sheet.createRow(i);
				
				Cell cell = row.createCell(0);
				cell.setCellValue(productDTO.getProductId());
				
				cell = row.createCell(1);
				cell.setCellValue(productDTO.getProductNm());
				
				cell = row.createCell(2);
				cell.setCellValue(productDTO.getPrice());
				
				cell = row.createCell(3);
				cell.setCellValue(productDTO.getDeliveryPrice());
				
				cell = row.createCell(4);
				cell.setCellValue(productDTO.getEnrollAt());
				
				cell = row.createCell(5);
				cell.setCellValue(productDTO.getBrandId());
				
				i++;
			}
			
//			for (int i = 1; i < 10; i++) {
//				
//				row = sheet.createRow(i); 
//				
//				Cell cell = row.createCell(0);
//				cell.setCellValue("컬럼1 데이터");
//				
//				cell = row.createCell(1);
//				cell.setCellValue("컬럼2 데이터");
//				
//				cell = row.createCell(2);
//				cell.setCellValue("컬럼3 데이터");
//				
//				cell = row.createCell(3);
//				cell.setCellValue("컬럼4 데이터");
//				
//				cell = row.createCell(4);
//				cell.setCellValue("컬럼5 데이터");
//				
//			}

			// 엑셀 파일 생성 및 다운로드
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();
	
		} catch(IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	@GetMapping("/brandExport")
	public void brandExport(HttpServletResponse response) {
  
		try {
			 
			// Excel 파일명
			String fileName = "brandList";
			
			// Excel Sheet
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("브랜드리스트");
			
			// Excel 헤더
			String[] header = {"브랜드이름", "판매여부"};
			Row row = sheet.createRow(0);                 // 1row를 생성한다.
			for (int i = 0; i < header.length; i++) {
			    Cell cell = row.createCell(i);			  // 1cell을 생성한다.
			    cell.setCellValue(header[i]);			  // 데이터를 대입한다.
			}
	
			// Excel 본문
			List<BrandDTO> brandList = schedulerDAO.getBrandList();
			
			int i = 1;
			for (BrandDTO brandDTO : brandList) {
				
				row = sheet.createRow(i); 
				
				Cell cell = row.createCell(0);
				cell.setCellValue(brandDTO.getBrandNm());
				
				cell = row.createCell(1);
				cell.setCellValue(brandDTO.getActiveYn());
				
			}

			// 엑셀 파일 생성 및 다운로드
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();
	
		} catch(IOException e) {
		    e.printStackTrace();
		}
		
	}
}
