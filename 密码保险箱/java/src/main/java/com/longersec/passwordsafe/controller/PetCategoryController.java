package com.longersec.passwordsafe.controller;


import com.longersec.passwordsafe.common.result.BusinessException;
import com.longersec.passwordsafe.common.result.DataResult;
import com.longersec.passwordsafe.common.result.code.BaseResponseCode;
import com.longersec.passwordsafe.pojo.entity.PetCategory;
import com.longersec.passwordsafe.pojo.vo.response.MenuResp;
import com.longersec.passwordsafe.service.PetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PetCategoryController {
	@Autowired
	private PetCategoryService petCategoryService;

	/** 添加分类 **/
	@PostMapping("addCategory")
	public DataResult<Integer> addCategory(@RequestBody PetCategory petCategory) {
		boolean save = petCategoryService.save(petCategory);
		if (!save) {
			throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
		}
		return DataResult.success();
	}

	/**查询菜单 **/
	@GetMapping("getMenu")
	public DataResult<List<MenuResp>> DataResultgetMenu(){
		List<MenuResp> list = petCategoryService.getMenu();
		DataResult<List<MenuResp>> success = DataResult.success();
		success.setData(list);
		return success;
	}
}
