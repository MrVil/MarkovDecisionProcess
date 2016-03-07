package agent.planningagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import util.HashMapUtil;
import environnement.Action;
import environnement.Etat;
import environnement.IllegalActionException;
import environnement.MDP;
import environnement.gridworld.ActionGridworld;


/**
 * Cet agent met a jour sa fonction de valeur avec value iteration 
 * et choisit ses actions selon la politique calculee.
 * @author laetitiamatignon
 *
 */
public class ValueIterationAgent extends PlanningValueAgent{
	/**
	 * discount facteur
	 */
	protected double gamma;
	protected Map<Etat,Double> carteValeur = new HashMap<Etat,Double>();
	
	//*** VOTRE CODE


	
	/**
	 * 
	 * @param gamma
	 * @param mdp
	 */
	public ValueIterationAgent(double gamma,MDP mdp) {
		super(mdp);
		this.gamma = gamma;
		for(Etat e : mdp.getEtatsAccessibles()){
			carteValeur.put(e,0.);
		}
		//*** VOTRE CODE
	
	
	}
	
	
	public ValueIterationAgent(MDP mdp) {
		this(0.9,mdp);
	}
	
	/**
	 * 
	 * Mise a jour de V: effectue UNE iteration de value iteration 
	 */
	@Override
	public void updateV(){
		//delta est utilise pour detecter la convergence de l'algorithme
		//lorsque l'on planifie jusqu'a convergence, on arrete les iterations lorsque
		//delta < epsilon 
		this.delta=0.0;
		//*** VOTRE CODE
		
	
		
		
		// mise a jour vmax et vmin pour affichage du gradient de couleur:
		//vmax est la valeur de max pour tout s de V
		//vmin est la valeur de min pour tout s de V
		// ...
		
		//******************* a laisser a la fin de la methode
		this.notifyObs();
	}
	
	
	/**
	 * renvoi l'action executee par l'agent dans l'etat e 
	 */
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
		
		
		return carteValeur.get(_e);
		
	}
	/**
	 * renvoie la (les) action(s) de plus forte(s) valeur(s) dans l'etat e 
	 * (plusieurs actions sont renvoyes si valeurs identiques, liste vide si aucune action n'est possible)
	 */
	@Override
	public List<Action> getPolitique(Etat _e) {
		List<Action> l = new ArrayList<Action>();
		double valMax = Double.MIN_VALUE;
		List<Action> meilleuresActions = new ArrayList<Action>();
		
		for(Action a : l){
			try {
				Map<Etat,Double> carteProba = mdp.getEtatTransitionProba(_e, a);
			} catch (IllegalActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//for(Etat etat : carteProba){
		}
		
		
		return l;
		
	}
	
	@Override
	public void reset() {
		super.reset();
		//*** VOTRE CODE
		
		
		
		
		
		/*-----------------*/
		this.notifyObs();

	}


	public void setGamma(double arg0) {
		this.gamma = arg0;
	}

	
}
