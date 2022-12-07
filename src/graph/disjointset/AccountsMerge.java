package graph.disjointset;

import java.util.*;

public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
        input.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
        input.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));
        input.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));

        List<List<String>> result = mergeAccounts(input);
        System.out.println(result);
    }

    /*
      accounts = [
        ["John","johnsmith@mail.com","john_newyork@mail.com"], -> 0
        ["John","johnsmith@mail.com","john00@mail.com"], -> 1
        ["Mary","mary@mail.com"], -> 2
        ["John","johnnybravo@mail.com"] -> 3
      ]

      result = [
        ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
        ["Mary","mary@mail.com"],
        ["John","johnnybravo@mail.com"]
      ]

     */
    private static List<List<String>> mergeAccounts(List<List<String>> input) {
        Map<String, Integer> nodeByAccount = new HashMap<>();
        DisjointSet disjointSet = new DisjointSet(input.size());

        for (int node = 0; node < input.size(); node++) {
            List<String> accounts = input.get(node);
            for (int accountIndex = 1; accountIndex < accounts.size(); accountIndex++) {
                String account = accounts.get(accountIndex);
                if (nodeByAccount.containsKey(account)) {
                    disjointSet.union(node, nodeByAccount.get(account));
                } else {
                    nodeByAccount.put(account, node);
                }
            }
        }

        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            mergedAccounts.add(new ArrayList<>());
        }

        for (Map.Entry<String, Integer> entry : nodeByAccount.entrySet()) {
            String account = entry.getKey();
            int node = entry.getValue();

            int rootNode = disjointSet.find(node);

            mergedAccounts.get(rootNode).add(account);
        }

        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if (mergedAccounts.get(i).isEmpty()) {
                continue;
            }

            String name = input.get(i).get(0);

            List<String> entries = new ArrayList<>();
            entries.add(name);
            entries.addAll(mergedAccounts.get(i));

            result.add(entries);
        }

        return result;
    }

}
