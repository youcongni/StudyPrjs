package fjnu.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GACfgInfo {

	private Properties properties = null;

	
	public GACfgInfo(Properties properties) {
		this.properties = properties;
	}

	/**
	 * 该方法是类的构造函数，主要用于初始化一些参数等，例如Properties，该类封装 了读取properties文件的信息；
	 * 
	 * @param propertyFilePath
	 *            :properties文件的地址;
	 */
	public GACfgInfo(String propertyFilePath) {
		super();
		properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(propertyFilePath));
			properties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			System.err.println("文件未发现的异常！");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO 异常！");
			e.printStackTrace();
		}
	}

	public static GAParameter getGAParameters() {
		// 构造文件配置类
		String pathname = "src/fjnu/domain/GAParameter.properties";
		Properties properties = new Properties();
		File file = new File(pathname);
		FileInputStream inStream;
		try {
			inStream = new FileInputStream(file);
			properties.load(inStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 获取配置文件中的参数；
		GAParameter gaParameters = new GAParameter();// 实例化一个GAParameter对象，用于保存GAParameter参数；
		String chromosomeLength = properties.getProperty("chromosomeLength");
		String crosProb = properties.getProperty("crossoverProbability");
		String mutateProbabilty = properties.getProperty("mutateProbabilty");
		String maxIteratorNum = properties.getProperty("maxIteratorNum");
		String populationSize = properties.getProperty("populationSize");
		String encodings = properties.getProperty("encoding");
		String iNm = properties.getProperty("implClsNameOfIChromosomeOpt");
		String maxFitness = properties.getProperty("maxFitness");
		// 根据配置文件中的值，进行设置GA参数
		if ((chromosomeLength == null) || chromosomeLength.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值；
			gaParameters.setChromosomeLength(10);
		} else {
			chromosomeLength.trim();//
			gaParameters.setChromosomeLength(Integer.valueOf(chromosomeLength)
					.intValue());
		}
		if (crosProb == null || crosProb.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setCrossoverProbability(0.3);
		} else {
			crosProb.trim();
			gaParameters.setCrossoverProbability(Double.valueOf(crosProb)
					.doubleValue());
		}
		if (mutateProbabilty == null || mutateProbabilty.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMutateProbabilty(0.02);
		} else {
			mutateProbabilty.trim();
			gaParameters.setMutateProbabilty(Double.valueOf(mutateProbabilty));
		}
		if (maxIteratorNum == null || maxIteratorNum.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMaxIteratorNum(1000);
		} else {
			maxIteratorNum.trim();
			gaParameters.setMaxIteratorNum(Integer.valueOf(maxIteratorNum)
					.intValue());
		}
		if (populationSize == null || populationSize.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setPopulationSize(30);
		} else {
			populationSize.trim();
			gaParameters.setPopulationSize(Integer.valueOf(populationSize));
		}
		if (maxFitness == null || maxFitness.equals("")) {// 针对maxFitness，若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMaxFitness(20.0);
		} else {
			maxFitness.trim();
			gaParameters.setMaxFitness(Double.valueOf(maxFitness));
		}
		// 借助List<StringBuffer>获取染色体编码；针对encodings，若是不为空，直接设置为配置文件的值；若为空，则获取默认值0,1
		List<StringBuffer> encodes = new ArrayList<StringBuffer>();
		if (encodings == null || encodings.equals("")) {
			StringBuffer encoding = new StringBuffer();
			encoding.append("0");
			encoding.append("1");
			gaParameters.setEncodes(encodes);
		} else {
			String[] codes = encodings.split(",");
			int len = codes.length;
			for (int i = 0; i < len; i++) {
				StringBuffer encoding = new StringBuffer();
				encoding.append(codes[i].trim());
				encodes.add(encoding);
			}
			gaParameters.setEncodes(encodes);
		}
		if (iNm == null || iNm.equals("")) {// 采用默认的实现类名
			String sb = "not implsName";
			gaParameters.setImplClsName(sb);
		} else {
			gaParameters.setImplClsName(iNm);
		}
		return gaParameters;

	}

	/**
	 * 该方法主要用于获取算法的配置参数
	 * 
	 * @return 返回参数信息给用户
	 */
	public GAParameter getParametersOfGA() {

		// 获取配置文件中的参数；
		GAParameter gaParameters = new GAParameter();// 实例化一个GAParameter对象，用于保存GAParameter参数；
		String chromosomeLength = properties.getProperty("chromosomeLength");
		String crosProb = properties.getProperty("crossoverProbability");
		String mutateProbabilty = properties.getProperty("mutateProbabilty");
		String maxIteratorNum = properties.getProperty("maxIteratorNum");
		String populationSize = properties.getProperty("populationSize");
		String encodings = properties.getProperty("encoding");
		String iNm = properties.getProperty("implClsNameOfIChromosomeOpt");
		String maxFitness = properties.getProperty("maxFitness");
		// 根据配置文件中的值，进行设置GA参数
		if ((chromosomeLength == null) || chromosomeLength.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值；
			gaParameters.setChromosomeLength(10);
		} else {
			chromosomeLength.trim();//
			gaParameters.setChromosomeLength(Integer.valueOf(chromosomeLength)
					.intValue());
		}
		if (crosProb == null || crosProb.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setCrossoverProbability(0.3);
		} else {
			crosProb.trim();
			gaParameters.setCrossoverProbability(Double.valueOf(crosProb)
					.doubleValue());
		}
		if (mutateProbabilty == null || mutateProbabilty.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMutateProbabilty(0.02);
		} else {
			mutateProbabilty.trim();
			gaParameters.setMutateProbabilty(Double.valueOf(mutateProbabilty));
		}
		if (maxIteratorNum == null || maxIteratorNum.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMaxIteratorNum(1000);
		} else {
			maxIteratorNum.trim();
			gaParameters.setMaxIteratorNum(Integer.valueOf(maxIteratorNum)
					.intValue());
		}
		if (populationSize == null || populationSize.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setPopulationSize(30);
		} else {
			populationSize.trim();
			gaParameters.setPopulationSize(Integer.valueOf(populationSize));
		}
		if (maxFitness == null || maxFitness.equals("")) {// 针对maxFitness，若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMaxFitness(20.0);
		} else {
			maxFitness.trim();
			gaParameters.setMaxFitness(Double.valueOf(maxFitness));
		}
		// 借助List<StringBuffer>获取染色体编码；针对encodings，若是不为空，直接设置为配置文件的值；若为空，则获取默认值0,1
		List<StringBuffer> encodes = new ArrayList<StringBuffer>();
		if (encodings == null || encodings.equals("")) {
			encodes.add(new StringBuffer("0"));
			encodes.add(new StringBuffer("1"));
			gaParameters.setEncodes(encodes);
		} else {
			String[] codes = encodings.split(",");
			int len = codes.length;
			for (int i = 0; i < len; i++) {
				StringBuffer encoding = new StringBuffer();
				encoding.append(codes[i].trim());
				encodes.add(encoding);
			}
			gaParameters.setEncodes(encodes);
		}
		if (iNm == null || iNm.equals("")) {// 采用默认的实现类名
			String sb = "not implsName";
			gaParameters.setImplClsName(sb);
		} else {
			gaParameters.setImplClsName(iNm);
		}
		return gaParameters;
	}

	public static void main(String[] args) {
		// String propertyFilePath = "src/fjnu/domain/GAParameter.properties";
		// GACfgInfo configurationInfo = new GACfgInfo(propertyFilePath);
		GAParameter gaParameters = GACfgInfo.getGAParameters();
		System.out.println(gaParameters.getChromosomeLength() + "--->"
				+ gaParameters.getMaxIteratorNum() + "-->"
				+ gaParameters.getMaxFitness() + "-->"
				+ gaParameters.getMutateProbabilty() + "-->"
				+ gaParameters.getPopulationSize() + "-->"
				+ gaParameters.getCrossoverProbability() + "-->"
				+ gaParameters.getImplClsName());
		List<StringBuffer> sbList = gaParameters.getEncodes();
		int size = sbList.size();
		for (int i = 0; i < size; i++) {
			System.out.print(sbList.get(i).toString());
		}
	}
}
