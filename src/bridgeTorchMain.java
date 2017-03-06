import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class bridgeTorchMain {
	public static void main(String args[])throws IOException{
		Console c = System.console();
		if(c == null){
			System.err.println("No Console");
			System.exit(1);
		}
		
		run.main(args);
		
	}

}
