public class Ship {
  private String name;
  private int size;     // Number of squares the ship takes up
  private int[] health;
  private int currentRow;
  private int currentColumn;
  private char orientation; // The ship's orientation (horizontal/vertical), 'h' or 'v'
  private boolean isPlaced;

  // Default constructor
  public Ship() {
    size = 0;
    orientation = 'v';
    isPlaced = false;
  }
  
  public Ship(int shipSize) {
    size = shipSize;
    
    health = new int[shipSize];
    for (int i = 0; i < shipSize; i++)
    {
      health[i] = 0;
    }
 
    isPlaced = false;
  }
  
  public Ship(String shipName, int shipSize) {
    name = shipName;
    size = shipSize;
    
    health = new int[shipSize];
    for (int i = 0; i < shipSize; i++)
    {
      health[i] = 0;
    }
  }
  
  public Ship(int shipSize, int shipRow, int shipColumn, char shipOrientation) {
    size = shipSize;
    
    health = new int[shipSize];
    for (int i = 0; i < shipSize; i++)
    {
      health[i] = 0;
    }
    
    currentRow = shipRow;
    currentColumn = shipColumn;
    orientation = shipOrientation;
  }
  
  public void setName(String shipName) {
    name = shipName;
  }
  
  public void setSize(int sz) { 
    size = sz;
    
    health = new int[sz];
    for (int i = 0; i < sz; i++)
    {
      health[i] = 0;
    }
  }
  
  public void setCurrentRow(int row) {
    currentRow = row;
  }
  
  public void setCurrentColumn(int column) {
    currentColumn = column;
  }
  
  public void setOrientation(char or) {
    orientation = or;
  }
  
  public void setIsPlaced(boolean placed) {
    isPlaced = placed;
  }
  
  public String getName() {
    return name;
  }
  
  public int getSize() {
    return size;
  }
  
  public int getCurrentRow() {
    return currentRow;
  }
  
  public int getCurrentColumn() {
    return currentColumn;
  }
  
  public int getOrientation() {
    return orientation;
  }
  
  public boolean getIsPlaced() {
    return isPlaced;
  }
  
  public void hitShip(int location)
  {
    health[location] = 1;
  }
  
  public boolean isAlive()
  {
    boolean isAlive = false;
    
    for (int i = 0; i < size; i++)
    {
      if (health[i] == 0)
      {
        isAlive = true;
        break;
      }
    }
    
    return isAlive;
  }
  
  public boolean isSameName(String shipName)
  {
    if (shipName == name)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
}
