public class WinCheck{

  private int[][] state;
  
  public WinCheck(int[][] state){
    this.state = state;
  }
  
  public boolean vertical(int num) {
    for (int i = 0; i < state[0].length; i++) {
      boolean yes = true;
      for (int j = 0; j < state.length; j++) {
        if (state[j][i] != num)
          yes = false;
      }
      if (yes)
        return true;
    }
    return false;
  }

  public boolean horizontal(int num) {
    for (int i = 0; i < state.length; i++) {
      boolean yes = true;
      for (int j = 0; j < state[i].length; j++) {
        if (state[i][j] != num)
          yes = false;
      }
      if (yes)
        return true;
    }
    return false;
  }

  public boolean diagonal(int num) {
    boolean yes = true;
    for (int i = 0; i < state.length; i++) {
      if (state[i][state[i].length - 1 - i] != num) {
        yes = false;
      }
    }
    if (yes)
      return true;

    yes = true;
    for (int i = 0; i < 3; i++) {
      if (state[i][i] != num) {
        yes = false;
      }
    }
    if (yes)
      return true;

    return false;
  }
  
}