package com.longersec.passwordsafe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longersec.passwordsafe.mapper.PetCategoryMapper;
import com.longersec.passwordsafe.pojo.entity.PetCategory;
import com.longersec.passwordsafe.pojo.vo.response.MenuResp;
import com.longersec.passwordsafe.service.PetCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PetCategoryServiceImpl extends ServiceImpl<PetCategoryMapper, PetCategory> implements PetCategoryService {

	@Override
	public List<MenuResp> getMenu() {
		QueryWrapper<PetCategory> queryWrapper = new QueryWrapper<>();
		//增加未删除的菜单查询条件
		queryWrapper.eq("deleted",0);
		//查询所有菜单
		List<PetCategory> list = baseMapper.selectList(queryWrapper);
		//先查询一级菜单
		queryWrapper.eq("level",0);
		List<PetCategory> oneList = baseMapper.selectList(queryWrapper);
		//查询二季菜单
		queryWrapper.clear();
		queryWrapper.eq("deleted",0).eq("level",1);
		List<PetCategory> twoList = baseMapper.selectList(queryWrapper);
		log.info("菜单为:"+list);
		log.info("一级菜单为:"+oneList);
		log.info("二级菜单为:"+twoList);
		//封装最终集合
		List<MenuResp> menuResp = new ArrayList<>(10);
		for (PetCategory oneCategory : oneList) {
			MenuResp menuResp1 = new MenuResp();
			BeanUtils.copyProperties(oneCategory,menuResp1);
			List<PetCategory> children = new ArrayList<>(10);
			for (PetCategory twoCategory : twoList) {
				if (oneCategory.getId().equals(twoCategory.getPid())){
					children.add(twoCategory);
				}
			}
			menuResp1.setChildren(children);
			menuResp.add(menuResp1);
		}
		return menuResp;
	}
}
