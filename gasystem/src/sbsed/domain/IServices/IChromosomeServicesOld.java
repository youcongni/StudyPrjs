package sbsed.domain.IServices;

import sbsed.domain.ChromosomeOld_zk;

public interface IChromosomeServicesOld {
	/**
	 * 变异策略,分为多种，单点变异，多点变异等；
	 * 
	 * @param length
	 *            :染色体的长度
	 */
	ChromosomeOld_zk mutateOfChromosome(int length);

	/**
	 * 个体的适应值求取
	 * 
	 * @return
	 */
	double calculateFitness();

}
