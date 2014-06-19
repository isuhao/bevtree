package test;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Monster {

	private String name = "怪";
	// 只在内存中，存下面技能执行时间,用于ai
	private Map<Integer, Long> cds = new HashMap<Integer, Long>();

	private String target;

	private Point point;

	private int skill = 1000;// 拥有技能

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	/**
	 * 技能ID
	 * 
	 * @param id
	 * @return 此技能可以执行的时间
	 */
	public Long getCds(Integer id) {
		Long cd = cds.get(id);
		return cd == null ? 0 : cd;
	}

	/**
	 * 设置技能下次可以执行的时间
	 * 
	 * @param id
	 * @param time
	 */
	public void setCds(Integer id, Long time) {
		cds.put(id, time);
	}

	public boolean HasSound() {
		Random rd = new Random();
		Boolean hasSound = rd.nextBoolean();
		return hasSound;
	}
}
