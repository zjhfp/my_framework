package com.pan.bbf.paginator;

import java.util.List;

public class Page<T> implements Pagination{
	/** 页码 */
	protected int pageNo;
	/** 每页记录条数 */
	protected int pageSize;
	/** 总页数 */
	protected int totalSize;
	/** 总记录条数 */
	protected int totalRecords = -1;

	/** 用于存放查询结果 */
	protected List<T> resultList;

	public Page(int pageNo, int pageSize) {
		if (pageNo <= 0) {
			throw new IllegalArgumentException("pageNo must be greater than 0.");
		}
		if (pageSize <= 0) {
			throw new IllegalArgumentException("pageSize must be greater than 0.");
		}
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	@Override
	public int getPageNo() {
		return pageNo;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getTotalRecords() {
		return totalRecords;
	}

	@Override
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		if (totalRecords < 0) { // 如果总数为负数, 表未设置
			totalSize = 0;
		} else { // 计算总页数
			totalSize = (totalRecords / pageSize) + (totalRecords % pageSize == 0 ? 0 : 1);
		}
	}
	
	@Override
	public int getTotalSize(){
		return totalSize;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}
