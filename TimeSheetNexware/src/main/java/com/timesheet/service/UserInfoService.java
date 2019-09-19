package com.timesheet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.mappers.UserInfoMapper;
import com.timesheet.model.UserInfo;

@Service
public class UserInfoService {
	
	@Autowired
	UserInfoMapper userMapper;
	
	
	public boolean insertUserInfo(List<UserInfo> userList) {
		
		int createdRow=userMapper.insertUserInfoList(userList);
		
		return (0<createdRow);
	}
	
	public List<UserInfo> getUserInfoListByEmployeeId(int employeeId,int year,String month){
		
		return userMapper.getUserInfoListByEmployeeId(employeeId, year, month);
		
	}

}
