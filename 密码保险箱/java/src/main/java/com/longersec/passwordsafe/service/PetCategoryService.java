package com.longersec.passwordsafe.service;

import com.longersec.passwordsafe.pojo.entity.PetCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longersec.passwordsafe.pojo.vo.response.MenuResp;

import java.util.List;

public interface PetCategoryService extends IService<PetCategory> {

	List<MenuResp> getMenu();
}

