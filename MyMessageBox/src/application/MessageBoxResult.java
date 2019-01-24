package application;

public enum MessageBoxResult {
	Przerwij("Wciœniêto Przerwij"), Ponów("Wciœniêto Ponów"), Ignoruj("Wciœniêto Ignoruj"), OK("Wciœniêto OK"), Anuluj("Wciœniêto Anuluj"), Tak("Wciœniêto Tak"), Nie("Wciœniêto Nie");

	private String result;

	MessageBoxResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
}
