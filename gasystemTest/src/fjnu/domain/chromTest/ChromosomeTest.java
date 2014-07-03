package fjnu.domain.chromTest;

import static org.mockito.Mockito.*;//

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fjnu.domain.chrom.Chromosome;
import fjnu.domain.chrom.IChromosomeOpt;
import fjnu.domain.input.GAParameter;

public class ChromosomeTest {

	private Random mockRandom = null;
	private GAParameter mockGaParameter = null;
	private Chromosome chromosome = null;

	@Before
	public void setUp() throws Exception {
		mockRandom = mock(Random.class);
		mockGaParameter = mock(GAParameter.class);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewChromosomeWhenCodesIs() {

		// 数据准备
		GAParameter mockGaParameter = mock(GAParameter.class);
		List<StringBuffer> mockCodes = new ArrayList<StringBuffer>();
		mockCodes.add(new StringBuffer("1"));
		mockCodes.add(new StringBuffer("2"));
		mockCodes.add(new StringBuffer("3"));
		when(mockGaParameter.getEncodes()).thenReturn(mockCodes);
		when(mockGaParameter.getChromosomeLength()).thenReturn(3);
		Random mockRandom = mock(Random.class);

		// 测试了优先顺序
		when(mockRandom.nextInt(3)).thenReturn(2).thenReturn(1).thenReturn(0);

		// 驱动测试
		// Chromosome actualChromosome = new Chromosome(mockRandom,
		// mockGaParameter);
		// List<StringBuffer> actualCodes = actualChromosome.getEncodes();
		// List<StringBuffer> expectedCodes = new ArrayList<StringBuffer>();
		// expectedCodes.add(new StringBuffer("3"));
		// expectedCodes.add(new StringBuffer("2"));
		// expectedCodes.add(new StringBuffer("1"));
		// for (int i = 0; i < 3; i++) {
		// Assert.assertEquals(expectedCodes.get(i).toString(), actualCodes
		// .get(i).toString());
		// }

	}

	/**
	 * 模拟开头的变异
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testChromosomeMutateWhenPosIsStart()  {
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
		
		//////断言部分没有

	}

	@Test
	public void testChromosomeMutateWhenPosIsEnd() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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

		Chromosome realhromosome = new Chromosome(mockRandom, mockGaParameter,
				null);
		realhromosome.mutate(1, value, mockGaParameter);

	}

	@Test
	public void testChromosomeMutateWhenPosIsMedia() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
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

		Chromosome realhromosome = new Chromosome(mockRandom, mockGaParameter,
				null);
		realhromosome.mutate(1, value, mockGaParameter);

	}

	@Test
	public void testChromosomeCalculateFitness() {

		IChromosomeOpt mockChromosomeOpt = mock(IChromosomeOpt.class);
		when(mockChromosomeOpt.calcuFitness()).thenReturn(1.0);

		GAParameter mockGaParameter = mock(GAParameter.class);
		List<StringBuffer> mockCodes = new ArrayList<StringBuffer>();
		mockCodes.add(new StringBuffer("1"));
		mockCodes.add(new StringBuffer("2"));
		mockCodes.add(new StringBuffer("3"));
		
		when(mockGaParameter.getEncodes()).thenReturn(mockCodes);
		when(mockGaParameter.getChromosomeLength()).thenReturn(3);
		Random mockRandom = mock(Random.class);

		when(mockRandom.nextInt(3)).thenReturn(2).thenReturn(1).thenReturn(0);
		// 驱动测试
		// Chromosome actualChromosome = new Chromosome(mockRandom,
		// mockGaParameter);

	}
}