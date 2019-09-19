package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.timesheet.model.UserInfo;
import com.timesheet.service.UserInfoService;

@RestController
public class TimeSheetRestController {
	
	@Autowired
	UserInfoService userInfoService;

	@PostMapping("/save")
	public boolean uploadStatus(@RequestBody List<UserInfo> userList) {
		return userInfoService.insertUserInfo(userList);
	}

}
