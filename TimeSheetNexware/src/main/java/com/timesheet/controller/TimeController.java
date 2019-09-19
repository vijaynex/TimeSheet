package com.timesheet.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.timesheet.model.UserInfo;
import com.timesheet.service.ExcelService;
import com.timesheet.service.UserInfoService;

@Controller
public class TimeController {

	@Autowired
	ExcelService excelService;
	
	@Autowired
	UserInfoService userInfoService;

	private static String uploadFolder = "E:\\sts\\sts-workspace\\TimeSheetNexware\\ExcelFiles//";

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(Model model, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:upload";
		}
		

		String filePath = uploadFolder +LocalDate.now() +file.getOriginalFilename();
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<UserInfo> userInfoList = excelService.getDataFromFile(filePath);

		model.addAttribute("userInfoList", userInfoList);

		return "jquerytable";
	}
	
	@GetMapping("/employeeSearch")
    public String employeeSearch(Model model,UserInfo userInfo) {
		
       model.addAttribute("userInfo",userInfo);
        
       return "employeeSearch";
	}
	
	@PostMapping("/userReport")
    public String userReport(UserInfo userInfo,Model model) {
		
		int employeeId=userInfo.getEmployeeId();
		int year=userInfo.getYear();
		String month=userInfo.getMonth();
		List<UserInfo> userInfoList = userInfoService.getUserInfoListByEmployeeId(employeeId, year, month);
        
		model.addAttribute("userInfoList", userInfoList);
       return "employeeTimeSheet";
	}
}
