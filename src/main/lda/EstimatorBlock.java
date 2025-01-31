package main.lda;

import java.util.List;

import main.lda.Configs;
import main.lda.Corpus;
import main.lda.Model;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.StatUtils;

/*
 * This module estimates the topic model, i.e. maximises the lower bound on
 * log likelihood using MLE. This will use variational EM algorithm to 
 * estimate \alpha and \beta,  taking the corpus, \phi and \gamma as input.
 */

public class EstimatorBlock {
	
	// do parameter estimation of \alpha and \beta
	public void estimate(Corpus corpus, Model model, Configs conf){
		
		// Extract the relevant info
		int nbrTopics = model.getNbrTopics();
		int vocabSize = model.getVocabSize();
		int nbrDocs = corpus.getNbrDocs();
		int nbrWords;
		List<Document> documents = corpus.getDocs();
		Document doc;
		RealVector alpha = model.getAlpha();
		RealMatrix phi;
		List<RealMatrix> phiAll = model.getPhi(); 
		List<Integer> docWords;
		Utilities utils = new Utilities();
		
		// estimate beta
		//RealMatrix beta = new Array2DRowRealMatrix(nbrTopics, vocabSize);
		//Using the previous beta from the model
		RealMatrix beta = model.getBeta();
		
		// For each document
		for(int docId = 0; docId < nbrDocs; docId++){
			doc = documents.get(docId);
			docWords = doc.getWordIds();
			nbrWords = doc.getDocSize();
			phi = phiAll.get(docId);
			
			// For each word in the document
			for(int n = 0; n < nbrWords; n++){
				int wordId = docWords.get(n);
				
				// Column of beta corresponding to the current word found in the document
				RealVector betaWordCol = beta.getColumnVector(wordId);
				RealVector phiWordCol = phi.getColumnVector(n);
				
				beta.setColumnVector(wordId, betaWordCol.add(phiWordCol));
			}
		}
		
		// For each topic
		for(int topicId = 0; topicId < nbrTopics; topicId++)
		// Normalize the beta matrix along rows
			beta.setRow(topicId, utils.normalize(beta.getRow(topicId)));


		// Estimate alpha by Newton-Raphson iterations
		
		System.out.println("Entering NR iterations");
		alpha = utils.performNR(conf, alpha, model.getGamma());
		
		// Update the model
		model.setBeta(beta);
		model.setAlpha(alpha);
	}
}
