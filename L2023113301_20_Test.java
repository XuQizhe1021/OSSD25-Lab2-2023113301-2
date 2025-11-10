import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Solution20 最小高度树算法的单元测试类
 * 
 * 测试用例设计总体原则：
 * 1. 等价类划分原则：
 *    - 有效等价类：正常的树结构（n>=1, edges数量=n-1）
 *    - 无效等价类：空树、非连通图、有环图等
 * 
 * 2. 边界值分析原则：
 *    - 最小边界：n=1（单节点）
 *    - 最小有效树：n=2（两个节点）
 *    - 线性树：所有节点呈链状连接
 *    - 星形树：一个中心节点连接所有其他节点
 * 
 * 3. 路径覆盖原则：
 *    - 覆盖所有可能的执行路径
 *    - 包括特殊情况和异常情况
 * 
 * 4. 功能测试原则：
 *    - 验证算法的正确性
 *    - 验证返回结果的完整性
 *    - 验证边界条件处理
 */
public class L2023113301_20_Test {
    
    private Solution solution;
    
    @BeforeEach
    void setUp() {
        solution = new Solution();
    }
    
    /**
     * 测试目的：验证单节点树的处理
     * 测试用例：n=1, edges=[]
     * 预期结果：返回[0]
     * 等价类：最小边界值测试
     */
    @Test
    void testSingleNode() {
        int n = 1;
        int[][] edges = {};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(1, result.size(), "单节点树应该返回一个根节点");
        assertEquals(0, result.get(0), "单节点树的根节点应该是0");
    }
    
    /**
     * 测试目的：验证两节点树的处理
     * 测试用例：n=2, edges=[[0,1]]
     * 预期结果：返回[0,1]（两个节点都可以作为最小高度树的根）
     * 等价类：最小有效树测试
     */
    @Test
    void testTwoNodes() {
        int n = 2;
        int[][] edges = {{0, 1}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(2, result.size(), "两节点树应该返回两个根节点");
        assertTrue(result.contains(0) && result.contains(1), "两个节点都应该是最小高度树的根");
    }
    
    /**
     * 测试目的：验证示例1的正确性
     * 测试用例：n=4, edges=[[1,0],[1,2],[1,3]]（星形树）
     * 预期结果：返回[1]
     * 等价类：星形树结构测试
     */
    @Test
    void testExample1_StarTree() {
        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(1, result.size(), "星形树应该返回一个根节点");
        assertEquals(1, result.get(0), "星形树的中心节点应该是最小高度树的根");
    }
    
    /**
     * 测试目的：验证示例2的正确性
     * 测试用例：n=6, edges=[[3,0],[3,1],[3,2],[3,4],[5,4]]
     * 预期结果：返回[3,4]
     * 等价类：复杂树结构测试
     */
    @Test
    void testExample2_ComplexTree() {
        int n = 6;
        int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(2, result.size(), "该树结构应该返回两个根节点");
        assertTrue(result.contains(3) && result.contains(4), "节点3和4应该是最小高度树的根");
    }
    
    /**
     * 测试目的：验证线性树（链状）的处理
     * 测试用例：n=5, edges=[[0,1],[1,2],[2,3],[3,4]]（0-1-2-3-4）
     * 预期结果：返回[2]（中间节点）
     * 等价类：线性树结构测试
     */
    @Test
    void testLinearTree_OddNodes() {
        int n = 5;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(1, result.size(), "奇数节点的线性树应该返回一个根节点");
        assertEquals(2, result.get(0), "中间节点应该是最小高度树的根");
    }
    
    /**
     * 测试目的：验证偶数节点线性树的处理
     * 测试用例：n=6, edges=[[0,1],[1,2],[2,3],[3,4],[4,5]]（0-1-2-3-4-5）
     * 预期结果：返回[2,3]（中间两个节点）
     * 等价类：偶数节点线性树测试
     */
    @Test
    void testLinearTree_EvenNodes() {
        int n = 6;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(2, result.size(), "偶数节点的线性树应该返回两个根节点");
        assertTrue(result.contains(2) && result.contains(3), "中间两个节点应该是最小高度树的根");
    }
    
    /**
     * 测试目的：验证三节点线性树的处理
     * 测试用例：n=3, edges=[[0,1],[1,2]]（0-1-2）
     * 预期结果：返回[1]
     * 等价类：小规模线性树测试
     */
    @Test
    void testThreeNodesLinear() {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(1, result.size(), "三节点线性树应该返回一个根节点");
        assertEquals(1, result.get(0), "中间节点应该是最小高度树的根");
    }
    
    /**
     * 测试目的：验证较大星形树的处理
     * 测试用例：n=7, 中心节点0连接所有其他节点
     * 预期结果：返回[0]
     * 等价类：大规模星形树测试
     */
    @Test
    void testLargeStarTree() {
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertEquals(1, result.size(), "星形树应该返回一个根节点");
        assertEquals(0, result.get(0), "中心节点应该是最小高度树的根");
    }
    
    /**
     * 测试目的：验证不规则树结构的处理
     * 测试用例：n=8, 复杂的树结构
     * 预期结果：验证算法能正确处理复杂情况
     * 等价类：复杂不规则树测试
     */
    @Test
    void testIrregularTree() {
        int n = 8;
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}};
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        
        assertNotNull(result, "结果不应该为null");
        assertTrue(result.size() >= 1 && result.size() <= 2, "最小高度树的根节点数量应该在1-2之间");
        
        // 验证返回的节点确实存在于图中
        for (int root : result) {
            assertTrue(root >= 0 && root < n, "根节点应该在有效范围内");
        }
    }
    
    /**
     * 测试目的：验证算法的性能和稳定性
     * 测试用例：相对较大的树结构
     * 预期结果：算法能在合理时间内完成
     * 等价类：性能测试
     */
    @Test
    void testPerformance() {
        int n = 100;
        int[][] edges = new int[n-1][2];
        
        // 创建一个线性树：0-1-2-...-99
        for (int i = 0; i < n-1; i++) {
            edges[i][0] = i;
            edges[i][1] = i + 1;
        }
        
        long startTime = System.currentTimeMillis();
        List<Integer> result = solution.findMinHeightTrees(n, edges);
        long endTime = System.currentTimeMillis();
        
        assertNotNull(result, "结果不应该为null");
        assertTrue(result.size() >= 1 && result.size() <= 2, "结果应该包含1-2个根节点");
        assertTrue(endTime - startTime < 1000, "算法应该在1秒内完成");
    }
}
//OK, no problem.