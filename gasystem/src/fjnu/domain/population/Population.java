package fjnu.domain.population;

import java.util.List;
import java.util.Random;

import fjnu.domain.chrom.Chromosome;
import fjnu.domain.input.GAParameter;

/**
 * 该类为种群类，其主要封装了有关种群的操作：初始化、变异操作、选择操作、交叉操作；
 * 
 * @author zhaohongxu
 * 
 */
public class Population {

	private int size;// 种群规模
	double crossoverProbility;// 交叉概率
	double mutateProbility;// 交叉概率

	Chromosome[] chromosomes = null;

	GAParameter parameter = null;// 对配置文件的操作，用于获取文件的配置参数；
	Random random = null;

	/**
	 * 构造器：指定初始化GA参数
	 * 
	 * @param GAParameter
	 *            parameter
	 */
	public Population(GAParameter parameter, Random random) {
		super();
		this.random = random;
		this.parameter = parameter;
		this.size = parameter.getPopulationSize();
		this.crossoverProbility = parameter.getCrossoverProbability();
		this.mutateProbility = parameter.getMutateProbabilty();
		chromosomes = new Chromosome[size];
		for (int i = 0; i < size; i++) {
			chromosomes[i] = new Chromosome(random);
		}
	}

	public Population() {
		super();
	}

	/**
	 * 种群的初始化操作
	 * 
	 * @return 一个新的种群
	 */
	public Population initializePopulation() {
		Population newPopulation = new Population();
		for (int i = 0; i < size; i++) {// 根据种群规模进行初始化个体
			chromosomes[i] = new Chromosome(random, parameter, null);
		}
		newPopulation.setChromosomes(chromosomes);
		newPopulation.setSize(size);
		newPopulation.setParameter(parameter);
		return newPopulation;
	}

	/**
	 * 种群的变异操作；变异以后返回中间种群，种群进行变异操作时，是根据 配置文件中的变异概率来决定的
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void mutate(int mutateNum,List<StringBuffer> codes) {
		for (int i = 0; i < size; i++) {
			double mutatePro = random.nextDouble();
			if (mutatePro <= mutateProbility) {
//				List<StringBuffer> codes1 = chromosomes[i].getEncodes();
				chromosomes[i].mutate(mutateNum, codes, parameter);
			}
		}

	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public GAParameter getParameter() {
		return parameter;
	}

	public void setParameter(GAParameter parameter) {
		this.parameter = parameter;
	}

}