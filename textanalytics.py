from __future__ import absolute_import
from __future__ import division, print_function, unicode_literals

from sumy.parsers.html import HtmlParser
from sumy.parsers.plaintext import PlaintextParser
from sumy.nlp.tokenizers import Tokenizer
from sumy.summarizers.lsa import LsaSummarizer as Summarizer
from sumy.nlp.stemmers import Stemmer
from sumy.utils import get_stop_words


LANGUAGE = "english"
SENTENCES_COUNT = 3


if __name__ == "__main__":
    #url = "http://www.zsstritezuct.estranky.cz/clanky/predmety/cteni/jak-naucit-dite-spravne-cist.html"
    #parser = HtmlParser.from_url(url, Tokenizer(LANGUAGE))
    # or for plain text files
    # parser = PlaintextParser.from_file("document.txt", Tokenizer(LANGUAGE))
    #f = open("test.txt", "r")  # opens file with name of "test.txt"
    with open('test.txt', 'r') as myfile:
        data = myfile.readlines()


    data = [x.strip() for x in data]
    # job_titles = [line.decode('utf-8').strip() for line in data]


    #parser =
    #stemmer = Stemmer(LANGUAGE)
    para = "".join(data)
    data1 = PlaintextParser.from_file('test.txt', Tokenizer(LANGUAGE))
    stemmer = Stemmer(LANGUAGE)

    summarizer = Summarizer(stemmer)
    summarizer.stop_words = get_stop_words(LANGUAGE)

    for sentence in summarizer(data1.document, SENTENCES_COUNT):
        print(sentence)
    #print(data1.SIGNIFICANT_WORDS)



    #summarizer = Summarizer(stemmer)
    #summarizer.stop_words = get_stop_words(LANGUAGE)

    #for sentence in summarizer(myList, SENTENCES_COUNT):
        #print(sentence)
