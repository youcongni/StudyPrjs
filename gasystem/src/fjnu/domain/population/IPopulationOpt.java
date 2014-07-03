package fjnu.domain.population;

import java.util.List;

public interface IPopulationOpt {
	/**
	 * 
	 * @param fitnesses:种群中所有个体的适应度值
	 * @return：被选中的个体下标
	 */
	List<Integer> choose(List<Double> fitnesses);
}
