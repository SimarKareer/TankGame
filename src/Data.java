import java.util.ArrayList;

public class Data {
	ArrayList<Tank> tanks;
	ArrayList<Bullet> bullets;

	public Data(ArrayList<Tank> a, ArrayList<Bullet> b) {
		tanks = a;
		bullets = b;
	}

	public ArrayList<Tank> getTanks() {
		return tanks;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
