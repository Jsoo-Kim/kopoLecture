import java.util.Comparator;

public class IntRankComparator implements Comparator<int[]> {

	@Override
	public int compare(int[] o1, int[] o2) {
		// 성적 비교
		int gradeComparison = Integer.compare(o1[0], o2[0]);
		// 성적이 같을 경우 학생 번호로 비교
		if (gradeComparison == 0) {
			return Integer.compare(o1[1], o2[1]);
		}
		return -gradeComparison; // 음수를 반환해서 내림차순 정렬
	}
}