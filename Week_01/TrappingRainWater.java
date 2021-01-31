class TrappingRainWater {
    // 使用栈
    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            // 当栈顶元素小于当前遍历的元素,弹出栈顶元素,计算雨水量
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int h = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                sum = sum + distance * (Math.min(height[stack.peek()],height[i]) - h);
            }
            stack.add(i);
        }
        return sum;
    }
}