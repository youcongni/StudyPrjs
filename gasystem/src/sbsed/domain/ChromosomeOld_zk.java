package sbsed.domain;

import java.util.Random;

import sbsed.domain.IServices.IChromosomeServicesOld;

public class ChromosomeOld_zk {

	private String expression;// 染色体的表示形式；

	private IChromosomeServicesOld ics = null;

	public ChromosomeOld_zk(IChromosomeServicesOld ics) {
		super();
		this.ics = ics;
	}

	public ChromosomeOld_zk() {
		super();
		expression = "";
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * 对染色体进行变异操作,变异后需要重新对染色体的表达式赋值
	 * 
	 * @return：变异后的染色体
	 */
	public ChromosomeOld_zk mutateOfChromosome() {
		int length = expression.length();
		ChromosomeOld_zk newChromosome = ics.mutateOfChromosome(length);
		return newChromosome;
	}

	/**
	 * 对染色体进行计算适应度操作；
	 * 
	 * @return：获取的染色体的个体适应度
	 */
	public double calculateFitness() {
		return ics.calculateFitness();
	}

	/**
	 * 初始化一个长度为size的个体，该个体的编码是采用的0-1编码；
	 * 
	 * @param size
	 *            :染色体的长度
	 */
	public String intialChromoSome(int size) {
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			int num = random.nextInt(2);
			expression += String.valueOf(num);
		}
		return expression;
	}

	/**
	 * 该main方法主要用于测试与Chromosome相关的操作；
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			ChromosomeOld_zk chromosome = new ChromosomeOld_zk();
			String result = chromosome.intialChromoSome(6);
			System.out.println("第" + (i + 1) + "个个体的表达式：" + "-->" + result);
		}
	}
}