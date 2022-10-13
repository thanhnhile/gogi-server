package vn.com.gigo.dtos;

import java.util.List;

public class PagingDto {
	private int currentPage;
	private int totalPages;
	private long totalElements;
	private List<?> content;
	public PagingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PagingDto(int currentPage,int totalPages, long totalElements, List<?> content) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.content = content;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public List<?> getContent() {
		return content;
	}
	public void setContent(List<?> content) {
		this.content = content;
	}
	
	
}
