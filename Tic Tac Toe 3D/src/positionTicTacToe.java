
public class positionTicTacToe {
	int x;
	int y;
	int z;
	int state;

	public void printPosition()
	{
		System.out.print("("+x+","+y+","+z+")");
		System.out.println("state: "+state);

	}

	positionTicTacToe(int setX,int setY,int setZ,int setState)
	{
		x = setX;
		y = setY;
		z = setZ;
		state = setState;
	}
	positionTicTacToe(int setX,int setY,int setZ)
	{
		x = setX;
		y = setY;
		z = setZ;
		state = -1;
	}
}
