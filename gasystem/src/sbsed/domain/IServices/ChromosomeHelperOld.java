package sbsed.domain.IServices;

import java.util.Random;

import sbsed.domain.ChromosomeOld_zk;

public class ChromosomeHelperOld implements IChromosomeServicesOld {

	@Override
	public ChromosomeOld_zk mutateOfChromosome(int length) {
		ChromosomeOld_zk newChromosome = null;
		String expression = "010100100";

		// 产生一个变异位置
		Random random = new Random();
		int position = random.nextInt(length);
		// 定位到染色体的变异位置并取出其中数字
		char ch = expression.charAt(position);
		// 对染色体实行变异操作
		if (ch == '0') {
			ch = '1';
		} else {
			ch = '0';
		}
		// 重新组织新的染色体;如果位置为开始位置:
		if (position == 0) {
			String tempData = expression.substring(1);
			tempData = ch + tempData;
		} else if (position == (length - 1)) {// 重新组织新的染色体;如果位置为结束位置:
			String tempData = expression.substring(0, length - 1);
			tempData = tempData + ch;
		} else {
			String tempData = expression.substring(0, position);
			String tempData2 = expression.substring(position + 1);
			tempData = tempData + ch + tempData2;
		}
		return newChromosome;
	}

	@Override
	public double calculateFitness() {

		return 0;
	}

}
