public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>((n1, n2) -> (n1.getValue() - n2.getValue()));
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() == k) {
                // 如果堆已满,则比较堆顶元素与当前元素,判断是否将当前元素加入堆中
                if (queue.peek().getValue() < entry.getValue()) {
                    queue.poll();
                    queue.offer(entry);
                }
            } else {
                queue.offer(entry);
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().getKey();
        }
        return res;
    }