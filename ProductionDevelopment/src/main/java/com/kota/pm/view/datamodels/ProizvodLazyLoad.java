package com.kota.pm.view.datamodels;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
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

	private KontrolerProizvodnihPodataka kpp;

	
	
	public ProizvodLazyLoad(KontrolerProizvodnihPodataka kpp) {
		super();
		this.kpp = kpp;
	}


	@Override
	public int getRowCount() {
		return Math.toIntExact(kpp.countProizvod());
	}


	@Override
	public Proizvod getRowData(String rowKey) {
		return kpp.findProizvodById(Long.parseLong(rowKey)).get();
		
	}


	@Override
	public String getRowKey(Proizvod object) {
		return String.valueOf(object.getId());
	}

	@Override
	public List<Proizvod> load(int first, int pageSize, Map<String, SortMeta> sortBy,
			Map<String, FilterMeta> filterBy) {
		Pageable page = PageRequest.of(first/pageSize,pageSize);
		return kpp.findAllProizvod(page);
	}
	
	
}
