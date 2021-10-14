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
	public static synchronized void writeLastReturn(FinalConversationSetReturn ConversationSet,int numberConversationSet) {
		File directoryFile=new File("RESULTS"+"/Final");
		if ((!directoryFile.exists()) || (!directoryFile.isDirectory())){
			directoryFile.mkdir();
		}
		File dir = new File("RESULTS"+"/Final/ConversationSet"+numberConversationSet+".txt");
		String writingInFile = "";
		for(int i=0;i<ConversationSet.getSize();i++) {
			
			FinalConversationReturn thisConv = ConversationSet.getConvSet().get(i);
			int j=0;
			for (Event event : thisConv.getConv()) {
				writingInFile+="e"+j+"(";
				for (String param : event.getParamsWithoutFromTo()) {
					writingInFile+=param+", ";
				}
				writingInFile = writingInFile.substring(0, writingInFile.length() - 2);
				writingInFile+="); ";
				j++;
			}
			writingInFile = writingInFile+"\n";
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
