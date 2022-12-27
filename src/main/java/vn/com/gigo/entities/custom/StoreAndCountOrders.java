package vn.com.gigo.entities.custom;



public class StoreAndCountOrders {
	private Long storeId;
	private String storeName;
	private String storeAddress;
	private Integer countOrders;
	
	
	public StoreAndCountOrders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StoreAndCountOrders(Long storeId, String storeName, String storeAddress, Integer countOrders) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.countOrders = countOrders;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long id) {
		this.storeId = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public Integer getCountOrders() {
		return countOrders;
	}
	public void setCountOrders(Integer countOrders) {
		this.countOrders = countOrders;
	}
	
	
}
