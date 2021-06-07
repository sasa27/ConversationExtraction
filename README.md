# ConversationExtraction
Conversation (a.k.a. session) extraction from event log

Parallel algorithm for recovering conversations (sequences of correlated events interchanged among different components that achieve a certain goal) from event logs.
## Contents
* src/ contains the source code
* log/trace/ex[1,6]id/ contains 6 event log examples along with regex files
* logentrainementlog/ contains other types of tests like one without regex or the divide and conquer tests
* RESULTS/ where the results are gonne be

## Usage
You can compile the program by running the script **build.sh**.

Once compiled, you can run the method with the script **Extracteur.sh**:
  ```
  Extracteur.sh -i <input> -r <regex> -o <output>
  
  or for test :
  
  Extracteur.sh -i <input> -o <output> 
  but in this case your file must have line with : 
  name(param,param...)
  ```
  **\<input\>** : your input log.

  **\<regex\>** : the file that contains the regex that cover the actions in the log.
  
  Models are generated in the **\<output\>** folder.

Other options:
 
-f if you only want the first result

It is an eclipse project, you can import it directly with eclipse if you want to modify the code.

### Prerequisite

Event logs with events composed of parameter assignments and ordered with timestamps. 
The events can be correlated by means of varied patterns (ex: key based correlation)
If the events are not of the form event({set of parameter assignments}), a file of regular expressions to form the raw event log.

If using a regex :


Each action of the log has to contains a timestamp, following the date format given in the regex file with the line:

**-d \<dateFormat\>** 

The different groups of the regex are the following:

*date* : the timestamp of the event

*label* : the label of the event

*parami* : a parameter of the event, with *i* incrementing from 1


The actions has to contain as parameter an identifier of the component that has sent the message (the source, denoted "Host=\<id\>" in the log), and an identifier of the component that receive the message (the destination, denoted "Dest=\<id\>" in the log). 

The actions log is composed of *Requests* and *Responses*. Each *Request* has at least one associated *Response* next in the log. (Currently, *Responses* are identified in the log by containing the word "response" in the label or the parameters) 

Internal actions are events where the source and the destination are equals.

If not using a regex :

events need to be like that:

Label(param1,param2,param3,...)

## Overview
Highly parallel algorithm to retrieve conversations from event logs, without having any knowledge about the used correlation mechanisms. 
To make the event log exploration effective and efficient, our algorithm is based upon a formalisation of the notion of correlation patterns and is guided by the quality of the generated conversations. As there is no consensus about what a relevant conversation should be, the conversation quality can be adapted to meet user needs and viewpoints. 

Our algorithm is based upon a strategy mixing the divide and conquer paradigm with the depth-search approach  and an heuristic based upon the evaluation of invariants and conversation quality attributes. Both the strategy and heuristic allow to quicker find a first solution. Our algorithm can also return the conversation sets that meet quality attributes, and sort them from the best to the lower quality. 

See paper at https://perso.limos.fr/~sesalva/

## TODO
 - [ ] revisit the wrtting of invariants and metrics 
 - [ ] optimise the computation of invariant sat.
 - [ ] propose other output formats  
