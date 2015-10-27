package com.pan.bbf.paginator;

public interface Pagination {
	/**
	 * @return 当前页码
	 */
	int getPageNo();

	/**
	 * @return 每页记录数
	 */
	int getPageSize();

	/**
	 * @return 总记录数: 负数表尚未设置, 挡截器会使用count语句统计总数; 0或正整数表总数已设置, 挡截器将不会统计总数.
	 */
	int getTotalRecords();

	/**
	 * @param totalCount 设置记录总数
	 */
	void setTotalRecords(int totalRecords);
	
	/**
	 * @return 总页数
	 */
	int getTotalSize();
}
