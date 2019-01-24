package application;

public enum MessageBoxResult {
	Przerwij("Wci�ni�to Przerwij"), Pon�w("Wci�ni�to Pon�w"), Ignoruj("Wci�ni�to Ignoruj"), OK("Wci�ni�to OK"), Anuluj("Wci�ni�to Anuluj"), Tak("Wci�ni�to Tak"), Nie("Wci�ni�to Nie");

	private String result;

	MessageBoxResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
}
