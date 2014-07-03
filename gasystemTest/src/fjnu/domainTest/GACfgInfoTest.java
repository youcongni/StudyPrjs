package fjnu.domainTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fjnu.domain.GACfgInfo;
import fjnu.domain.GAParameter;

import static org.mockito.Mockito.*;

/**
 * 针对GACfgInfo(Properties properties)的参数；
 * 
 * @author zhaohongxu
 * 
 */
public class GACfgInfoTest {

	private GACfgInfo gaCfgInfo = null;// 测试类
	private GAParameter gaParameter = null;// 保存GA参数；
	private Properties mockProperties = null;// Properties与参数配置文件打交道的类

	@Before
	public void setUp() throws Exception {
		mockProperties = mock(Properties.class);
		gaCfgInfo = new GACfgInfo(mockProperties);
	}

	@After
	public void tearDown() throws Exception {
	}

	// 测试chromosomeLength参数
	@Test
	public void testGetParametersOfGAChrLenIsNullOrEmpty() {

		when(mockProperties.getProperty("chromosomeLength")).thenReturn(null);
		gaParameter = gaCfgInfo.getParametersOfGA();
		int actualChrLen = gaParameter.getChromosomeLength();
		int expectChrLen = 10;
		assertEquals(expectChrLen, actualChrLen);
	}

	// 测试implClsNameOfIChromosomeOpt参数
	@Test
	public void testGetParametersOfGAImplClsIsNullOrEmpty() {
		when(mockProperties.getProperty("implClsNameOfIChromosomeOpt"))
				.thenReturn("not implsName");
		gaParameter = gaCfgInfo.getParametersOfGA();
		String actualImplClsName = gaParameter.getImplClsName();
		String expectImplClsName = "not implsName";
		assertEquals(expectImplClsName, actualImplClsName);
	}

	// 测试获取编码方案encoding参数
	@Test
	public void testGetGAParametersWhenEncodesIsNullOrEmpty() {
		// 测试准备
		when(mockProperties.getProperty("encoding")).thenReturn(null);
		// 测试驱动
		gaParameter = gaCfgInfo.getParametersOfGA();
		List<StringBuffer> actualList = gaParameter.getEncodes();
		List<StringBuffer> expectList = new ArrayList<StringBuffer>();
		expectList.add(new StringBuffer("0"));
		expectList.add(new StringBuffer("1"));
		int actualListLen = actualList.size();
		int expectListLen = expectList.size();
		// 测试断言：长度关系
		Assert.assertEquals(expectListLen, actualListLen);
		if (expectListLen == actualListLen) {
			for (int i = 0; i < expectListLen; i++) {// 测试断言：值是否相同
				assertEquals(expectList.get(i).toString(), actualList.get(i)
						.toString());
			}
		}
	}
	
	////////////////////////还有其它参数未写测试代码

	// 测试配置文件中各项参数不为空的情况；
	////////////////////////////////参数不全
	@Test
	public void testGetGAParametersAreNotNullOrEmpty() {
		// 测试准备
		when(mockProperties.getProperty("chromosomeLength")).thenReturn("12");
		when(mockProperties.getProperty("encoding")).thenReturn("0,1,2");
		when(mockProperties.getProperty("implClsNameOfIChromosomeOpt"))
				.thenReturn("implsName");
		int expectChromosomeLen = 12;
		String expectImplClsNameOfIChromosomeOpt = "implsName";
		List<StringBuffer> expectEncoding = new ArrayList<StringBuffer>();
		expectEncoding.add(new StringBuffer("0"));
		expectEncoding.add(new StringBuffer("1"));
		expectEncoding.add(new StringBuffer("2"));
		// 测试驱动
		gaParameter = gaCfgInfo.getParametersOfGA();
		int actualChromosomeLen = gaParameter.getChromosomeLength();
		List<StringBuffer> actualEncoding = gaParameter.getEncodes();
		String actualImplClsNameOfIChromosomeOpt = gaParameter.getImplClsName();
		// 测试断言：
		assertEquals(expectChromosomeLen, actualChromosomeLen);
		assertEquals(expectImplClsNameOfIChromosomeOpt,
				actualImplClsNameOfIChromosomeOpt);

		int actualListLen = actualEncoding.size();
		int expectListLen = expectEncoding.size();
		// 测试断言：长度关系
		Assert.assertEquals(expectListLen, actualListLen);
		if (expectListLen == actualListLen) {
			for (int i = 0; i < expectListLen; i++) {// 测试断言：值是否相同
				assertEquals(expectEncoding.get(i).toString(), actualEncoding
						.get(i).toString());
			}
		}

	}
}
