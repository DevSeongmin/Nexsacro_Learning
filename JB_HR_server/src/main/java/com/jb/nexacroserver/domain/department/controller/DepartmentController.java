package com.jb.nexacroserver.domain.department.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.nexacroserver.domain.department.dto.DepartmentSearch;
import com.jb.nexacroserver.domain.department.service.DepartmentService;
import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataTypes;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.tx.PlatformException;
import com.nexacro.java.xapi.tx.PlatformResponse;
import com.nexacro.java.xapi.tx.PlatformType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	@GetMapping
	public ResponseEntity<byte[]> getDepartmentList() throws PlatformException {
		PlatformData outData = new PlatformData();

		DataSet dsDepartment = new DataSet("ds_department");
		dsDepartment.addColumn("departmentId", DataTypes.INT);
		dsDepartment.addColumn("departmentName", DataTypes.STRING);

		List<DepartmentSearch> departmentList = departmentService.getDepartmentList();

		for (DepartmentSearch dept : departmentList) {
			int row = dsDepartment.newRow();
			dsDepartment.set(row, "departmentId", dept.getDepartmentId());
			dsDepartment.set(row, "departmentName", dept.getDepartmentName());
		}

		outData.addDataSet(dsDepartment);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PlatformResponse pRes = new PlatformResponse(baos, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		pRes.setData(outData);
		pRes.sendData();

		return ResponseEntity
			.ok()
			.contentType(MediaType.APPLICATION_XML)
			.body(baos.toByteArray());
	}
}


