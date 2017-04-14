package ecx.mpopijac.restaurants.models;

public enum ServiceStatus {
	SUCCESS, ERROR, UNKNOWN_ERROR;

	public static ServiceStatus returnStatus(int returnedValue) {
		switch (returnedValue) {
		case 0:
			return ServiceStatus.ERROR;
		case 1:
			return ServiceStatus.SUCCESS;
		default:
			return ServiceStatus.UNKNOWN_ERROR;
		}
	}
}
