package agent.planningagent;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import environnement.Action;
import environnement.Etat;
import environnement.MDP;
import environnement.gridworld.ActionGridworld;
/**
 * Cet agent choisit une action aleatoire parmi toutes les autorisees dans chaque etat
 * @author lmatignon
 *
 */
public class AgentRandom extends PlanningValueAgent{
	
	
	public AgentRandom(MDP _m) {
		super(_m);
	}

	@Override
	public Action getAction(Etat e) {
		
		int numAction;
		
		List<Action> listeAction = getPolitique(e);
		Random rand = new Random();
		try{
			numAction = rand.nextInt(listeAction.size());				
		}catch(IllegalArgumentException except)
		{
			return null;
		}
		return listeAction.get(numAction);
		
	}

	
	
	@Override
	public double getValeur(Etat _e) {
		return 0.0;
	}

	

	@Override
	public List<Action> getPolitique(Etat _e) {
		//*** VOTRE CODE
		
		List<Action> actionsPossibles = mdp.getActionsPossibles(_e);
		return actionsPossibles;
	}

	@Override
	public void updateV() {
		System.out.println("l'agent random ne planifie pas");
	}

	public void setGamma(double parseDouble) {
		// TODO Auto-generated method stub
		
	}




}
