package lda;

import java.util.List;

/*
 * This class contains all the configurations for the estimation and inference
 * algorithms, and also initialization values. These are used to configure the TopicModeler.
 */

public class Configs {
	
	// Number of topics to extract
	private int nbrTopics;
	
	// Initial alpha
	private List<Double> initAlpha;
	
	// Max number of iterations for variational inference
	private int varIters;
	
	// Convergence threshold for variational inference
	private double varConvergence;
	
	// Max number of iterations for E-M
	private int emIters;
	
	// Convergence threshold for E-M
	private double emConvergence;
	
	// constructor initiliazes stuff to defalut values
	public Configs(){
		for(int i=0; i<100; i++)
			this.initAlpha.add((double) 1);
			this.nbrTopics = 100;
			this.varIters = 20;
			this.varConvergence = 1e-6;
			this.emIters = 100;
			this.emConvergence = 1e-4;
	}
	
	// setters
	public void setVarIters(int iters){
		this.varIters = iters;
	}
	
	public void setVarConvergence(double conv){
		this.varConvergence = conv;
	}
	
	public void setEmIters(int iters){
		this.emIters = iters;
	}

	public void setEmConvergence(double conv){
		this.emConvergence = conv;
	}
	
	public void setNbrTopics(int topics){
		this.nbrTopics = topics;		// has a default value of 100
	}
	
	public void setInitAlpha(List<Double> alpha){
		this.initAlpha = alpha;			// has a default value of 100
	}
	
	// getters
	public int getVarIters(){
		return this.varIters;
	}
	
	public double getVarConvergence(){
		return this.varConvergence;
	}
	
	public int getEmIters(){
		return this.emIters;
	}

	public double getEmConvergence(){
		return this.emConvergence;
	}
	
	public int getNbrTopics(){
		return this.nbrTopics;
	}
	
	public List<Double> getInitAlpha(){
		return this.initAlpha;
	}
}
