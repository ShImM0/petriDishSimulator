package simulator.model;

import java.util.List;

public interface DishInfo {
	public int getWidth();

	public int getHeight();

	public List<OrganismInfo> getOrganisms();

	public RuleWeights getRuleWeights();

	public void setRuleWeights(RuleWeights weights);

	public boolean getDiscriminationEnabled();

	public void setDiscriminationEnabled(boolean enabled);

	public boolean getWrapEnabled();

	public void setWrapEnabled(boolean enabled);
}
