package sbsed.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GACfgInfo {

	private Properties properties = null;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
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

	/**
	 * 该方法主要用于获取算法的配置参数
	 * 
	 * @return 返回参数信息给用户
	 */
	public GAParameter getParametersOfGA() {
		GAParameter gaParameters = new GAParameter();
		String chromosomeLength = properties.getProperty("chromosomeLength");
		if ((chromosomeLength == null) || chromosomeLength.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值；
			gaParameters.setChromosomeLength(10);
		} else {
			chromosomeLength.trim();//
			gaParameters.setChromosomeLength(Integer.valueOf(chromosomeLength)
					.intValue());
		}
		String crossoverProbability = properties
				.getProperty("crossoverProbability");
		if (crossoverProbability == null || crossoverProbability.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setCrossoverProbability(0.3);
		} else {
			crossoverProbability.trim();
			gaParameters.setCrossoverProbability(Double.valueOf(
					crossoverProbability).doubleValue());
		}
		String mutateProbabilty = properties.getProperty("mutateProbabilty");
		if (mutateProbabilty == null || mutateProbabilty.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMutateProbabilty(0.02);
		} else {
			mutateProbabilty.trim();
			gaParameters.setMutateProbabilty(Double.valueOf(mutateProbabilty));
		}
		String maxIteratorNum = properties.getProperty("maxIteratorNum");
		if (maxIteratorNum == null || maxIteratorNum.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMaxIteratorNum(1000);
		} else {
			maxIteratorNum.trim();
			gaParameters.setMaxIteratorNum(Integer.valueOf(maxIteratorNum)
					.intValue());
		}
		String populationSize = properties.getProperty("populationSize");
		if (populationSize == null || populationSize.equals("")) {// 若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setPopulationSize(30);
		} else {
			populationSize.trim();
			gaParameters.setPopulationSize(Integer.valueOf(populationSize));
		}
		String maxFitness = properties.getProperty("maxFitness");
		if (maxFitness == null || maxFitness.equals("")) {// 针对maxFitness，若是不为空，直接设置值；若为空，则获取默认值
			gaParameters.setMaxFitness(20.0);
		} else {
			maxFitness.trim();
			gaParameters.setMaxFitness(Double.valueOf(maxFitness));
		}
		// 借助List<String>获取染色体编码
		String encodings = properties.getProperty("encoding");
		List<StringBuffer> encodes = new ArrayList<StringBuffer>();
		// StringBuffer encoding = new StringBuffer();
		if (encodings == null || encodings.equals("")) {// 针对encodings，若是不为空，直接设置值；若为空，则获取默认值
			// encoding.append("0,1");
			// gaParameters.setEncoding(encoding);
			StringBuffer encoding = new StringBuffer();
			encoding.append("0");
			encoding.append("1");
			gaParameters.setEncodes(encodes);
		} else {
			// encoding.append(encodings);
			// gaParameters.setEncoding(encoding);
			String[] codes = encodings.split(",");
			int len = codes.length;
			for (int i = 0; i < len; i++) {
				StringBuffer encoding = new StringBuffer();
				encoding.append(codes[i].trim());
				encodes.add(encoding);
			}
			gaParameters.setEncodes(encodes);
		}

		String implClsNameOfChrom = properties
				.getProperty("implClsNameOfChrom");
		List<StringBuffer> implsNameList = new ArrayList<StringBuffer>();
		if (implClsNameOfChrom == null || implClsNameOfChrom.equals("")) {// 采用默认的实现类名
			System.out.println("==============??????????????==========");
			StringBuffer sb = new StringBuffer();
			sb.append("not implsName");
			implsNameList.add(sb);
			gaParameters.setImplClsName(implsNameList);
		} else {
			String[] implList = implClsNameOfChrom.split(",");
			int len = implList.length;
			for (int i = 0; i < len; i++) {
				StringBuffer sb = new StringBuffer();
				sb.append(implList[i].trim());
				implsNameList.add(sb);// 去除空格并将之添加进实现类名列表；
			}
			gaParameters.setImplClsName(implsNameList);
		}
		return gaParameters;
	}

	public static void main(String[] args) {
		String propertyFilePath = "src/sbsd/ui/GAParameter.properties";
		GACfgInfo configurationInfo = new GACfgInfo(propertyFilePath);
		GAParameter gaParameters = configurationInfo.getParametersOfGA();
		System.out.println(gaParameters.getChromosomeLength() + "--->"
				+ gaParameters.getMaxIteratorNum() + "-->"
				+ gaParameters.getMaxFitness() + "-->"
				+ gaParameters.getMutateProbabilty() + "-->"
				+ gaParameters.getPopulationSize() + "-->"
				+ gaParameters.getCrossoverProbability() + "-->"
				+ gaParameters.getEncodes().toString());
	}
}
