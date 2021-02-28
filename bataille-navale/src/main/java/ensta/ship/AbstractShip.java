package ensta.ship;


public class AbstractShip {
	
		/* **
		 * Attributes
		 */
		private char label;
		private String name;
		private int size;
		private Directions direction;
		private int strikeCount=0;
		
		/* **
		 * Encapsulation
		 */
		public char getLabel(){
		      return label;
		}
		
		public String getName(){
		      return name;
		}
		
		public int getLength(){
		      return size;
		}
		
		public int getSize(){
		      return size;
		}
		
		public Directions getDirection(){
		      return direction;
		}
		
		public void setDirection(Directions direction){
		      this.direction=direction;
		}
		/**
		 * AbstractShip Constructor
		 * @param name String
		 * @param label char
		 * @param size int
		 * @param d Directions
		 */

		public AbstractShip(String name, char label, int size, Directions d) {
			this.name = name;
	    	this.label = label;
	    	this.size = size;
	    	this.direction = d;
	    }
		
		/* **
	     * Methods
	     */
		
		/**
		 * Convert its direction to a 2Dvector 
		 * @return vector on its direction 
		 */
		
		public int[] dir2vertor() {
			
			int[] vec= {0,0};
			switch (this.direction) {
			case NORTH: 
				vec[0]=-1;
				vec[1]=0;
				return vec;
			
			case SOUTH: 
				vec[0]=1;
				vec[1]=0;
				return vec;
				
			case WEST: 
				vec[0]=0;
				vec[1]=-1;
				return vec;	
				
			case EAST: 
				vec[0]=0;
				vec[1]=1;
				return vec;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + this.direction);
			}
			
		}
		
		/**
		 * Add strike number  
		 */
		public void addSrike() {this.strikeCount++;}
		
		/**
		 *  Get if the ship is sunk
		 *  @return true if the ship is sunk
		 */
		public boolean isSunk() {return (this.size == this.strikeCount);}
		
}
