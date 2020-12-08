package cn.java.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * description：QQ：1841670794，870599752(加好友时记得备注哦)
 * author：丁鹏(大胆开车，幽默讲课)
 * date：20:16
 */
public class GoodDaoImpl {
    private SqlSession session = null;

    @Before
    public void init() throws Exception {
        //SqlSession--->SqlSessionFactory--->SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        InputStream ins = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sessionFacotory = ssfb.build(ins);
        session = sessionFacotory.openSession();
    }

    @Test
    public void getAllGoods(){
        //开始分页
        PageHelper.startPage(3,10);
        //开始执行sql语句
        List<Map<String,Object>> goodList = session.selectList("cn.java.dao.impl.GoodDaoImpl.getAllGoods");
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(goodList);
        //遍历执行后的结果
        List<Map<String, Object>> resultList = pageInfo.getList();
        resultList.forEach(temp-> System.out.println(temp));
    }

}
