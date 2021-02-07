public int nthUglyNumber(int n) {
        int[] arr = {2, 3, 5};
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        int count = 0;
        while (!queue.isEmpty()) {
            long num = queue.poll();
            if (++count >= n) {
                return (int)num;
            }
            for (int i : arr) {
                // 去掉重复元素
                if (!queue.contains(i * num)) {
                    queue.add(i * num);
                }
            }
        }
        return -1;
    }