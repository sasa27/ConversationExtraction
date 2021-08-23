# ConversationExtraction
Conversation (a.k.a. session) extraction from event log

Parallel algorithm for recovering conversations (sequences of correlated events interchanged among different components that achieve a certain goal) from event logs.
## Contents
* src/ contains the source code
* log/trace/ex[1,6]id/ contains 6 event log examples along with regex files
* logentrainementlog/ contains other types of tests like one without regex or the divide and conquer tests
* RESULTS/ where the results are gonne be

## Usage
You can run all the programm in once with the cutting, processing and finding the keys that works for all via buildcutFile.sh to build then cutFile.sh :

  cutFile.sh <insert the path to the file> <number of cut you want> <How much lines in each cut> -r <regex> -o <output>

the results will be in the repository RESULTS with the name that you choosed with -o + a number saying which file it choosed and at the end
there will be a fil named allpossiblekeys which is the file containing the set of keys that can be used for extracting from the file.


