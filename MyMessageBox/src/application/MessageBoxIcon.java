package application;

public enum MessageBoxIcon {
	Information("StatusInformation_64x"), Warning("StatusWarning_64x"), Alert("StatusAlert_64x"), CriticalError("StatusCriticalError_64x");

	private String img;

	MessageBoxIcon(String img) {
		this.img = img;
	}
	
	public String getImg() {
		return img;
	}
}
