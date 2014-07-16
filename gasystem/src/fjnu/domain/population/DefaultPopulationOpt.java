package fjnu.domain.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fjnu.domain.chrom.Chromosome;

public class DefaultPopulationOpt implements IPopulationOpt {

	/**
	 * @param fitnesses
	 *            :种群中所有个体的适应度值(缺省按等概率选择，故未用该参数值）
	 * @return：被选中的个体下标
	 */
	@Override
	public List<Integer> choose(List<Double> fitnessses) {
		int popSize = fitnessses.size();
		List<Integer> chooseResults = new ArrayList<Integer>();

		Random random = new Random();

		for (int i = 0; i < popSize; i++) {
			int index = random.nextInt(popSize);
			chooseResults.add(index);
		}

		return chooseResults;
	}

	/**
	 * 将字符编码转化成十进制数字
	 */
	@Override
	public List<Double> getFitness(List<Chromosome> chromosomes) {
		List<Double> fitnesses = new ArrayList<Double>();
		int size = chromosomes.size();
		for (int i = 0; i < size; i++) {

			List<StringBuffer> codeS = chromosomes.get(i).getCodes();
			int length = codeS.size();
			double result = 0;
			for (int j = length; j > 0; j--) {
				int number = Integer.valueOf(codeS.get(j).toString());
				result += number * Math.pow(10, j - 1);
			}
			fitnesses.add(result);
		}
		return fitnesses;
	}
}