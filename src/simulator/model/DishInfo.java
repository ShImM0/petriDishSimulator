package simulator.model;

public interface DishInfo {
	public int getWidth();

	public int getHeight();
	
	public RuleWeights getRuleWeights();
	
	public void setRuleWeights(RuleWeights weights);
}
