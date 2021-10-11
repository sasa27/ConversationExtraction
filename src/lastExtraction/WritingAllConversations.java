package lastExtraction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import main.SavingResults;
import objetsconversations.Conversation;
import objetsconversations.Event;

public class WritingAllConversations {
	public static void writeLastReturn(FinalConversationSetReturn ConversationSet) {
		for(int i=0;i<ConversationSet.getSize();i++) {
			File directoryFile=new File("RESULTS"+"/Final");
			if ((!directoryFile.exists()) || (!directoryFile.isDirectory())){
				directoryFile.mkdir();
			}
			File dir = new File("RESULTS"+"/Final/Conversation"+i+".txt");
			String writingInFile = "";
			FinalConversationReturn thisConv = ConversationSet.getConvSet().get(i);
			for (Event event : thisConv.getConv()) {
				writingInFile+=event.getligne() + "\n";
			}
			try {
				FileWriter fw = new FileWriter(dir.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(writingInFile);
				bw.close();
				//  threadpool.threadpool.shutdownNow();
			}
			catch(Exception e) {
				System.err.println(e);
			}
		}
	
    }
	
	
	
}
