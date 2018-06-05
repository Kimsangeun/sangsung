package model;

import java.util.Date;

public class ScheduleVO {
	Date resdate;
	int part1, part2, part3, gid, part;

	public ScheduleVO() {
	}

	public ScheduleVO(int gid, Date resdate, int part) {
		this.resdate = resdate;
		this.gid = gid;
		setPart(part);
	}

	public Date getResdate() {
		return resdate;
	}

	public void setResdate(Date resdate) {
		this.resdate = resdate;
	}

	public int getPart1() {
		return part1;
	}

	public void setPart1(int part1) {
		this.part1 = part1;
	}

	public int getPart2() {
		return part2;
	}

	public void setPart2(int part2) {
		this.part2 = part2;
	}

	public int getPart3() {
		return part3;
	}

	public void setPart3(int part3) {
		this.part3 = part3;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getPart() {
		return part;
	}
	public void setPart(int part) {
		switch (part) {
		case 1:
			this.setPart1(1);
			this.part=1;
			break;
		case 2:
			this.setPart2(1);
			this.part=2;
			break;
		case 3:
			this.setPart3(1);
			this.part=3;
			break;
		}
	}

}
