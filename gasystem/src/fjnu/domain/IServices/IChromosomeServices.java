package fjnu.domain.IServices;

/**
 * 该接口用于染色体中变异的事项，由于不同的变异策略，所以暴露该接口，便于用于扩张变异策略， 增加灵活性；
 * 
 * @author zhaohongxu
 * 
 */
public interface IChromosomeServices {

	/**
	 * 计算个体/染色体的适应度
	 * 
	 * @return：染色体的适应度
	 */
	double calculateFitness();

}
