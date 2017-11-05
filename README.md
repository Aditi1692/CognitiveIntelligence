# CognitiveIntelligence

Convert speech to text and back again by using Microsoft Bing Speech Recognition API. The project is mainly designed to gather useful information from any speech. The application converts the speech and translates it to text format. Then, there are different text analytics are performed. Like, text is summarized to help students getting useful information from a recorded lecture, sentiment analysis to detect the sentiment of the speaker, topic modelling to distill useful inforation.

# Instructions to run:

java -jar cognitiveIntellience.jar key_name filename_path(.wav)

This generates a file that is given as an input to the textanalytics.py file:

python textanalytics.py output_filename
