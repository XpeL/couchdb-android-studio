package ictcg.couchdb.retrofit.common;

import java.util.List;

public class _allChangesDto {
	private List<Row> rows;
	private int total_rows;
	private int offset;
	
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	public int getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	@Override
	public String toString() {
		return String.valueOf(total_rows);
	}
}
