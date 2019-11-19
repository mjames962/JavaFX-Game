package a2;

public class TokenDoor extends Door {
	
	public int requiredTokens;

	public TokenDoor(Vector2D pos) {
		super(pos);
	}
	
	
	public boolean meetsRequirement(Player ply) {
		return requiredTokens <= ply.getTokens();
	}
	
	
	
	
}
