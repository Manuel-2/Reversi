package reversi;

import java.util.ArrayList;

class MovementTrace {
	public final Position2D start;
	public Position2D finish;
	public final ArrayList<Disc> discTrace;
	
	public MovementTrace(Position2D start) {
		this.start = start;
		this.finish = null;
		discTrace = new ArrayList<Disc>();
	}
	
	public void setFinish(Position2D finish) {
		if(finish.equals(this.start)) {
			throw new IllegalArgumentException("you can't set a trace to start and finish in the same disc");
		}
		this.finish  = finish;
	}

}
