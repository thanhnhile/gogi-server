package vn.com.gigo.dtos;

public class DataResponse {
	private String errCode;
	private String errMsg;
	private int status;
	private Object data;
	
	public DataResponse() {
		super();
	}
	public DataResponse (Object data) {
		this.errCode = "200";
		this.errMsg = null;
		this.status = 200;
		this.data = data;
	}
	public DataResponse(String errCode, String errMsg, int status) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.status = status;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
