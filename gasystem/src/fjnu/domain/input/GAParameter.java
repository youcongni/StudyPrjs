package fjnu.domain.input;

import java.util.List;

/**
 * 该类主要封装了配置参数
 * 
 * @author zhaohongxu
 * 
 */
public class GAParameter {

	/**
	 * 染色体的长度
	 */
	private int chromosomeLength;
	/**
	 * 交叉概率
	 */
	private double crossoverProbability;
	/**
	 * 变异概率
	 */
	private double mutateProbabilty;
	/**
	 * 最大迭代次数
	 */
	private int maxIteratorNum;
	/**
	 * 种群规模
	 */
	private int populationSize;
	/**
	 * 最大适应值
	 */
	private double maxFitness;
	/**
	 * 个体编码
	 */
	private List<StringBuffer> encodes;
	/**
	 * 染色体操作接口的实现类
	 */
	private String implClsNmOfIChrOpt;//
	

	public List<StringBuffer> getEncodes() {
		return encodes;
	}

	public void setEncodes(List<StringBuffer> encodes) {
		this.encodes = encodes;
	}

	public String getImplClsNmOfIChrOpt() {
		return implClsNmOfIChrOpt;
	}

	public void setImplClsNmOfIChrOpt(String implClsName) {
		this.implClsNmOfIChrOpt = implClsName;
	}

	public int getChromosomeLength() {
		return chromosomeLength;
	}

	public void setChromosomeLength(int chromosomeLength) {
		this.chromosomeLength = chromosomeLength;
	}

	public double getCrossoverProbability() {
		return crossoverProbability;
	}

	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	public double getMutateProbabilty() {
		return mutateProbabilty;
	}

	public void setMutateProbabilty(double mutateProbabilty) {
		this.mutateProbabilty = mutateProbabilty;
	}

	public int getMaxIteratorNum() {
		return maxIteratorNum;
	}

	public void setMaxIteratorNum(int maxIteratorNum) {
		this.maxIteratorNum = maxIteratorNum;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public double getMaxFitness() {
		return maxFitness;
	}

	public void setMaxFitness(double maxFitness) {
		this.maxFitness = maxFitness;
	}

}
