package ie.gmit.sw.stringcompare;


//Enums for all the algorithms, this essentially replaces the usual switch statement
//Adding a new algorithm would be easy enough to add it's name and class here
//Take advantage of polymorphism rather than hardcoding strings into a switch statement
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
	
	//Methods for returning the name and new instances, the names are for populating a map
	public abstract StringComparable getNewInstance();
	public abstract String getName();
}
