package split;

import java.io.BufferedReader;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

import objetsconversations.Event;
/**
 * @author Blot Elliott
 *
 */

public class Trace {

	public ArrayList<Event> seq;	
	public int compteur;
	public Trace() {
		seq = new ArrayList<Event>();
	}
	
	public Trace(ArrayList<Event> t) {
		seq = t;
	}
	
	/**
	 * Build the trace from a file and regular expression matching the events. 
	 * @param file
	 * @param regex
	 */
	public Trace(File file, Regex regex) {
		compteur=0;
		seq = new ArrayList<Event>();
		try {
			BufferedReader br = new BufferedReader (new FileReader(file));
			String line = br.readLine();
			outside:
			while (line!=null) {
				for(int j = 0; j < regex.getPatterns().size(); j++) {
					Matcher m = regex.getPatterns().get(j).matcher(line);
					if(m.find()) {
						seq.add(new Event(line, m));
						line = br.readLine();
						continue outside;
					}
				}
				System.err.println("no regex match line: " + line);
				System.exit(3);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("File "+ file + " not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException");
			e.printStackTrace();
		}
	}
	
	public Trace(File file) {
		System.out.println(file.getAbsolutePath());
		seq = new ArrayList<Event>();
		try {
			
			BufferedReader br = new BufferedReader (new FileReader(file));
			String line = br.readLine();
			while (line!=null) {
				System.out.println("au log");
				seq.add(new Event(line));
				line = br.readLine();
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("File "+ file + " not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException");
			e.printStackTrace();
		}
		
	}
			
	
	
	/**
	 * Return every request with host=from. 
	 */
	public ArrayList<Event> getEvery(String from, String to){
		ArrayList<Event> res = new ArrayList<Event>();
		for (Event e : getSeq()) {
			if (e.isReq() && e.getFrom().equals(from)/* && e.getTo().equals(to)*/) {
				res.add(e);
			}
		}
		return res;
	}
	
	/**
	 * Return  the sub-trace starting at index begin and ending at end.
	 */
	public Trace subTrace(int begin, int end) {
		return new Trace(new ArrayList<Event>(seq.subList(begin, end)));
	}
	
	public String toString() {
		return Arrays.deepToString(seq.toArray());
	}
	
	public boolean containsAll(ArrayList<Event> events) {
		for (Event e: events) {
			if (!seq.contains(e)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * For debugging purpose, print the events.
	 */
	public String debug() {
		String res = "[";
		for (Event e : seq) {
			res = res + e.debug() + ";" ;
		}
		return res;
	}
	
	/**
	 * Check if every request has a unique response in the traces
	 */
	/*public boolean complete(String comp) {
		int nbReq=0;
		int nbResp=0;
		for (int i = 0; i < seq.size(); i++) {
			Event e = getEvent(i);
			if (e.getFrom().equals(comp) || e.getTo().equals(comp)) {
				if (e.isReq()){
					nbReq++;
				}
				else {
					nbResp++;
				}
			}
		}
		return nbReq == nbResp;
	}*/
	
	/**
	 * Write the trace in file. 
	 */
	public void writeTrace(File file) {
		try {
			BufferedWriter bw = new BufferedWriter (new FileWriter(file));
			for (Event e: seq) {
				bw.write(e + "\n");
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return the sequence of event of the trace.
	 */
	public ArrayList<Event> getSeq() {
		//ArrayList<Event> res = new ArrayList<Event>(seq);
		return seq;
	}
	
	/**
	 * Add the event e in the end of the trace.
	 */
	public void addEvent(Event e) {
		seq.add(e);
	}
	
	/** 
	 * Check if the trace is empty.
	 */
	public boolean isEmpty() {
		return seq.isEmpty();
	}
	
	
	
	/**
	 * Return the event at index.
	 */
	public Event getEvent(int index) {
		return seq.get(index);
	}
	
	/**
	 * Return the index of the event e.
	 */
	public int indexOf(Event e) {
		return seq.indexOf(e);
	}
	
	/**
	 * Return the size of the trace.
	 */
	public int getSize() {
		return seq.size();
	}
	
	/**
	 * Return the last request send or received by comp.
	 */
	public Event lastReq(String comp) {
		for (int i = seq.size()-1; i >= 0; i--) {
			Event e = seq.get(i);
			if (e.isReq() && (e.getParamsWithoutFromTo().contains(Event.from + "=" + comp) ||
					e.getParamsWithoutFromTo().contains(Event.to + "=" + comp))){
				return e;
			}
		}
		//System.err.println("no lastReq in the trace case 5");
		//System.exit(3);
		return null;
	}
	
}
