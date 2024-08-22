package com.application.utility.practice1.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BrandDTO {
	
	private long brandId;
	private String brandNm;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enteredAt;
	private String activeYn;
}
