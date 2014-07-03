package fjnu.domain.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultPopulationOpt implements IPopulationOpt {

	/**
	 * @param fitnesses:种群中所有个体的适应度值(缺省按等概率选择，故未用该参数值）
	 * @return：被选中的个体下标
	 */
	@Override
	public 	List<Integer> choose(List<Double> fitnessses ){
			int popSize=fitnessses.size();
			List<Integer> chooseResults=new ArrayList<Integer>();
			
			Random  random=new Random();
			
			for(int i=0;i<popSize;i++){
				int index=random.nextInt(popSize);
				chooseResults.add(index);
			}
			
			return chooseResults;
	}

}
