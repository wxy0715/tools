package com.longersec.passwordsafe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longersec.passwordsafe.mapper.PetConfigPasswordEncryptKeyMapper;
import com.longersec.passwordsafe.pojo.entity.PetConfigPasswordEncryptKey;
import com.longersec.passwordsafe.service.PetConfigPasswordEncryptKeyService;
import org.springframework.stereotype.Service;

@Service
public class PetConfigPasswordEncryptKeyServiceImpl extends ServiceImpl<PetConfigPasswordEncryptKeyMapper, PetConfigPasswordEncryptKey> implements PetConfigPasswordEncryptKeyService {

}
