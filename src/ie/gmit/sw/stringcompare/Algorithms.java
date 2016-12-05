package ie.gmit.sw.stringcompare;

public enum Algorithms {
	DAMERAULEVENSHTEIN{
		@Override
		public StringComparable getNewInstance(){
			return new DamerauLevenshtein();
		}

		@Override
		public String getName() {
			return "damerau-levenshtein distance";
		}
	},
	HAMMING{
		@Override
		public StringComparable getNewInstance(){
			return new Hamming();
		}

		@Override
		public String getName() {
			return "hamming distance";
			
		}
	},
	LEVENSHTEIN{
		@Override
		public StringComparable getNewInstance(){
			return new Levenshtein();
		}

		@Override
		public String getName() {
			return "levenshtein distance";
		}
	};
	
	public abstract StringComparable getNewInstance();
	public abstract String getName();
}
