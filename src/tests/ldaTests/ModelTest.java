package tests.ldaTests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import main.lda.Configs;
import main.lda.Corpus;
import main.lda.Model;
import main.lda.Vocabulary;

public class ModelTest {
	
	/************************************************************/
	public static void main(String[] args){
		// Path to the file
		String corpusPath = "data/nostemming/summaryfeatures.txt";
		String vocabPath = "data/nostemming/clean_vocabulary.txt";
		
		Corpus movieSummaries;
		Vocabulary movieVocab;
		
		//Creating the file to read the documents from
		File documentFile = new File(corpusPath);
		File vocabFile = new File(vocabPath);
		
		movieSummaries = new Corpus(documentFile);
		movieVocab = new Vocabulary(vocabFile);
		
		// Create the configs
		Configs conf = new Configs();
		conf.setNbrTopics(20);
		
		// Initialize the model
		Model model = new Model();
		model.initModel(movieSummaries, conf, movieVocab);
		System.out.println("Created the model!");
		
		System.out.println("Nbr of topics: " + model.getNbrTopics());
		System.out.println("Vocab size: " + model.getVocabSize());
		System.out.println("Nbr of reviews: " + movieSummaries.getNbrDocs());
		
		// Reading from the file
		model.readModelFromFile("data/nostemming/modelDumpMethod.txt");
		
		double[] estimates = model.getTopicEstimate(973);
		System.out.println(estimates);
		
	}
	/***************************************************************/

}
