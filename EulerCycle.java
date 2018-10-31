        //Algorithm  EulerCycle(G)
        //Input: Connected graph G with all vertices having even degrees
        //Output: Euler cycle
        Stack<Integer> stack = new Stack<>();//The stack to keep track of cycles
        stack.push(0);//Push vertex A to stack
        ArrayList<Integer> eulerCycle = new ArrayList<>(n);//Array to hold cycle
        while (!stack.empty()) {
            int h = stack.peek();
            int i = 0;
            while (i < n && a[h][i] == 0) {
                i++;// break if a[h][i] != 0
            }
            if (i == n) {//If vertex h is isolated
                eulerCycle.add(h);
                stack.pop();
            } else {
                stack.push(i);
                a[h][i]--;//Remove the edge from h to i
                a[i][h]--;
            }
        }
        f.writeBytes("" + (char) ('A' + eulerCycle.get(0)));
        for (int p = 1; p < eulerCycle.size(); ++p) {
            f.writeBytes(" " + (char) ('A' + eulerCycle.get(p)));
        }
