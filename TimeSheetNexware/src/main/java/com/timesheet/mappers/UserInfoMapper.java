package com.timesheet.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.timesheet.model.UserInfo;

@Mapper
public interface UserInfoMapper {
	
	List<UserInfo> findAll();

	int insertUserInfoList(List<UserInfo> userList);
	
	List<UserInfo> getUserInfoListByEmployeeId(int employeeId,int year,String month);

}
