package com.center.buuluu.util;

import java.util.List;

public class PageUtil {
	/**
	 * 总记录数
	 */
	private int total;

	/**
	 * 每页记录数
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private int pageNum;

	/**
	 * 当前页结果集
	 */
	private List<?> datas;

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
