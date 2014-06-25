package sbsed.domainTest;

//import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sbsed.domain.GACfgInfo;
import sbsed.domain.GAParameter;
import static org.mockito.Mockito.*;//

public class ConfigurationInfoTest {

	private String propetyFilePath = null;
	private Properties mockProperties = null;
	private GACfgInfo confInfo = null;

	@Before
	public void setUp() throws Exception {
		propetyFilePath = "src/sbsed/domainTest/GAParameter.properties";
		confInfo = new GACfgInfo(propetyFilePath);
		mockProperties = mock(Properties.class);
		
		/*
		 * propetyFilePath = "src/sbsed/domainTest/GAParameter.properties";
		 * mockProperties = new Properties(); mockProperties.load(new
		 * FileInputStream(propetyFilePath)); confInfo = new
		 * GACfgInfo(propetyFilePath);
		 */
	}

	@Test
	public void testGetParaOfGAWhenChromosoIsNull() {
		when(mockProperties.getProperty("chromosomeLength")).thenReturn(null);
		confInfo.setProperties(mockProperties);
		// 驱动
		GAParameter gaPara = confInfo.getParametersOfGA();
		int expectedChromosomeLength = 10;
		int actualChromosomeLength = gaPara.getChromosomeLength();
		// 断言
		Assert.assertEquals(expectedChromosomeLength, actualChromosomeLength);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetParametersOfGAWhenChromosomelengthIsNull() {
		// 测试准备
		when(mockProperties.get("chromosomeLength")).thenReturn(null);
		confInfo.setProperties(mockProperties);
		int expectedChromosomelength = 10;
		// 驱动测试框架获取获取数据
		GAParameter gaPara = confInfo.getParametersOfGA();
		int actualChromosomelength = gaPara.getChromosomeLength();
		// 测试判断
		Assert.assertEquals(expectedChromosomelength, actualChromosomelength);
	}

	@Test
	public void testGetParaOfGAWhenMaxIteratorNumIsNull() {

		GAParameter gaPara = confInfo.getParametersOfGA();
		int expectedMaxIteratorNum = 1000;
		int actualMaxIteratorNum = gaPara.getMaxIteratorNum();
		Assert.assertEquals(expectedMaxIteratorNum, actualMaxIteratorNum);
	}

	@Test
	public void testGetParaOfGAWhenMutateProbabilityIsNull() {
		when(mockProperties.get("mutateProbability")).thenReturn(null);
		GAParameter gaPara = confInfo.getParametersOfGA();
		double expectedMutateProbability = 0.02;
		double actualMutateProbability = gaPara.getMutateProbabilty();
		Assert.assertEquals(expectedMutateProbability, actualMutateProbability);
	}

	@Test
	public void testGetParaOfGAWhenCrossProbabilityIsNull() {
		when(mockProperties.get("crossoverProbability")).thenReturn(null);
		GAParameter gaPara = confInfo.getParametersOfGA();
		double expectedCrossProbability = 0.3;
		double actualCrossProbability = gaPara.getCrossoverProbability();
		Assert.assertEquals(expectedCrossProbability, actualCrossProbability);
	}

	@Test
	public void testGetParaOfGAWhenMaxFitnessIsNull() {
		GAParameter gaPara = confInfo.getParametersOfGA();
		double expectedMaxFitness = 20.0;
		double actualMaxFitness = gaPara.getMaxFitness();
		Assert.assertEquals(expectedMaxFitness, actualMaxFitness);

	}

	@Test
	public void testGetParaOfGAWhenPopulationSizeIsNull() {
		GAParameter gaPara = confInfo.getParametersOfGA();
		double expectedPopulationSize = 30;
		double actualPopulationSize = gaPara.getPopulationSize();
		Assert.assertEquals(expectedPopulationSize, actualPopulationSize);
	}

	@Test
	public void testGetParaOfGAWhenencodingIsNull() {
		when(mockProperties.getProperty("encoding")).thenReturn("0,1");
		GAParameter gaPara = confInfo.getParametersOfGA();
		StringBuffer expectedEncoding = new StringBuffer();
		expectedEncoding.append("0,1");
		List<StringBuffer> actualEncoding = gaPara.getEncodes();
		Assert.assertEquals(expectedEncoding.toString(),
				actualEncoding.toString());
	}

	@Test
	public void testGetParaOfGAWhenImplChromIsNull() {
		when(mockProperties.getProperty("implsNameOfChrom")).thenReturn(null);

		GAParameter gaParameter = confInfo.getParametersOfGA();
		List<StringBuffer> actualPara = gaParameter.getImplClsName();
		List<StringBuffer> expectedPara = new ArrayList<StringBuffer>();
		StringBuffer sb = new StringBuffer();
		sb.append("not implsName");
		expectedPara.add(sb);

		int actualLen = actualPara.size();
		int expectedLen = expectedPara.size();
		if (actualLen == expectedLen) {
			for (int i = 0; i < actualLen; i++) {
				Assert.assertEquals(expectedPara.get(i).toString(), actualPara
						.get(i).toString());
			}
		} else {
			//
			System.out.println("！！！方法设置值错误！！！");
		}
	}

	// 以上所有的测试都是在检测为空的时候，是否显示默认值；

	// 以下是检测GAParameter的参数不为空的时候，是否显示为配置的值；
	@Test
	public void testWhenGetParametersOfGAIsNotNull() {

		when(mockProperties.getProperty("chromosomeLength")).thenReturn("20");
		when(mockProperties.getProperty("populationSize")).thenReturn("60");
		when(mockProperties.getProperty("maxFitness")).thenReturn("60");
		when(mockProperties.getProperty("crossoverProbability")).thenReturn(
				"0.7");
		when(mockProperties.getProperty("mutateProbability"))
				.thenReturn("0.02");
		when(mockProperties.getProperty("maxIteratorNum")).thenReturn("12000");
		when(mockProperties.getProperty("encoding")).thenReturn("0,1,2");
		when(mockProperties.getProperty("implClsNameOfChrom")).thenReturn(
				"impl1,impl2");
		confInfo.setProperties(mockProperties);
		GAParameter gaPara = confInfo.getParametersOfGA();
		List<StringBuffer> expectedList = new ArrayList<StringBuffer>();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb1.append("impl1");
		sb2.append("impl2");
		expectedList.add(sb1);
		expectedList.add(sb2);

		List<StringBuffer> actualImplList = gaPara.getImplClsName();
		int len = actualImplList.size();
		for (int i = 0; i < len; i++) {
			Assert.assertEquals(expectedList.get(i).toString(), actualImplList
					.get(i).toString());
		}

		// when(mockProperties.getProperty("implClsNameOfChrom")).thenReturn(implList);

		// 测试染色体长度
		int actualChromosomelength = gaPara.getChromosomeLength();
		int expectedChromosomelength = 20;
		Assert.assertEquals(expectedChromosomelength, actualChromosomelength);
		// 测试编码方案
		// String expectedEncoding = "0,1,2";
		// List<String> actualEncoding = gaPara.getEncodes();
		// Assert.assertEquals(expectedEncoding, actualEncoding.toString());
		// 测试种群大小
		double expectedPopulationSize = 60;
		double actualPopulationSize = gaPara.getPopulationSize();
		Assert.assertEquals(expectedPopulationSize, actualPopulationSize);
		// 测试适应度大小
		double expectedMaxFitness = 60.0;
		double actualMaxFitness = gaPara.getMaxFitness();
		Assert.assertEquals(expectedMaxFitness, actualMaxFitness);
		// 测试交叉概率
		double expectedCrossProbability = 0.7;
		double actualCrossProbability = gaPara.getCrossoverProbability();
		Assert.assertEquals(expectedCrossProbability, actualCrossProbability);
		// 测试变异概率
		double expectedMutateProbability = 0.02;
		double actualMutateProbability = gaPara.getMutateProbabilty();
		Assert.assertEquals(expectedMutateProbability, actualMutateProbability);
		// 测试最大迭代次数
		int expectedMaxIteratorNum = 12000;
		int actualMaxIteratorNum = gaPara.getMaxIteratorNum();
		Assert.assertEquals(expectedMaxIteratorNum, actualMaxIteratorNum);
	}
}