package api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class FunctionResponse {

	private final String status;
	private final String message;
	private final int code;
	private final String timestamp;

	private final Hashtable<String, Object> data;
	private final List<String> errors;
	private final MetaData meta = new MetaData();

	public FunctionResponse(String input) {
		this.status = "success";
		this.message = "Request successful";
		this.code = 200;
		this.timestamp = LocalDateTime.now().toString();
		this.errors = new ArrayList<>();

		this.data = new Hashtable<>();

		try {
			getData();

		} catch (Exception e) {

			this.errors.add(e.getClass().getSimpleName());
		}

	}

	public void getData(String input) {

	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Hashtable<String, Object> getData() {
		return data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public MetaData getMeta() {
		return meta;
	}

}
