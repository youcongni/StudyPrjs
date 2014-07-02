package fjnu.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import fjnu.domain.IServices.IChromosomeServices;

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
public class Chromosome {// 染色体

	private IChromosomeServices ics = null;//
	private GAParameter gAParameter = null;
	private List<StringBuffer> encodes = null;// 保存个体的编码表示
	// private String encoding;//
	private Random random = null;

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	/**
	 * 初始化染色体；首相从外界中获取该染色体的编码方案及其编码长度； 进行染色体的随机生成时，
	 * 每次随机的产生一个随机位置，用于从编码方案中随机挑选字符；
	 * 
	 * @param random
	 *            随机类，主要用于产生随机位置；
	 * @param length
	 *            长度，便是染色体的长度；
	 */
	public Chromosome(Random random, GAParameter gaParameter,
			IChromosomeServices ics) {
		this.gAParameter = gaParameter;
		this.ics = ics;
		this.random = random;
		encodes = new ArrayList<StringBuffer>();
		int chromesomeLength = gaParameter.getChromosomeLength();// 染色体长度保存；
		List<StringBuffer> codes = gaParameter.getEncodes();// 获取编码方案；
		int encodingLenght = codes.size();// 保存编码字符的长度
		for (int i = 0; i < chromesomeLength; i++) {
			int position = random.nextInt(encodingLenght);// 生成的编码方案不能超过，保存编码字符的长度
			StringBuffer sb = codes.get(position);
			encodes.add(sb);
		}
	}

	public Chromosome() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		super();
		this.random = new Random();
		// 代码整理Properties
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(
					"src/fjnu/domain/GAParameter.properties"));
			properties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			System.err.println("文件未发现的异常！");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO 异常！");
			e.printStackTrace();
		}// 代码整理Properties
		GACfgInfo gaCfgInfo = new GACfgInfo(properties);
		this.gAParameter = gaCfgInfo.getParametersOfGA();
		String implClsNameOfChromName = "gAParameter.getImplClsName()";
		Class<IChromosomeOpt> chromosomeOpt = (Class<IChromosomeOpt>) Class
				.forName(implClsNameOfChromName).newInstance();

		// this.ics
	}

	/**
	 * 染色体的变异操作，变异后返回一个染色体；
	 * 
	 * @param num
	 *            变异策略的声明；例如，单点变异、两点变异等；
	 * @param codes
	 *            染色体的编码串
	 * @param gaPara
	 *            GA参数
	 * @return 变异后的染色体
	 */
	public Chromosome mutate(int mutateNum, List<StringBuffer> codes,
			GAParameter gaPara) {
		Chromosome newChromosome = null;

		// 产生一个合法的变异位置
		int len = codes.size();// 编码串的长度；
		int pos = random.nextInt(len);// 第一次调用，产生随机数字
		StringBuffer sb = codes.get(pos);// 获取变异位置的编码
		// 获取编码方案
		List<StringBuffer> codesFangAn = gaPara.getEncodes();
		// 产生替换编码
		int size = codesFangAn.size();
		int position = random.nextInt(size);// 第二次调用，产生随机数字
		StringBuffer sbInstead = codesFangAn.get(position);// 产生目标编码
		while (sbInstead.toString().equals(sb.toString())) {
			position = random.nextInt(size);
			sbInstead = codesFangAn.get(position);
		}
		// 替换操作，重新组织新的染色体;如果位置为开始位置:
		StringBuffer[] sBuffers = new StringBuffer[len];
		if (pos == 0) {// 如果位置为开头

			for (int i = 0; i < len; i++) {
				sBuffers[i] = codes.get(i);
			}
			// 获取开头元素，并替换
			int sizee = sBuffers[pos].length();
			sBuffers[pos].replace(0, sizee, sbInstead.toString());
			for (int i = 0; i < len; i++) {// 重新组织新的染色体;
				System.out.print(sBuffers[i].toString());
			}

		} else if (pos == (len - 1)) {// 重新组织新的染色体;如果位置为结束位置:
		// StringBuffer[] sBuffers = new StringBuffer[len];
			for (int i = 0; i < len; i++) {
				sBuffers[i] = codes.get(i);
			}
			// 获取开头元素，并替换
			int sizee = sBuffers[pos].length();
			sBuffers[len - 1].replace(0, sizee, sbInstead.toString());
			for (int i = 0; i < len; i++) {// 重新组织新的染色体;
			// codes.add(sBuffers[i]);
				System.out.print(sBuffers[i].toString());
			}
		} else {// 重新组织新的染色体;如果位置为中间:
		// StringBuffer[] sBuffers = new StringBuffer[len];
			for (int i = 0; i < len; i++) {
				sBuffers[i] = codes.get(i);
			}
			// 获取元素，并替换
			int sizee = sBuffers[pos].length();
			sBuffers[pos].replace(0, sizee, sbInstead.toString());
			for (int i = 0; i < len; i++) {// 重新组织新的染色体;
				System.out.print(sBuffers[i].toString());
			}
		}
		List<StringBuffer> s2 = new ArrayList<StringBuffer>();
		for (int i = 0; i < len; i++) {
			s2.add(sBuffers[i]);
		}
		this.encodes = s2;
		newChromosome.setEncodes(s2);
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

	public static void main(String[] args) {

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