package com.dao;
import java.util.*;
import com.entity.*;
/**
 * @version 时间：2018年5月12日 上午9:07:35
 *接口
 */
public interface IUserDao {
	//查所有
	List<UserData> getAll();
	//查单个
	UserData getOne(int deptno);
	//新增
	int insert (UserData d2);
	//修改
	int update(UserData d2);
	//删除
	int delete(int deptno);
}
