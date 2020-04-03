import java.io.*;
import java.util.*;

public class GenerateTestData implements Config {
    private static Map<Integer, Set<Integer>> graph;
    private static int[] dict = new int[V_COUNT];
    private static Random random = new Random(RANDOM_SEED);
    static {
        graph = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < V_COUNT; i++){
            graph.put(i, new HashSet<>());
            int v = random.nextInt(Integer.MAX_VALUE);
            while (set.contains(v)){
                v = random.nextInt(Integer.MAX_VALUE);
            }
            dict[i] = v;
        }
    }

    public static void main(String[] args){
        Random random = new Random();
        for (int i = 0; i < E_COUNT; i++){
            int from = random.nextInt(V_COUNT);
            int to = random.nextInt(V_COUNT);
            while (from == to || graph.get(from).contains(to)){
                from = random.nextInt(V_COUNT);
                to = random.nextInt(V_COUNT);
            }
            graph.get(from).add(to);
        }
        write(graph);
    }

    private static void write(Map<Integer, Set<Integer>> graph){
        File dest = new File(DATA_PATH);
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(dest)))){
            for(Map.Entry<Integer, Set<Integer>> pointEntry : graph.entrySet()){
                for (int to : pointEntry.getValue()){
                    writer.println(dict[pointEntry.getKey()] + "," + dict[to] + "," + random.nextInt(MAX_MONEY));
                }
            }
        }
        catch (IOException e){
            System.out.println("写入文件失败：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
