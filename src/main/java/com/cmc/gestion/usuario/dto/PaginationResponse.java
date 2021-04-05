package com.cmc.gestion.usuario.dto;

import java.util.List;

public class PaginationResponse<T> {
	private List<T> data;
	private int pageSize;
	private int pageKey;
	private long pageTotal;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageKey() {
		return pageKey;
	}
	public void setPageKey(int pageKey) {
		this.pageKey = pageKey;
	}
	public long getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(long pageTotal) {
		this.pageTotal = pageTotal;
	}
	
	

}
