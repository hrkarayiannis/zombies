package IALSOMADEAGAMEWITHZOMBIESINIT.Enemies;


public class SmallSlime extends BasicZombie {

	final static String smallslime_path = "Images/smallslime.png";
	
	public SmallSlime(BigSlime parent) {
		super(parent.generateChildX(), parent.generateChildY(), smallslime_path);
		hp = 1;
		points = 1;
		speed = 2;
	}

}
