import java.util.Comparator;

// 按年龄降序排序，年龄相同则按姓名升序排序
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 首先按年龄降序比较
        int ageCompare = Integer.compare(v2.getAge(), v1.getAge());
        if (ageCompare != 0) {
            return ageCompare;
        }
        // 年龄相同则按姓名升序比较（字典序）
        return v1.getName().compareTo(v2.getName());
    }
}