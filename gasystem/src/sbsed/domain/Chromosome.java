package sbsed.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sbsed.domain.IServices.IChromosomeServices;

/**
 * 该类为染色体类，主要封装了GA算法中的染色体的个体变异、求解适应值的操作；染色体需要向外界展示个体编码，
 * 用属性encoding表示，初始化染色体时，需要借助随机类Random，为避免内存空间浪费，在进行初始化时， 由外层
 * 调用者传入随机Random类，编码是进行随机生成的。
 * 
 * @author: 赵康
 * @teacher:倪友聪
 * @time:2014年6月24日
 * 
 */
public class Chromosome {

	private IChromosomeServices ics;
	private GAParameter gAParameter = null;
	private List<StringBuffer> encodes;// 保存个体的编码表示,主要由从配置文件中读取的编码方案读取的编码组成；
	// private String encoding;//
	Random random;

	/**
	 * 初始化染色体；首相从外界中获取该染色体的编码方案及其编码长度； 进行染色体的随机生成时，
	 * 每次随机的产生一个随机位置，用于从编码方案中随机挑选字符；
	 * 
	 * @param random
	 *            随机类，主要用于产生随机位置；
	 * @param length
	 *            长度，便是染色体的长度；
	 */
	public Chromosome(Random random, GAParameter gaParameter) {
		this.gAParameter = gaParameter;
		this.random = random;
		encodes = new ArrayList<StringBuffer>();
		int chromesomeLength = gaParameter.getChromosomeLength();// 染色体长度保存；
		List<StringBuffer> codes = gaParameter.getEncodes();// 获取编码方案；
		int encodingLenght = codes.size();// 保存编码字符的长度

		for (int i = 0; i < chromesomeLength; i++) {
			int posEncodes = random.nextInt(encodingLenght);// 生成的编码方案不能超过，保存编码字符的长度
			StringBuffer sb = codes.get(posEncodes);
			encodes.add(sb);
		}
	}

	/**
	 * 染色体的变异操作，变异后返回一个染色体；
	 * 
	 * @param num
	 *            变异策略的声明；例如，单点变异、两点变异等；
	 * @return 变异后的染色体
	 */
	public Chromosome mutate(int mutateNum) {
		
		//产生mutateNum个变异位置，他们需要不相同的
		
		
		
		
		
		Chromosome newChromosome = null;
		String expression = "010100100";
		System.out.println("010100100");
		int i = 0;
		// 产生一个变异位置
		while (i < mutateNum) {
			Random random = new Random();
			int length = expression.length();
			int position = random.nextInt(length);
			// 定位到染色体的变异位置并取出其中数字
			char ch = expression.charAt(position);
			// 对染色体实行变异操作
			if (ch == '0') {
				ch = '1';
			} else {
				ch = '0';
			}
			// 重新组织新的染色体;如果位置为开始位置:
			if (position == 0) {
				String tempData = expression.substring(1);
				tempData = ch + tempData;
				System.out.println(tempData);
			} else if (position == (length - 1)) {// 重新组织新的染色体;如果位置为结束位置:
				String tempData = expression.substring(0, length - 1);
				tempData = tempData + ch;
				System.out.println(tempData);
			} else {
				String tempData = expression.substring(0, position);
				String tempData2 = expression.substring(position + 1);
				tempData = tempData + ch + tempData2;
				System.out.println(tempData);
			}
			i++;
		}
		return newChromosome;
	}

	public List<StringBuffer> getEncodes() {
		return encodes;
	}

	public void setEncodes(List<StringBuffer> encodes) {
		this.encodes = encodes;
	}

	public double caculateFitness() {
		return ics.calculateFitness();
	}
}

/*
 * public static void main(String[] args) { Random random = new Random(); String
 * propertyFilePath = "src/sbsed/domain/GAParameter.properties"; GACfgInfo
 * gaCfgInfo = new GACfgInfo(propertyFilePath); GAParameter gaParameter =
 * gaCfgInfo.getParametersOfGA(); Chromosome chromosome = new Chromosome(random,
 * gaParameter); List<StringBuffer> sList = chromosome.getEncodes(); int size =
 * sList.size(); for (int i = 0; i < size; i++) {
 * System.out.print(sList.get(i).toString()); } }
 */