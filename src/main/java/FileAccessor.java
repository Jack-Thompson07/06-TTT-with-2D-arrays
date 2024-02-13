import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class FileAccessor{

  private File f;
  private Scanner s;
  private Player[] players;
  
  public FileAccessor(File f){
    this.f = f;
  }
}