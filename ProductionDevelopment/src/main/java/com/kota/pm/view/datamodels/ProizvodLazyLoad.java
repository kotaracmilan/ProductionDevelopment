package com.kota.pm.view.datamodels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerProizvodnihPodataka;
import com.kota.pm.domain.product.Proizvod;

@Component("proizvodLazyLoad")
public class ProizvodLazyLoad extends LazyDataModel<Proizvod> implements SelectableDataModel<Proizvod>{

	private static final long serialVersionUID = -8439240131325695785L;

	@Autowired
	private KontrolerProizvodnihPodataka kpp;
	
	@Override
	public List<Proizvod> load(int first, int pageSize, String sortField, 
			SortOrder sortOrder, Map<String, Object> filter) {
		
		Pageable page = new PageRequest(first/pageSize,pageSize);
		return kpp.findAllProizvod(page);
	}
	
	@Override
	public int getRowCount() {
		return Math.toIntExact(kpp.countProizvod());
	}
	
	@Override
	public Integer getRowKey(Proizvod proizvod) {
		return Math.toIntExact(proizvod.getId());
	}

	@Override
	public Proizvod getRowData(String rowKey) {
		return kpp.findProizvodById(Long.parseLong(rowKey)).get();
		
	}

	@Override
	public List<Proizvod> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		Pageable page = new PageRequest(first,pageSize);
		return kpp.findAllProizvod(page);
	}
	
	
}
