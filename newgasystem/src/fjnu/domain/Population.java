package fjnu.domain;

/**
 * 该类为种群类，其主要封装了有关种群的操作：初始化、变异操作、选择操作、交叉操作；
 * 
 * @author zhaohongxu
 * 
 */
public class Population {

	int size;// 种群规模
	double crossoverProbility;// 交叉概率
	double mutateProbility;// 交叉概率

	Chromosome[] chromosomes = null;

	GACfgInfo cfgInfo = null;// 对配置文件的操作，用于获取文件的配置参数；

	/**
	 * 构造器：指定初始化GA参数
	 * 
	 * @param size
	 * @param crossoverProbility
	 * @param mutateProbility
	 * @param cfgInfo
	 */
	public Population(int size, double crossoverProbility,
			double mutateProbility, GACfgInfo cfgInfo) {
		super();
		this.size = size;
		this.crossoverProbility = crossoverProbility;
		this.mutateProbility = mutateProbility;
		this.cfgInfo = cfgInfo;
		chromosomes = new Chromosome[size];
	}

	public Population() {
		super();
	}

	public Population initializePopulation() {
		Population newPopulation = new Population();
		
		
		
		
		return newPopulation;

	}

}