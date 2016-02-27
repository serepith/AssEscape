package AssEscape;

public class FinishLine extends ImpassableZone{
	//another box. or more accurately, a box inside a box
	
	int entrancex;
	int entrancey;
	int entrancew;
	int entranceh;
	
	public FinishLine(int xx, int yy, int ww, int hh, int ex, int ey, int ew, int eh){
		
		super(xx, yy, ww, hh);
		x = xx;
		y = yy;
		width = ww;
		height = hh;
		entrancex = ex;
		entrancey = ey;
		entrancew = ew;
		entranceh = eh;
	}
	
	
	
}
