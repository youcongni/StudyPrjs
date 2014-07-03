package fjnu.domain.chrom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import fjnu.domain.IServices.IChromosomeServices;
import fjnu.domain.input.GACfgInfo;
import fjnu.domain.input.GAParameter;

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


	private GAParameter gaParameter = null;
	private List<StringBuffer> encodes = null;// 保存个体的编码表示
	// private String encoding;//
	private Random random = null;

	private int chromesomeLength=0;
	
	private List<StringBuffer> codes=null;// 保存编码方案
	
	private double fitness=Double.MIN_VALUE;
	
	private IChromosomeOpt ChromosomeOpt = null;//染色体操作的实现类，主要用于计算适应值等

	/**
	 * 初始化染色体；首相从外界中获取该染色体的编码方案及其编码长度； 进行染色体的随机生成时，
	 * 每次随机的产生一个随机位置，用于从编码方案中随机挑选字符；
	 * 
	 * @param random
	 *            随机类，主要用于产生随机位置；
	 * @param length
	 *            长度，便是染色体的长度；
	 */
	public Chromosome() {
		gaParameter = GACfgInfo.getInstance().getGAParameters();
		this.random = new Random();
		initialChromosome();
	}
	
	//依赖对象注入，便于测试
	public Chromosome(Random random,GAParameter parameter){
		this.gaParameter=parameter;
		this.random=random;
	}

	private void initialChromosome(){
		encodes = new ArrayList<StringBuffer>();
		chromesomeLength = gaParameter.getChromosomeLength();// 染色体长度保存；
		codes = gaParameter.getEncodes();// 获取编码方案；
		String  implNameofIChrOpt=gaParameter.getImplClsNmOfIChrOpt();
		
		try {
			ChromosomeOpt=(IChromosomeOpt) Class.forName(implNameofIChrOpt).newInstance();
		} catch (InstantiationException  e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int encodingLenght = codes.size();// 保存编码字符的长度
		for (int i = 0; i < chromesomeLength; i++) {
			int position = random.nextInt(encodingLenght);// 生成的编码方案不能超过，保存编码字符的长度
			StringBuffer sb = codes.get(position);
			encodes.add(sb);
		}
		
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
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void mutate(int mutateNum) {

		//产生变异位置
		int encodesLen= encodes.size();
		int mutatePos=random.nextInt(encodesLen);
		
		//产生新编码位
		int codesLen= codes.size();
		int  codesPos=random.nextInt(codesLen);
		
		//进行变异
		encodes.set(mutatePos, codes.get(codesPos));
	}

    //获取适应值
	public double getFitness() {
		if(fitness==Double.MIN_VALUE){
			fitness=ChromosomeOpt.calcuFitness(encodes);
		}
		return fitness;
	}

	public Chromosome copy(){
		Chromosome  newChromosome=new Chromosome();
		
		List<StringBuffer> newEncodes=new ArrayList<StringBuffer>();
		
		int len=encodes.size();
		for(int i=0;i<len;i++){
			String tempStr=encodes.get(i).toString();
			newEncodes.add(new StringBuffer(tempStr));
		}
		
		return newChromosome;
	}
	
}
