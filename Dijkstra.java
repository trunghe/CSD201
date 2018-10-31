import java.io.RandomAccessFile;
import java.util.Stack;

public class Dijkstra {

    int[][] a = new int[20][20];
    int n = 0;
    char v[] = "ABCDEFGHIJKLMNOP".toCharArray();
    int deg[] = new int[20];

    // vFrom: from vertex, vTo: to vertex
    void dijkstra(int vFrom, int vTo, RandomAccessFile f) throws Exception {
        int[] S = new int[20]; // the set S
        int[] dist = new int[20]; // current distances
        int[] path = new int[20]; // current path
        boolean[] selected = new boolean[20];
        int m, i, k, t, INFI;
        INFI = 99;// 99 is considered infinity value
        //Khoi tao
        for (i = 0; i < n; i++) {
            selected[i] = false;
            dist[i] = a[vFrom][i]; // At the first step distance is a direct distance
            path[i] = vFrom;       // and the vertex before vertex i is the vertex  vFrom
        }

        k = vFrom;// k is selected to the set S
        selected[k] = true;
        S[0] = vFrom;
        m = 1; // add the vertex k the the S set
        while (k != vTo) //Thuc hien vong lap cho den khi gap nut vTo
        {
            t = INFI;
            k = -1;
            //Tim dinh gan nhat de dua vao tap  S
            for (i = 0; i < n; i++) {
                if (selected[i]) continue;
                if (dist[i] < t) {
                    t = dist[i];
                    k = i;
                }
            }
            if (t == INFI) {
                f.writeBytes("\nKhong co duong di\r\n");
                return;
            }
            //Dua  dinh k vao tap S da xet
            selected[k] = true;
            S[m++] = k;
            //Tinh lai khoang cach
            for (i = 0; i < n; i++) {
                if (selected[i]) continue;
                if (dist[i] > dist[k] + a[k][i]) {
                    dist[i] = dist[k] + a[k][i];
                    path[i] = k;
                }
            }
        } // end of while(k != vTo)

        Stack<Integer> u = new Stack();
        f.writeBytes("\n\n The length of shortest path from " + v[vFrom]
                + " to  " + v[vTo] + " is  " + dist[vTo] + "\r\n");
        f.writeBytes("\n Path:\r\n");
        i = vTo;
        u.push(i);
        do {
            i = path[i];
            u.push(i);
        }
        while (i != vFrom);

        int x, y;
        y = u.pop();
        while (!u.isEmpty()) {
            x = y;
            y = u.pop();
            f.writeBytes("" + v[x]);
            f.writeBytes(" -> (" + a[x][y] + ")");
        }
        f.writeBytes("" + v[y] + "\r\n");
    }
}
