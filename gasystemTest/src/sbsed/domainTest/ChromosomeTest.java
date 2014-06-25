package sbsed.domainTest;

import static org.mockito.Mockito.*;//

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sbsed.domain.Chromosome;
import sbsed.domain.GAParameter;

public class ChromosomeTest {

	private Random mockRandom = null;
	GAParameter mockGaParameter = null;
	Chromosome chromosome = null;

	@Before
	public void setUp() throws Exception {
		mockRandom = mock(Random.class);
		mockGaParameter = mock(GAParameter.class);
		// chromosome = new Chromosome(random, encodes, length);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewChromosome() {
		List<StringBuffer> value = new ArrayList<StringBuffer>();
		StringBuffer e1 = new StringBuffer();
		StringBuffer e2 = new StringBuffer();
		StringBuffer e3 = new StringBuffer();
		e1.append("1");
		e2.append("2");
		e3.append("0");
		value.add(e1);
		value.add(e2);
		value.add(e3);
		int length = value.size();
		when(mockRandom.nextInt(length)).thenReturn(2);
		when(mockGaParameter.getEncodes()).thenReturn(value);
		when(mockGaParameter.getChromosomeLength()).thenReturn(6);
		chromosome = new Chromosome(mockRandom, mockGaParameter);
		List<StringBuffer> sbList = chromosome.getEncodes();
		int size = sbList.size();
		for (int i = 0; i < size; i++) {
			System.out.print(sbList.get(i).toString());
		}
	}

	@Test
	public void testChromosomeMutate() {

	}
}