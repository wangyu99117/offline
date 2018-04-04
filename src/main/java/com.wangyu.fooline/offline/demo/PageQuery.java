package com.wangyu.fooline.offline.demo;

public class PageQuery {

	/**
	 * 数据id，传此参可提高查询效率，查询结果为大于id的记录
	 */
	private long startId = 0l;

	/**
	 * 数据id，传此参可提高查询效率，查询结果为小于id的记录
	 */
	private long endId = Long.MAX_VALUE;

	private int pageNo = 1;

	private int pageSize = 10;

	private String sortField;

	private String sortDir = "desc";

	public PageQuery(){}

	public PageQuery(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public PageQuery(int pageNo, int pageSize, String sortField) {
		this(pageNo, pageSize);
		this.sortField = sortField;
	}

	public PageQuery(int pageNo, int pageSize, String sortField, String sortDir) {
		this(pageNo, pageSize, sortField);
		this.sortDir = sortDir;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getStartId() {
		return startId;
	}

	public PageQuery setStartId(long startId) {
		this.startId = startId;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public long getEndId() {
		return endId;
	}

	public PageQuery setEndId(long endId) {
		this.endId = endId;
		return this;
	}

	@Override
	public String toString() {
		return "PageQuery{" +
				"startId=" + startId +
				", endId=" + endId +
				", pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", sortField='" + sortField + '\'' +
				", sortDir='" + sortDir + '\'' +
				'}';
	}
}
