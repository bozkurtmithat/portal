package my.portal.common;

public class DtoStringWrapper extends DtoWrapper<String> {

	private static final long serialVersionUID = 1L;
	private String value;

	public DtoStringWrapper() {
	}

	public DtoStringWrapper(String value) {
		super();
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
