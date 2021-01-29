package com.kota.pm.view.datamodels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
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

	private KontrolerPodatakaRadnika kpr;
	
	

	public EmployeeLazyLoadDataModel(KontrolerPodatakaRadnika kpr) {
		super();
		this.kpr = kpr;
	}

	@Override
	public int getRowCount() {
		if(kpr == null)
			System.out.println("KPR je NULL u LazyLoadModelu");
			
		return Math.toIntExact(kpr.getEmployeeCount());
	}
	
	@Override
	public String getRowKey(Employee employee) {
		return String.valueOf(employee.getId());
	}


	@Override
	public List<Employee> load(int first, int pageSize, Map<String, SortMeta> sortBy,
			Map<String, FilterMeta> filterBy) {
		Pageable page = PageRequest.of(first/pageSize, pageSize);
		List<Employee> l = kpr.listaZaposlenih(page);
		return l;
	}
	
	

}
