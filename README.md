# ConversationExtraction
Conversation (a.k.a. session) extraction from event log

Parallel alogithm for recovering conversations (sequences of correlated events interchanged among different components that achieve a certain goal) from event logs.

## Contents
* src/ contains the soufce code :)
* examples/ contains 6 event log examples along with regex files.

## Prerequisite
Event logs with events composed of parameter assignments and ordered with timestamps. 
The events can be correlated by means of varied patterns (ex: key based correlation)
If the events are not of the form event({set of parameter assignments}), a file of regumlar expressions to formet the raw event log.

The different groups of the regex are the following:

*date* : the timestamp of the event

*label* : the label of the event

*parami* : a parameter of the event, with *i* incrementing from 1

## Usage
  ```
  XXX.sh -i <input> -r <regex> -o <output>
  ```
  **\<input\>** : your event log.

  **\<regex\>** : the file that contains the regex.
  
Other options: 

-t   shows the duration of each step of the program.


## Overview
Highly parallel algorithm to retrieve conversations from event logs, without having any knowledge about the used correlation mechanisms. 
To make the event log exploration effective and efficient, our algorithm is based upon a formalisation of the notion of correlation patterns and is guided by the quality of the generated conversations. As there is no consensus about what a relevant conversation should be, the conversation quality can be adapted to meet user needs and viewpoints. 

Our algorithm is based upon a strategy mixing the divide and conquer paradigm with the depth-search approach  and an heuristic based upon the evaluation of invariants and conversation quality attributes. Both the strategy and heuristic allow to quicker find a first solution. Our algorithm can also return the conversation sets that meet quality attributes, and sort them from the best to the lower quality. 

See paper at https://perso.limos.fr/~sesalva/

## TODO
 - [ ] revisit the wrtting of invariants and metrics 
 - [ ] optimise the computation of invariant sat.
 - [ ] propose other output formats  
