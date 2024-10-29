package com.jb.nexacroserver.domain.employee.controller;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.nexacroserver.domain.employee.dto.EmployeeSearch;
import com.jb.nexacroserver.domain.employee.service.EmployeeService;
import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataTypes;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.tx.PlatformException;
import com.nexacro.java.xapi.tx.PlatformResponse;
import com.nexacro.java.xapi.tx.PlatformType;
import com.nexacro.uiadapter.spring.core.annotation.ParamDataSet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<byte[]> getEmployeeList(@RequestParam(required = false) Long departmentId) throws PlatformException {
		PlatformData outData = new PlatformData();

		DataSet dsEmployee = new DataSet("ds_employee");
		dsEmployee.addColumn("employeeId", DataTypes.INT);
		dsEmployee.addColumn("employeeName", DataTypes.STRING);
		dsEmployee.addColumn("gender", DataTypes.STRING);
		dsEmployee.addColumn("departmenCode", DataTypes.STRING);
		dsEmployee.addColumn("departmentName", DataTypes.STRING);
		dsEmployee.addColumn("positionCode", DataTypes.STRING);
		dsEmployee.addColumn("positionName", DataTypes.STRING);
		dsEmployee.addColumn("salary", DataTypes.INT);
		dsEmployee.addColumn("hireDate", DataTypes.DATE);
		// 필요한 다른 컬럼들도 추가하세요

		List<EmployeeSearch> employeeList = employeeService.getEmployeeList(departmentId);

		for (EmployeeSearch emp : employeeList) {
			int row = dsEmployee.newRow();
			dsEmployee.set(row, "employeeId", emp.getEmployeeId());
			dsEmployee.set(row, "employeeName", emp.getEmployeeName());
			dsEmployee.set(row, "gender", emp.getGender());
			dsEmployee.set(row, "departmenCode", emp.getDepartmenCode());
			dsEmployee.set(row, "departmentName", emp.getDepartmentName());
			dsEmployee.set(row, "positionCode", emp.getPositionCode());
			dsEmployee.set(row, "positionName", emp.getPositionName());
			dsEmployee.set(row, "salary", emp.getSalary());
			dsEmployee.set(row, "hireDate", emp.getHireDate());
		}

		outData.addDataSet(dsEmployee);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PlatformResponse pRes = new PlatformResponse(baos, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		pRes.setData(outData);
		pRes.sendData();

		return ResponseEntity
			.ok()
			.contentType(MediaType.APPLICATION_XML)
			.body(baos.toByteArray());
	}



	@GetMapping("/{employeeId}")
	public ResponseEntity<byte[]> deleteEmployee(@PathVariable Long employeeId) throws PlatformException {
		employeeService.deleteEmployee(employeeId);

		PlatformData outData = new PlatformData();

		// 삭제 성공 메시지를 담은 데이터셋 생성
		DataSet dsResult = new DataSet("ds_result");
		dsResult.addColumn("result", DataTypes.STRING);
		dsResult.addColumn("message", DataTypes.STRING);

		int row = dsResult.newRow();
		dsResult.set(row, "result", "SUCCESS");
		dsResult.set(row, "message", "Employee successfully deleted");

		outData.addDataSet(dsResult);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PlatformResponse pRes = new PlatformResponse(baos, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		pRes.setData(outData);
		pRes.sendData();

		return ResponseEntity
			.ok()
			.contentType(MediaType.APPLICATION_XML)
			.body(baos.toByteArray());
	}


	@PostMapping("")
	public ResponseEntity<byte[]> addEmployee(@ParamDataSet(name="ds_input") DataSet dsInput) throws PlatformException {
		// 입력 데이터 처리
		String employeeName = dsInput.getString(0, "employeeName");
		String gender = dsInput.getString(0, "gender");
		int departmentId = dsInput.getInt(0, "departmentId");
		int positionId = dsInput.getInt(0, "positionId");
		int salary = dsInput.getInt(0, "salary");

		System.out.println("employeeName: " + employeeName);
		System.out.println("gender " + gender);
		System.out.println("departmentId: " + departmentId);
		System.out.println("positionId: " + positionId);
		System.out.println("salary: " + salary);


		// 여기서 employeeService를 사용하여 직원 추가 로직 구현
		// 예: employeeService.addEmployee(employeeName, gender, departmentId, positionId, salary);

		PlatformData outData = new PlatformData();

		// 결과 메시지를 담은 데이터셋 생성
		DataSet dsResult = new DataSet("ds_result");
		dsResult.addColumn("result", DataTypes.STRING);
		dsResult.addColumn("message", DataTypes.STRING);

		int row = dsResult.newRow();
		dsResult.set(row, "result", "SUCCESS");
		dsResult.set(row, "message", "Employee successfully added");

		outData.addDataSet(dsResult);

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