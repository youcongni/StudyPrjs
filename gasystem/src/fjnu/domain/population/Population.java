package fjnu.domain.population;

import java.util.List;
import java.util.Random;

import fjnu.domain.chrom.Chromosome;
import fjnu.domain.input.GACfgInfo;
import fjnu.domain.input.GAParameter;

/**
 * 该类为种群类，其主要封装了有关种群的操作：初始化、变异操作、选择操作、交叉操作；
 * 
 * @author zhaohongxu
 * 
 */
public class Population {

	private int popSize;// 种群规模
	private double crossoverProbility;// 交叉概率
	private double mutateProbility;// 交叉概率
	private GAParameter parameter = null;// 对配置文件的操作，用于获取文件的配置参数；
	
	private Random random = null;
	
	private List<Chromosome> chromosomes = null;
	
	private Chromosome bestChrom =null;


	public Chromosome getBestChrom() {
		//比较各个适应值
		bestChrom=chromosomes.get(0).copy();
		
		return bestChrom;
	}

	/**
	 * 构造器：指定初始化GA参数
	 * 
	 * @param GAParameter
	 *            parameter
	 */
	public Population(GAParameter parameter, Random random) {
		this.random = random;
		this.parameter = parameter;
		initializePopulation() ;
	}

	public Population() {
		this.parameter = GACfgInfo.getInstance().getParametersOfGA();
		initializePopulation() ;
	}

	/**
	 * 种群的初始化操作
	 * 
	 * @return 一个新的种群
	 */
	private void initializePopulation() {
		this.popSize = parameter.getPopulationSize();
		this.crossoverProbility = parameter.getCrossoverProbability();
		this.mutateProbility = parameter.getMutateProbabilty();
		
		for (int i = 0; i < popSize; i++) {
			Chromosome chromosome=new Chromosome();
			chromosomes.add(chromosome);
		}
	}

	/**
	 * 种群的变异操作；变异以后返回中间种群，种群进行变异操作时，是根据 配置文件中的变异概率来决定的
	 * 
	 */
	public void mutate(int mutateNum) {
		for (int i = 0; i < popSize; i++) {
			double mutatePro = random.nextDouble();
			if (mutatePro <= mutateProbility) {
				chromosomes.get(i).mutate(mutateNum);
			}
		}
	}
	

}