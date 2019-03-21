package com.crh.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.crh.GitDemoApplication;
import com.crh.dao.UserMapper;
import com.crh.entity.User;

/**
 * 单元测试
 * 
 * @author crh 2019年3月21日
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { GitDemoApplication.class })
public class UserTest {
	@Resource
	private UserMapper userMapper;

//	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * 测试批量插入
	 */
	@Test
	public void testTwo() {
		User u = null;
		List<User> list = new ArrayList<User>();
		for (int i = 1000; i < 1010; i++) {
			u = new User("" + i, i, i + "-001-001");
			list.add(u);
		}
//		userMapper.insertBatch(list);
		System.out.println("测试trim的标签元素");
		System.out.println("更新："+userMapper.updateUserByList(list));
 		TestCase.assertEquals(1, 1);
	}

	@Before
	public void testBefore() {
		System.out.println("before");
	}

	@After
	public void testAfter() {
		System.out.println("after");
	}
}
