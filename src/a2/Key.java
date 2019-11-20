package a2;

import a2.Player.Item;

public class Key extends Collectible {

	public Item item;
	
	
	public Key(Vector2D pos,Item key) {
		super(pos);
		this.item = key;
	}
	
	public Item getItem() {
		return item;
	}
}
