package com.kota.pm.view.datamodels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.zaposleni.Employee;
@Component("lazyDataLoadEmployee")
public class EmployeeLazyLoadDataModel extends LazyDataModel<Employee> {

	private static final long serialVersionUID = -6213812980087475628L;

	@Autowired
	private KontrolerPodatakaRadnika kpr;
	
	@Override
	public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		Pageable page = new PageRequest(first/pageSize, pageSize);
		List<Employee> l = kpr.listaZaposlenih(page);
		return l;
	}

	@Override
	public int getRowCount() {
		if(kpr == null)
			System.out.println("KPR je NULL u LazyLoadModelu");
			
		return Math.toIntExact(kpr.getEmployeeCount());
	}
	
	@Override
	public Integer getRowKey(Employee employee) {
		return employee.getId();
	}
	
	

}
