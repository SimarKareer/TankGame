import java.awt.Rectangle;

public class Bullet extends Rectangle {
	float /* x, y, width, height, */ xMotion, yMotion;
	int damage;
	double speed;

	public Bullet(int a, int b, int c, int d, float e, float f, double g, int h) {
		setRect(a, b, c, d);
		xMotion = e;
		yMotion = f;
		speed = g;
		damage = h;
	}
}
