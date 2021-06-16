package main;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * @author Blot Elliott
 *
 */
public class FullOptions {
	
	public static void setOptions(String[] args, boolean reg) throws Exception {
		System.out.println("pb la");
		final Options options = configParameters(reg);
		
	    final CommandLineParser parser = new DefaultParser();
	    try {
		    final CommandLine line = parser.parse(options, args);
		    	    	
		    Main.log = line.getOptionValue("input");
		    if (reg) {
		    	Main.regex = line.getOptionValue("reg");	 
		    }
		    Main.output = line.getOptionValue("output");
		    
		    boolean idmode = line.hasOption("id");
		    if(idmode) {
		    	Main.mode = "-id";
		    }
		    else {
		    	Main.mode = ""; 
		    }
		   

		    
		    
		    // Timer
		    boolean timerMode = line.hasOption("timer");
		    if(timerMode) {
		    	Main.timerMode = true;
		    }
		    
		    boolean arretpremiermode = line.hasOption("premier");
		    if(arretpremiermode) {
		    	Main.premier = true;
		    }
	    
	    }catch(Exception e) {
	    	System.out.println("Usage : Main -i <input> -r <regex> -o <output>\n"
	    			+ "-i/-input : log file to analyse\n"
	    			+ "-r/-regex : regex file to use on the input file\n"
	    			+ "-o/-output : name of the output directory\n"
	    			+ "Options :\n"
	    			+ "-f\fif you want to stop at the first result\n"
	    			+ "-t\tshow the duration of each step of the program\n"
	    			+ "-id\tuse session identifier to split the log\n"
	    			);
	    	System.exit(1);}
	}
	
	private static Options configParameters(boolean reg) {
		final Options options = new Options();
		final Option logFileOption = Option.builder("i")
				.longOpt("input")
				.desc("log file to analyse")
				.hasArg(true)
				.argName("input")
				.required(true)
				.build();
		if (reg) {
			final Option regexOption = Option.builder("r")
					.longOpt("reg")
					.desc("regex to use")
					.hasArg(true)
					.argName("reg")
					.required(true)
					.build();
			options.addOption(regexOption);
		}
	
	    final Option timerFileOption = Option.builder("t") 
	            .longOpt("timer") 
	            .desc("Timer") 
	            .hasArg(false) 
	            .required(false) 
	            .build();
	    
	    final Option arretpremier = Option.builder("f") 
	            .longOpt("premier") 
	            .desc("premier") 
	            .hasArg(false) 
	            .required(false) 
	            .build();
	    
	    final Option outputOption = Option.builder("o")
				.longOpt("output")
				.desc("output file")
				.hasArg(true)
				.argName("output")
				.required(true)
				.build();
	    
	    final Option modeOption = Option.builder("id")
				.longOpt("sessID")
				.desc("use session id")
				.hasArg(false)
				.argName("sessID")
				.required(false)
				.build();

	
	    
	
	    options.addOption(logFileOption);
	    options.addOption(timerFileOption);
	    options.addOption(outputOption);
	    options.addOption(modeOption);
	    options.addOption(arretpremier);
	
	    return options;
	}
	
	
}
