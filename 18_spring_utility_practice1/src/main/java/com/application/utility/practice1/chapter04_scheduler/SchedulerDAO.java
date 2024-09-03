package com.application.utility.practice1.chapter04_scheduler;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.utility.practice1.data.BrandDTO;
import com.application.utility.practice1.data.ProductDTO;

@Mapper
public interface SchedulerDAO {

	public List<ProductDTO> getProductList();
	public List<BrandDTO> getBrandList();
	
}
