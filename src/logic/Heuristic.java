package logic;

public class Heuristic {
	public enum hType{
		MANHATTEN,
		EUCLIDEAN
	}
	
	private final hType type;
	
	public Heuristic(hType ht){
		type = ht;
	}
}
