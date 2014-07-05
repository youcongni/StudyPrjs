package fjnu.domain.chromTest;

import static org.mockito.Mockito.*;//

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fjnu.domain.chrom.Chromosome;
import fjnu.domain.chrom.IChromosomeOpt;
import fjnu.domain.input.GAParameter;

public class ChromosomeTest {

	private Random mockRandom = null;
	private GAParameter mockGaParameter = null;
	private Chromosome actualChromosome = null;

	@Before
	public void setUp() throws Exception {
		mockRandom = mock(Random.class);
		mockGaParameter = mock(GAParameter.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	// 染色体初始化的测试
	@SuppressWarnings("unchecked")
	@Test
	public void testChromosomeIntial() {

		String implClsChrOptName = "fjnu.domain.chrom.TempChromoseOpt";
		int chrLen = 4;
		List<StringBuffer> expectedEncodes = new ArrayList<StringBuffer>();
		expectedEncodes.add(new StringBuffer("1"));
		expectedEncodes.add(new StringBuffer("2"));
		expectedEncodes.add(new StringBuffer("0"));
		expectedEncodes.add(new StringBuffer("1"));

		// 数据准备
		GAParameter mockGaParameter = mock(GAParameter.class);
		List<StringBuffer> mockCodes = new ArrayList<StringBuffer>();// 实例化一个
																		// 编码方案
		mockCodes.add(new StringBuffer("0"));
		mockCodes.add(new StringBuffer("1"));
		mockCodes.add(new StringBuffer("2"));
		when(mockGaParameter.getEncodes()).thenReturn(mockCodes);// 模拟产生的编码方案{0,1,2}
		when(mockGaParameter.getChromosomeLength()).thenReturn(chrLen);// 模拟产生染色体的长度，
		when(mockGaParameter.getImplClsNmOfIChrOpt()).thenReturn(
				implClsChrOptName);// 模拟初始化时实现的类名

		// 测试了优先顺序
		when(mockRandom.nextInt(mockCodes.size())).thenReturn(1).thenReturn(2)
				.thenReturn(0).thenReturn(1);

		// 驱动测试
		actualChromosome = new Chromosome(mockRandom, mockGaParameter);
		try {
			Method method = actualChromosome.getClass().getDeclaredMethod(
					"initialChromosome", new Class[] {});
			method.setAccessible(true);// 设置为可以测试的公有属性
			method.invoke(actualChromosome, new Object[]{});// 驱动私有方法的执行

			Field field = actualChromosome.getClass().getDeclaredField(
					"encodes");
			field.setAccessible(true);// 设置为可以测试的公有属性
			List<StringBuffer> actualEncodes = (ArrayList<StringBuffer>) field.get(actualChromosome);
			int actualLen = actualEncodes.size();
			int expectLen = expectedEncodes.size();
			Assert.assertEquals(expectLen, actualLen);
			if (actualLen == expectLen) {
				for (int i = 0; i < actualLen; i++) {
					Assert.assertEquals(expectedEncodes.get(i).toString(),
							actualEncodes.get(i).toString());
				}
			}	
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		
		

	}

	/**
	 * 模拟开头的变异
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testChromosomeMutateWhenPosIsStart() {
		// 测试准备
		List<StringBuffer> codes = new ArrayList<StringBuffer>();
		codes.add(new StringBuffer("1"));
		codes.add(new StringBuffer("0"));
		codes.add(new StringBuffer("1"));
		codes.add(new StringBuffer("1"));
		List<StringBuffer> encodes = new ArrayList<StringBuffer>();
		encodes.add(new StringBuffer("0"));
		encodes.add(new StringBuffer("1"));
		encodes.add(new StringBuffer("2"));

		mockGaParameter = mock(GAParameter.class);

		when(mockGaParameter.getEncodes()).thenReturn(encodes);// 模拟获取编码方案
		when(mockRandom.nextInt(codes.size())).thenReturn(1);// 模拟变异的位置结尾
		when(mockRandom.nextInt(encodes.size())).thenReturn(2);// 模拟产生的目标码是2

		Chromosome realhromosome = new Chromosome(mockRandom, mockGaParameter);
		realhromosome.mutate(1);

		// ////断言部分没有

	}

	@Test
	public void testChromosomeMutateWhenPosIsEnd()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		// 模拟真实值，即编码串“1011”
		List<StringBuffer> value = new ArrayList<StringBuffer>();
		value.add(new StringBuffer("1"));
		value.add(new StringBuffer("0"));
		value.add(new StringBuffer("1"));
		value.add(new StringBuffer("1"));
		// 模拟真实值，即编码方案“012”
		List<StringBuffer> encodes = new ArrayList<StringBuffer>();
		StringBuffer sbBuffer1 = new StringBuffer("0");
		StringBuffer sbBuffer2 = new StringBuffer("1");
		StringBuffer sbBuffer3 = new StringBuffer("2");
		encodes.add(sbBuffer1);
		encodes.add(sbBuffer2);
		encodes.add(sbBuffer3);

		mockGaParameter = mock(GAParameter.class);

		when(mockGaParameter.getEncodes()).thenReturn(encodes);// 模拟获取编码方案
		when(mockRandom.nextInt(value.size())).thenReturn(3);// 模拟变异的位置结尾
		when(mockRandom.nextInt(encodes.size())).thenReturn(2);// 模拟产生的目标码是2

		Chromosome realhromosome = new Chromosome(mockRandom, mockGaParameter);
		realhromosome.mutate(1);

	}

	@Test
	public void testChromosomeMutateWhenPosIsMedia()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		// 模拟真实值，即编码串“1011”
		List<StringBuffer> value = new ArrayList<StringBuffer>();
		value.add(new StringBuffer("1"));
		value.add(new StringBuffer("0"));
		value.add(new StringBuffer("1"));
		value.add(new StringBuffer("1"));
		// 模拟真实值，即编码方案“012”
		List<StringBuffer> encodes = new ArrayList<StringBuffer>();
		StringBuffer sbBuffer1 = new StringBuffer("0");
		StringBuffer sbBuffer2 = new StringBuffer("1");
		StringBuffer sbBuffer3 = new StringBuffer("2");
		encodes.add(sbBuffer1);
		encodes.add(sbBuffer2);
		encodes.add(sbBuffer3);

		mockGaParameter = mock(GAParameter.class);

		when(mockGaParameter.getEncodes()).thenReturn(encodes);// 模拟获取编码方案
		when(mockRandom.nextInt(value.size())).thenReturn(2);// 模拟变异的位置
		when(mockRandom.nextInt(encodes.size())).thenReturn(2);// 模拟产生的目标码是2

		Chromosome realhromosome = new Chromosome(mockRandom, mockGaParameter);
		realhromosome.mutate(1);

	}

	@Test
	public void testChromosomeCalculateFitness() {

		IChromosomeOpt mockChromosomeOpt = mock(IChromosomeOpt.class);

		GAParameter mockGaParameter = mock(GAParameter.class);
		List<StringBuffer> mockCodes = new ArrayList<StringBuffer>();
		mockCodes.add(new StringBuffer("0"));
		mockCodes.add(new StringBuffer("1"));
		mockCodes.add(new StringBuffer("2"));

		when(mockChromosomeOpt.calcuFitness(mockCodes)).thenReturn(1.0);

		when(mockGaParameter.getEncodes()).thenReturn(mockCodes);
		when(mockGaParameter.getChromosomeLength()).thenReturn(3);
		Random mockRandom = mock(Random.class);

		when(mockRandom.nextInt(3)).thenReturn(2).thenReturn(1).thenReturn(0);
		// 驱动测试
		// Chromosome actualChromosome = new Chromosome(mockRandom,
		// mockGaParameter);

	}
}