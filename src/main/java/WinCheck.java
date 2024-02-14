public class WinCheck {

  private int[][] state;

  ////////////////////////////////
  // Methods
  ////////////////////////////////
  public int getWin(int[][] state) {
    this.state = state;

    if (tie())
      return 3;

    if (vertical(1) || horizontal(1) || diagonal(1))
      return 1;

    if (vertical(2) || horizontal(2) || diagonal(2))
      return 2;

    return 0;
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
    for (int i = 0; i < state.length; i++) {
      if (state[i][i] != num) {
        yes = false;
      }
    }
    if (yes)
      return true;

    return false;
  }

  public boolean tie() {
    int count = 0;

    for (int[] i : this.state) {
      for (int j : i) {
        if (j != 0)
          count++;
      }
    }

    if (count == Math.pow(this.state.length, 2))
      return true;
    return false;
  }

}