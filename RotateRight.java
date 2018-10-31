        class RotateRight {
            /* Tac vu rotateRight: xoay phai cay nhi phan tim kiem co nut goc
            la root, yeu cau root phai co nut con ben trai (goi la nut p)
            Sau khi xoay phai thi nut con ben trai p tro thanh nut goc, nut goc
            cu tro thanh nut con ben phai cua nut goc moi
            Tac vu nay tra ve con tro chi nut goc moi
             */
            Node rotateRight(Node p) {
                if (p == null || p.left == null) {
//                    System.out.println(" Cannot rotate right.");
                    return p;
                }
                Node pl = p.left;
                p.left = pl.right;
                pl.right = p;
                return (pl);
            }
            
            void breadth(Node prev, Node p, Boolean isLeft, RandomAccessFile f) throws Exception {
                HashMap<Node, Pair> childToParent = new HashMap<>();
                if (p == null) {
                    return;
                }
                BQueue q = new BQueue();
                q.enqueue(p);
                childToParent.put(p, new Pair(prev, isLeft));
                Node r;
                while (!q.isEmpty()) {
                    r = q.dequeue();
                    if (r.left != null && r.info.price < 7) {
                        prev = childToParent.get(r).node;
                        isLeft = childToParent.get(r).isLeft;
                        r = rotateRight(r);
                        if (isLeft) {
                            prev.left = r;
                        } else if (!isLeft) {
                            prev.right = r;
                        } else {
                            root = r;
                        }
                        return;
                    }
                    if (r.left != null) {
                        q.enqueue(r.left);
                        childToParent.put(r.left, new Pair(r, true));
                    }
                    if (r.right != null) {
                        q.enqueue(r.right);
                        childToParent.put(r.right, new Pair(r, false));
                    }
                }
            }
            
            class Pair {
                Node node;
                Boolean isLeft;

                public Pair(Node node, Boolean isLeft) {
                    this.node = node;
                    this.isLeft = isLeft;
                }
            }
        }
