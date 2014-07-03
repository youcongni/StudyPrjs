package fjnu.domain.populationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fjnu.domain.chrom.Chromosome;
import fjnu.domain.input.GAParameter;
import fjnu.domain.population.Population;

import static org.mockito.Mockito.*;

public class PopulationTest {

	private GAParameter mockParameter = null;
	private Random mockPopRandom = null;
	private Random mockChrRandom = null;
	Chromosome mockChromosome[] = null;

	private Population population = null;

	@Before
	public void setUp() throws Exception {
		mockParameter = mock(GAParameter.class);
		mockPopRandom = mock(Random.class);
		mockChrRandom = mock(Random.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialPopulation() {

		// 测试准备
		// 指定编码方案
		List<StringBuffer> encoding = new ArrayList<StringBuffer>();
		encoding.add(new StringBuffer("0"));
		encoding.add(new StringBuffer("1"));
		encoding.add(new StringBuffer("2"));
		// 指定参数
		int expectedSize = 3;// 指定种群规模
		when(mockParameter.getPopulationSize()).thenReturn(expectedSize);
		int chromsomeLenth = 3;// 指定染色体的长度
		when(mockParameter.getChromosomeLength()).thenReturn(chromsomeLenth);
		when(mockParameter.getEncodes()).thenReturn(encoding);
		int pos[] = { 0, 1, 2 };

		when(mockPopRandom.nextInt(encoding.size())).thenReturn(pos[0])
				.thenReturn(pos[1]).thenReturn(pos[2]).thenReturn(pos[0])
				.thenReturn(pos[1]).thenReturn(pos[2]).thenReturn(pos[0])
				.thenReturn(pos[1]).thenReturn(pos[2]);

		population = new Population(mockParameter, mockPopRandom);
		// 测试驱动
		population.initializePopulation();
		Chromosome[] chromosomes = population.getChromosomes();

		// 测试断言
		int actualSize = chromosomes.length;
		assertEquals(expectedSize, actualSize);

	}

	/**
	 * 测试种群的变异操作
	 */
	@Test
	public void testPopulationMutate() {

		int size = 3;
//		mockChromosome = new Chromosome[size];
//		for (int i = 0; i < size; i++) {
//			mockChromosome[i] = mock(Chromosome.class);//？？？？？？？？？模拟出现错去
//		}
		// 弹出错误
		// 测试准备
		// 指定编码方案
		List<StringBuffer> encoding = new ArrayList<StringBuffer>();
		encoding.add(new StringBuffer("0"));
		encoding.add(new StringBuffer("1"));
		encoding.add(new StringBuffer("2"));

		// 制定染色体编码
		List<StringBuffer> codes = new ArrayList<StringBuffer>();
		codes.add(new StringBuffer("0"));
		codes.add(new StringBuffer("1"));
		codes.add(new StringBuffer("1"));
		codes.add(new StringBuffer("1"));

		int mutateNum = 1;// 变异方案的指定：单点变异
		when(mockParameter.getEncodes()).thenReturn(encoding);// 获取编码方案
		when(mockParameter.getMutateProbabilty()).thenReturn(1.0);// 模拟获取变异概率
		when(mockParameter.getPopulationSize()).thenReturn(size);// 模拟获取种群规模
//		for (int i = 0; i < size; i++) {
//			when(mockChromosome[i].getEncodes()).thenReturn(codes);
//		}

		when(mockPopRandom.nextDouble()).thenReturn(0.1);
		
		when(mockPopRandom.nextInt(4)).thenReturn(0)
				.thenReturn(2).thenReturn(3);// 模拟产生变异位置;模拟产生变异位置对应的目标码
		
		when(mockPopRandom.nextInt(3)).thenReturn(1).thenReturn(2)
				.thenReturn(2);//

		population = new Population(mockParameter, mockPopRandom);

		population.mutate(mutateNum, codes);

	}
}
