package fjnu.domain.population;

import java.util.ArrayList;
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

	private int popSize;// 种群规模
	private double crossoverProbility;// 交叉概率
	private double mutateProbility;// 交叉概率
	private GAParameter parameter = null;// 对配置文件的操作，用于获取文件的配置参数；

	private Random random = null;

	private List<Chromosome> chromosomes = null;

	private Chromosome bestChrom = null;
	
	private IPopulationOpt iPopulationOpt;

	public Chromosome getBestChrom() {
		// 比较各个适应值
		bestChrom = chromosomes.get(0).copy();

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
		initializePopulation();
	}

	public Population() {
		// this.parameter = GACfgInfo.getInstance().getParametersOfGA();
		initializePopulation();
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
		
		iPopulationOpt = new DefaultPopulationOpt();//初始化默认的种群操作
		
		for (int i = 0; i < popSize; i++) {
			Chromosome chromosome = new Chromosome();
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

	/**
	 * 
	 * @param index1
	 *            :选择的第一个染色的下标为index1
	 * @param index2
	 *            :选择的第二个染色的下标为index2
	 */
	public void crossover(int index1, int index2) {
		int chrLen = parameter.getChromosomeLength();// 染色体的长度
		// 产生一个合法的交叉位置
		int crossPosition = random.nextInt(chrLen);
		while ((crossPosition == 0) || (crossPosition == (chrLen - 1))) {
			crossPosition = random.nextInt(chrLen);
		}

		Chromosome chromosome1 = chromosomes.get(index1);// 第一个染色体
		Chromosome chromosome2 = chromosomes.get(index2);// 第二个染色体
		List<StringBuffer> code1 = chromosome1.getCodes();// 第一个染色体的编码表示
		List<StringBuffer> code2 = chromosome2.getCodes();// 第二个染色体的编码表示

		for (int i = crossPosition; i < chrLen; i++) {// 交叉操作
			StringBuffer stringBuffer1 = code1.get(i);
			StringBuffer stringBuffer2 = code2.get(i);
			code1.set(i, stringBuffer2);
			code2.set(i, stringBuffer1);
		}
	}

	/**
	 * @description:采用随机选择的策略，即轮盘赌选择策略
	 * @param fitness
	 *            ：染色体的适应值
	 * @return 选择的染色体的下标
	 */
	public List<Integer> choose(List<Double> fitnesses) {
		int length = fitnesses.size();
		List<Integer> posIndex = new ArrayList<Integer>();
		for (int k = 0; k < length; k++) {
			int pos = 0;
			double sum = 0.0;
			for (int j = 0; j < length; j++) {// 计算总的适应度
				sum += fitnesses.get(j);
			}
			// 计算个体的选择区间的大小，根据适应度进行的计算
			double[] select = new double[length];
			for (int i = 0; i < length; i++) {
				select[i] = fitnesses.get(i) / sum;
			}
			// 计算个体的选择区间
			double[] ind_select = new double[length];
			for (int i = 0; i < length; i++) {
				if (i == 0) {
					ind_select[i] = select[i];
				} else {
					ind_select[i] = ind_select[i - 1] + select[i];
				}
			}
			// 产生一个随机数,根据随机数来确定个体的选择
			double select_num = random.nextDouble();
			for (int i = 0; i < length; i++) {
				if (select_num < ind_select[i]) {
					pos = i;
					posIndex.add(pos);
					break;
				}
			}			
		}
		return posIndex;
	}

	/**
	 * 求取染色体的适应值
	 * @return
	 */
	public List<Double> getFitness(){
		
		return iPopulationOpt.getFitness(chromosomes);
		
	}
}