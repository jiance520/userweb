package com.dao;
import java.util.*;
import com.entity.*;
/**
 * @version ʱ�䣺2018��5��12�� ����9:07:35
 *�ӿ�
 */
public interface IUserDao {
	//������
	List<UserData> getAll();
	//�鵥��
	UserData getOne(int deptno);
	//����
	int insert (UserData d2);
	//�޸�
	int update(UserData d2);
	//ɾ��
	int delete(int deptno);
}
