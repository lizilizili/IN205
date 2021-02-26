package ensta.ship;


public class AbstractShip {
		private char label;
		private String name;
		private int size;
		private int direction;
		
		public char getLabel(){
		      return label;
		}
		
		public String getName(){
		      return name;
		}
		
		public int getSize(){
		      return size;
		}
		
		public int getDirection(){
		      return direction;
		}
		
		/**
		 * AbstractShip Constructor
		 * @param name String
		 * @param label char
		 * @param size int
		 * @param d char
		 */

		public AbstractShip(String name, char label, int size, char d) {
			this.name = name;
	    	this.label = label;
	    	this.size = size;
	    	this.direction = d;
	    }
		
		/**
		 * Convert its direction to a 2Dvector 
		 * @return vector on its direction 
		 */
		
		public int[] dir2vertor() {
			
			int[] vec= {0,0};
			switch (this.direction) {
			case 'n': 
				vec[0]=-1;
				vec[1]=0;
				return vec;
			
			case 's': 
				vec[0]=1;
				vec[1]=0;
				return vec;
				
			case 'w': 
				vec[0]=0;
				vec[1]=-1;
				return vec;	
				
			case 'e': 
				vec[0]=0;
				vec[1]=1;
				return vec;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + this.direction);
			}
			
		}
}
