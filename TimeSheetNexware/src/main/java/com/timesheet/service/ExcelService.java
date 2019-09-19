package com.timesheet.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.timesheet.model.UserInfo;

@Service
public class ExcelService {

	public List<UserInfo> getDataFromFile(String filePath) {

		List<UserInfo> userInfoList = new ArrayList<>();

		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File(filePath));

			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			// 1. You can obtain a rowIterator and columnIterator and iterate over them
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				if (null != row.getCell(0) && !row.getCell(0).toString().equals("S_Id")) {
					UserInfo userInfo = new UserInfo();
					userInfo.setSerialId((int) Double.parseDouble(row.getCell(0).toString()));
					userInfo.setEmployeeId((int) Double.parseDouble(row.getCell(1).toString()));
					userInfo.setYear((int) Double.parseDouble(row.getCell(2).toString()));
					userInfo.setMonth(row.getCell(3).toString());
					userInfo.setDate(row.getCell(4).toString());
					userInfo.setHoliday(row.getCell(9).toString());
					if (userInfo.getHoliday().equalsIgnoreCase("working")) {
						userInfo.setInTime(row.getCell(5).toString());

						userInfo.setOutTime(row.getCell(6).toString());

						userInfo.setLunchTime(row.getCell(7).toString());

						userInfo.setTotalHours(row.getCell(8).toString());

					}

					userInfo.setProjectId((int) Double.parseDouble(row.getCell(10).toString()));

					userInfo.setRemarks(row.getCell(11).toString());

					userInfoList.add(userInfo);

				}
			}

			workbook.close();

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfoList;

	}

	
}
