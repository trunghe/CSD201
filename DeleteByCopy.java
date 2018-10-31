        class DeleteByCopy {

            boolean shorter; // Vi Java khong co kieu truyen tham chieu nen phai dung toan cuc
            //==========================================================================

            /* Tac vu rotateLeft: xoay trai cay nhi phan tim kiem co nut goc
            la root, yeu cau root phai co nut con ben phai (goi la nut p)
            Sau khi xoay trai thi nut con ben phai p tro thanh nut goc, nut goc
            cu tro thanh nut con ben trai cua nut goc moi
            Tac vu nay tra ve con tro chi nut goc moi 
             */
            Node rotateLeft(Node p) {
                if (p == null || p.right == null) {
                    System.out.println(" Cannot rotate left.");
                    return p;
                }
                Node pr = p.right;
                p.right = pr.left;
                pr.left = p;
                return (pr);
            }
            //---------------------------

            /* Tac vu rotateRight: xoay phai cay nhi phan tim kiem co nut goc
            la root, yeu cau root phai co nut con ben trai (goi la nut p)
            Sau khi xoay phai thi nut con ben trai p tro thanh nut goc, nut goc
            cu tro thanh nut con ben phai cua nut goc moi
            Tac vu nay tra ve con tro chi nut goc moi
             */
            Node rotateRight(Node p) {
                if (p == null || p.left == null) {
                    System.out.println(" Cannot rotate right.");
                    return p;
                }
                Node pl = p.left;
                p.left = pl.right;
                pl.right = p;
                return (pl);
            }
            //---------------------------

            //=====================================================
            /*Input: Cay goc p co nut bi xoa nam tren cay con trai va chieu cao
	          cua cay con trai bi giam, tuc la shorter = true. Tuy nhien chi so can
	          bang cua p chua duoc tinh lai.
            Xu ly:  Viec chieu cao cay con trai giam anh huong nhu the nao den cay p?
	          neu cay p mat can bang thi can bang lai, tinh lai shorter.
            Output: Cay p da can bang, tham so h cho biet cay co bi giam chieu cao
	          so voi truoc khi xoa nut tren nhanh cay trai khong*/
            //Khi goi thu tuc thi shorter=true, chieu cao cua cay con trai giam
            Node leftBal2(Node p) {
                Node p1, p2, pp;
                if (!shorter) {
                    return (p);
                }
                pp = null;
                switch (p.bal) {
                    case -1:/*Cay bi lech trai, chieu cao cay con trai giam 1
	                  thi tro thanh can bang, nhung chieu cao cay  p van giam,
	                  khong phai can bang cay, nhung tham so shorter tra ve la true,
	                  nen qua trinh van tiep tuc*/
                        p.bal = 0;
                        pp = p;
                        break;
                    case 0:/*Cay von can bang, chieu cao cay con trai giam 1
	                  thi tro thanh lech phai, nhung chieu cao khong giam*/
                        p.bal = +1;
                        shorter = false;
                        pp = p;
                        break;
                    case +1:/*can bang lai. Cay von lech phai, chieu cao cay con trai
                    giam 1 thi dan den he so can bang nut p la 2*/
                        p1 = p.right;
                        switch (p1.bal) {
                            case 0://Quay trai nut P
                                //chieu cao cua cay goc p khong bi giam
                                p.bal = +1;
                                p1.bal = -1;
                                shorter = false;
                                pp = rotateLeft(p);
                                break;
                            case +1://Xoay trai nut P
                                //chieu cao cua cay goc p van bi giam
                                p.bal = 0;
                                p1.bal = 0;
                                pp = rotateLeft(p);
                                break;
                            case -1://Phep xoay kep: xoay phai p1, roi xoay trai p
                                p2 = p1.left;
                                switch (p2.bal) {
                                    case 0:
                                        p.bal = 0;
                                        p1.bal = 0;
                                        break;
                                    case -1:
                                        p.bal = 0;
                                        p1.bal = +1;
                                        break;
                                    case +1:
                                        p.bal = -1;
                                        p1.bal = 0;
                                        break;
                                }
                                p2.bal = 0;
                                p.right = rotateRight(p1);
                                pp = rotateLeft(p);
                        }
                        ;//End of switch(p1.bal)
                };
                return (pp);
            }

            ;//leftBal2
            //---------------------------
            /*Input: Cay goc p co nut bi xoa nam tren cay con phai va chieu cao
	          cua cay con phai bi giam, tuc la shorter = true. Tuy nhien chi so
	          can bang cua p chua duoc tinh lai.
            Xu ly:  Viec chieu cao cay con phai giam anh huong nhu the nao den cay p?
	          neu cay p mat can bang thi can bang lai, tinh lai shorter.
            Output: Cay p da can bang, tham so shorter cho biet cay co bi giam chieu cao
	          so voi truoc khi xoa nut tren nhanh cay phai khong*/
            Node rightBal2(Node p) {
                Node p1, p2, pp;
                pp = null;
                if (!shorter) {
                    return (p);
                }
                switch (p.bal) {
                    case +1:/*Cay bi lech phai, chieu cao cay con phai giam 1
	                  thi tro thanh can bang, nhung chieu cao cay  p van giam,
	                  khong phai can bang cay, nhung tham so shorter tra ve la true,
	                  nen qua trinh van tiep tuc*/
                        p.bal = 0;
                        pp = p;
                        break;
                    case 0:/*Cay von can bang, chieu cao cay con phai giam 1
	                  thi tro thanh lech trai, nhung chieu cao khong giam*/
                        p.bal = -1;
                        shorter = false;
                        pp = p;
                        break;
                    case -1:/*can bang lai. Cay von lech trai, chieu cao cay con phai
	                  giam 1 thi dan den he so can bang nut p la -2*/
                        p1 = p.left;
                        switch (p1.bal) {
                            case 0://Quay phai nut P
                                //chieu cao cua cay goc p khong bi giam
                                p.bal = -1;
                                p1.bal = +1;
                                shorter = false;
                                pp = rotateRight(p);
                                break;
                            case -1://Xoay phai nut P
                                //chieu cao cua cay goc p van bi giam
                                p.bal = 0;
                                p1.bal = 0;
                                pp = rotateRight(p);
                                break;
                            case +1://Phep xoay kep: xoay trai P1, roi xoay phai P
                                p2 = p1.right;
                                switch (p2.bal) {
                                    case 0:
                                        p.bal = 0;
                                        p1.bal = 0;
                                        break;
                                    case -1:
                                        p.bal = 1;
                                        p1.bal = 0;
                                        break;
                                    case +1:
                                        p.bal = 0;
                                        p1.bal = -1;
                                        break;
                                }
                                p2.bal = 0;
                                p.left = rotateLeft(p1);
                                pp = rotateRight(p);
                        }
                        ;//End of switch(p1.bal)
                };
                return (pp);
            }

            ;//rightBal2
            //=====================================================
            
            class SNode // node in stack
            {

                Node p;
                int d;// d=-1: p la con ben trai cua nut cha, 1 la con ben phai

                SNode(Node p1, int d1) {
                    p = p1;
                    d = d1;
                }
            }

            class MyStack {

                LinkedList a;

                MyStack() {
                    a = new LinkedList();
                }

                boolean isEmpty() {
                    return (a.isEmpty());
                }

                void push(Object x) {
                    a.add(x);
                }

                Object pop() {
                    return (a.removeLast());
                }
            }

            void remove(Node p) // Delete by copying
            {
                if (root == null) {
//                    System.out.println(" The tree is empty, no deletion");
                    return;
                }
                Node fp; // fp will be the father of p
                Node f, rp, p1;/*rp la nut thay the cho nut p co noi dung x,
		    f la nut cha cua nut thay the rp*/
                Stack s = new Stack();
                fp = null;

                if (p == null) {
//                    System.out.println(" The key " + x + " does not exist, no deletion");
                    return;
                }
                if (p.left == null && p.right == null) // p is a leaf node
                {
                    if (fp == null) // The tree is one node
                    {
                        root = null;
                    } else {
                        if (fp.left == p) {
                            fp.left = null;
                        } else {
                            fp.right = null;
                        }
                    }
                }

                if (p.left != null && p.right == null) // p has only left child
                {
                    if (fp == null) // p is a root
                    {
                        root = p.left;
                    } else {
                        if (fp.left == p) // p is aleft child
                        {
                            fp.left = p.left;
                        } else {
                            fp.right = p.left;
                        }
                    }
                }

                if (p.left == null && p.right != null) // p has only right child
                {
                    if (fp == null) // p is a root
                    {
                        root = p.right;
                    } else {
                        if (fp.left == p) // p is aleft child
                        {
                            fp.left = p.right;
                        } else {
                            fp.right = p.right;
                        }
                    }
                }

                if (p.left != null && p.right != null) // p has both left and right children
                {//Tim nut thay the, la nut phai nhat cua cay con trai
                    s.push(new SNode(p, -1)); // Di ve ben trai de timnut thay the
                    f = p;
                    rp = p.left;//Khoi dau chon rp la nut goc cay trai, sau do se
                    //di ve phai de tim nut phai nhat
                    while (rp.right != null) {
                        f = rp;
                        s.push(new SNode(f, 1));
                        rp = rp.right;
                    }
                    p.info = rp.info;
                    //doi noi dung cua p va rp, roi xoa rp
                    if (f == p) {
                        p.left = rp.left;
                    } else {
                        f.right = rp.left;/*rp la phai nhat, do do khong co con phai
	                 vi khong con rp nen nut cha phai chi den nut sau do*/
                    }
                }
                //Bat dau can bang cac cay tu dinh Stack, tuc la lan nguoc ve goc

                if (s.isEmpty()) {
                    return;
                }

                shorter = true;
                SNode t;
                t = (SNode) s.pop();
                p = t.p;
                if (t.d == -1) {
                    p1 = leftBal2(p);
                } else {
                    p1 = rightBal2(p);
                }

                if (s.isEmpty()) {
                    root = p1;
                    return; //Da o nut cuoi cung la nut goc
                }
                while (!s.isEmpty()) {
                    t = (SNode) s.pop();
                    p = t.p;

                    if (t.d == -1) {
                        p.left = p1;
                    } else {
                        p.right = p1;
                    }
                    if (!shorter) {
                        break;
                    } else {
                        if (t.d == -1) {
                            p1 = leftBal2(p);
                        } else {
                            p1 = rightBal2(p);
                        }
                    }
                    if (s.isEmpty()) {
                        root = p1;
                        break; //Da o nut cuoi cung la nut goc
                    }
                }
            }

            boolean havingBothChildren(Node p) {
                return p.left != null && p.right != null;
            }

            void remove() {
                LinkedList<Node> queue = new LinkedList<>();
                queue.addLast(root);
                while (!queue.isEmpty()) {
                    Node p = queue.pollFirst();
                    if (havingBothChildren(p) && p.info.price < 7) {
                        remove(p);
                        return;
                    }
                    if (p.left != null) {
                        queue.addLast(p.left);
                    }
                    if (p.right != null) {
                        queue.addLast(p.right);
                    }
                }
            }
        }

        new DeleteByCopy().remove();
