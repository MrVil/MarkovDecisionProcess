package agent.planningagent;

import environnement.Action;
import environnement.Etat;
import environnement.IllegalActionException;
import environnement.MDP;

import java.util.*;


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
	protected Map<Etat,Double> carteValeur = new HashMap<>();

	public static final double POINTS_BUT = 1.;
	public static final double POINTS_ABSORBANTS = -1.;
	public static final double POINTS_INITIAUX = 0.;
	
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
			carteValeur.put(e,POINTS_INITIAUX);
		}
	}
	
	
	public ValueIterationAgent(MDP mdp) {
		this(0.9,mdp);
	}
	
	/**
	 * 
	 * Mise a jour de V: effectue UNE iteration de value iteration 
	 */
	@Override
	public void updateV() {
		//delta est utilise pour detecter la convergence de l'algorithme
		//lorsque l'on planifie jusqu'a convergence, on arrete les iterations lorsque
		//delta < epsilon

        Map<Etat, Double> carteValeurTemp = new HashMap<>(carteValeur);
		this.delta = 0.0;
		//*** VOTRE CODE
		for (Etat e : mdp.getEtatsAccessibles()) {
            double value = - Double.MAX_VALUE;
			Map<Etat, Double> transition = null;
			for (Action a : mdp.getActionsPossibles(e)) {
                try {
					transition = mdp.getEtatTransitionProba(e, a);
                } catch (Exception e1) {e1.printStackTrace();}
				Set<Etat> k = transition.keySet();
                double sum = 0;
				for (Etat next : k) {
                    sum += transition.get(next) * (mdp.getRecompense(e, a, next) + this.gamma * carteValeurTemp.get(next));
                    System.out.println("----> Somme :"+sum);
                }
                if (value < sum) {
                    value = sum;
                }
			}
            if(!(mdp.estAbsorbant(e)))
                carteValeur.put(e,value);
            System.out.println("CARTE VALEUR "+carteValeur.get(e));
		}
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
		
		if(carteValeur.containsKey(_e))
		    return carteValeur.get(_e);
		return 0;
	}
	/**
	 * renvoie la (les) action(s) de plus forte(s) valeur(s) dans l'etat e 
	 * (plusieurs actions sont renvoyes si valeurs identiques, liste vide si aucune action n'est possible)
	 */
	@Override
	public List<Action> getPolitique(Etat _e) {
		List<Action> l = new ArrayList<Action>();
		double valMax = -Double.MAX_VALUE;
		List<Action> meilleuresActions = mdp.getActionsPossibles(_e);
		System.out.println("->Etat : " + _e.toString());
		for (Action action : meilleuresActions) {
			try {
				System.out.println("--> Action : " + action.toString() );
				Map<Etat, Double> transition = mdp.getEtatTransitionProba(_e,action);
				Set<Etat> k = transition.keySet();
				for (Etat etat : k) {
					double val = carteValeur.get(etat);
					double probaXVal = transition.get(etat)*val;
					System.out.println("---->Valeur de la case " + etat + " : " + val);
					if(mdp.estAbsorbant(etat)){

					}
					if(mdp.getRecompense(_e,action,etat) == mdp.getRecompenseMax()){
						l = new ArrayList<>();
						l.add(action);
						valMax = Double.MAX_VALUE;
						break;
					}
					if(probaXVal>valMax){
						valMax = probaXVal;
						l = new ArrayList<>();
						l.add(action);
					}else if(probaXVal == valMax){
						l.add(action);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
